package wordcount

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

// Read more from: https://www.scalatest.org/user_guide/writing_your_first_test

class MainTest extends AnyFlatSpec {
  class MockSource(s : String) extends Source {
    override protected val iter: scala.Iterator[Char] = s.iterator
  }
  object MockSource {
    def apply(s: String): MockSource = new MockSource(s)
  }

  "countWords" should " count the words" in {
    assert ( WordCount.countWords(MockSource("one two . three?!@ may")) == 4)
    assert ( WordCount.countWords(MockSource(" ?? one two . three?!@ ma y @")) == 5)
    assert ( WordCount.countWords(MockSource("a")) == 1)
    assert ( WordCount.countWords(MockSource("")) == 0)
    assert ( WordCount.countWords(MockSource("???")) == 0)
    assert ( WordCount.countWords(MockSource("??? what ???")) == 1)
  }

   // WordCount.countBytes skipped as it uses Java lib (scala fails with bin files)\

  "findLongestLine" should " find the longest lines " in {
    assert ( WordCount.findLongestLine(MockSource("a\nbc\ndef\np")) == 3 )
    assert ( WordCount.findLongestLine(MockSource("")) == 0 )
    assert ( WordCount.findLongestLine(MockSource("asdfasdfasdfadf")) == 15)
  }

  "countLines" should " count the lines " in {
    assert( WordCount.countLines(MockSource("")) == 0 )
    assert( WordCount.countLines(MockSource("\n")) == 1 )
    assert ( WordCount.countLines(MockSource("a\nbc\ndef\np")) == 4 )
  }
}