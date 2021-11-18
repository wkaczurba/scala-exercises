import scala.collection.mutable.ListBuffer

object Console2 {

  def incAll(xs : List[Int]) = {
    val buffer = new ListBuffer[Int]

    for (x <- xs)
      buffer += x + 1
    buffer.toList
  }

  def main(args: Array[String]): Unit = {
    print(incAll(List(1,2,3,4)))
  }
}

