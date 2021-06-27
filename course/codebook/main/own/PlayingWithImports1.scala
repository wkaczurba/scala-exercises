package own


import scala.collection.mutable.{ Map => MutableMap }

// Does not work: import scala.collection.immutable{Map, Set};

//import my...
import scala.collection.mutable.{ Map => MutableMap }

object PlayingWithImports1 extends App {

  val anArray = Array(1,2,3,4,5)
  val anArray2 : Array[Int] = Array(1,2,3,4,5)

  // Lists:
  val list = List("a", "b", "c")
  val myList = "a" :: "b" :: "c" :: Nil

  //Sequences:
  val sequence = Seq(1,2,3,4,5)


  //val x :MutableMap = ['a' -> 2];

}
