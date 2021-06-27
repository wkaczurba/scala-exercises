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

