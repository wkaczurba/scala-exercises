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

