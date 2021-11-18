The Word Counter 
================

You should write your own implementation of the famous and legendary unix utility. It should answer the following commands: 

     wc -c <filename> prints the byte count 
     wc -l <filename> prints the line count
     wc -m <filename> prints the character count
     wc -w <filename> prints the word count
     wc -L <filename> prints the length of the longest line 

We encourage you to use scala.io.Source and recursion when it's possible. The utility should be built as a jar file, by [scala build tool][SBT]. Please cover parts of your code with [ScalaTest][scala-test]. It has a huge variety of Spec types. You may choose any suitable for you.

[Here](/codebook/main/ch0/WordCount.scala) you may find a snippet that will help.


## Before you begin
Please read about one very notable trait from the standard library. The trait is called `DelayedInit`. More details [here][delayed-init].

What does it mean, and why I gave you the information about it right now?
Ok, let me explain: the `App` trait that is extended in countless tutorials and examples is broken. `App` extends `DelayedInit`, so both are broken. You may find more information in the official [documentation][app-doc].

That's why you should know it before you start writing your first application and not use `App` trait. Instead you should use standard `main` method.


## Methodical purpose:
The goal of this project is to introduce the foundations of the language and it's de-facto standard build tool to students alongside with the most used testing framework: ScalaTest. We encourage using functional approach. During this project students are going to implement something they know and already familiar with. Something they can touch in real life. That should be fun and stressless.

[SBT]: https://www.scala-sbt.org/
[scala-test]: https://www.scalatest.org/
[app-doc]: http://www.scala-lang.org/api/current/scala/App.html
[delayed-init]: http://www.scala-lang.org/api/current/scala/DelayedInit.html


