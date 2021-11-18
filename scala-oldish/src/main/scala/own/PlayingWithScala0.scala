package own

object PlayingWithScala0 {

  val unit: Unit = () // just a unit.

  // this also returns a unit:
  val block: Unit = { println("Hello"); println("Bello") }

  // carrots + onions: it returns Int.
  val block2: Int = { val carrots = { 1 + 2 }
    val onions = 2
    carrots + onions
  }

  def main(args: Array[String]): Unit = {
    print("Test1")
    print (unit)

    // executing block:
    block
  }

}
