package own.Ch5

import shapeless._ // hmmm.. FIXME: It would be great to describe where to import shapeless from.;
 // TODO: Talk with Paul -> it would be good to start from basiscs, such as correct library ie.: https://dzone.com/articles/shapeless-monomorphic-scala-vs-polymorphic-scala
 // Looks like this is the lib: https://mvnrepository.com/artifact/com.chuusai/shapeless

object ch5_hetereogenous_lists {

  // Todo: reading + examples from here: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/
  // TODO: Reading + another good tutorial: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/


  // https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#heterogenous-lists

  // HList - part of shapeless.
  def main(args : Array[String]) = {
    //val
    val list : List[String] = "i" :: "love" :: "cookies" :: Nil

    val hlist = "i" :: "love" :: "cookies" :: HNil

    // the hlist is of weird type:
    val hlistExplicit : String :: String :: String :: shapeless.HNil = hlist
    println("hlist is one of weird type: " + hlist.getClass.toString)

    //
    println (("firstElement" :: HNil).head)
  }
}

// https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.1.0#operations-on-hlistsrecordscoproductsunionstuplesproducts

// Tuples on Steroids - p.32:
import syntax.std.tuple._

object ch5_tuples_on_steroids {


  def main(args : Array[String]) = {
    val myTuple = ("life", "begins", "at", 40)
    println(myTuple.tail)
    println(myTuple.getClass.toString) // this is "scala.Tuple4"

    // TODO: Code this one:
    //  https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#hlist-style-operations-on-standard-scala-tuples

  }
}


object ch5_Lenses {
  def main(args : Array[String]): Unit = {
    // https://github.com/ppopoff/scala-course/blob/master/textbook/en/ch5_out_of_standard_library/1_lenses.md

  }
}