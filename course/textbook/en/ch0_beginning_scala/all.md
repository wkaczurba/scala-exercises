Before you begin
================

Scala is the one of many languages that work on the top of Java Runtime
Environment. Scala works on JVM, uses Java classes, and smoothly interoperates
with them. That's why we should install Java first. The course materials are
designed for `Scala` 2.12, and JDK 1.8. So, some of the samples may not work
properly on earlier versions of those platforms.

## Install JDK
JDK (Java Development Kit) will be required for further `Scala` development.
You can find all the required installation instructions
[here][jdk-install-overview]. The same instructions may work for various
`JDK` versions. `Open JDK` will also work fine. So, if you are Linux user
you may easily install `Open JDK` package. And deal with it.

## Check installed version of Java
After `JDK` installation `java` and `javac` should be added to your `$PATH`

    # for windows:
    \>java –version
    Java version "1.8.0_31"
    Java (TM) SE Run Time
    Environment (build 1.8.0_31-b31)
    Java Hotspot (TM) 64-bit Server
    VM (build 25.31-b07, mixed mode)


    # forLinux
    [~] java -version
    openjdk version "1.8.0_102"
    OpenJDK Runtime Environment (build 1.8.0_102-b14)
    OpenJDK 64-Bit Server VM (build 25.102-b14, mixed mode)

All listed outputs platforms work well for us.

## Add JAVA_HOME variable to your environment
You can read [here][java-home-windows], how to make it work on Windows.
If you are proud [`Linux`][java-home-linux]or OS X user you can export it to
`bash.rc`.


## Install SdkMan!
Of course you can waste your time on manual installations. But we propose
you a better solution — a platform that can help you install and manage
different software components designed to run on JVM.
`SDKMAN!` - supports `Scala`, `Clojure`, `Groovy` and `Kotlin`. It also
supports various build systems, from `Maven` to `sbt`. For those who
familiar with ruby or python `SDKMAN!` works the same way as `RVM` or `pyenv`.

`SDKMAN!` installation guide can be found [here][sdkman].

After the installation you must restart your shell, or open a new tab. In the
example below, we're installing the latest version of `Scala` and `sbt` (Simple
build tool)

    # in your shell
    # the latest version of scala will be installed. You can also
    # specify the version you want
    $ sdk install scala

    Downloading: scala 2.12.0

    In progress...

    ############################################################## 100.0%

    Installing: scala 2.12.0
    Done installing!

    Do you want scala 2.12.0 to be set as default? (Y/n): y

    Setting scala 2.12.0 as default.


When `Scala` is installed, let's install `sbt`:

    [~] sdk install sbt
    Downloading: sbt 0.13.13

    In progress...

    ############################################################## 100.0%

    Installing: sbt 0.13.13
    Done installing!

    Do you want sbt 0.13.13 to be set as default? (Y/n): y

    Setting sbt 0.13.13 as default.

That's it the stuff is up and running.


## Which IDE I should use?
There are various `IDE`s that support `Scala`. Here I will describe pros and
cons of the most notorious of them.

IntelliJ IDEA Community Edition supports `Scala` and `Dotty`. And we do
recommend is as your default IDE. You can also use `ScalaIDE` or `ENSIME`. You
can also use `Vim`.

> If you're eager to install Scala-IDE, the easiest way to do it is do
> download pre-build IDE (by clicking 'Download IDE' button), or follow
> the link below. You can also install it as a plugin for Eclipse, if you
> know what you are doing :)

IntelliJ IDEA: https://www.jetbrains.com/idea/specials/idea/idea.html
Scala-IDE: http://scala-ide.org/download/sdk.html
Ensime: https://github.com/ensime


### Pros and Cons of your IDE choice
By choosing IntelliJ you will get the best `Scala` support ever, tons of plugins
and acceptable performance. It works faster than Eclipse (by my personal
experience). But if you want to have `Play framework` support you should
consider buying full (Ultimate) version. You can still develop play apps inside
Community version with some lack of comfort. If you're a happy owner of Ultimate
version, for you the choice is obvious.

By choosing Scala IDE you will receive Eclipse with great official Scala support.
Eclipse is a free platform that has lots of different plugins (free and
commercial). And the most important thing is: you receive the official support
for Play Framework.

ENSIME - for those who like Emacs or Vim. It has good Scala support. No more
no less.

[jdk-install-overview]: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
[java-home-windows]: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
[java-home-linux]: http://askubuntu.com/questions/175514/how-to-set-java-home-for-java/175519#175519
[sdkman]: http://sdkman.io/install.html

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

OOP 101
=======

This topic is all about object-oriented features of `Scala`. We assume that
you're familiar with OOP concepts, that's why we will recommend you to read
[A Scala Tutorial for Java Programmers][oop-for-java-devs], which is a part
of the official `Scala` documentation.

You could also benefit from the 'trait section' of the official
[documentation][traits].

A short overview of object-oriented part of `Scala` you may find
in [this][oop-overview] presentation.

*Note:*
> on page 22 of the given presentation, there's a type `ScalaObject` in type
> hierarchy diagram, that is subtype of `AnyRef`. This type was considered
> deprecated and doesn't exist any longer. Now all java and scala objects are
> subtypes of `AnyRef`

Yet another one, more detailed overview you may find [here][oop-more-detailed].

## More on objects
[Here][objects-in-scala] you may read more about objects in `Scala`.


## Constructor overloading
`Scala` supports [constructor overloading][ctor-overloading]. But it's not
the best way to handle a problem. It's not an *idiomatic* solution. This
function becomes useful, if your overloaded constructor will be called in
java context. In other cases overloading your class companion's `apply` will
be a good strategy.


## Video
The story of traits: https://www.youtube.com/watch?v=VdBkw9TU_u4
the video illustrates multiple inheritance in `Scala` compared to other
languages like `Java` and `Groovy`.

[traits]: http://docs.scala-lang.org/tutorials/tour/mixin-class-composition
[oop-for-java-devs]: http://docs.scala-lang.org/tutorials/scala-for-java-programmers.html
[oop-overview]: https://www.techfak.uni-bielefeld.de/ags/pi/lehre/ProgSem13/oopScalaPrint.pdf
[oop-more-detailed]: http://www.vasinov.com/blog/scala-oop-galore/
[ctor-overloading]: https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s04.html
[objects-in-scala]: https://madusudanan.com/blog/scala-tutorials-part-4-objects/

Scala type system
=================
This topic is about `Scala` type system. As the main source of inspiration, we
recommend you to read the corresponding section of
[Twitter Scala School][type-basics], and
[Scala's Types of types][types-of-types] which I guess is the most powerful
description of `Scala` types for newcomers.

## Bounds
If you're reading about Bounds, stop doing this. Since `2.11` those beasts are
considered deprecated. `Dotty` doesn't have them, so it means that future
versions of `Scala`, won't have them too. Save your time for something bigger.

## Variance
An [article][variance], that will give you a good explanation of variance in
`Scala`.

## Rules of variance

  - If you have a mutable container -- make it invariant.
  - If you have an immutable container -- make it covariant.
  - If you have a transformation (like function):
    - Input: covariant
    - Output: contrvariant


## Value types
also exists in `Scala` you can find more information [here][value-types].

## Type aliases
Use them when possible. They will make your code more readable. Let's be honest
that `Map[Username, Key]` looks a way better than `Map[String, String]`. You
may find a number of examples [here][type-aliases], just skip to `Example`
section.

[variance]: https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
[type-basics]: https://twitter.github.io/scala_school/type-basics.html
[types-of-types]: http://ktoso.github.io/scala-types-of-types/
[value-types]: http://docs.scala-lang.org/overviews/core/value-classes.html
[type-aliases]: http://www.scala-lang.org/files/archive/spec/2.12/04-basic-declarations-and-definitions.html#type-declarations-and-type-aliases

Conclusion
==========
At the end of this chapter, we would recommend you to look at the
[Effective Scala][effective-scala], and after that to start with practical
part. You may find your first task inside `assignments.md`, which is located
in the current folder.

And please, don't be upset if you weren't able to understand all concepts that
were listed inside this chapter. It's OK. You can always come back to them later.

[effective-scala]: http://twitter.github.io/effectivescala/

Scala beans
===========

`Scala` has a good interoperability with `Java`. It also could ease your job
when you even designing Java beans. For those who are not familiar with `Java`,
you may read about beans [here][java-beans]. There are some features of `Scala`
that will make your life much easier.


## Getters and setters
`Scala` has its own mechanism similar to [Project Lombok][project-lombok]. The
mechanism is called `BeanProperty` and it's built into the standard library.
All you need to do is to create a bean and add `@BeanProperty` each field that
should have its own getter and setter.
[Here][bean-property-alvin] you will learn about that property. The
`BeanProperty` documentation can be found [here][bean-property-doc].

If you want to generate getter and setter for boolean variable.
[This][bool-prop] class may come handy. As the result you will get a method that
looks like this one: `isProperty`.


## Uninitialized values
`Scala` also supports uninitialized values. You may need this feature when
creating your beans. Let's take a look at `Java` example below:

    class MyClass {
        // By default any subtype of Object is initialized to null
        // primitive types are initialized with their default values
        String uninitialized;
    }

The same stuff can be applied to `Scala`:


    class {
      // underscore syntax tells scala that the field will not be initialized
      var uninitialized: String = _
    }

But please **Don't do this**. Initialize values were you can. This feature must
be used only if you forced by library or framework you are using. You may
receive lots of `NullPointerException`s as the result. You should know about
this feature because it might save you some time. If you want to delay the
initialization you should use `lazy`.

[java-beans]: https://en.wikipedia.org/wiki/JavaBeans
[project-lombok]: https://projectlombok.org/
[bean-property-doc]: https://www.scala-lang.org/api/2.12.0/scala/beans/BeanProperty.html
[bean-property-alvin]: http://alvinalexander.com/scala/how-to-create-scala-javabeans-beanproperty-java-libraries
[bean-property-illustrated]: https://daily-scala.blogspot.ru/2009/09/beanproperties.html
[bool-prop]: http://www.scala-lang.org/api/2.12.0/scala/beans/BooleanBeanProperty.html

Before you begin
================

Scala is the one of many languages that work on the top of Java Runtime
Environment. Scala works on JVM, uses Java classes, and smoothly interoperates
with them. That's why we should install Java first. The course materials are
designed for `Scala` 2.12, and JDK 1.8. So, some of the samples may not work
properly on earlier versions of those platforms.

## Install JDK
JDK (Java Development Kit) will be required for further `Scala` development.
You can find all the required installation instructions
[here][jdk-install-overview]. The same instructions may work for various
`JDK` versions. `Open JDK` will also work fine. So, if you are Linux user
you may easily install `Open JDK` package. And deal with it.

## Check installed version of Java
After `JDK` installation `java` and `javac` should be added to your `$PATH`

    # for windows:
    \>java –version
    Java version "1.8.0_31"
    Java (TM) SE Run Time
    Environment (build 1.8.0_31-b31)
    Java Hotspot (TM) 64-bit Server
    VM (build 25.31-b07, mixed mode)


    # forLinux
    [~] java -version
    openjdk version "1.8.0_102"
    OpenJDK Runtime Environment (build 1.8.0_102-b14)
    OpenJDK 64-Bit Server VM (build 25.102-b14, mixed mode)

All listed outputs platforms work well for us.

## Add JAVA_HOME variable to your environment
You can read [here][java-home-windows], how to make it work on Windows.
If you are proud [`Linux`][java-home-linux]or OS X user you can export it to
`bash.rc`.


## Install SdkMan!
Of course you can waste your time on manual installations. But we propose
you a better solution — a platform that can help you install and manage
different software components designed to run on JVM.
`SDKMAN!` - supports `Scala`, `Clojure`, `Groovy` and `Kotlin`. It also
supports various build systems, from `Maven` to `sbt`. For those who
familiar with ruby or python `SDKMAN!` works the same way as `RVM` or `pyenv`.

`SDKMAN!` installation guide can be found [here][sdkman].

After the installation you must restart your shell, or open a new tab. In the
example below, we're installing the latest version of `Scala` and `sbt` (Simple
build tool)

    # in your shell
    # the latest version of scala will be installed. You can also
    # specify the version you want
    $ sdk install scala

    Downloading: scala 2.12.0

    In progress...

    ############################################################## 100.0%

    Installing: scala 2.12.0
    Done installing!

    Do you want scala 2.12.0 to be set as default? (Y/n): y

    Setting scala 2.12.0 as default.


When `Scala` is installed, let's install `sbt`:

    [~] sdk install sbt
    Downloading: sbt 0.13.13

    In progress...

    ############################################################## 100.0%

    Installing: sbt 0.13.13
    Done installing!

    Do you want sbt 0.13.13 to be set as default? (Y/n): y

    Setting sbt 0.13.13 as default.

That's it the stuff is up and running.


## Which IDE I should use?
There are various `IDE`s that support `Scala`. Here I will describe pros and
cons of the most notorious of them.

IntelliJ IDEA Community Edition supports `Scala` and `Dotty`. And we do
recommend is as your default IDE. You can also use `ScalaIDE` or `ENSIME`. You
can also use `Vim`.

> If you're eager to install Scala-IDE, the easiest way to do it is do
> download pre-build IDE (by clicking 'Download IDE' button), or follow
> the link below. You can also install it as a plugin for Eclipse, if you
> know what you are doing :)

IntelliJ IDEA: https://www.jetbrains.com/idea/specials/idea/idea.html
Scala-IDE: http://scala-ide.org/download/sdk.html
Ensime: https://github.com/ensime


### Pros and Cons of your IDE choice
By choosing IntelliJ you will get the best `Scala` support ever, tons of plugins
and acceptable performance. It works faster than Eclipse (by my personal
experience). But if you want to have `Play framework` support you should
consider buying full (Ultimate) version. You can still develop play apps inside
Community version with some lack of comfort. If you're a happy owner of Ultimate
version, for you the choice is obvious.

By choosing Scala IDE you will receive Eclipse with great official Scala support.
Eclipse is a free platform that has lots of different plugins (free and
commercial). And the most important thing is: you receive the official support
for Play Framework.

ENSIME - for those who like Emacs or Vim. It has good Scala support. No more
no less.

[jdk-install-overview]: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
[java-home-windows]: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
[java-home-linux]: http://askubuntu.com/questions/175514/how-to-set-java-home-for-java/175519#175519
[sdkman]: http://sdkman.io/install.html

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

OOP 101
=======

This topic is all about object-oriented features of `Scala`. We assume that
you're familiar with OOP concepts, that's why we will recommend you to read
[A Scala Tutorial for Java Programmers][oop-for-java-devs], which is a part
of the official `Scala` documentation.

You could also benefit from the 'trait section' of the official
[documentation][traits].

A short overview of object-oriented part of `Scala` you may find
in [this][oop-overview] presentation.

*Note:*
> on page 22 of the given presentation, there's a type `ScalaObject` in type
> hierarchy diagram, that is subtype of `AnyRef`. This type was considered
> deprecated and doesn't exist any longer. Now all java and scala objects are
> subtypes of `AnyRef`

Yet another one, more detailed overview you may find [here][oop-more-detailed].

## More on objects
[Here][objects-in-scala] you may read more about objects in `Scala`.


## Constructor overloading
`Scala` supports [constructor overloading][ctor-overloading]. But it's not
the best way to handle a problem. It's not an *idiomatic* solution. This
function becomes useful, if your overloaded constructor will be called in
java context. In other cases overloading your class companion's `apply` will
be a good strategy.


## Video
The story of traits: https://www.youtube.com/watch?v=VdBkw9TU_u4
the video illustrates multiple inheritance in `Scala` compared to other
languages like `Java` and `Groovy`.

[traits]: http://docs.scala-lang.org/tutorials/tour/mixin-class-composition
[oop-for-java-devs]: http://docs.scala-lang.org/tutorials/scala-for-java-programmers.html
[oop-overview]: https://www.techfak.uni-bielefeld.de/ags/pi/lehre/ProgSem13/oopScalaPrint.pdf
[oop-more-detailed]: http://www.vasinov.com/blog/scala-oop-galore/
[ctor-overloading]: https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s04.html
[objects-in-scala]: https://madusudanan.com/blog/scala-tutorials-part-4-objects/

Scala type system
=================
This topic is about `Scala` type system. As the main source of inspiration, we
recommend you to read the corresponding section of
[Twitter Scala School][type-basics], and
[Scala's Types of types][types-of-types] which I guess is the most powerful
description of `Scala` types for newcomers.

## Bounds
If you're reading about Bounds, stop doing this. Since `2.11` those beasts are
considered deprecated. `Dotty` doesn't have them, so it means that future
versions of `Scala`, won't have them too. Save your time for something bigger.

## Variance
An [article][variance], that will give you a good explanation of variance in
`Scala`.

## Rules of variance

  - If you have a mutable container -- make it invariant.
  - If you have an immutable container -- make it covariant.
  - If you have a transformation (like function):
    - Input: covariant
    - Output: contrvariant


## Value types
also exists in `Scala` you can find more information [here][value-types].

## Type aliases
Use them when possible. They will make your code more readable. Let's be honest
that `Map[Username, Key]` looks a way better than `Map[String, String]`. You
may find a number of examples [here][type-aliases], just skip to `Example`
section.

[variance]: https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
[type-basics]: https://twitter.github.io/scala_school/type-basics.html
[types-of-types]: http://ktoso.github.io/scala-types-of-types/
[value-types]: http://docs.scala-lang.org/overviews/core/value-classes.html
[type-aliases]: http://www.scala-lang.org/files/archive/spec/2.12/04-basic-declarations-and-definitions.html#type-declarations-and-type-aliases

Conclusion
==========
At the end of this chapter, we would recommend you to look at the
[Effective Scala][effective-scala], and after that to start with practical
part. You may find your first task inside `assignments.md`, which is located
in the current folder.

And please, don't be upset if you weren't able to understand all concepts that
were listed inside this chapter. It's OK. You can always come back to them later.

[effective-scala]: http://twitter.github.io/effectivescala/

Scala beans
===========

`Scala` has a good interoperability with `Java`. It also could ease your job
when you even designing Java beans. For those who are not familiar with `Java`,
you may read about beans [here][java-beans]. There are some features of `Scala`
that will make your life much easier.


## Getters and setters
`Scala` has its own mechanism similar to [Project Lombok][project-lombok]. The
mechanism is called `BeanProperty` and it's built into the standard library.
All you need to do is to create a bean and add `@BeanProperty` each field that
should have its own getter and setter.
[Here][bean-property-alvin] you will learn about that property. The
`BeanProperty` documentation can be found [here][bean-property-doc].

If you want to generate getter and setter for boolean variable.
[This][bool-prop] class may come handy. As the result you will get a method that
looks like this one: `isProperty`.


## Uninitialized values
`Scala` also supports uninitialized values. You may need this feature when
creating your beans. Let's take a look at `Java` example below:

    class MyClass {
        // By default any subtype of Object is initialized to null
        // primitive types are initialized with their default values
        String uninitialized;
    }

The same stuff can be applied to `Scala`:


    class {
      // underscore syntax tells scala that the field will not be initialized
      var uninitialized: String = _
    }

But please **Don't do this**. Initialize values were you can. This feature must
be used only if you forced by library or framework you are using. You may
receive lots of `NullPointerException`s as the result. You should know about
this feature because it might save you some time. If you want to delay the
initialization you should use `lazy`.

[java-beans]: https://en.wikipedia.org/wiki/JavaBeans
[project-lombok]: https://projectlombok.org/
[bean-property-doc]: https://www.scala-lang.org/api/2.12.0/scala/beans/BeanProperty.html
[bean-property-alvin]: http://alvinalexander.com/scala/how-to-create-scala-javabeans-beanproperty-java-libraries
[bean-property-illustrated]: https://daily-scala.blogspot.ru/2009/09/beanproperties.html
[bool-prop]: http://www.scala-lang.org/api/2.12.0/scala/beans/BooleanBeanProperty.html

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


