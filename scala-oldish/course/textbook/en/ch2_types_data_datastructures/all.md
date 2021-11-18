Types and enumerations
======================

For many of us enumerations are indispensable. Even if your language doesn't
support them. Somebody will reinvent that wheel again. `Scala` doesn't have
`enum`or `enumeration` keywords. That's why newcomers from `Java` and `C#` are
eager do something creative, like:

    // A real life example :)
    object Weekday {
      val Monday = 0
      ...
      val Sunday = 6
    }

And then those values will be used, if they were `Java` enumerations:

    if (weekday == Weekday.Friday) {
       stop(wearing, Tie)
    }

But what if somebody forget that the first day doesn't start with zero.
Assuming that Monday == 1:

    val sunday = 7

It's a bad design. `Scala` has a better way to define enumerable types. And
its much better that `Java` or `C#` approach.


## Our first enumeration

    sealed trait TrafficLight
    case object Green extends TrafficLight
    case object Yellow extends TrafficLight
    case object Red extends TrafficLight
    case object Broken extends TrafficLight

Now, let's get down to business...

## `case` keyword
before `object` tells us that the object can be used in pattern matching
operation. It also means that the object has predefined `equals`, `hashcode`,
serialization and default implementation of `toString` method.

## `sealed` keyword
`Scala` has `sealed` keyword. When used prevents further inheritance outside
of its compilation unit (in our case file). Why do we need this? Well, because
we will have information about all possible subtypes of given type at the
compile time, to throw an error. We can use `sealed abstract class` instead of
`trait`. The first option becomes pretty useful if you want to integrate your
code with `Java`. Trait seems more idiomatic.
Let's define the following function:

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
    }

And we will got:

    warning: match may not be exhaustive.
    It would fail on the following input: Broken
           def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
                                                            ^
    tellWhatTheLightIs: (tl: TrafficLight)Unit

We can also define recursive structures. You can use `case class` for this
purpose:

    sealed trait Tree
    case class Leaf(value: Int) extends Tree
    case class Node(l: Tree, r: Tree) extends Tree

Of course we could imagine that our traffic light will never get broken. We
don't care about an error (that will never happen :))

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
      case _ => println("Baby I Don't Care")
    }

But you *should not do it*. If you're willing to ignore an error, ask yourself
a question: "Do I know what I'm doing?" That's why we need to use a `sealed`
keyword.

That's it. We've just created an idiomatic `Scala` enumeration. Which actually
is not an enumeration. It's Algebraic Data Type or [ADT][adt-wiki].

Many types from the standard library are implemented as ADT's: List, Option,
Try and lot's of others. If you wish to learn more, follow the links from
this topic.

Further reading
===============
Don't be surprised if I say that you can read about Algebraic Data Types in
[Wikipedia][adt-wiki]. It's a decent article. There's also interesting
[blog post][scala-adt] about ADT's `Scala`. You may also find this
[presentation][scala-adt-2] pretty useful.

[adt-wiki]: https://en.wikipedia.org/wiki/Algebraic_data_type
[scala-adt]: https://gleichmann.wordpress.com/2011/01/30/functional-scala-algebraic-datatypes-enumerated-types/
[scala-adt-2]: http://tpolecat.github.io/presentations/algebraic_types.html#18

More on lists
=============

## Headshot
`Option` has its `get`, list has its `head`. It also has `init` and `tail`.
Here what you may get using those methods on empty list:

    // for empty list:
    init: java.lang.UnsupportedOperationException
    head: java.lang.NoSuchElementException
    last: java.lang.NoSuchElementException
    tail: java.lang.UnsupportedOperationException

Of course it won't happen if you always check your list for emptiness. And of
course it won't happen to you because you *always* check your lists. Right?
Or maybe you have a list that won't be empty because it was created that way.

Calling `list.head` its sidekicks -- is the best way to perform head-shot on
yourself.

> Do whatever possible to avoid calling list.head, list.tail and others

Calling `headOption` is much better that using `head`. Of course if you don't
mind having a redundant container. `lastOption` behaves better. If you're
somehow bound to indexes, using `isDefinedAt` that accepts integral index as its
parameter may also help. All written about assumes checks that *could not
happen*. You may also find a thousand reasons to intentionally avoid those
checks. There's an idiomatic way of dealing with this issue: use pattern
matching. List is an Algebraic Data Type, so you won't miss Nil. You
can pattern match list items, so there no need to call `head/tail` explicitly:

    def printRec(list: List[String]): Unit = list match {
      case Nil  => ()
      case x:xs => println(x)
                   printRec(xs)
    }

*Performance of lined lists*
> Scala's List that corresponds to `scala.collection.immutable.List` is a
> simple linked list. Adding a new value to the head of list is the cheapest
> operation that has algorithmic complexity O(1). If you're going to write at
> the end of that list, the complexity will be O(n). Please, keep that in mind.

## Optional list
I do read a lot of code that was created by `Scala` newbies. And I often see
the following anti-pattern, where it could be avoided: `Option[List[A]]`. And
every time I see it, I ask the writer to explain himself. List is like a single
element Option. It can also be empty. There's no need for additional container.

## ::Nil
Previously we discussed the following way to construct a list:

    val mylist = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

`::` is not a built-in operator. Its the name of class/method that is usually
called `Cons`. Moreover `::` is one of the rarest kind of operators that have
right-associative. That's why when we have `Nil` at the end of the construct
other `::`s will be appropriate because we've already created a list.
You may get more details from `Programming in scala` book. Furthermore `Nil` is
more idiomatic than `List.empty` or `List()`.

    scala> println (Nil == List())
    true

    scala> println (Nil eq List())
    true

    scala> println (Nil equals List())
    true

    scala> System.identityHashCode(Nil)
    374527572

    scala> System.identityHashCode(List())
    374527572


Further reading
===============
You may learn more about lists and other collections on the
[Twitter Scala School](tschool-col) website. Alvin Alexander wrote a bunch of
handy tutorials about lists. You may find them [here](aal1) [here](aal2) and
[here](aal3). Official [documentation](list-doc) is always useful. AN empty list
can be described with various notations, more info [here](empty-list)

[tschool-col]: https://twitter.github.io/scala_school/collections.html
[aal1]: http://alvinalexander.com/scala/scala-list-class-examples
[aal2]: http://alvinalexander.com/scala/how-create-scala-list-range-fill-tabulate-constructors
[aal3]: http://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable
[list-doc]: http://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[empty-list]: http://stackoverflow.com/questions/5981850/scala-nil-vs-list

Options
=======
Many of you are already familiar with `Optional` from `Java`. And I won't be
surprised if you already used it. `Scala`'s `Option` does the same things. Some
`Java` developers who are not familiar with `Java 8` may know `Optional` from
`Google`'s `Guave`

This container type is usually used in avoidance of `null` that leads to
`NullPointerException`. `Optional` has a couple of notorious methods: `isEmpty`
and `nonEmpty`. The first one corresponds to `isPresent` from `Guava`. And we
still have people who are doing `Option`s wrong.

Wrong usage of `Option` is very common issue. **At first** it's a **concept**
which tells that object may not be present. It's not about running out of
`NPE`. Yes `NullPointerException` is a serious issue. But it doesn't stay behind
that. Some folks are inventing their own language to evade
`NullPointerException`. But, let's go back to the correct usage of `Option` in
`Scala`:

    if (option.isEmpty)
      default
    else
      // may blow with NoSuchElementException (without check)
      option.get

Yes we do perform the check, and it's not going to blow. But trust me, you may
forgot to check the container and then call `get`. There's another case: you
have a check, but the conditional statement is complicated enough to be
incorrect. And even unit tests may lie. Some of your colleagues may tend to
tailor the unit tests for the code. We can rewrite the code above in a shorter
and cleaner way:

    option getOrElse default

It's much easier to spot an error or bug in lesser amount of code. There's also
`orElse` method that allows you to chain `Option` calls.

Frequently you need to transform a value inside an `Option`, of course if its
value is present. There's a method which is called `map`. It extracts a value
from the `Option` performs transformation, and pushes it back to the container.

    val messageOpt = Some("Hello")
    val updMessageOpt = messageOpt.map(msg => "$mgs cruel world!")

    updMessageOpt: Option[String]

But sometimes we may have something like this:

    val messageOptOpt = Some(Some("Hello"))

`Option`s could be tremendously nested, and you have to deal with it pretty
often. `FlatMap` or `flatten` solve that problem. The first one works like `map`
but it `flatten`s the resulting data structure. It will return the transformed
value wrapped inside a single `Option`. The second one simply eliminates option
nesting

    val updMessageOptOpt = messageOptOpt.flatMap(msg => "$mgs cruel world!")
    res0: updMessageOptOpt: Option[String]

    messageOptOpt.flatten == Some("Hello")
    res1: Option[String] = Some(Hello)

`Scala` has another mechanism that drastically eases work with `Option` and it
is called `for comprehension`

    val ox = Some(1)
    val oy = Some(2)
    val oz = Some(3)

    for (x <- ox; y <- oy; z <- oz)
      yield x + y + z

    // res0: Option[Int] = 6

If at least one of those `Options` equals `None` you will get None. If the same
applied to lists, you will get `Nil` as the result.

And do whatever is possible to avoid direct `get` call. It may lead to potential
problems.


Further reading
===============
 - [Here](opt-guide) you may read about a proper usage of `Option`.
 - Perhaps you may not understand even a 20% of [this](opt-video-1) talk, if so
   come back a later when you will have better understanding of `Scala`.
 - A [cheat sheet](opt-cheat-sheet) that might be useful.

[opt-guide]: http://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html
[opt-video-1]: https://www.youtube.com/watch?v=gVXt1RG_yN0
[opt-cheat-sheet]: http://blog.tmorris.net/posts/scalaoption-cheat-sheet/

Tuples
======
Tuples are amazing feature of functional languages (and some imperative,
including python). In functional languages tuples often used as record types.
You may create a tuple with required fields and then wrap it using mechanism
similar `newtype`in [`Haskell`](haskell_newtype). In functional languages you
can not avoid using tuples. They are good in `dictionaries`.
[Convolution](convolution) won't be also possible without them.

`Scala` is an object-oriented. For those who don't agree with me: `Function1` is
and object that represents a function. In `Scala` everything is an object.
`Case class`es are pretty useful and in the most situations they are better than
tuples. Even `case class` represents it's own type.

But some-time object-oriented people have a need to use tuples. The main issue:
tuples are not aliased.

> If tuple is not intended to be anonymous -- it must be named

It's a common practice to use type aliasing for tuples:

    type Point = (Double, Double)

After that you may reference your tuple by alias to avoid weird things:

    // bad!
    def drawLine(x: (Double, Double), y: (Double, Double)): Line = ???

    // way better
    def drawLine(x: Point, y: Point): Line = ???


Each tuple element in `Scala` can be called by their index:

    // awful!
    val y = point._2 // второй элемент

That looks pretty ugly when you work with collections:

    // that's ugly
    points foreach { point: Point =>
      println(s"x: ${point._1}, y: ${point._2}")
    }

And it's not a proper way of doing things. But there are some exceptional cases,
that improve readability:

    // makes sense
    rows.groupBy(_._2)

But in most of the cases underscored syntax should not be used. It's even better
to forget about it, than use it. Scala has more natural ways of dealing with
tuples.

> You can always avoid calling pair._2, so do it.

To understand why tuples behave as they are, let's take a closer look to other
functional languages.

Question: why tuple indexes in `Scala` start with 1, when lists start with 0.
Answer: History. To access a tuple element in `SML` you should use `#1` and `#2`
[functions](tuples_in_sml). In `Haskell` you should use `fst` and `snd`.

    -- Haskell doesn't use parentheses for funcito argument.
    fst tuple

But there's no function that gets the third or fifth element of the tuple? Look
[here](tuples_in_haskell) if you don't believe me. You may not believe me if I
say that pattern matching is the *most natural* way to access a tuple. And not
only in `Haskell`.

**Ocaml**

    let third (_, _, elem) = elem

**Erlang**

    1> Tuple = {1,3,4}.
    {1,3,4}

    2> Third = fun ({_Fst, _Snd, Thrd}) -> Thrd end.
    #Fun<erl_eval.6.50752066>

    3> Third(Tuple).
    4

**Python**
It's not a functional language, but knows the trick:

    >> (ip, hostname) = ("127.0.0.1", "localhost")
    >>> ip
    '127.0.0.1'
    >>> hostname
    'localhost'
    >>>

And now let's apply our knowledge to **Scala**

    // assume that we have a rectangle
    trait Rectangle {
      def topLeft: Point
      ...
    }

    // pattern matched on binding
    val (x0, y0) = rectangle.topLeft

    // pattern matched inside lambda expression:
    points foreach { case (x, y) =>
      println(s"x: ${x}, y: ${y}")
    }

You can also use traditional mechanism using `match` keyword.

> You can use a tuple as an anonymous storage. And sometimes it wise enough.

The problem is: many functional languages allow you to have a pattern function
signatures:

    -- some code in haskell
    -- defining function type
    map :: (a -> b) -> [a] -> [b]

    -- here you may see function signature's pattern matching

    -- if our argument is an empty list return itself:

    -- using x:xs to represent list is more idiomatic, but I think if you
    -- are not familiar with haskell head:tail will be better.
    -- : - is cons operator similar to Scala's ::
    map fun (head:tail) = fun head : map fun tail

You can do it the same way in `SML` and in `Erlang`. `Scala` doesn't have this
feature. Here tuples come pretty handy. The cost is additional memory allocated
for tuple object:

    // pretty haskellish
    def map [A, B] (f: A => B, l: List[A]): List[B] = (f, l) match {
        case (f, Nil) => List.empty
        case (f, head::tail) => f(head) :: map(f, tail)
    }

Sometimes we need to update one or couple of tuple elements. You can use `copy`
method to do the job:

    val dog = ("Rex", 13)
    val olderDog = tuple.copy(_2 = 14)


Further reading
===============
A [chapter][scala-wiki-tuples] `Scala` wiki-book that demonstrates tuples.

[scala-wiki-tuples]: https://en.wikibooks.org/wiki/Scala/Tuples
[convolution]: https://en.wikipedia.org/wiki/Convolution_(computer_science)
[tuples_in_haskell]: http://stackoverflow.com/questions/15558278/how-to-get-nth-element-from-a-10-tuple-in-haskell
[tuples_in_sml]: http://www.cs.cornell.edu/courses/cs312/2004fa/lectures/lecture3.htm
[haskell_newtype]: https://wiki.haskell.org/Newtype

Types and enumerations
======================

For many of us enumerations are indispensable. Even if your language doesn't
support them. Somebody will reinvent that wheel again. `Scala` doesn't have
`enum`or `enumeration` keywords. That's why newcomers from `Java` and `C#` are
eager do something creative, like:

    // A real life example :)
    object Weekday {
      val Monday = 0
      ...
      val Sunday = 6
    }

And then those values will be used, if they were `Java` enumerations:

    if (weekday == Weekday.Friday) {
       stop(wearing, Tie)
    }

But what if somebody forget that the first day doesn't start with zero.
Assuming that Monday == 1:

    val sunday = 7

It's a bad design. `Scala` has a better way to define enumerable types. And
its much better that `Java` or `C#` approach.


## Our first enumeration

    sealed trait TrafficLight
    case object Green extends TrafficLight
    case object Yellow extends TrafficLight
    case object Red extends TrafficLight
    case object Broken extends TrafficLight

Now, let's get down to business...

## `case` keyword
before `object` tells us that the object can be used in pattern matching
operation. It also means that the object has predefined `equals`, `hashcode`,
serialization and default implementation of `toString` method.

## `sealed` keyword
`Scala` has `sealed` keyword. When used prevents further inheritance outside
of its compilation unit (in our case file). Why do we need this? Well, because
we will have information about all possible subtypes of given type at the
compile time, to throw an error. We can use `sealed abstract class` instead of
`trait`. The first option becomes pretty useful if you want to integrate your
code with `Java`. Trait seems more idiomatic.
Let's define the following function:

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
    }

And we will got:

    warning: match may not be exhaustive.
    It would fail on the following input: Broken
           def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
                                                            ^
    tellWhatTheLightIs: (tl: TrafficLight)Unit

We can also define recursive structures. You can use `case class` for this
purpose:

    sealed trait Tree
    case class Leaf(value: Int) extends Tree
    case class Node(l: Tree, r: Tree) extends Tree

Of course we could imagine that our traffic light will never get broken. We
don't care about an error (that will never happen :))

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
      case _ => println("Baby I Don't Care")
    }

But you *should not do it*. If you're willing to ignore an error, ask yourself
a question: "Do I know what I'm doing?" That's why we need to use a `sealed`
keyword.

That's it. We've just created an idiomatic `Scala` enumeration. Which actually
is not an enumeration. It's Algebraic Data Type or [ADT][adt-wiki].

Many types from the standard library are implemented as ADT's: List, Option,
Try and lot's of others. If you wish to learn more, follow the links from
this topic.

Further reading
===============
Don't be surprised if I say that you can read about Algebraic Data Types in
[Wikipedia][adt-wiki]. It's a decent article. There's also interesting
[blog post][scala-adt] about ADT's `Scala`. You may also find this
[presentation][scala-adt-2] pretty useful.

[adt-wiki]: https://en.wikipedia.org/wiki/Algebraic_data_type
[scala-adt]: https://gleichmann.wordpress.com/2011/01/30/functional-scala-algebraic-datatypes-enumerated-types/
[scala-adt-2]: http://tpolecat.github.io/presentations/algebraic_types.html#18

More on lists
=============

## Headshot
`Option` has its `get`, list has its `head`. It also has `init` and `tail`.
Here what you may get using those methods on empty list:

    // for empty list:
    init: java.lang.UnsupportedOperationException
    head: java.lang.NoSuchElementException
    last: java.lang.NoSuchElementException
    tail: java.lang.UnsupportedOperationException

Of course it won't happen if you always check your list for emptiness. And of
course it won't happen to you because you *always* check your lists. Right?
Or maybe you have a list that won't be empty because it was created that way.

Calling `list.head` its sidekicks -- is the best way to perform head-shot on
yourself.

> Do whatever possible to avoid calling list.head, list.tail and others

Calling `headOption` is much better that using `head`. Of course if you don't
mind having a redundant container. `lastOption` behaves better. If you're
somehow bound to indexes, using `isDefinedAt` that accepts integral index as its
parameter may also help. All written about assumes checks that *could not
happen*. You may also find a thousand reasons to intentionally avoid those
checks. There's an idiomatic way of dealing with this issue: use pattern
matching. List is an Algebraic Data Type, so you won't miss Nil. You
can pattern match list items, so there no need to call `head/tail` explicitly:

    def printRec(list: List[String]): Unit = list match {
      case Nil  => ()
      case x:xs => println(x)
                   printRec(xs)
    }

*Performance of lined lists*
> Scala's List that corresponds to `scala.collection.immutable.List` is a
> simple linked list. Adding a new value to the head of list is the cheapest
> operation that has algorithmic complexity O(1). If you're going to write at
> the end of that list, the complexity will be O(n). Please, keep that in mind.

## Optional list
I do read a lot of code that was created by `Scala` newbies. And I often see
the following anti-pattern, where it could be avoided: `Option[List[A]]`. And
every time I see it, I ask the writer to explain himself. List is like a single
element Option. It can also be empty. There's no need for additional container.

## ::Nil
Previously we discussed the following way to construct a list:

    val mylist = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

`::` is not a built-in operator. Its the name of class/method that is usually
called `Cons`. Moreover `::` is one of the rarest kind of operators that have
right-associative. That's why when we have `Nil` at the end of the construct
other `::`s will be appropriate because we've already created a list.
You may get more details from `Programming in scala` book. Furthermore `Nil` is
more idiomatic than `List.empty` or `List()`.

    scala> println (Nil == List())
    true

    scala> println (Nil eq List())
    true

    scala> println (Nil equals List())
    true

    scala> System.identityHashCode(Nil)
    374527572

    scala> System.identityHashCode(List())
    374527572


Further reading
===============
You may learn more about lists and other collections on the
[Twitter Scala School](tschool-col) website. Alvin Alexander wrote a bunch of
handy tutorials about lists. You may find them [here](aal1) [here](aal2) and
[here](aal3). Official [documentation](list-doc) is always useful. AN empty list
can be described with various notations, more info [here](empty-list)

[tschool-col]: https://twitter.github.io/scala_school/collections.html
[aal1]: http://alvinalexander.com/scala/scala-list-class-examples
[aal2]: http://alvinalexander.com/scala/how-create-scala-list-range-fill-tabulate-constructors
[aal3]: http://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable
[list-doc]: http://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[empty-list]: http://stackoverflow.com/questions/5981850/scala-nil-vs-list

Options
=======
Many of you are already familiar with `Optional` from `Java`. And I won't be
surprised if you already used it. `Scala`'s `Option` does the same things. Some
`Java` developers who are not familiar with `Java 8` may know `Optional` from
`Google`'s `Guave`

This container type is usually used in avoidance of `null` that leads to
`NullPointerException`. `Optional` has a couple of notorious methods: `isEmpty`
and `nonEmpty`. The first one corresponds to `isPresent` from `Guava`. And we
still have people who are doing `Option`s wrong.

Wrong usage of `Option` is very common issue. **At first** it's a **concept**
which tells that object may not be present. It's not about running out of
`NPE`. Yes `NullPointerException` is a serious issue. But it doesn't stay behind
that. Some folks are inventing their own language to evade
`NullPointerException`. But, let's go back to the correct usage of `Option` in
`Scala`:

    if (option.isEmpty)
      default
    else
      // may blow with NoSuchElementException (without check)
      option.get

Yes we do perform the check, and it's not going to blow. But trust me, you may
forgot to check the container and then call `get`. There's another case: you
have a check, but the conditional statement is complicated enough to be
incorrect. And even unit tests may lie. Some of your colleagues may tend to
tailor the unit tests for the code. We can rewrite the code above in a shorter
and cleaner way:

    option getOrElse default

It's much easier to spot an error or bug in lesser amount of code. There's also
`orElse` method that allows you to chain `Option` calls.

Frequently you need to transform a value inside an `Option`, of course if its
value is present. There's a method which is called `map`. It extracts a value
from the `Option` performs transformation, and pushes it back to the container.

    val messageOpt = Some("Hello")
    val updMessageOpt = messageOpt.map(msg => "$mgs cruel world!")

    updMessageOpt: Option[String]

But sometimes we may have something like this:

    val messageOptOpt = Some(Some("Hello"))

`Option`s could be tremendously nested, and you have to deal with it pretty
often. `FlatMap` or `flatten` solve that problem. The first one works like `map`
but it `flatten`s the resulting data structure. It will return the transformed
value wrapped inside a single `Option`. The second one simply eliminates option
nesting

    val updMessageOptOpt = messageOptOpt.flatMap(msg => "$mgs cruel world!")
    res0: updMessageOptOpt: Option[String]

    messageOptOpt.flatten == Some("Hello")
    res1: Option[String] = Some(Hello)

`Scala` has another mechanism that drastically eases work with `Option` and it
is called `for comprehension`

    val ox = Some(1)
    val oy = Some(2)
    val oz = Some(3)

    for (x <- ox; y <- oy; z <- oz)
      yield x + y + z

    // res0: Option[Int] = 6

If at least one of those `Options` equals `None` you will get None. If the same
applied to lists, you will get `Nil` as the result.

And do whatever is possible to avoid direct `get` call. It may lead to potential
problems.


Further reading
===============
 - [Here](opt-guide) you may read about a proper usage of `Option`.
 - Perhaps you may not understand even a 20% of [this](opt-video-1) talk, if so
   come back a later when you will have better understanding of `Scala`.
 - A [cheat sheet](opt-cheat-sheet) that might be useful.

[opt-guide]: http://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html
[opt-video-1]: https://www.youtube.com/watch?v=gVXt1RG_yN0
[opt-cheat-sheet]: http://blog.tmorris.net/posts/scalaoption-cheat-sheet/

