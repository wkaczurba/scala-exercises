package playground

object CaseClassesExercises {

  case class Person(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val jack1 = new Person("Jack", 23)
    val jack2 = new Person("Jack", 23)

    println(s"jack1==jack2? ${jack1==jack2}; jack1=${jack1.toString}")

    // sort-of prototype-pattern:
    val jack3 = jack2.copy(age=34)
    println(s"jack3 = ${jack3.toString}")
  }


  // NOTES:
  /**
    // 1. class parameters are fields
    // 2. sensible toString (println(instance) = println(instance.toString))
    // 3. equals and hashCode implemented OOTB (jack == jim2)
    // 4. CCs have handy copy method eg. jim3 = jim.copy(age = 45)
    // 5. CCs have companion objects ( val thePerson = Person; val mary = Person("Mary", 23) )
    // 6. CCs are serializable ( Akka )
    // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING
    case object UnitedKingdom {
      def name: String = "The UK of GB and NI"
    }

    /*
      TODO: Expand MyList - use case classes and case objects
     */
   */
}
