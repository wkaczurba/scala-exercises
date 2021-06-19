package own.Ch5

// TODO: Ask Paul about this one:
//   is there any particular depndency for typeCLasses? Tried one below but it looks like it is incorrect.
//   libraryDependencies += "org.typelevel" %% "simulacrum_2.13" % "1.0.1" - cant find this dependency. ?
//
//   import simulacrum._


//p 35/36

//object ch5_failing {
//  // We won't touch that dog. It doesn't bark but still bites.
//  class Animal {}
//  class Dog(tag: String) extends Animal
//
//  // using simulacrum libraty that provides us with neat syntax
//  @typeclass trait Barks[D] { // FIXE: This fails. talk to Paul.
//    def bark: String
//  }
//
//  // implementing a method for our Dog type
//  implicit object DogBarking extends Barks[Dog] {
//    def bark = "Wow, ladies"
//  }
//
//  val james = new Dog("James")
//  // Here's the simulacrum code that
//  // allowes you to use `bark` as it was part of the object
//  println(james.bark)
//}
