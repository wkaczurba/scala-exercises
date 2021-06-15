package own.Ch3

import scala.collection.immutable.NumericRange

// TODO: LInks:


object Ch3_0_Purity_and_Immutablility {

  // PUrity + immutability.
  def main(args : Array[String]): Unit = {
    // NOthing in this chatper
  }
}

object Ch3_1_Partial_functions {
  // TODO: https://en.wikipedia.org/wiki/Partial_function
  // https://blog.bruchez.name/posts/scala-partial-functions-without-phd/
  // https://twitter.github.io/scala_school/pattern-matching-and-functional-composition.html#PartialFunction
  // https://alvinalexander.com/scala/how-to-define-use-partial-functions-in-scala-syntax-examples/

}

object Ch3_2_GoodFunctions_and_Composition {
  // two WAYS OF type definition.

//  def map[B](f : A => B) = {
//
//  }

  // questions: what is arity...

  def dup(x : Int): Int = x*2
  def inc (x : Int): Int = x + 1

  val incDup = dup _ compose inc _ // HMMM....

  def main(args : Array[String]) = {
    println(incDup(3))
  }
}

// High order functions: (accepts another function as argument; it returns another function)

// TODO: Paul -> this does not work.
//object Ch3_3_HighOrderFunctions {
//  type Point = (Double, Double)
//  sealed trait Funname
//  case object Sine extends Funname
//  case object Cosine extends Funname
//
//  //val xs = -10.0 to 10.0 by 0.1 // TODO: talk to Paul about this one - seems like this is no longer supported in 2.13 ???
//  //val xs : NumericRange.Inclusive[BigDecimal] = BigDecimal(-10.0) to BigDecimal(10.0) by 0.1
//  val xs : IndexedSeq[Double] = ( -100 to 100 by 1 ).map( _ / 10.0)
//
//
//  // function that draws something
//  def plot(points : Seq[Point]) = ???
//
//  def plotFunction(fun : Funname) = name match {
//    case Sine =>
//      // val ys : IndexedSeq[Double] = (xs map math.sin) //  TODO: Discuss this one with Paul (it does not work.!
//      val ys = xs.map(fun)
//      val xs = (ys map math.cos)
//      val coords = xs zip ys
//      plot(coords)
//    case Cosine =>
//      // the same ...
//  }
//
//  def main(args : Array[String]): Unit = {
//    plotFunction(Sine)
//  }
//}


object  ch3_passing_functions_p24 {
  type PlotFun = Double => Double // Improtant! Function getting a Double arg and producing a Double result...
  type Point = (Double, Double)

  def plotFunction(fun : PlotFun): Unit = {
    val xs = Range.BigDecimal.inclusive(0, 100, 0.1).map(_ .toDouble)
    val ys = xs map fun
    // FIXME: Question to Paul: why "xs map fun" does not work?
    val coords : IndexedSeq[Point] = xs zip ys // TODO: Ask Paul: what is the type of this one?
    plot(coords)
  }

  //def plot(coords: Array[Point]) = { // TODO
  def plot(coords: Seq[Point]): Unit = { // TODO: Discuss with Paul: should I use seq or array in here? Why so weird IndexedSeq is returned???
    coords.foreach( println )
  }

  def main( args : Array[String]): Unit = {
    plotFunction(math.sin)
  }
}


// TODO: Ask Paul: should we use type annotation after each function - as IntelliJ asks for that?


object ch3_currying {
  // Single multi-argument function is segregated to chained single argument function calls...

  // currying notation:
  def sayMyName(name : String) : String = "Your name is " + name + "";

  //def concat(first: String, second: String) : String = first + second
  def concat(first:String)(second:String) : String = first + second // two functions.

  def main(args : Array[String]) = {
    println(concat("hello")(" world"))
    println ( concat("x")_ ) // this is lambda that is predefined with x and accepts argument _

    val greet = concat("Hello ")_ // lambda
    println(greet("Roman")) // Hello ROman
  }
}