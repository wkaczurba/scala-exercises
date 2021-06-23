package wordcount

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

// Read more from: https://www.scalatest.org/user_guide/writing_your_first_test

// TODO: Add more tests.
class MainTest extends AnyFlatSpec {
  class MockSource(s : String) extends Source {
    override protected val iter: scala.Iterator[Char] = s.iterator
  }
  object MockSource {
    def apply(s: String): MockSource = new MockSource(s)
  }

  "WordCount" should " count the words" in {
    assert ( WordCount.countWords(MockSource("one two . three?!@ may")) == 4)
  }

//  "findLongestLine" should " count the words" in {
//    assert ( WordCount.countWords(MockSource("one two . three?!@ may")) == 4)
//  }

//  "main" should "count bytes" in {
//    assert ( Main.main("-c", "123456789")} )
//  }
}