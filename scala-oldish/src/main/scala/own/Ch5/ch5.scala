package own.Ch5

//import own.Ch5.ch5_typeClasses.Animal
//import own.Ch5.ch5_typeClasses_.Animal
//import own.Ch5.ch5_typeClasses_1.{Animal, Barks}
//import own.Ch5.ch5_typeClasses_c.Animal
//import own.Ch5.ch5_typeClasses_old.Animal

import java.awt.Dimension

import shapeless._

import scala.util.Sorting // hmmm.. FIXME: It would be great to describe where to import shapeless from.;
//import monocle._
 // TODO: Talk with Paul -> it would be good to start from basiscs, such as correct library ie.: https://dzone.com/articles/shapeless-monomorphic-scala-vs-polymorphic-scala
 // Looks like this is the lib: https://mvnrepository.com/artifact/com.chuusai/shapeless

object ch5_hetereogenous_lists {

  // Todo: reading + examples from here: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/
  // TODO: Reading + another good tutorial: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/


  // https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#heterogenous-lists

  // HList - part of shapeless.
  def main(args : Array[String]) = {
    //val
    val list : List[String] = "i" :: "love" :: "cookies" :: Nil

    val hlist = "i" :: "love" :: "cookies" :: HNil

    // the hlist is of weird type:
    val hlistExplicit : String :: String :: String :: shapeless.HNil = hlist
    println("hlist is one of weird type: " + hlist.getClass.toString)

    //
    println (("firstElement" :: HNil).head)
  }
}

// https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.1.0#operations-on-hlistsrecordscoproductsunionstuplesproducts

// Tuples on Steroids - p.32:
import syntax.std.tuple._

object ch5_tuples_on_steroids {

  def main(args : Array[String]): Unit = {
    val myTuple = ("life", "begins", "at", 40)
    println(myTuple.tail)
    println(myTuple.getClass.toString) // this is "scala.Tuple4"

    // TODO: Code this one:
    //  https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#hlist-style-operations-on-standard-scala-tuples
  }
}


object ch5_Lenses_1 {
  // Sophisticated structurd by case-classes nesting...
  // https://github.com/ppopoff/scala-course/blob/master/textbook/en/ch5_out_of_standard_library/1_lenses.md

  case class Dog (name : String, age : Int)
  val fido = Dog("Fido", 5)
  val olderFido = fido.copy(age = 7) // trick of copying - ch1.

  def main(args : Array[String]): Unit = {
    println("fido: " + fido)
    println("olderFido: " + olderFido)
  }
}

object ch5_Lenses_2 {
  // manual object construction + deconstruction for each specific is not an appropriate way of dealing ...

  case class Person(str: String, age: Int, addr: Address)
  case class Address(str: String, str1: String, str2: String)

  // TODO: Follow this example: https://www.scala-exercises.org/shapeless/lenses
  // Other implementations: "monocle", "scalaz"

  def main(args : Array[String]): Unit = {
    val person = Person("John Wick", 37, Address("Residencia di Compostello", "Cortina", "IT 232"))

    val ageLens = lens[Person] >> Symbol("age")
    val age1 : Int = ageLens.get(person) // inferred type is integer.

    val person2 = ageLens.set(person)(38)

    println("person1: " + person)
    println("person2: " + person2)
  }
}

object ch5_implicits {
  def main(args : Array[String]) : Unit = {
    // implicit parameters; TODO: http://baddotrobot.com/blog/2015/07/03/scala-implicit-parameters/

    // implicit conversion;
    //   TODO: http://baddotrobot.com/blog/2015/07/14/scala-implicit-functions/

    // implicit classes => can be used to provide extension methods.
    //   TODO: https://docs.scala-lang.org/overviews/core/implicit-classes.html


    // TODO: Talk to Paul about Ch5-34: this is ambiguous; No idea what Action { ... } is.

    // FIXME: Ch5 - 34: Talk to Paul that this does not compile:
    class Request;
    class Ok(message : String)(implicit r : Request)

//    Action = {
//      implicit request => Ok("ok: " + request + "]")
//    }

    // Conclusion "In the most cases implicits are not what you want".
    // link: does not load: http://www.artima.com/weblogs/viewpost.jsp?thread=179766
  }
}

object ch5_typeClasses1 {

  // type class allows to describe object's behavior without:
  //   -- changing the object's source code
  //   -- extending that object Let's illustrate that by the following example:

  case class Animal();

  trait Barks {
    def bark = "Woof"
  }

  // Barks shoudl be added to the class:
  class Dog(tag : String) extends Animal with Barks {
    // ...
  }

  def main(args : Array[String]) : Unit = {
    val d = new Dog("Jason")
      println(d.bark)

  }
}

// TODO: THis looks good: https://dev.to/jmcclell/inheritance-vs-generics-vs-typeclasses-in-scala-20op
// TODO: LOok into this one: https://medium.com/virtuslab/typeclasses-scala-be35c0ef0ee9

// Ad-hoc polymporphism:
object ch5_typeClasses2 {

  // type class allows to describe object's behavior without:
  //   -- changing the object's source code
  //   -- extending that object Let's illustrate that by the following example:

  case class Animal();
  trait Barks {
    def bark = "Woof"
  }

  class Dog(tag : String) extends Animal

//   HERE: Using type-classes for ad-hoc polymporphism:
//  @typeclass trait Barks[D] { // FIXME: Paul this does not work. - ch5-35.
//    def bark: String
//  }

}

//object ch5_typeClasses_in_standard_library_1 {
//
//  // TODO: Look into Links:
//  //  - https://www.scala-lang.org/api/2.12.0/scala/math/Ordering.html
//  //  - https://www.scala-lang.org/api/2.12.0/scala/math/Ordered.html
//
//  trait Dimension
//  case class Item(id : Long, name: String, size: Dimension)
//
//  def sort[T] (list : List[T]) (implicit  ord: Ordering[T]) : List[T] = {
//    // ....
//
//    ord.gt(x, y) // TODO: Ask Paul: what is ord.gt ?, what is x, what is y?
//
//    // ....
//  }
//}

object ch5_typeClasses_in_standard_library_2 {

  case class Item(id : Long, name: String)

  // default ordering (it is made implicit)
  implicit val byId: Ordering[Item] = Ordering.by(_.id)

  // another one
  implicit val byName: Ordering[Item] = Ordering.by(_.name)

  val items = List(Item(1, "Charger"), Item(2, "Mouse"), Item(3, "Whatever"))

  // ??? sort
  // http://like-a-boss.net/2012/07/30/ordering-and-ordered-in-scala.html
  // FIXME: Ask Paul why it does not sort, even though it says it should sort.
  //sort(items) // doc says that default sorting will be by ID, but why? it does not look like that.
  // sort(items)(byName)

  // TODO: Links:
  //   - http://like-a-boss.net/2012/07/30/ordering-and-ordered-in-scala.html
  //   - https://engineering.sharethrough.com/blog/2015/05/18/type-classes-for-the-java-engineer/
  //   - https://github.com/mpilquist/simulacrum
}

object ch5_exceptions_revisited {
  // TODO:
  //   Links: https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-6-error-handling-with-try/
  //   https://tersesystems.com/blog/2012/12/27/error-handling-in-scala/
  //   https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-6-error-handling-with-try/
  //   https://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null/
  //   https://danielwestheide.com/blog/the-neophytes-guide-to-scala-part-7-the-either-type/

}