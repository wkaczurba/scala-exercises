package lectures.part1basics

import scala.util.Random

object CallByName {

  def callByName(cbn : => Int) = {
    println(cbn);
    println(cbn);
  }

  def main(args: Array[String]) = {
//    val x : => Int = {
//      5
//    }
    def x : Int = Random.nextInt()
    callByName(x)
  }
}
