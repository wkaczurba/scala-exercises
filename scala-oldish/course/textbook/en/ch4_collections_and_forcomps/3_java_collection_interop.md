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

