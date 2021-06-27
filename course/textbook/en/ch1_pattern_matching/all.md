Case classes & Case objects
===========================

From the previous chapter you learned what `objects` and `classes` are.
`Scala` has a special form of classes that supports [pattern matching][pm-wiki].
You will learn more about pattern matching further this chapter.

You may consider `case clases` as a records in functional languages. Or you
may treat them as immutable java beans. That's how they look like:

    case class Monster(health: Int = 100,
                       wearpon: Wearpon = Claws,
                       name: String)

    val richard = Monster(250, Handgun, "Richard")


An instance of given class can be used for pattern matching. It has `equals` and
`hashcode` defined. It also supports serialization and contains predefined
`toString`. It is also a subtype of trait `Product`. Case objects behave the
same way (except that they don't accept input parameters).

There's a companion object for each `case class`. The companion contains `apply`
method. That's why you don't need `new` keyword to create an instance of a
`case class`.

Every `case class` has an amazing `copy` method. Many of us created a countless
`update` methods instead of using builtin method.

Further reading
===============
More about `case class` you can read [here][case-class] and
[here][case-class-tutor] (more detailed)

[pm-wiki]: https://en.wikipedia.org/wiki/Pattern_matching
[case-class]: https://twitter.github.io/scala_school/basics2.html#caseclass
[case-class-tutor]: http://docs.scala-lang.org/tutorials/tour/case-classes.html

Another word about assignment
=============================
Let's get back to the assignment operator. It's not that simple as we
previously thought:

    // it was a simple variable binding:
    // no alarms and no surprises
    scala> val address = ("localhost", 80)
    address: (String, Int) = (localhost,80)

    scala> val (host, port) = address
    host: String = localhost
    port: Int = 80

We've just decomposed the tuple, and bund their elements to the variables, but
you can do more:

    scala> val first::rest = List(1,2,3,4,5)
    first: Int = 1
    rest: List[Int] = List(2, 3, 4, 5)

You can do the same with case classes:

    case class Person(name: String, age: Int)

    val max = Person("Max", 36)
    // max: Person = Person(Max,36)

    val Person(n, a) = max
    // n: String = Max
    // a: Int = 36

And even more than that:

    scala> val p @ Person(n, a) = max
    // p: Person = Person(Max,36)
    // n: String = Max
    // a: Int = 36

In the last case you bound the whole record to variable named `p`.
By name `n` we will get the name of the person, the same as `p.name`.
By name `a` we will get the age. The same as `p.age`.

As you may see assignment is not that simple as you may see. The same
functionality is implemented in other languages, like `Erlang` or `Python`.

Pattern matching
================

## Data construction and decomposition

### Data construction
In previous chapter we looked at the different ways of list creation. We used
cons operator `::` to create a list. But we also used a very unusual construct
that doesn't employ `new` keyword. It was definitely not a constructor:

    val myArray = Array(2100, 2099)
    val myList = List(1,2,3,4,5,6,7,8,9)

And yes, it's not a constructor. It's a method call. There's an object (you may
think of them as built-in singletons) that is called `Array`and that object
has a method that is called `apply`. This method is also defined for functions,
because in `Scala` functions are objects too.

    // the same as above
    val myArray = Array.apply(2100, 2099)

More about the `apply` method you may find in [Twitter Scala School][apply]

### Data extraction
There is a couple of methods that called `unapply` and `unapplySeq`. You can
read more about extractors [here][unapply].


## Pattern matching
Pattern matching is widely used in functional languages. It allows you to extract
the data from compound object, by having an information about their structure.

From this [video][video-tutorial] you will learn a lot about construction and
deconstruction of data, you will also learn about pattern matching. We recommend
you to watch this video because it rocks. We also recommend you
[this][pm-tutor] tutorial.

As you may have noticed the assignment operator does the pattern matching.

`Scala` has a syntactic sugar for pattern matching that works inside a function
body:

    // before
    list.filter(item => item match {
      case phone: Cellphone => true
      case _ => false
    }

    // after
    list.filter {
      case phone: Cellphone => true
      case _ => false
    }

You can use pattern matching inside any `Function1`'s body. `Scala` will compile
this operation to `PartialFunction` instance, which is a subtype of `Funciton`.

You can also use pattern matching for regular expressions. You may find
more details [here][pm-regex].

[video-tutorial]: https://www.youtube.com/watch?v=1vxIRkYZfmc

[apply]: https://twitter.github.io/scala_school/basics2.html#apply
[unapply]: http://docs.scala-lang.org/tutorials/tour/extractor-objects.html
[pm-tutor]: http://docs.scala-lang.org/tutorials/tour/pattern-matching
[pm-regex]: https://www.scala-lang.org/api/2.12.x/scala/util/matching/Regex.html

Exception handling
==================

`Scala` has a very similar way of the exception production and handling. In
`Scala`, all exceptions are [unchecked][unchecked-ex].

You may read more about the exception handling in Jacob Jenkov's
[blog][jenkov-ex], where the traditional (imperative) approach is illustrated
nicely.

`Scala` also supports functional ways of exception handling, and we will come
back to them at the end of the course.

[unchecked-ex]: http://crunchify.com/better-understanding-on-checked-vs-unchecked-exceptions-how-to-handle-exception-better-way-in-java/
[jenkov-ex]: http://tutorials.jenkov.com/scala/exception-try-catch-finally.html

Case classes & Case objects
===========================

From the previous chapter you learned what `objects` and `classes` are.
`Scala` has a special form of classes that supports [pattern matching][pm-wiki].
You will learn more about pattern matching further this chapter.

You may consider `case clases` as a records in functional languages. Or you
may treat them as immutable java beans. That's how they look like:

    case class Monster(health: Int = 100,
                       wearpon: Wearpon = Claws,
                       name: String)

    val richard = Monster(250, Handgun, "Richard")


An instance of given class can be used for pattern matching. It has `equals` and
`hashcode` defined. It also supports serialization and contains predefined
`toString`. It is also a subtype of trait `Product`. Case objects behave the
same way (except that they don't accept input parameters).

There's a companion object for each `case class`. The companion contains `apply`
method. That's why you don't need `new` keyword to create an instance of a
`case class`.

Every `case class` has an amazing `copy` method. Many of us created a countless
`update` methods instead of using builtin method.

Further reading
===============
More about `case class` you can read [here][case-class] and
[here][case-class-tutor] (more detailed)

[pm-wiki]: https://en.wikipedia.org/wiki/Pattern_matching
[case-class]: https://twitter.github.io/scala_school/basics2.html#caseclass
[case-class-tutor]: http://docs.scala-lang.org/tutorials/tour/case-classes.html

Another word about assignment
=============================
Let's get back to the assignment operator. It's not that simple as we
previously thought:

    // it was a simple variable binding:
    // no alarms and no surprises
    scala> val address = ("localhost", 80)
    address: (String, Int) = (localhost,80)

    scala> val (host, port) = address
    host: String = localhost
    port: Int = 80

We've just decomposed the tuple, and bund their elements to the variables, but
you can do more:

    scala> val first::rest = List(1,2,3,4,5)
    first: Int = 1
    rest: List[Int] = List(2, 3, 4, 5)

You can do the same with case classes:

    case class Person(name: String, age: Int)

    val max = Person("Max", 36)
    // max: Person = Person(Max,36)

    val Person(n, a) = max
    // n: String = Max
    // a: Int = 36

And even more than that:

    scala> val p @ Person(n, a) = max
    // p: Person = Person(Max,36)
    // n: String = Max
    // a: Int = 36

In the last case you bound the whole record to variable named `p`.
By name `n` we will get the name of the person, the same as `p.name`.
By name `a` we will get the age. The same as `p.age`.

As you may see assignment is not that simple as you may see. The same
functionality is implemented in other languages, like `Erlang` or `Python`.

Pattern matching
================

## Data construction and decomposition

### Data construction
In previous chapter we looked at the different ways of list creation. We used
cons operator `::` to create a list. But we also used a very unusual construct
that doesn't employ `new` keyword. It was definitely not a constructor:

    val myArray = Array(2100, 2099)
    val myList = List(1,2,3,4,5,6,7,8,9)

And yes, it's not a constructor. It's a method call. There's an object (you may
think of them as built-in singletons) that is called `Array`and that object
has a method that is called `apply`. This method is also defined for functions,
because in `Scala` functions are objects too.

    // the same as above
    val myArray = Array.apply(2100, 2099)

More about the `apply` method you may find in [Twitter Scala School][apply]

### Data extraction
There is a couple of methods that called `unapply` and `unapplySeq`. You can
read more about extractors [here][unapply].


## Pattern matching
Pattern matching is widely used in functional languages. It allows you to extract
the data from compound object, by having an information about their structure.

From this [video][video-tutorial] you will learn a lot about construction and
deconstruction of data, you will also learn about pattern matching. We recommend
you to watch this video because it rocks. We also recommend you
[this][pm-tutor] tutorial.

As you may have noticed the assignment operator does the pattern matching.

`Scala` has a syntactic sugar for pattern matching that works inside a function
body:

    // before
    list.filter(item => item match {
      case phone: Cellphone => true
      case _ => false
    }

    // after
    list.filter {
      case phone: Cellphone => true
      case _ => false
    }

You can use pattern matching inside any `Function1`'s body. `Scala` will compile
this operation to `PartialFunction` instance, which is a subtype of `Funciton`.

You can also use pattern matching for regular expressions. You may find
more details [here][pm-regex].

[video-tutorial]: https://www.youtube.com/watch?v=1vxIRkYZfmc

[apply]: https://twitter.github.io/scala_school/basics2.html#apply
[unapply]: http://docs.scala-lang.org/tutorials/tour/extractor-objects.html
[pm-tutor]: http://docs.scala-lang.org/tutorials/tour/pattern-matching
[pm-regex]: https://www.scala-lang.org/api/2.12.x/scala/util/matching/Regex.html

Exception handling
==================

`Scala` has a very similar way of the exception production and handling. In
`Scala`, all exceptions are [unchecked][unchecked-ex].

You may read more about the exception handling in Jacob Jenkov's
[blog][jenkov-ex], where the traditional (imperative) approach is illustrated
nicely.

`Scala` also supports functional ways of exception handling, and we will come
back to them at the end of the course.

[unchecked-ex]: http://crunchify.com/better-understanding-on-checked-vs-unchecked-exceptions-how-to-handle-exception-better-way-in-java/
[jenkov-ex]: http://tutorials.jenkov.com/scala/exception-try-catch-finally.html

