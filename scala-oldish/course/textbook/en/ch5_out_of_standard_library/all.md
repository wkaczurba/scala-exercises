Heterogeneous lists
===================

In this section we will talk about the most an promising data structure that
will be included in `Dotty` and further versions of `Scala`.
[The first originated][hlists-haskell] in 2004 as `Haskell` library for
heterogeneous lists. They are also implemented in `Shapeless` -- a library that
was created by Miles Sabin for generic programming in `Scala`. It is used
inside `Parboiled`, `Finch`, and [other][built-with-shapeless] libraries.

`HList` - is a data structure that can be thought as a hybrid of linked list
and tuple. Here's the example:

    // ordinary linked list
    val list = "i" :: "love" :: "cookies" :: Nil

    // HList
    import shapeless._

    val hlist = "i" :: 'ate :: 8 :: "cookies" :: HNil

[Here][hlist-tutorial] you may find a good tutorial about `HLists`.

In `HList` some errors do appear at during the compile time

    scala> ("head" :: HNil).head
    res0: String = head

    // Compile time error
    scala> HNil.head
    error: could not find implicit value for parameter c:
    shapeless.ops.hlist.IsHCons[shapeless.HNil.type] HNil.head

Compared to List.head?

    // Runtime exception
    scala> Nil.head
    java.util.NoSuchElementException: head of empty list
      at scala.collection.immutable.Nil$.head(List.scala:417)
      ... 29 elided

Which one you think is better?
You may also find more information about `HList` architecture
[here][hlist-tutorial-2].


Tuples on steroids
==================
`Shapeless` allows standard `Scala` tuples to be manipulated in exactly the same
ways as `HList`. All you need to do is add the following implicit to your scope:

    import syntax.std.tuple._

And now:

    scala> ("life", 'begins, "at", 40).tail
    res1: (Symbol, String, Int) = ('begins, "at", 40)

More about using `Scala` tuples as `HList`s you can read
[here][tuples-as-hlists].


Further reading
===============
You may also look at the [documentation][old-doc]. It may be a little bit
outdated. But it will be enough to get the concept. There's a table of functions
that supported by `HList` you may find [here][hlist-fun-table].

[hlists-haskell]: http://hackage.haskell.org/package/HList
[hlist-builders]: http://ivanyu.me/blog/2016/01/11/type-safe-query-builders-in-scala-revisited-shapeless/
[built-with-shapeless]: https://github.com/milessabin/shapeless/wiki/Built-with-shapeless
[hlist-tutorial]: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/
[hlist-tutorial-2]: http://akmetiuk.com/blog/2016/09/30/dissecting-shapeless-hlists.html
[tuples-as-hlists]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#hlist-style-operations-on-standard-scala-tuples
[hlist-fun-table]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.1.0#operations-on-hlistsrecordscoproductsunionstuplesproducts
[old-doc]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#heterogenous-lists

Lenses
======
You can define sophisticated structures by nesting case-classes. And you will.
But sooner or later you will have a need to change a content of that structure.
Here's the simplest case possible:

    case class Dog (name: String, age: Int)

    val fido = Dog("Fido", 6)
    val olderFido = fido.copy(age = 7)

But what will you do in the following case?

    case class Address(street: String,
                       city: String,
                       postcode: String)

    case class Person(name: String, age: Int, address: Address)

Don't you think that manual object construction and deconstruction for
each specific is not an appropriate way of dealing with this issue?
Here lenses become useful.

Why those things were named that way? Because they can focus on something
really important. In our case on the specific part of your data structure.
As the result you will have an opportunity to update that structure.

Let's say you have a person who become a one year older. How we could
react on this change?

    val person = Person("Joe Grey", 37,
                        Address("Southover Street",
                                "Brighton", "BN2 9UA"))

    // Read a field
    val age1 = ageLens.get(person) // Type inferred is Int
    // age1 == 37

    // Update a field
    val person2 = ageLens.set(person)(38)
    // person2.age == 38

The same actions could be applied to an address field. As you have noticed
it's pretty simple. Unfortunately `Scala` doesn't have built-in lenses, that's
why you should use an external library that will provide you with this
functionality. We propose you to use `shapeless`. The example above was
implemented with help of that library.

There are various implementations of lenses for `Scala`. You may use
[scalaz][scalazl] or [monocle][monocle] if you with. Monocle provides you
with more advanced optics than any other `Scala` library. We definitely
recommend you to use it in future.

`Shapeless` is already included in your `CLASSPATH`, that's why we recommend
you to use it.

Further reading
===============
[Here][lenses-smpl] you may find an example that demonstrates lenses in
`shapeless`

[lenses-smpl]: https://github.com/milessabin/shapeless/blob/master/examples/src/main/scala/shapeless/examples/lenses.scala
[scalazl]:  http://eed3si9n.com/learning-scalaz/Lens.html
[monocle]: https://github.com/julien-truffaut/Monocle

Implicits
=========

There is more than on entity that could be annotated with `implicit` keyword
in `Scala`. Those entities could be implicitly passed, converted or enriched
with additional functionality (aka `extension methods`).


## Types of implicits
 - Implicit parameters. You may read about them [here][impl-parameters]
 - Implicit conversions. [Here][impl-conversions] and [here][impl-conversions-2]
   you may read about them.
 - Implicit classes (can be used to provide extension methods). More information
   [here][impl-classes]. They are represented as an `implicit` classes and
   objects.


### Implicit lookup
[More][impl-lookup] about implicit lookup.


## Implicits with lambdas and currying
Play Framework has a very popular construct:

    Action { implicit request =>
      Ok("ok: [" + request + "]")
    }

Let's figure out what implicit does there. The rewritten version of code above:

    Action { request =>
      implicit val r = request
      Ok("ok: [" + request + "]")
    }

The signature of `Ok` class, may look like this:

    class Ok(message: String)(implicit r: Request).

The `implicit` keyword can be used only for *the last* curried argument.
It denotes the spot where an implicit parameter can be placed. That's how it
will look like without implicits:

    Action { request =>
      Ok("ok: [" + request + "]")(request)
    }


## Conclusion
In the most cases implicits are not what you want. This language construct was
intendend for `DSL` developers (Domain Specific Languages). Another usage is
extension-methods. There's an [article][pimp-my-lib] explaining that approach.

[pimp-my-lib]: http://www.artima.com/weblogs/viewpost.jsp?thread=179766
[impl-conversions]: http://docs.scala-lang.org/tutorials/tour/implicit-conversions
[impl-conversions-2]: http://baddotrobot.com/blog/2015/07/14/scala-implicit-functions/
[impl-parameters]: http://baddotrobot.com/blog/2015/07/03/scala-implicit-parameters/
[impl-classes]: http://docs.scala-lang.org/overviews/core/implicit-classes.html
[impl-lookup]: http://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

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

Exceptions revisited
====================
`Scala` supports various ways of exception handling. Since 2.10 `Scala` has a
`scala.util.Try`. There's another more functional class called `Either`.

[Here][Try] you may read more about `scala.util.Try`. Here's a
[review][error-handling-in-scala] of the most popular ways of exception handling
in `Scala`.

[More][scala-either] [details][scala-either-2] about `Either`.

[Try]: http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html
[error-handling-in-scala]: https://tersesystems.com/2012/12/27/error-handling-in-scala/
[scala-either]: http://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null
[scala-either-2]: http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html

Heterogeneous lists
===================

In this section we will talk about the most an promising data structure that
will be included in `Dotty` and further versions of `Scala`.
[The first originated][hlists-haskell] in 2004 as `Haskell` library for
heterogeneous lists. They are also implemented in `Shapeless` -- a library that
was created by Miles Sabin for generic programming in `Scala`. It is used
inside `Parboiled`, `Finch`, and [other][built-with-shapeless] libraries.

`HList` - is a data structure that can be thought as a hybrid of linked list
and tuple. Here's the example:

    // ordinary linked list
    val list = "i" :: "love" :: "cookies" :: Nil

    // HList
    import shapeless._

    val hlist = "i" :: 'ate :: 8 :: "cookies" :: HNil

[Here][hlist-tutorial] you may find a good tutorial about `HLists`.

In `HList` some errors do appear at during the compile time

    scala> ("head" :: HNil).head
    res0: String = head

    // Compile time error
    scala> HNil.head
    error: could not find implicit value for parameter c:
    shapeless.ops.hlist.IsHCons[shapeless.HNil.type] HNil.head

Compared to List.head?

    // Runtime exception
    scala> Nil.head
    java.util.NoSuchElementException: head of empty list
      at scala.collection.immutable.Nil$.head(List.scala:417)
      ... 29 elided

Which one you think is better?
You may also find more information about `HList` architecture
[here][hlist-tutorial-2].


Tuples on steroids
==================
`Shapeless` allows standard `Scala` tuples to be manipulated in exactly the same
ways as `HList`. All you need to do is add the following implicit to your scope:

    import syntax.std.tuple._

And now:

    scala> ("life", 'begins, "at", 40).tail
    res1: (Symbol, String, Int) = ('begins, "at", 40)

More about using `Scala` tuples as `HList`s you can read
[here][tuples-as-hlists].


Further reading
===============
You may also look at the [documentation][old-doc]. It may be a little bit
outdated. But it will be enough to get the concept. There's a table of functions
that supported by `HList` you may find [here][hlist-fun-table].

[hlists-haskell]: http://hackage.haskell.org/package/HList
[hlist-builders]: http://ivanyu.me/blog/2016/01/11/type-safe-query-builders-in-scala-revisited-shapeless/
[built-with-shapeless]: https://github.com/milessabin/shapeless/wiki/Built-with-shapeless
[hlist-tutorial]: http://enear.github.io/2016/04/05/bits-shapeless-1-hlists/
[hlist-tutorial-2]: http://akmetiuk.com/blog/2016/09/30/dissecting-shapeless-hlists.html
[tuples-as-hlists]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#hlist-style-operations-on-standard-scala-tuples
[hlist-fun-table]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.1.0#operations-on-hlistsrecordscoproductsunionstuplesproducts
[old-doc]: https://github.com/milessabin/shapeless/wiki/Feature-overview:-shapeless-2.0.0#heterogenous-lists

Lenses
======
You can define sophisticated structures by nesting case-classes. And you will.
But sooner or later you will have a need to change a content of that structure.
Here's the simplest case possible:

    case class Dog (name: String, age: Int)

    val fido = Dog("Fido", 6)
    val olderFido = fido.copy(age = 7)

But what will you do in the following case?

    case class Address(street: String,
                       city: String,
                       postcode: String)

    case class Person(name: String, age: Int, address: Address)

Don't you think that manual object construction and deconstruction for
each specific is not an appropriate way of dealing with this issue?
Here lenses become useful.

Why those things were named that way? Because they can focus on something
really important. In our case on the specific part of your data structure.
As the result you will have an opportunity to update that structure.

Let's say you have a person who become a one year older. How we could
react on this change?

    val person = Person("Joe Grey", 37,
                        Address("Southover Street",
                                "Brighton", "BN2 9UA"))

    // Read a field
    val age1 = ageLens.get(person) // Type inferred is Int
    // age1 == 37

    // Update a field
    val person2 = ageLens.set(person)(38)
    // person2.age == 38

The same actions could be applied to an address field. As you have noticed
it's pretty simple. Unfortunately `Scala` doesn't have built-in lenses, that's
why you should use an external library that will provide you with this
functionality. We propose you to use `shapeless`. The example above was
implemented with help of that library.

There are various implementations of lenses for `Scala`. You may use
[scalaz][scalazl] or [monocle][monocle] if you with. Monocle provides you
with more advanced optics than any other `Scala` library. We definitely
recommend you to use it in future.

`Shapeless` is already included in your `CLASSPATH`, that's why we recommend
you to use it.

Further reading
===============
[Here][lenses-smpl] you may find an example that demonstrates lenses in
`shapeless`

[lenses-smpl]: https://github.com/milessabin/shapeless/blob/master/examples/src/main/scala/shapeless/examples/lenses.scala
[scalazl]:  http://eed3si9n.com/learning-scalaz/Lens.html
[monocle]: https://github.com/julien-truffaut/Monocle

Implicits
=========

There is more than on entity that could be annotated with `implicit` keyword
in `Scala`. Those entities could be implicitly passed, converted or enriched
with additional functionality (aka `extension methods`).


## Types of implicits
 - Implicit parameters. You may read about them [here][impl-parameters]
 - Implicit conversions. [Here][impl-conversions] and [here][impl-conversions-2]
   you may read about them.
 - Implicit classes (can be used to provide extension methods). More information
   [here][impl-classes]. They are represented as an `implicit` classes and
   objects.


### Implicit lookup
[More][impl-lookup] about implicit lookup.


## Implicits with lambdas and currying
Play Framework has a very popular construct:

    Action { implicit request =>
      Ok("ok: [" + request + "]")
    }

Let's figure out what implicit does there. The rewritten version of code above:

    Action { request =>
      implicit val r = request
      Ok("ok: [" + request + "]")
    }

The signature of `Ok` class, may look like this:

    class Ok(message: String)(implicit r: Request).

The `implicit` keyword can be used only for *the last* curried argument.
It denotes the spot where an implicit parameter can be placed. That's how it
will look like without implicits:

    Action { request =>
      Ok("ok: [" + request + "]")(request)
    }


## Conclusion
In the most cases implicits are not what you want. This language construct was
intendend for `DSL` developers (Domain Specific Languages). Another usage is
extension-methods. There's an [article][pimp-my-lib] explaining that approach.

[pimp-my-lib]: http://www.artima.com/weblogs/viewpost.jsp?thread=179766
[impl-conversions]: http://docs.scala-lang.org/tutorials/tour/implicit-conversions
[impl-conversions-2]: http://baddotrobot.com/blog/2015/07/14/scala-implicit-functions/
[impl-parameters]: http://baddotrobot.com/blog/2015/07/03/scala-implicit-parameters/
[impl-classes]: http://docs.scala-lang.org/overviews/core/implicit-classes.html
[impl-lookup]: http://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

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

Exceptions revisited
====================
`Scala` supports various ways of exception handling. Since 2.10 `Scala` has a
`scala.util.Try`. There's another more functional class called `Either`.

[Here][Try] you may read more about `scala.util.Try`. Here's a
[review][error-handling-in-scala] of the most popular ways of exception handling
in `Scala`.

[More][scala-either] [details][scala-either-2] about `Either`.

[Try]: http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html
[error-handling-in-scala]: https://tersesystems.com/2012/12/27/error-handling-in-scala/
[scala-either]: http://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null
[scala-either-2]: http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html

