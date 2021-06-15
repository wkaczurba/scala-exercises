package own.Ch3

import own.Ch3.ch3_ref_or_value_passing.Cl1

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

// TODO: Move this into separate main file.
// TODO: MOre on currying:
//   1. http://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html
//   2. https://en.wikibooks.org/wiki/Scala/Currying
//   3. http://fruzenshtein.com/scala-currying-functions/
//   4. https://alvinalexander.com/scala/scala-curried-partially-applied-functions-how-compiled-scalac/
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

object ch3_closures {
  // TODO: review the following resources:
  // - https://alvinalexander.com/scala/how-to-use-closures-in-scala-fp-examples/
  // - https://www.slideshare.net/knoldus/functions-closures
  // - https://openhome.cc/eGossip/Blog/UnderstandingLambdaClosure4.html

}
// TODO: Review this one: http://twitter.github.io/scala_school/pattern-matching-and-functional-composition.html#composition

object ch3_ref_or_value_passing {
  class Cl1 (name : String, age : Int);

  def main(args : Array[String]) = {
    val x : AnyRef = ""
    //val y : AnyRef = 5
    //val p : AnyVal = "xxx" // How this is possible?; AnyVal vs String..?
    //val q = AnyVal = new Cl1("monster1", 44)
    val q : AnyRef = new Cl1("monster1", 44)

    // TODO: WHat is a difference between AnyVal and AnyRef? - it would be good to have a clear definition; what are value classes? -> Scala Language Specification, section 12.2

    // TODO: Read about this ones:
    // - https://www.scala-lang.org/api/current/scala/AnyRef.html
    // - https://www.scala-lang.org/api/current/scala/AnyVal.html
    // call by name: https://tpolecat.github.io/2014/06/26/call-by-name.html and http://locrianmode.blogspot.com/2011/07/scala-by-name-parameter.html
    // call by need (memoization): https://en.wikipedia.org/wiki/Memoization
    // call by future: "Evaluation strategy where arguments are evaluated concurrently inside the function body. Scala has Futures so it also adopts this strategy."
  }
}

object ch3_lazy_init {

  class Cl1(name :String) { println ("initialised" + name)}

//  lazy val bob : Cl1 = new Cl1("Bob")

  def main(args : Array[String]) = {
    lazy val bob : Cl1 = new Cl1("Bob")
    val jeffrey = new Cl1("Jeffrey")

    println("code started")

    Thread.sleep(2000)
    println("jeff" + jeffrey)
    println("bob: " + bob)

    // TODO: More reading:
    //  https://matt.might.net/articles/implementing-laziness/
    //  https://blog.codecentric.de/en/2016/02/lazy-vals-scala-look-hood/


  }
}
