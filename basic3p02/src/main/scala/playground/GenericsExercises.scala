package playground

object GenericsExercises {

  trait Animal;
  trait Cat extends Animal;

  class MyList[A] {
    def add[B >: A](num : B) : MyList[B] = {  // TOOD: Write down + memorise [B >: A]
      new MyList[B]
    }
  }

  def main(args: Array[String]): Unit = {
    val animalList : MyList[Animal] = new MyList[Animal];
    val c = new Cat() {}

    val myAnimalList2 : MyList[Animal] = animalList.add(c)
    // FIXME: cannot cast to "Cat" => why?: -> we are supposed to return MyList[B]
    //val myCatList2 : MyList[Cat] = animalList.add(c)

  }

  // Companion object:
  object MyList { // TODO: NOTE: object MyList[A] won't work here.
    def empty[A] : MyList[A] = new MyList[A]() // here we go: empty parameterized function returning parameterized myList[A]
  }

  class MyMap[Key, Value]
  val listOfIntegers = MyList.empty[Int]

  // Variance problems:
  class Flower
  class Tulip extends Flower
  class Rose extends Flower

  // 1. Variance
  class CovariantList[+A]
  val flower: Flower = new Flower
  val flowerList: CovariantList[Flower] = new CovariantList[Tulip]
  // flowerList.add(Rose) => we return roseList hmmm.... [HARD QUESTION. ?]

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantList : InvariantList[Flower] = new InvariantList[Flower]
  // These won't work as we are dealing with invariance
  //val invariantList2 : InvariantList[Tulip] = new InvariantList[Flower]
  //val invariantList3 : InvariantList[Flower] = new InvariantList[Tulip]

  // 3. CONTRAVARIANCE:
  class Trainer[-A] // ??
  val trainer : Trainer[Tulip] = new Trainer[Flower] // THis looks very odd...

  // 4. Bounded types:
  class Cage :

}
