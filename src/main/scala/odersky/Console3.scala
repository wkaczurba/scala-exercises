import scala.collection.mutable.ListBuffer

// The most efficient is not to use ListBuffers at all...:
object Console3 {

  def mappingFunc[T,U](xs : List[T], f: T => U) : List[U] = {
    val lb = new ListBuffer[U];
    for (x <- xs) {
      lb += f(x)
    }
    lb.toList
  }

  def main(args: Array[String]): Unit = {

    println(mappingFunc(List(1, 2, 3, 4), (x:Int) => x + 1)) // Note: needs (x:Int) as type cannot be inferred (?)
  }
}

