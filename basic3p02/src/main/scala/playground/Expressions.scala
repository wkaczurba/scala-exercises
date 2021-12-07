package playground

object Expressions {

  def main(args: Array[String]) : Unit = {

    val maybe2 = if (Math.random() > 0.5) 1 else 2 // FIXME: add braces for clarity // REMEMBER: NO returns...

    println(s"maybe 2=${maybe2}")

    println(if (maybe2 == 2) "it is two" else "it is not two")

  }
}
