Syntax 101
==========

## Introduction
The best syntaxt tutorial can be found [here][scala_in_5_minutes]. It will help
you yo pick up the basics. Many of listed concepts will be explained further
in more detail. In this topic we will add some additional information to that
tutorial.


## Assigmnents/bindings
In Scala we can define a classical variable (for many programming languages you
may already know). You can do it by using `var` keyword. If you want to define
a variable in functional way, you should use `val` keyword instead. For those
who familiar with `Java`, `val` is works the like `final` modifier. Shortly,
`val`s can not be reassigned. Functional approach engourages you to use `val`s.
But sometimes there's no other visible option that reassigment. For now you
should avoid `var`s anywhere you can, and then, maybe avoid them at all.


## Unusual literal
Very often we need a function that won't return a thing. Some languages are
using `void` type for that purpose. Scala has it's own `void`, and it's called
`Unit`. And it even has it's own literal:

    // here we defined a variable of type Unit
    val unit: Unit = ()


## Code blocks
Sometimes you have tell more than usual. And some languages are forcing you to
create a separate function for it. `Scala` has a construct which is called
block:

    val block = {
      println("hello")
      println("world")
    }

    // hello
    // world
    // block: Unit = ()

Blocks are expressions, and they are useful when you are not satisfied with
oneliners. Blocks allow nesting. The value of the last line of the block is
returned:

    scala> val block2 = {
         |   // it can be relaced with oneliner But I'm here
         |   // to demonstrate you block nesting
         |   val carrots = {
         |     1 + 2
         |   }
         |
         |   val onions = 2
         |
         |   carrots + onions
         | }


## Imports
`Scala` allows you to make imports much shorter (compared to Java). But in the
most of the cases, folks is staying with traditional java-like imports, except
the cases when they need to alias an imported type. Look what `Scala` can do to
your imports.

    // import all
    import my.librarly._

    // import only specific items
    import scala.collection.immutable{Map, Set}

    // rename Map to MutableMap
    import scala.collection.mutable.{Map => MutableMap}

    // exclude Map and Set from import
    import scala.collection.mutable.{Map => _, Set => _}

    // You can also use nested imports
    // Scalaz object is located inside scalaz package
    import scalaz._, Scalaz._

You can read more about imports [here][imports-in-scala]. You can also import
the stuff you want *inside* a method, class, object, block. But use this feature
only when you really need it. `Scala` allows you to use relative imports.


## Collections
### Arrays
Arrays in `Scala` are collections:

    val anArray = Array(9, 8, 7, 6, 5)
    val anArray: Array[Int] = Array(9, 8, 7, 6, 5)

You should also know that in `Scala` arrays are invariant. (In `Java` they
are co-variant).

### Lists
You can construct lists in the following manner:

    val list = List("a", "b", "c")

And there's another way to do it:

    val myList = "a" :: "b" :: "c" :: Nil

`Nil` defines, the end of the list, `::` - is `Cons` operator. If you need more
details about lists and their basic operations you can read it [here][lists].
List — is the most widely used data structure. You should know it better then
others.


### Sequences (Seq)
`Scala` has a `Seq` trait. For now think about traits as you think about
interfaces in object-oriented languages. You may also consider them mixins, if
you are familiar with that concept.

    val sequence = Seq(1,3,4,5)

Seq trait a way to refer to List or any other collection in an abstract way.
[Here][seq_list] you may read about the differences between `Seq` and `List`.



For further reading
==================
  - You can try `Scala` in your [browser][scala-in-your-browser].
  - We also recommend you to use the materials from [Twitter Scala School][tss]
    thoughout the course.
  - [The official documentation][offdoc].

[scala-in-your-browser]: http://scalatutorials.com/tour/
[tss]: http://twitter.github.io/scala_school/
[offdoc]: http://docs.scala-lang.org/
[scala_in_5_minutes]: https://learnxinyminutes.com/docs/scala/
[sicp]: https://en.wikipedia.org/wiki/Structure_and_Interpretation_of_Computer_Programs
[lists]: https://www.tutorialspoint.com/scala/scala_lists.htm
[seq_list]: http://stackoverflow.com/a/10866807/1655785
[imports-in-scala]: https://www.scala-lang.org/docu/files/ScalaReference.pdf#page=58

