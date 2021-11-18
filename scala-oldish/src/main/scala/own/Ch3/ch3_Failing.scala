package own.Ch3

// from https://github.com/wkaczurba/scala-course/blob/master/textbook/en/ch3_functional_thinking/2_high_order_functions_currying.md
// TODO: Talk to Paul about this example.
//object Ch3_Failing {
//  type Point = (Double, Double)
//
//  sealed trait Funname
//  case object Sinus extends Funname
//  case object Cosinus extends Funname
//
//  val xs = -10.0 to 10.0 by 0.1 // TODO: This is error
//
//  // Imagine that we have a function that draws a chart by given set of points
//  def plot(points: Seq[Point]) = ???
//
//  def plotFunction(name: Funname) = name match {
//    case Sinus =>
//      // get list of evaluated values
//      val ys = (xs map math.sin) // "map" is not accepted...
//      // glue xs and ys together
//      val coords = xs zip ys
//      // plot them
//      plot(coords)
//    case Cosinus =>
//    // the same should be done here...
//  }
//}

