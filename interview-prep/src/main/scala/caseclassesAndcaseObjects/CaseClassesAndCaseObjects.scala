package caseclassesAndcaseObjects

object CaseClassesAndCaseObjects {
  // useful for pattern-matching.

  // case class-> automatic String, equals, etc.
  //   - apply
  //   - toString
  //   - copy
  //   - equals/hashCode
  case class Book(val name: String, author: String, isbn: String);
  //  case object Book {
  //    def whatever() : Unit = { println("Whatever...") }
  //  }

  case class Book2(name: String) // name is also a val.

  def main(args: Array[String]): Unit = {
    val b = Book("J", "N", "1234") // no need for "new" as case-classes have apply
    val b2 = b.copy() // copy but a new isbn
    val c = b.copy(isbn = "1235") // copy but a new isbn

    println(s"Book b: ${b}\nBook c: ${c}")
    assert(b == b2)

    val x = Book2("d")
  }


}
