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

