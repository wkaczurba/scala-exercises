// Based on : https://levelup.gitconnected.com/how-to-implement-a-functional-list-in-scala-203131815d12

sealed trait MyList[+A]  {
  // create a right associative function
  def ::[B >: A](a:B):MyList[B]
  def init: MyList[A]
}

case object Nil extends MyList[Nothing] {
  override def ::[B >: Nothing](a: B): MyList[B] = Cons(a, Nil)

  override def init: MyList[Nothing] = ???
}

case class Cons[+A](head:A, tail:MyList[A]) extends MyList[A] {
  override def ::[B >: A](a: B): MyList[B] = Cons(a, head :: tail)
}

/*
  Create a companion object contains functions for creating
  and working with List
 */
object MyList{
  def apply[A](a:A*): MyList[A] = if(a.isEmpty) Nil else Cons(a.head, apply(a.tail: _*))
}

object Main extends App {
  val a = 5 :: Nil
  val b = 7 :: 6 :: a
  println(b)

}