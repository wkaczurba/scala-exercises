package apply

// Companion object:
object Person {
  def apply(firstname : String, lastname: String): Person = {
    return new Person(firstname, lastname)
  }
}

// Apply:
sealed class Person(val firstname: String, val lastname: String) {
}

object ApplyExample1 {
  def main(s : Array[String]): Unit = {
    val p = Person("Josh", "Montana")

    if (p == Person("Josh", "Montana")) { // this will be true only if case class
      println("true")
    }  else {
      println("false")
    }
  }
}

// unapply: ?
