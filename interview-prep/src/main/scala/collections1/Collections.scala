package collections1

object Collections {

  // https://docs.scala-lang.org/overviews/collections/overview.html

  // Traits:
  // scala.collection -> immutable Traversable -> Iterable :
  //     Seq -> IndexedSeq, LinearSeq
  //     Set -> SortedSet -> BItset,
  //     Map -> SortedMap
  // scala.collection.mutable -> mutable.
  //

  // Implementations:
  // Maps: HashMap, ListMap, TreeMap (SortedMap)
  // Set: HashSet, ListSet, TreeSet (SortedSet)
  // Seq:
  //      IndexedSeq: {Vector, NumericRange, String, Range};
  //      LinearSeq: {List,Stack, Stream, Queue}

  def main(args: Array[String]): Unit = {

    Map("x" -> 24, "y" -> 25, "z" -> 26)
    List(1, 2, 3)
      // TODO: fold, foldRight etc.

  }

}
