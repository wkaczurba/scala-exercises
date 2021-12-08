package adtsandenums

// https://blog.rockthejvm.com/algebraic-data-types/

// more on sealed classes is here: https://medium.com/codex/sealed-classes-and-traits-in-scala-62e6076053c6

// sum type:

sealed trait Weather  // sealed trait -> all case objects are exhaustively defined in
// a single file and cannot be altered from the outside
case object Sunny extends Weather
case object Windy extends Weather
case object Rainy extends Weather
case object Cloudy extends Weather

// Weather is a sum of Sunny + Windy + Rainy + Cloudy

object AdtSum {

  def main(args: Array[String]): Unit = {

    //val rand = util.sSet(Sunny, Windy, Rainy, Cloudy)
    val weather = util.Random.shuffle( Set(Sunny, Windy, Rainy, Cloudy) ).head

    // The thing about the weather is that it will be exhaustive.
    weather match {
      case Sunny => println("Sun is shining")
      case Windy => println("Wind is blowing")
      case Rainy => println("Rain is lashing")
      case Cloudy => println("Overcast as usual")
    }



  }

  // TODO: Scala3 enums

  // https://www.baeldung.com/scala/case-objects-vs-enumerations

  // TODO: Read also this one: https://www.baeldung.com/scala/sealed-keyword


  // ADTs: sum and product types...

  // Sum types:


}
