package apply

// https://docs.scala-lang.org/tour/extractor-objects.html

// Notes:
//  - for extractor methods
//  - usually Option as output

object Email {

  def apply(login : String, domain : String): String = {
    login+"@"+domain
  }
  def unapply(email: String) : Option[(String, String)] = {
    val split = email split "@";
    if (split.length == 2) {
      Some(split(0), split(1))
    } else None
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val email = Email("zupa", "mail.ru")

    println(s"email: ${email}")
    val (login, domain) = Email.unapply(email).get
    println(s"login: ${login}, domain: ${domain}")

    // pattern matching w/unapply:
    val s = "Some@string"

    // simple pattern matching:
    s match {
      case Email(name) => println(s"s is an email. ==> ${name}")
      case _ => println("s is not an email.")
    }
  }
}
