// note that logical package structure may not correspond with
// folder structure as it ts in Java
package scalacourse
package ch0


// Unlike Java it's not required to have a class that
// is named the same way as the file
// As you may see a single file can host multiple objects or classes.
object Main {

  // You are free to remove this code at all.
  // Do it on your own risk :)
  def main(args: Array[String]): Unit = {
    printHelp
  }


  private def printHelp = println(Res.helpMessage)
}


// you may use this object to store some application-related
// functions, but don't insist on that design
object WordCount {

  // ??? Leaves function unimplemented. It makes compilation
  // pass but you will get something like this:
  // scala.NotImplementedError: an implementation is missing
  def countLines(text: String): Int = ???

  def countWords(text: String): Int = ???

}


object Res {

  // we do expect you to use multiline string with string
  // interpolation if (it is required)
  val helpMessage: String = """There should be a help message"""
}

