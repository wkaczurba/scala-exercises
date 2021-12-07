package playground

import scala.annotation.tailrec

object RecursionExercises {

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

  // TODO: Exercises from 16:00, https://www.udemy.com/course/rock-the-jvm-scala-for-beginners/learn/lecture/7660616#overview
  // concat a string n times
  // isPrime function tailrec
  // fibonacci func

  // Not tail-rec
  def concat(s : String, n : Int) : String = {
    if (n < 1) s else
      concat(s, n-1) + s
  }

  def concat2(s : String, n : Int) : String = {
    @tailrec
    def concatHelper(s : String, n : Int, acu : String) : String = {
      if (n < 1) acu else {
        concatHelper(s, n - 1, acu + s)
      }
    }

    concatHelper(s, n, "")
  }


  def isPrime(n : Int) : Boolean = {
    @tailrec
    def isPrimeHelper(n : Int, d : Int) : Boolean = {
      if (d > n/2) true
      else
        n % d != 0 && isPrimeHelper(n, d + 1)
    }
    isPrimeHelper(n, 2)
  }

  def fibonacci(n : Int) : Int = {
    if (n < 2) return 1
    fibonacci(n - 1) + fibonacci(n - 2)
  }

  def fibonacci2(n : Int) : Int = {
    @tailrec
    def fibonacciHelper(i : Int, last : Int, nextToLast : Int) : Int = {
      if (i >= n - 1) last
      else
        fibonacciHelper(i + 1, last+nextToLast, last)
    }
    fibonacciHelper(0, 1, 1)
  }

//  @tailrec
//  def fibonacci2(n : Int) : Int = {
//    def fibonacciHelper( n : Int, acu : Int) : Int = {
//      if (n < 2) 1 else
//        fibonacciHelper(n-1) + fibonacciHelper(n-2)
//    }
//
//  }

  def main(args : Array[String]) = {
    //println(concat("x", 100000)) // stack-overflow
    //println(concat2("x", 100000)) // OK: tail-recusrive.

    var i = 0;
    for (i <- Range(0, 20)) {
      println(s"fibonnacii(${i}) == ${fibonacci(i)}, ${fibonacci2(i)}")
    }

    //println(isPrime(4))
    /*
    println(s"f1(5)=${factorial1(5)}")
    println(s"f2(5)=${factorial2(5)}")
    //println(factorial1(50000)) // stack-overflow
    println(factorial2(50000)) // works fine, no stack-overflow
    */

  }

}
