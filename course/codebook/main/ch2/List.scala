package scalacourse
package ch2

// TODO:
//

sealed trait List [+A] {
  def size: Long
  def isEmpty: Boolean

  def head: A
  def last: A
  def headOpt: Option[A]
  def lastOpt: Option[A]
  def tail: List[A]
  def init: List[A]

  // should return index of given element
  def indexOf[A](elem: A): Long

  // Returns first n elements of the list
  // if n > list size it will return the whole list
  def take (n: Int): List[A]

  // returns last n elements of the list
  def takeRight(n: Int): List[A]

  def reverse: List[A]

  def zip [B] (that: List[B]): List[(A, B)]
  def zipWithIndex: List[(A, Long)]

  def append(that: List[A]): List[A]

  // appends element to the beginning of list
  def cons(a: A): List[A]

  // High order functions
  // Recursively implement the following methods:
  def map [B] (f: (A) => B): List[B]
  def filter (predicate: (A) => Boolean): List[A]

  def count(predicate: (A) => Boolean): Int
  def find (predicate: (A) => Boolean): Option[A]
  def exists(predicate: (A) => Boolean): Boolean

  // Partial function
  // creates a new collection by its partial application to all elements
  // of given list where this function is defined
  def collect(pfun: PartialFunction[A,B]): List[B]

  // Currying
  def foldLeft [B] (z: B) (operator: (B, A) => B): B
  def foldRight [B] (z: B) (operator: (A, B) => B): B
}


object List {
  def apply [T] (items: T*) = ???


  def fill [T] (value: T)(size: Int) = ???
  def empty: List = ???
}

// case class Cons() extends List[+A]
// case class Nil() extends List[+A]


// something else is also missing ;)?

case class init: List[A] extends List[A]
case class def indexOf[A](elem: A): Long extends List[A]


