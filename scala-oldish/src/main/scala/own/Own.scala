package own

object OwnExperiment {

  // Checking:  (head :: tail ::: this) -> writing it differently.:

  val l1 : List[Int] = 1 :: 2 :: 3 :: Nil
  val l2 : List[Int] = 1 :: (2 :: 3 :: Nil)
  val l3 : List[Int] = 1 :: (2 :: (3 :: Nil))
  //val l4 : List[Int] = (1 :: 2) :: 3 :: Nil // THis will not compile, as (1 :: 2) wont produce list, second arg is not List-compatible.

  val head = 1::2::3::Nil
  val tail = 6::7::8::Nil
  val middle = 4 :: 5 :: Nil


  def main(args : Array[String]) = {

    println(l1)
    println(l2)
    println(l3)


    println(l1 == l2 && l1 == l3)

    println(head ::: middle ::: tail)
    println(1::2::3 :: middle ::: 6::7::8:: Nil)
    println(1::2::3 :: (middle ::: 6::7::8:: Nil)) // middle list + following ones is a list.
    println(1::2::(3 :: (middle ::: 6::7::8:: Nil))) // 3 :: (middle) ( element + list)
  }

}
