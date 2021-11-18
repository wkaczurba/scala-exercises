package odersky

case class Entry[K, V](key : K, value : V)

object Entry {
  def Entry[K,V](tuple : (K, V)): Entry[K, V] = new Entry(tuple._1, tuple._2)
}

// TODO:
//  It would be nice to allow for expansion to supertypes? as in lists from Odersky's book
//  so that add allow to add with entry which is a supertype of pre-existing type (For both keys and values)

//class Map1[K,V](private val vector: Vector[Entry[K,V]]) { // FIXME: Unsafe as it can contain duplicates.
class Map1[K,V](private val vector: Vector[Entry[K,V]]) { // FIXME: Unsafe as it can contain duplicates.

  def add(key : K, value : V): Map1[K, V] = add(Entry(key,value))

  private def add(implicit entry : Entry[K,V]): Map1[K, V] = indexOf(entry.key) match {
    case -1 => new Map1[K,V](vector.appended(entry))
    case index => new Map1[K,V](vector.updated(index, entry))
  }

  def find (x : K): Option[V] = {
    vector.find(e => e.key == x).map( entry  => entry.value )
  }

  private def indexOf( key : K ) = {
    vector.indexWhere(e => e.key == key)
  }

  def delete( key : K): Map1[K, V] = new Map1(vector.filter(e => e.key != key))
  override def toString(): String = "Map1[" + vector.mkString(",") + "]"
}

case class EmptyMap[K,V]() extends Map1[K,V](Vector.empty)

object Map1 {
  def apply[K,V](entries : Entry[K,V]*): Map1[K, V] ={
    var emptyMap : Map1[K,V] = new EmptyMap[K,V]()
    for (e <- entries) {
      emptyMap = emptyMap.add(e)
    }
    new Map1(entries.toVector) // FIXME: This allows for duplicates.
  }

  // FIXME: THis is wrong:
//  def apply[K,V](pairs : (K,V)*): Map1[K, V] = {
//    pairs.foreach(println)
//
//    val vector : Vector[Entry[K,V]] = pairs.map(p => Entry[K,V](p._1, p._2)).toVector
//    new Map1(vector)
//  }
}


object Vectors {
  def main(args : Array[String]) : Unit = {
    val a = Vector.fill(3)(Vector.empty)


    val b = a.updated(2, "Ala")
    println(b);

    val c = b.updated(0, 0.3)

    //println("Map will be here...")
    println(c);

//    val x = Entry("pear", "gruszka")
//    val y = Entry("fruit", "owoc")
//    val z = Entry("mango", "exotic fruit")

    //val vector = Vector(x, y, z)
    //val map = Map1[String, String](x,y,z)
    val map = Map1("pear" ->  "gruszka", "mango" -> "exotic fruit", "fruit" -> "owoc")
    val map2 = map.add("banana", "bananas in Costa Rica")
    println( map.find("pear").orElse(Some("Not found") ))
    println("map: " + map)

    println("map2: " + map2)
  }
}

object Maps {
  def main(args : Array[String]) : Unit = {


    //val m = Map(2->3, 4->5, 6->7)
    val m = Map1(2->3, 4->5, 6->7)
    println(m)
  }
}