Type classes
============

Type class is a design-pattern that allows you to implement ad-hoc polymorphism
in `Scala`. In some languages (like `Haskell`) type classes are embedded inside
the language syntax. In this section we will illustrate the implementation of
type classes in `Scala`.

To make the long story short: type class gives you a capacity to describe
object's behavior (by its type) without
 - changing the object's source code
 - extending that object
Let's illustrate that by the following example:

**Inheritance polymorphism**

    trait Barks {
      def bark = "Wow!"
    }

    // Barks should be added to the class
    class Dog(tag: String) extends Animal with Barks { ... }

    // somewere in your source code
    val jason = new Dog("Jason")
    println(jason.bark)

**Ad-hoc polymorphism**

    // We won't touch that dog. It doesn't bark but still bites.
    class Dog(tag: String) extends Animal

    // using simulacrum libraty that provides us with neat syntax
    @typeclass trait Barks[D] {
      def bark: String
    }

    // implementing a method for our Dog type
    implicit object DogBarking extends Barks[Dog] {
        def bark = "Wow, ladies"
    }

    val james = new Dog("James")
    // Here's the simulacrum code that
    // allowes you to use `bark` as it was part of the object
    println(james.bark)

It means that the corresponding instance of `Barks` will be chosen accordingly
with method implementation.

Type class pattern follows the "[Open-closed principle][open-closed]" -
"Open for extension, closed for modification".


Type classes in standard library
================================
Despite the fact that in `Scala` type classes are not submitted as syntactic
construct, they are widely used as a design-pattern. [Ordering][ordering] can be
a good example. Note that `Ordering` is more idiomatic than [Ordered][ordered].
`Ordering` gives you more flexibility: you can create any order you want and
apply it where you want. Implicitly or explicitly. An instance of ordering can
be passed inside a method in the following way:

    def sort [T] (list: List[T]) (implicit ord: Ordering[T]): List[T] = {
      ...
      // calling one of ordering's methods here
      ord.gt(x,y)
      ...
    }

Defining `Ordering` for arbitrary data type:

    case class Item (id: Long, name: String, size: Dimension)

    // default ordering (that's why we've made it implicit)
    implicit val byId: Ordering[Item] = Ordering.by(_.id)

    // another option
    val byName: Ordering[Item] = Ordering.by(_.name)

And now we can sort some stuff:

    sort(items) // will be sorted by id (default case)
    sort(items)(byName) // will be sorted by name

More details about `Ordered` and `Ordering` you may get [here][both-ords].


Further reading
===============
Now, when you have a basic understanding of type classes, we recommend you to
watch the video materials listed below, where you can learn how this pattern
could be implemented in `Scala`. More theory you may find [here][tc-0].
For practical part you may use [Simulacrum][simulacrum]. You can also use the
standard type-classes from `scalaz` or `cats`.

Videos
======

1. [Tutorial: Typeclasses in Scala](https://www.youtube.com/watch?v=sVMES4RZF-8)
2. [The Typeclass Pattern - An Alternative to Inheritance](https://www.youtube.com/watch?v=CCsGHPxA9E0)

[ordering]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordering.html
[ordered]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordered.html
[both-ords]: http://like-a-boss.net/2012/07/30/ordering-and-ordered-in-scala.html
[tc-0]: https://engineering.sharethrough.com/blog/2015/05/18/type-classes-for-the-java-engineer/
[open-closed]: https://en.wikipedia.org/wiki/Open/closed_principle
[simulacrum]: https://github.com/mpilquist/simulacrum

