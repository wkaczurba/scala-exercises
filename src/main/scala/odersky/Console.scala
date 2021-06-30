object Console {

  // Super inefficient:
  def incAll(xs : List[Int]) : List[Int] = xs match {
     case List() => List()
     case x :: xs1 => x + 1 :: incAll(xs1)
  }

  def main(args: Array[String]): Unit = {
    incAll(List(1,2,3,4))
  }
}

