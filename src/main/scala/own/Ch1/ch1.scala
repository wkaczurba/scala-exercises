package own.Ch1

import java.io.FileInputStream
import java.lang.RuntimeException

import jdk.internal.joptsimple.internal.Strings

//sealed class Claws
//  case class c extends Claws

sealed trait Weapon
  case class Claws() extends Weapon

object Claws extends Claws {
  override def toString: String = "claws companion object..."
}   // Companion object for weapon, sort of weird...
object Talon extends Claws {
  override def toString: String = { "more than a claw..." }
}
object Handgun extends Weapon {}

case class Monster(health: Int = 100,
  weapon : Weapon = Claws,
  name : String)

object Ch1_CaseClasses_CaseObjects {
  val richard : Monster = new Monster (250, Handgun, "Richard")
  val monsterB = Monster(name="Whatever")

  def main(args: Array[String]): Unit = {

    println("Welcome")

    println(richard)
    println(monsterB)

    val copyOfMonsterB = monsterB.copy()
    println("copyOfMonsterB: " + copyOfMonsterB) // copy operator.
  }

  // TODO: Try copy!.
}

// TODO: Move it to a separate file
object Ch1_Assignments {
  def main(args : Array[String]): Unit = {

    // first...rest:
    val first::rest = List(1, 2, 3, 4, 5, 6)

    println("first:" + first)
    println("rest:" + rest)

    val address = ("localhost", 80) // tuple (Starts from 1...
    println("address(1):" + address._1 )
    println("address(2):" + address._2 )

    case class Person(name : String, age : Int)

    val maxim = Person("max", 45)
    println(maxim)

    val Person(c, d) = maxim
    println(c)
    println(d)

    // weiredest stuff: TODO:
    val p @ Person(n, a) = maxim
    println("p="+p)
    println("n="+n)
    println("n="+a)
  }
}

// TODO: MOve to a separate file:
object ch1_DataConstruction_and_decomposition {

  def main(args : Array[String]) : Unit = {
    val myArray: Array[Int] = Array(2100, 2099)
    val myList = List(1, 2, 3, 4, 5, 6,7 ,8, 9)

    val arrayApply: Array[Int] = Array.apply(2100, 2099)
    println("are equal?: " + (myArray.equals( arrayApply) )) // why they are not equal;?
    println("are equal?: " + (myArray.toList.equals(arrayApply.toList))) // why they are not equal woth eq.;? Looks like eq is == and equals is object-based comparison TODO: Discuss with Pavel
    println("  myArray.toList=" + myArray.toList)
    println("  arrayApply.toList=" + arrayApply.toList)
  }
}

object ch1_DataExtraction {
  // TODO: Accordingly to :  https://docs.scala-lang.org/tour/extractor-objects.html
}

object ch1_exceptionHandling { // TODO: Move to a separate file.
  // TODO: look up the resources:
  // - http://crunchify.com/better-understanding-on-checked-vs-unchecked-exceptions-how-to-handle-exception-better-way-in-java/
  // - http://tutorials.jenkov.com/scala/exception-try-catch-finally.html

  def main(args : Array[String]) : Unit = {
    try {
      new FileInputStream(new java.io.File(".asdf")).read()

      //println(5 / 0)
    } catch {
      case e @ ( _ : ArithmeticException) => println("Caught arithmetic exception:"); e.printStackTrace()
      case e @ ( _ : RuntimeException) => println("Caught runtimeexception:"); e.printStackTrace()
      case e @ ( _ : Exception) => println("Caught exception: "); e.printStackTrace() // It would be checked-exception in Java but here it is unchecked...
    }
    System.out.println("program goes on...")

  }
}