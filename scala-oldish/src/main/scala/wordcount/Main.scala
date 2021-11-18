package wordcount

import scala.io.{BufferedSource, Source}
import java.nio.file.{Files, Paths}

//import wordcount.WordCount.Res // for byte-reading....

// Unlike Java it's not required to have a class that
// is named the same way as the file
// As you may see a single file can host multiple objects or classes.
object Main {

  // You are free to remove this code at all.
  // Do it on your own risk :)
  def main(args: Array[String]): Unit = {
    if (args.length == 2) args(0) match {
      case "-c" => print (WordCount.countBytes(args(1)))
      case "-l" => print (WordCount.countLines(Source.fromFile(args(1))))
      case "-m" => print (WordCount.countCharacters(Source.fromFile(args(1))))
      case "-w" => print("word count")
      case "-L" => print (WordCount.findLongestLine(Source.fromFile(args(1))))
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
  def countBytes(path: String): Any = { // TODO: FIXME does not look great.
    val is = Files.newInputStream(Paths.get(path))
    var count = 0: Long
    var av = 0: Long
    while ( {
      av = is.available(); av
    } > 0) {
      count += is.skip(av)
    }
    count
  }

  def findLongestLine(buf: BufferedSource): Int = {
      // buf.getLines().maxBy(_.length) -> returns longest line, but probably might consume much more memory
    buf.getLines().map(_.length).max
  }

  def countLines(buf: BufferedSource): Int = {
    buf.getLines().count(_ => true)
  }

  def countWords(text: String): Int = ???

  def countCharacters(buf : BufferedSource) : Int = {
    buf.count(_ => true)
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

