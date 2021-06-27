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

