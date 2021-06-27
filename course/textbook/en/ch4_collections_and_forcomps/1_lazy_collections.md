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

