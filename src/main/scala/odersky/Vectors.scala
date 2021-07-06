package odersky

import shapeless.T

case class Entry[K, V](key : K, value : V)

object Vectors {
  def main(args : Array[String]) : Unit = {
    val a = Vector.fill(3)(Vector.empty)


    val b = a.updated(2, "Ala")
    println(b);

    val c = b.updated(0, 0.3)

    //println("Map will be here...")
    println(c);

    val x = Entry("pear", "gruszka")
    val y = Entry("fruit", "owoc")
    val z = Entry("mango", "exotic fruit")

    val vector = Vector(x, y, z)

    def findX[T] (x : T): Option[T] = {
      vector.find(e => e == x).map(entry => entry.value)
    }

  }
}

object Maps {
  def main(args : Array[String]) : Unit = {
    val m = Map(2->3, 4->5, 6->7)
    m
  }
}