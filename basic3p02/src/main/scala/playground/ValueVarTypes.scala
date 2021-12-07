package playground

object ValueVarTypes:
  //var b : Boolean (this wont work - it is an object.

  def main(args: Array[String]): Unit = {
    val x = 16 // inference
    println(s"x=$x")

    val y : Int = 16;
    println(s"y=$y")

    // Variables: Boolean, Long, Char, Int, Short, Float, Double
    // var mutableBool : Boolean (needs to be initialized)
    var mutableBool = true

    mutableBool = true
    println(s"mutableBool=${mutableBool}")

    // UNit
    val u1 : Unit = {} // is a return of from a block
    val u2 = { mutableBool = false };

    println (s"u1 = ${u1}")
    println (s"u2 = ${u2}")
    println (s"u1==u2?: ${(u1==u2)}") // two units are equal.

    val codeBlock = {
      val y=2; val z=y+1

      if (z > y) "hello" else "goodbye"
    }
    println(codeBlock)

  }

abstract class Alass {
  var x : Boolean
}