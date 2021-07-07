package odersky

// resources: https://dev.to/mt40/simple-hash-map-with-scala-in-15-minutes-4jch

class HashMap[K,V] private (entries: Vector[Entry[K,V]], initialCapacity : Int = 25) {

  // Can be done as a list or another mechanism
  //entries = Vector.fill(initialCapacity.max(entries.length))(Vector.empty)
  //entries = Vector.fill(initialCapacity)(Vector.empty)
  def length: Int = entries.length

  def add(key : K, value : V) : Unit = {

    // if does not contain
    entries.indexWhere(e => e.key == key) match {
      case -1 => {
        //entries.updated
        //new HashMap(entries.
        true
      }
      case _ => { // entry exists
        //entries.
        true
      }
    }
  }

  def remove(key : K) = ???
  def get(key : K) = ???

//  private def init: HashMap[K,V] = { new HashMap[K,V](Vector.fill(25)(Vector.empty)) }
}

object HashMap {

}



