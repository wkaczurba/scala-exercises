package adtsandenums

object AdtProduct {

  case class Geoloc(val latitude : Double, val longitude: Double)

  def main(args: Array[String]): Unit = {

    val g = Geoloc(2,2) // (Double x Double cartesian product becaause of constructor)

    println(s"Geoloc: ${g}")

    g match {
      case Geoloc(x, y) => println(s"x = ${x}, y = ${y}") // using unapply method.
    }
  }

}
