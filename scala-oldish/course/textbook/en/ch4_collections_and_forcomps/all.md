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

Lazy collections [optional]
===========================

This topic is all about lazy collections in `Scala`. They are called
[`Streams`][scaladoc] which is quite ingenious this days. Please do not confuse
them with *Java 8 Streams* they are completely different. Also you should not
confuse them  (`scala.collection.immutable.Stream`) with `Akka streams` or
`Reactive Streams`. Unfortunately this word is overused not only inside `JVM`,
but inside `Scala` ecosystem.

The most important thing you should know about `Scala`'s lazy collection that
they memoize the computations in memory. That's why they can not be used to
create infinite data structures which are popular in the most functional
languages.

A good [introduction][streams-intro] to `Streams`. A part of  `Scala Cookbook`.
A very descriptive example can be found [here][streams-intro-2].
About possible issues with lazy collections in `Scala` you may read in the
[first][streams-1] and the [second][streams-2] parts of an article.

You should know about this type of collections, but I bet you wont' use it in
production. There will be no practical tasks about this topic.

[scaladoc]: http://www.scala-lang.org/api/current/scala/collection/immutable/Stream.html
[streams-intro]: http://alvinalexander.com/scala/how-to-use-stream-class-lazy-list-scala-cookbook
[streams-intro-2]: http://derekwyatt.org/2011/07/29/understanding-scala-streams-through-fibonacci/
[streams-1]: http://blog.dmitryleskov.com/programming/scala/stream-hygiene-i-avoiding-memory-leaks/
[streams-2]: http://blog.dmitryleskov.com/programming/scala/stream-hygiene-ii-hotspot-kicks-in/

For comprehensions
==================
The main thing you need to know about `list generators` aka `for comprehensions`
that they were not designed to be a loop implementation.

This syntactic construct can be used not only for iteration, you can use it for
filtering. Moreover this construct is pretty expensive for iteration. Using tail
recursion or `while` loop will be more effective.

`For comprehension` is a syntactic sugar for `map`, `flatMap` and `withFilter`
methods. A `yield` keyword is used for further data aggregation in the resulting
structure (a structure that was operated).

An amazing [video][fors-video], that will tell you all you want to know about
for comprehensions.

[fors-video]: https://www.youtube.com/watch?v=WDaw2yXAa50

Java collections interoperability
=================================

Sometimes you need to work with `Java` collections. I do often use a number of
classes from `java.util.concurrent` package like `ConcurrentHashMap` or
`BlockingQueue`. `Scala` allows you to use a standard methods from Java's
`Collections API`. `Scala`'s standard library contains a couple of objects (at
the time this topic was written) :

 - ~~[scala.collection.JavaConversions][conversions]~~
 - [scala.collection.JavaConverters][converters]

The first one was marked as deprecated in version `2.12`. **Don't use it**.
The [documentation][converters] for `JavaConverters` is pretty explanatory.

[conversions]: http://www.scala-lang.org/api/current/scala/collection/JavaConversions$.html
[converters]: http://www.scala-lang.org/api/current/scala/collection/JavaConverters$.html

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

Lazy collections [optional]
===========================

This topic is all about lazy collections in `Scala`. They are called
[`Streams`][scaladoc] which is quite ingenious this days. Please do not confuse
them with *Java 8 Streams* they are completely different. Also you should not
confuse them  (`scala.collection.immutable.Stream`) with `Akka streams` or
`Reactive Streams`. Unfortunately this word is overused not only inside `JVM`,
but inside `Scala` ecosystem.

The most important thing you should know about `Scala`'s lazy collection that
they memoize the computations in memory. That's why they can not be used to
create infinite data structures which are popular in the most functional
languages.

A good [introduction][streams-intro] to `Streams`. A part of  `Scala Cookbook`.
A very descriptive example can be found [here][streams-intro-2].
About possible issues with lazy collections in `Scala` you may read in the
[first][streams-1] and the [second][streams-2] parts of an article.

You should know about this type of collections, but I bet you wont' use it in
production. There will be no practical tasks about this topic.

[scaladoc]: http://www.scala-lang.org/api/current/scala/collection/immutable/Stream.html
[streams-intro]: http://alvinalexander.com/scala/how-to-use-stream-class-lazy-list-scala-cookbook
[streams-intro-2]: http://derekwyatt.org/2011/07/29/understanding-scala-streams-through-fibonacci/
[streams-1]: http://blog.dmitryleskov.com/programming/scala/stream-hygiene-i-avoiding-memory-leaks/
[streams-2]: http://blog.dmitryleskov.com/programming/scala/stream-hygiene-ii-hotspot-kicks-in/

For comprehensions
==================
The main thing you need to know about `list generators` aka `for comprehensions`
that they were not designed to be a loop implementation.

This syntactic construct can be used not only for iteration, you can use it for
filtering. Moreover this construct is pretty expensive for iteration. Using tail
recursion or `while` loop will be more effective.

`For comprehension` is a syntactic sugar for `map`, `flatMap` and `withFilter`
methods. A `yield` keyword is used for further data aggregation in the resulting
structure (a structure that was operated).

An amazing [video][fors-video], that will tell you all you want to know about
for comprehensions.

[fors-video]: https://www.youtube.com/watch?v=WDaw2yXAa50

Java collections interoperability
=================================

Sometimes you need to work with `Java` collections. I do often use a number of
classes from `java.util.concurrent` package like `ConcurrentHashMap` or
`BlockingQueue`. `Scala` allows you to use a standard methods from Java's
`Collections API`. `Scala`'s standard library contains a couple of objects (at
the time this topic was written) :

 - ~~[scala.collection.JavaConversions][conversions]~~
 - [scala.collection.JavaConverters][converters]

The first one was marked as deprecated in version `2.12`. **Don't use it**.
The [documentation][converters] for `JavaConverters` is pretty explanatory.

[conversions]: http://www.scala-lang.org/api/current/scala/collection/JavaConversions$.html
[converters]: http://www.scala-lang.org/api/current/scala/collection/JavaConverters$.html

Collections
===========

You should implement two types of immutable collections: 

  * immutable.List 
  * immutable.Map 

Though it may sound like a walk in a park it won't be that easy. We imposed some conditions and limitations to make it even more exciting and fun. 

How it must be done: 

 - List should be implemented as a case class inherited sealed traits 
 - Both collections should implement own apply and unapply methods 
 - Use currying aka partial applications for some high-order functions 
 - Both collections should implement map, flatMap, filter and withFilter methods, and size optimization: each call for .size or .length method must return the value in O(1). 
 - Map should use our own implementation of list for buckets.

[Here](/codebook/main/ch2/List.scala) you may find a snippet for List. You're free to use it. Map should be implemented in a similar manner

## Conditions and limitations: 

 - You're limited in the usage of if expression to its absolute elimination. Ifs are simple -- use pattern matching instead. 
 - No loops. Use recursion, create your own map/filter methods but please, do not use any loops at all, with the only exception: you can use the loop syntax if it's relying on our own implementation of map, flatMap or withFilter methods. 
 - Build this artifact as a library and publish it locally. 

## Bonus: 
 - And as a bonus you may try to embed your collection to the existing collections framework 
 - As the addtional bonus you may use ScalaCheck to test your collections. That could possibly make your testing process easier. 

And of course, your code should be tested. 


## Further reading
Try to use tail recursion when possible. The `@tailrec` annotation will help
you. You can read more about tail calls [here]

[Here][trampolines] you may read about trampolines and [here][scala-rec-fun]
about the ways they can be useful in `Scala`.

A very interesting article on [Dr. Dobbs's][tcall-opt] website. You may read
there about specifics of tail recursion implementation for JVM.

[tail-call]: https://en.wikipedia.org/wiki/Tail_call
[trampolines]: http://blog.richdougherty.com/2009/04/tail-calls-tailrec-and-trampolines.html
[scala-rec-fun]: http://fruzenshtein.com/scala-recursive-function/
[tcall-opt]: http://www.drdobbs.com/jvm/tail-call-optimization-and-java/240167044
