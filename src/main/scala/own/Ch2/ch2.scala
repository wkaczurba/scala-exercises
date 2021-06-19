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
  val strangeList = List.range(1, 22449, 19)
  val opt = strangeList.findLast(_ % 23 == 7)

  def main(args : Array[String]) : Unit = {
    optionsExample()
    flattening()
  }

  def optionsExample() : Unit = {
    println("Looking for a strange number: " + opt.getOrElse("but nothing found"))
  }

  def flattening() : Unit = {
    val x : Option[Option[Option[String]]] = Some(Some(Some("Abc")))

    println(x.flatten) // gets: Some(Some(Abc))

    // println(x.flatMap( msg => "$msg" )) // FIXME: Talk to Paul on how to fix this one.
  }
}

object Ch2_Tuples {
  // main issue: tuples are not aliased
  type Point = (Double, Double)

  def drawLine(a : Point, b : Point) : Unit = {
    println(a._1, a._2) // ugly a bit...
  }

  val points : Array[Point] = Array( (1, 2), (3, 4), (5, 6))

  def main(args : Array[String]) = {
    points.foreach(p => println(p._1 + " " + p._2)) // a bit ugly.

    println("Group by:")
    println(points.groupBy(_._2)) // TODO: Try more..
  }

  println("points foreach case(x, y) => { ... }") //
  points foreach { case (x, y) => {
    println(s"point.x=$x, point.y=$y")
  }}
}

// TODO: Discuss  ch2 - 18 where it begins "pretty haskellish" -> what is this about? I dont code Erland and Haskell.

object Ch2_Tuples_copying {
  // updating an element in tuple:

  val dog = ("Rex", 13)
  val olderDog = dog.copy(_2 = 15) // updates + modifies second element ; FIXME: TO Paul - there is a mistake in the code "tuple.copy" should be "dog.copy"

  def main(args : Array[String]) = {
    println("dog:" + dog)
    println("olderDog: " + olderDog)
  }

}

// TODO: More examples:
