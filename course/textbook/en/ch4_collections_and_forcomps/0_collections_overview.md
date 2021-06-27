Scala Collections library
=========================

`Scala` has sophisticated collections library. It is sophisticated by it's
structure and architecture. In the next versions of `Scala` it could be changed:
`Scala` is on its way to simplification. It means that collections will be
simplified too. But for now we're talking about actual `2.12` collections, which
didn't change much since `Scala 2.8`. And yes, they were re-implemented once in
`2.8` why should they stay the same :)?

## Overview
At the beginning we would recommend you to read a brief review from Alvin
Alexander's [article][overview_1_aa]. The description here is simple enough to
get the big picture. To get better understanding we would recommend you to read
the following [article][overview_2] which will give you more detail. So, now you
learned that collections library consists of three parts:

  - `scala.collection`
  - `scala.collection.mutable`
  - `scala.collection.immutable`

`Traversable` in on the top of the hierarchy. It defines the most basic
operations that `Scala` collection has to offer. Some sources are saying that it
contains only the `foreach` method. If I were you I
[wouldn't trust][methods_from_traversable_like] them. Collections library is
using *Cake pattern*. You may read more about this pattern [here][cake_pattern]
if you wish. The essence of this pattern to increase the amount of atomic traits
to increase modularity.

Always prefer immutable collections. They are co-variant and thread-safe. If
you are using mutable collections always use `mutable` prefix. You may read
more about best practices [here][collections_best_practices].

The most notorious collection types reviewed [here][collections]. You may find
there a description of basic collection methods defined for each trait. To
learn more about any specific collection type you can use the api
[documentation][scaladoc].
The architecture of `Scala` collections is explained [here][arch].


## Arrays and Strings
In `Scala` arrays are *invariant* [collections][collections_arrays]. Strings
are collections [too][collections_strings].


## Parallel collections
Since `2.9` `Scala` supports parallel collections. You may read a book about
`Scala 2.8` that doesn't have this collections. Please keep that in mind.

A brief overview can be found [here][parallel_brief].

The detailed information about parallel collections can be found in the official
[documentation][parallel_doc].

There are two types of parallel collections:

  - [mutable][parallel_mutable]
  - [immutable][parallel_immutable]

General advice -- don't use parallel collection at all. In the most cases they
won't give you a better performance. In case where they can be useful,
considering `Spark` may be a better option.

If you are going to use parallel collections. Do not perform side-effects
operations. They can be a source of race conditions.

[overview_1_aa]: http://alvinalexander.com/scala/understanding-scala-collections-hierarchy-cookbook
[overview_2]: http://www.47deg.com/blog/adventures-with-scala-collections
[methods_from_traversable_like]: http://bit.ly/2hX38tv
[cake_pattern]: http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth
[collections_best_practices]: https://twitter.github.io/effectivescala/#Collections-Use

[arch]: http://docs.scala-lang.org/overviews/core/architecture-of-scala-collections.html
[scaladoc]: https://www.scala-lang.org/api/current/index.html

[collections]: http://docs.scala-lang.org/overviews/collections/introduction
[collections_arrays]: http://docs.scala-lang.org/overviews/collections/arrays.html
[collections_strings]: http://docs.scala-lang.org/overviews/collections/strings

[parallel_brief]: http://alvinalexander.com/scala/how-to-use-parallel-collections-in-scala-performance
[parallel_doc]: http://docs.scala-lang.org/overviews/parallel-collections/overview.html
[parallel_immutable]: https://www.scala-lang.org/api/current/index.html#scala.collection.parallel.immutable.package
[parallel_mutable]: http://www.scala-lang.org/api/current/index.html#scala.collection.parallel.mutable.package

