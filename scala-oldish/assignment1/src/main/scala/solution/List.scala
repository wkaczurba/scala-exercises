package solution

import java.util.NoSuchElementException

import scala.collection.mutable.ListBuffer

  abstract class List[+T] { // +T: covariant, ch 19
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def length: Int = if (isEmpty) 0 else 1 + tail.length

    def takeRight(n: Int): List[T] = // adopted from Odersky's book.
      if (isEmpty) Nil
      else if (n <= 0) this
      else tail.takeRight(n - 1)

    // This allows to handle different types in list; once a new element of different type is appended - a common supertype will be used.
    // def ::[U >: T](x : U): List[U] = new scala.::(x, this)
    def ::[U >: T](x: U): List[U] = {
      new ::(x, this)
    }
      // NOTE: param U >: T means U a supertype of list element T; we taken U, a supertype of T and return U-typpe.
      // this way we create a new list of common super type.

    def :::[U >: T](prefix : List[U]) : List[U] = {
      if (prefix.isEmpty) this
      else prefix.head :: prefix.tail ::: this // HMmm... -> I am confused with this one.
      // NOTE: Both ::: and :: end in a colon , they both bind to the right and are both right-associative.
      //
    }

    def slowMapTo[U](f: T => U) : List[U] = { // SUperbly inefficient mapper:
      if (this.isEmpty) return Nil
      f(head) :: this.tail.slowMapTo(f)
    }

    // Below is adopted Odersky's implementation (more efficient); adopted conversion with foldRIght
    // Recursive implementation of a list:
    def map[U](f: T => U) : List[U] = { // adopted from Odersky's
      var b = new ListBuffer[U]
      var these = this
      while (!these.isEmpty) {
        b += f(these.head)
        these = these.tail
      }
      b.foldRight(Nil : List[U])(_ :: _)
    }

    def filter(predicate: (T) => Boolean): List[T] = {
      if (this.isEmpty) Nil
      else if (predicate.apply(this.head)) head :: tail.filter(predicate)
      else tail.filter(predicate)
    }

    def count(predicate: (T) => Boolean): Int = {
      if (isEmpty) 0
      else if (predicate.apply(head)) tail.count(predicate) + 1
      else tail.count(predicate)
    }
    def find (predicate: (T) => Boolean): Option[T] = {
      if (isEmpty) None
      else if (predicate.apply(head)) Some (head)
      else tail.find(predicate)
    }
    def exists(predicate: (T) => Boolean): Boolean = find(predicate).isDefined


    // Partial function
    // creates a new collection by its partial application to all elements
    // of given list where this function is defined
    //def collect(pfun: PartialFunction[A,B]): List[B] = ???

//
//    // Currying
//    def foldLeft [B] (z: B) (operator: (B, A) => B): B = ???
//    def foldRight [B] (z: B) (operator: (A, B) => B): B = ???


    override def toString: String = {
      val sb : StringBuilder = new StringBuilder("List=[");

      var current = this;
      while (!current.isEmpty) {
        sb.append(current.head.toString);
        current = current.tail
        if (!current.isEmpty) sb.append(", ")
      }
      sb.append("]").toString()
    }

    // Adding an element to a list:
    def += [U >: T] (e : U): List[U] = {
      this ::: e :: Nil
    }

    def += [U >: T] (e : List[U]): List[U] = {
      this ::: e ::: Nil
    }

    def append[U >: T] (that : List[U]): Unit = {
      this += that
    }

    def cons[U >: T](a: U): List[U] = {
      a :: this
    }

    def indexOf(elem: T): Long = {
      if (isEmpty) return -1L
      else if (head == elem) return 0L
      else tail.indexOf(elem) match {
        case -1 => -1L
        case x : Long => x + 1
      }
    }

    // TODO: Returns first n elements of the list,
    // if n > list size it will return the whole list
    def take (n: Int) : List[T] = {
      if (n == 1) this.head::Nil
      else this.head :: tail.take(n-1)
    }

    def reverse(): List[T] = {
      if (!tail.isEmpty) tail.reverse() += this.head
      else Nil
    }
  }

  object List {
    // FIXME: This is the other way-around
    def apply[A](elems : A*): List[A] = {
      return elems.foldRight(Nil : List[A])(_ :: _)
    }
  }

  // Nil object: extends >>Nothing<<
  case object Nil extends List[Nothing] {
    override def isEmpty = true

    def head: Nothing =
      throw new NoSuchElementException("head of empty list")

    def tail: Nothing =
      throw new NoSuchElementException("tail of empty list")
  }

  final case class ::[T](head: T, tail: List[T]) extends List[T] { // head and tail are args name of super-constructor, so they will be assigned to the superconstructor
    override def isEmpty: Boolean = false
  }

  object Main {
    def main(args: Array[String]): Unit = {
//      val list1: List[Int] = ::(-1, ::(-2, ::(-3, ::(-4, Nil))))
//      //val list2 : List[Int] = 1 :: 2 :: 3 :: Nil
//
//      val list2 = list1.drop(2)
//
//      println(list1)
//      println("After drop(2):")
//
//
      val list3 = List(1, 2, 3, 4, 5)
      println("List with apply: " + list3)

      val list4 = List(1, 2, 3, 4, 5)

//      val l = Array(1,2,3,4)
//      val s = l.foldLeft("0")(_.toString + "+" + _.toString);
//      val s2 = l.foldRight("0")(_.toString + "+" + _.toString);
//      println(s2)

//      println("Adding element: " + (list4 += 100))
//      println("Adding self: " + (list4 += list4))

//      val list5 = list4 += 99;
//      println("To string mapped: " + list5)

      val list5 = list4 += 99;
      println("Multiplication - slow to map " + list5.slowMapTo( x => x * 10))
      println("Multiplication - normal map " + list5.map( x => x * 10))

      println("list5:" + list5)
      println("list5:" + list5.indexOf(99))

      println(list5.take(6))

      val listX = List(10, 11, 12, 13, 14, 15, 16)
      println(listX.reverse())

      val listY = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      val predicate = (x : Int) => x % 3 == 0
      println("Divadable by 3: " + listY.filter(predicate) + " count: " + listY.count(predicate))

      println("Finding 4 in " + listY)
      println(listY.find(x => x == 4) + "\n")

      println("Finding 51123 in " + listY + "; result: " + listY.find(x => x == 51123))


      println("Println testing exists(81): " + listY.exists(x => x == 81) + "\n")
      println("Println testing exists(4): " + listY.exists(x => x == 4) + "\n")

    }
  }

  object ch22p22p2 {

    abstract class Fruit;
    class Apple extends Fruit
    class Orange extends Fruit
    class Pear extends Fruit

    def main(args: Array[String]): Unit = {

      // NOrmal ::
      val apples = new Apple :: Nil

      val fruits1 : List[Fruit] = new Orange :: apples

      val fruits2 : List[Fruit] = new Pear :: new Pear :: new Pear :: Nil

      val longerList = fruits1 ::: fruits2;

      println(longerList)

      println("Inefficient mapper:")
      println(longerList.slowMapTo((x:Fruit) => x.toString.length ))
    }
  }
