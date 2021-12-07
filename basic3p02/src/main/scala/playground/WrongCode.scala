package playground

object WrongCode {
  def main(args : Array[String]): Unit = {
    var x = 10
    while (x > 0) { // FIXME: THis is wrong code : It is imperative programming.
      //println( x--) // this wont work. neither C or Java....
      println( x )
      x -= 1
    }
  }
}
