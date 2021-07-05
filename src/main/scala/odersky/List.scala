package odersky

import java.util.NoSuchElementException

import scala.collection.mutable.ListBuffer

  abstract class List[+T] { // +T: covariant, ch 19
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def length: Int = if (isEmpty) 0 else 1 + tail.length

    def drop(n: Int): List[T] =
      if (isEmpty) Nil
      else if (n <= 0) this
      else tail.drop(n - 1)

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

    // FIXME: SUperbly inefficient mapper:
    def slowMapTo[U](f: T => U) : List[U] = {
      // this will use listbuffer to make it more efficient:
      //val b = new ListBuffer[U] // a bit of a cheating in good cause...

      if (this.isEmpty) return Nil
      f(head) :: this.tail.slowMapTo(f)
    }

    // FIXME: Below is Odersky's implementation, need to convert ListBuffer to a list at the end...:
    // Recursive implementation of a list:
//    def map[U](f: T => U) : List[U] = { // from Odersky's
//      var b = new ListBuffer[U]
//      var these = this
//      while (!these.isEmpty) {
//        b += f(these.head)
//        these = these.tail
//      }
//      b.toList // TODO: Need to List function that creates
//    }

    override def toString: String = {
      val sb : StringBuilder = new StringBuilder("List=[");
      //var contents : T = Null;

      var current = this;
      while (!current.isEmpty) {
        sb.append(current.head.toString);
        current = current.tail
        if (!current.isEmpty) sb.append(", ")
      }
      sb.append("]").toString()
    }
  }

  object List {
    // FIXME: This is the other way-around
    def apply[A](elems : A*): List[A] = {
      var list : List[A] = Nil
      for (e <- elems)
        list = e :: list
      list
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
      val list1: List[Int] = ::(-1, ::(-2, ::(-3, ::(-4, Nil))))
      //val list2 : List[Int] = 1 :: 2 :: 3 :: Nil

      val list2 = list1.drop(2)

      println(list1)
      println("After drop(2):")


      val list3 = List(1, 2, 3, 4, 5)
      println("List with apply: " + list3)

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
