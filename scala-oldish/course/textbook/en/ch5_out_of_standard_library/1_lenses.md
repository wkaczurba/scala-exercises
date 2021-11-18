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

