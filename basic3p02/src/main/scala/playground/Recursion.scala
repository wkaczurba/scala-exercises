package playground

import scala.annotation.tailrec

object Recursion {

  def factorial1(i : Int) : Int = {
    if (i <= 1) 1 else {
      factorial1(i - 1) * i
    }
  }

  def factorial2(i : BigInt) : BigInt = {
    @tailrec
    def factorialHelper(i : BigInt, acu : BigInt) : BigInt = {
      if (i <= 1) acu else {
        factorialHelper(i - 1, i * acu) // tail-recursion: userecusive all as last call
      }
    }
    factorialHelper(i, 1)
  }

  def main(args : Array[String]) = {
    println(s"f1(5)=${factorial1(5)}")
    println(s"f2(5)=${factorial2(5)}")
    //println(factorial1(50000)) // stack-overflow
    println(factorial2(50000)) // works fine, no stack-overflow

  }

}
