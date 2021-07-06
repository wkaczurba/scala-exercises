package odersky

// resources: https://dev.to/mt40/simple-hash-map-with-scala-in-15-minutes-4jch

//case class Entry[K, V](key : K, vector : V)

class HashMap[K,V] private (entries: Vector[Entry[K,V]], initialCapacity : Int = 25) {

  entries = Vector.fill(initialCapacity)(Vector.empty)

  def add(key : K, value : V) : Boolean = {
    entries.indexWhere(e -> e.key == key)
  }

  def remove(key : K) = ???
  def get(key : K) = ???

  private def init: HashMap[K,V] = { new HashMap[K,V](Vector.fill(25)(Vector.empty)) }
}

object HashMap {

}



