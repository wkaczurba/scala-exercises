package wordcount

import org.scalatest.flatspec.AnyFlatSpec

// Read more from: https://www.scalatest.org/user_guide/writing_your_first_test
class MainTest extends AnyFlatSpec {

  "WordCount" should " count the words" in {
    assert ( WordCount.countWords("one two three") == 3)
  }


}