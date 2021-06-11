package wordcount

import scala.io.{BufferedSource, Source}

// Unlike Java it's not required to have a class that
// is named the same way as the file
// As you may see a single file can host multiple objects or classes.
object Main {

  // You are free to remove this code at all.
  // Do it on your own risk :)
  def main(args: Array[String]): Unit = {
    if (args.length == 2) args(0) match {
      case "-c" => WordCount.countCharacters(Source.fromFile(args(1)))
      case "-l" => print("line count")
      case "-m" => print("character count")
      case "-w" => print("word count")
      case "-L" => print("length of the longest line")
      case _ => printHelp
    } else {
      printHelp
    }
  }


  private def printHelp = println(Res.helpMessage)
}


// you may use this object to store some application-related
// functions, but don't insist on that design
object WordCount {

  // ??? Leaves function unimplemented. It makes compilation
  // pass but you will get something like this:
  // scala.NotImplementedError: an implementation is missing
  def countLines(buf: BufferedSource): Int = {
    var count = 0
    while (buf.getLines().hasNext)
      buf.next()
      count += 1
    count
  }

  def countWords(text: String): Int = {
    return 3
    //text.split
  }

  def countCharacters(buf : BufferedSource) : Int = {
    var count = 0
    while (buf.hasNext)
      count += 1
      buf.next()
    count
  }

}

object Res {

  // we do expect you to use multiline string with string
  // interpolation if (it is required)
  val helpMessage: String = """ wc -c <filename> prints the byte count
                              | wc -l <filename> prints the line count
                              | wc -m <filename> prints the character count
                              | wc -w <filename> prints the word count
                              | wc -L <filename> prints the length of the longest line """.stripMargin
}

