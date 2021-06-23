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

  def findLongestLine(buf: Source): Int = {
      // buf.getLines().maxBy(_.length) -> returns longest line, but probably might consume much more memory
    buf.getLines()
      .map(line => line.length)
      .max
  }

  def countLines(buf: Source): Int = {
    buf.getLines().count(l => true)
  }


  def merge(sequence : Seq[Char]) = {
    sequence.foldRight(List.empty[Char]) {
      case ( a, b ) => a.isLetter && a.isLetter
    }
  }

  def countWords(buf : Source): Int = {
    var words = 0

    buf.map(_.isLetter).foldLeft(false)(_ ^ _ =>)


    while(buf.hasNext) {
      if (buf.next().isLetter) {
        words += 1
      while (buf.hasNext && buf.next().isLetter) buf.next()
      }
    }
    words
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

