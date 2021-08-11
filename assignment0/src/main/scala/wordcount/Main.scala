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
    args match {
        case Array("-c", file) => print (WordCount.countBytes(file))
        case Array("-l", file) => print (WordCount.countLines(Source.fromFile(file)))
        case Array("-m", file) => print (WordCount.countCharacters(Source.fromFile(file)))
        case Array("-w", file) => print (WordCount.countCharacters(Source.fromFile(file)))
        case Array("-L", file) => print (WordCount.findLongestLine(Source.fromFile(file)))
        case _ => printHelp
    }
  }

  private def printHelp = println(Res.helpMessage)
}

// you may use this object to store some application-related
// functions, but don't insist on that design
object WordCount {
  def countBytes(path: String): Any = Files.size(Paths.get(path)) // scala.io doesnt deal well with bin files, so reverting to Java's lib.
  def findLongestLine(buf: Source): Int = buf.getLines().map(line => line.length).maxOption.getOrElse(0)
  def countLines(buf: Source): Int = buf.getLines().count(l => true)
  def countWords(buf : Source): Int = {
    buf.map(x => if (x.isLetter) 1 else 0).foldLeft(List[Int]())(
      (a: List[Int], b: Int) => if (a.nonEmpty && a.head == b) a else a.prepended(b)).sum
  }
  def countCharacters(buf : Source) : Int = buf.count(_ => true)
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

