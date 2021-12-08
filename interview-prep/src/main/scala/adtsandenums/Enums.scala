package adtsandenums

// from: https://docs.scala-lang.org/scala3/reference/enums/enums.html

enum Color(val rgb: Int):
  case Red extends Color(20)
  case Blue extends Color(33)
  case White extends Color(99)

object Enums {
  def main(args: Array[String]): Unit = {
    val x = Color.Red

    x match {
      case Color.Red => println(s"red is ${x.rgb}")
      case Color.Blue => println(s"blue is ${x.rgb}")
      case Color.White => println(s"white  is ${x.rgb}")
    }
  }
}