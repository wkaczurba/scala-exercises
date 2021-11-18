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

