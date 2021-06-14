package own.Ch2

import java.util.stream.Collectors

// Types + enums.

sealed class TrafficLight
case object Green extends TrafficLight // TODO: Note this is "case-object" and not "case-class"!
case object Yellow extends TrafficLight
case object Red extends TrafficLight
case object Broken extends TrafficLight

object Ch2_typesAndEnumeration {
  def main(args : Array[String]) : Unit = {
    tellWhatIsTheLight(Broken)
  }
  def tellWhatIsTheLight(t1: TrafficLight) : Unit = t1 match {
    case Red => println("It is red")
    case Yellow => println("It is yellow")
    case tl if !Array(Green, Yellow, Red).exists(_ == tl) => println("Funky traffic light; looks like: " + tl) // TODO: CHeck with Pavel this one; looks weird!
    // TODO: Try more examples from: https://alvinalexander.com/scala/how-to-use-if-then-expressions-guards-in-case-statements-scala/
    case _ => println("Sure look it...")
  }
}

// ref: https://github.com/wkaczurba/scala-course/blob/master/textbook/en/ch2_types_data_datastructures/1_more_on_lists.md
// FIXME: Discuss this one with Paul.
//object Ch2_headShot {
//  // dont use .head, or .tiail.
//
//  def main(args : Array[String]): Unit = {
//    val myList = 1 :: 2 :: 3 :: 4 :: 5 :: Nil // hmm. why? TODO: Discuss with Pavel, why would this make any sense at all?
//    printRec(myList)
//
//  }
//
//  @scala.annotation.tailrec
//  def printRec(li : List[Int]) : Unit = {
//    li match {
//      case Nil => ()
//      case (x:xs) => println(x) // FIXME: THis does not work (discuss with Pavel). (ref: https://stackoverflow.com/questions/15163904/scala-pattern-matching-with-sets)
//        printRec(xs)
//      case _ => {}
//    }
//
//  }
//}

object Ch2_emptyList {
  // check with Nil

  val x = List.range(1, 200, 10)
  val y = x.filter(_ > 100)

  def main(args : Array[String]) : Unit = {
    println("Hmm:")
    println(s"Is list=$y list empty? = " + y.isEmpty + " or: " + (y == Nil))
    val z = List.empty
    println(s"Is list=$z list empty? = " + z.isEmpty + " or: " + (z == Nil))
    // TOOD: use == Nill for checking...
  }

}

object Ch2_Options {

}
