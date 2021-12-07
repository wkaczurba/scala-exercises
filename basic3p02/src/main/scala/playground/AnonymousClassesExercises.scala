package playground

object AnonymousClassesExercises {

  trait MyPredicate[-T] {
    def test(t: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(a: A): B
  }

  class MyList[T] {
    def map[B](t: MyTransformer[MyList[T], MyList[B]]): MyList[B] = { // FIXME: Proably it needs to accept [T] and not MyList[T]
      t.transform(this)
    }

    def filter(t: MyPredicate[MyList[T]]) = { // FIXME: Proably it needs to accept [T] and not MyList[T]
      t.test(this)
    }

//    def flatMap[B](transformer: MyTransformer[T, MyList[B]]): MyList[B] = {
//
//    }
  }
}


//      3.  MyList:
//        - map(transformer) => MyList
//          - filter(predicate) => MyList
//        - flatMap(transformer from A to MyList[B]) => MyList[B]
//
//      class EvenPredicate extends MyPredicate[Int]
//      class StringToIntTransformer extends MyTransformer[String, Int]
//
//      [1,2,3].map(n * 2) = [2,4,6]
//      [1,2,3,4].filter(n % 2) = [2,4]
//      [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]


    /*

    */

  /*
  Exercises:
  1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3.  MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      [1,2,3].map(n * 2) = [2,4,6]
      [1,2,3,4].filter(n % 2) = [2,4]
      [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */

