package odersky

import java.util.NoSuchElementException
// ch22.

// Two subclasses: Nil and ::

abstract class List[+T] {  // +T: covariant, ch 19
  def isEmpty : Boolean
  def head: T
  def tail: List[T]

  def length : Int = if (isEmpty) 0 else 1 + tail.length

  def drop(n : Int) : List[T] =
    if (isEmpty) Nil
    else if (n <= 0) this
    else tail.drop(n - 1)
}

// Nil object: extends >>Nothing<<
case object  Nil extends List[Nothing] {
  override def isEmpty = true
  def head: Nothing =
    throw new NoSuchElementException("head of empty list")
  def tail: Nothing =
    throw new NoSuchElementException("tail of empty list")
}

// infix operator - chapter 16.
// the pattern x :: xs is treated as ::(x, xs)

// THis one can be done simplier - reuse of args:
//final case class ::[T](hd : T, tl: List[T]) extends List[T] {
//  def head = hd
//  def tail = tl
//  override def isEmpty : Boolean = false
//}

final case class ::[T](head : T, tail: List[T]) extends List[T] {
  override def isEmpty : Boolean = false
}


object Main {
  def main(args : Array[String]): Unit = {
    val list1 : List[Int] = ::(-1, ::(-2, ::(-3, ::(-f4, Nil))))
    //val list2 : List[Int] = 1 :: 2 :: 3 :: Nil

    val list2 = list1.drop(2)

    println(list1)
    println("After drop(2):")
    println(list2)

  }
}