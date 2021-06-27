zzBefore you begin
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


Case classes & Case objects
===========================

From the previous chapter you learned what `objects` and `classes` are.
`Scala` has a special form of classes that supports [pattern matching][pm-wiki].
You will learn more about pattern matching further this chapter.

You may consider `case clases` as a records in functional languages. Or you
may treat them as immutable java beans. That's how they look like:

    case class Monster(health: Int = 100,
                       wearpon: Wearpon = Claws,
                       name: String)

    val richard = Monster(250, Handgun, "Richard")


An instance of given class can be used for pattern matching. It has `equals` and
`hashcode` defined. It also supports serialization and contains predefined
`toString`. It is also a subtype of trait `Product`. Case objects behave the
same way (except that they don't accept input parameters).

There's a companion object for each `case class`. The companion contains `apply`
method. That's why you don't need `new` keyword to create an instance of a
`case class`.

Every `case class` has an amazing `copy` method. Many of us created a countless
`update` methods instead of using builtin method.

Further reading
===============
More about `case class` you can read [here][case-class] and
[here][case-class-tutor] (more detailed)

[pm-wiki]: https://en.wikipedia.org/wiki/Pattern_matching
[case-class]: https://twitter.github.io/scala_school/basics2.html#caseclass
[case-class-tutor]: http://docs.scala-lang.org/tutorials/tour/case-classes.html

Another word about assignment
=============================
Let's get back to the assignment operator. It's not that simple as we
previously thought:

    // it was a simple variable binding:
    // no alarms and no surprises
    scala> val address = ("localhost", 80)
    address: (String, Int) = (localhost,80)

    scala> val (host, port) = address
    host: String = localhost
    port: Int = 80

We've just decomposed the tuple, and bund their elements to the variables, but
you can do more:

    scala> val first::rest = List(1,2,3,4,5)
    first: Int = 1
    rest: List[Int] = List(2, 3, 4, 5)

You can do the same with case classes:

    case class Person(name: String, age: Int)

    val max = Person("Max", 36)
    // max: Person = Person(Max,36)

    val Person(n, a) = max
    // n: String = Max
    // a: Int = 36

And even more than that:

    scala> val p @ Person(n, a) = max
    // p: Person = Person(Max,36)
    // n: String = Max
    // a: Int = 36

In the last case you bound the whole record to variable named `p`.
By name `n` we will get the name of the person, the same as `p.name`.
By name `a` we will get the age. The same as `p.age`.

As you may see assignment is not that simple as you may see. The same
functionality is implemented in other languages, like `Erlang` or `Python`.

Pattern matching
================

## Data construction and decomposition

### Data construction
In previous chapter we looked at the different ways of list creation. We used
cons operator `::` to create a list. But we also used a very unusual construct
that doesn't employ `new` keyword. It was definitely not a constructor:

    val myArray = Array(2100, 2099)
    val myList = List(1,2,3,4,5,6,7,8,9)

And yes, it's not a constructor. It's a method call. There's an object (you may
think of them as built-in singletons) that is called `Array`and that object
has a method that is called `apply`. This method is also defined for functions,
because in `Scala` functions are objects too.

    // the same as above
    val myArray = Array.apply(2100, 2099)

More about the `apply` method you may find in [Twitter Scala School][apply]

### Data extraction
There is a couple of methods that called `unapply` and `unapplySeq`. You can
read more about extractors [here][unapply].


## Pattern matching
Pattern matching is widely used in functional languages. It allows you to extract
the data from compound object, by having an information about their structure.

From this [video][video-tutorial] you will learn a lot about construction and
deconstruction of data, you will also learn about pattern matching. We recommend
you to watch this video because it rocks. We also recommend you
[this][pm-tutor] tutorial.

As you may have noticed the assignment operator does the pattern matching.

`Scala` has a syntactic sugar for pattern matching that works inside a function
body:

    // before
    list.filter(item => item match {
      case phone: Cellphone => true
      case _ => false
    }

    // after
    list.filter {
      case phone: Cellphone => true
      case _ => false
    }

You can use pattern matching inside any `Function1`'s body. `Scala` will compile
this operation to `PartialFunction` instance, which is a subtype of `Funciton`.

You can also use pattern matching for regular expressions. You may find
more details [here][pm-regex].

[video-tutorial]: https://www.youtube.com/watch?v=1vxIRkYZfmc

[apply]: https://twitter.github.io/scala_school/basics2.html#apply
[unapply]: http://docs.scala-lang.org/tutorials/tour/extractor-objects.html
[pm-tutor]: http://docs.scala-lang.org/tutorials/tour/pattern-matching
[pm-regex]: https://www.scala-lang.org/api/2.12.x/scala/util/matching/Regex.html

Exception handling
==================

`Scala` has a very similar way of the exception production and handling. In
`Scala`, all exceptions are [unchecked][unchecked-ex].

You may read more about the exception handling in Jacob Jenkov's
[blog][jenkov-ex], where the traditional (imperative) approach is illustrated
nicely.

`Scala` also supports functional ways of exception handling, and we will come
back to them at the end of the course.

[unchecked-ex]: http://crunchify.com/better-understanding-on-checked-vs-unchecked-exceptions-how-to-handle-exception-better-way-in-java/
[jenkov-ex]: http://tutorials.jenkov.com/scala/exception-try-catch-finally.html

Case classes & Case objects
===========================

From the previous chapter you learned what `objects` and `classes` are.
`Scala` has a special form of classes that supports [pattern matching][pm-wiki].
You will learn more about pattern matching further this chapter.

You may consider `case clases` as a records in functional languages. Or you
may treat them as immutable java beans. That's how they look like:

    case class Monster(health: Int = 100,
                       wearpon: Wearpon = Claws,
                       name: String)

    val richard = Monster(250, Handgun, "Richard")


An instance of given class can be used for pattern matching. It has `equals` and
`hashcode` defined. It also supports serialization and contains predefined
`toString`. It is also a subtype of trait `Product`. Case objects behave the
same way (except that they don't accept input parameters).

There's a companion object for each `case class`. The companion contains `apply`
method. That's why you don't need `new` keyword to create an instance of a
`case class`.

Every `case class` has an amazing `copy` method. Many of us created a countless
`update` methods instead of using builtin method.

Further reading
===============
More about `case class` you can read [here][case-class] and
[here][case-class-tutor] (more detailed)

[pm-wiki]: https://en.wikipedia.org/wiki/Pattern_matching
[case-class]: https://twitter.github.io/scala_school/basics2.html#caseclass
[case-class-tutor]: http://docs.scala-lang.org/tutorials/tour/case-classes.html

Another word about assignment
=============================
Let's get back to the assignment operator. It's not that simple as we
previously thought:

    // it was a simple variable binding:
    // no alarms and no surprises
    scala> val address = ("localhost", 80)
    address: (String, Int) = (localhost,80)

    scala> val (host, port) = address
    host: String = localhost
    port: Int = 80

We've just decomposed the tuple, and bund their elements to the variables, but
you can do more:

    scala> val first::rest = List(1,2,3,4,5)
    first: Int = 1
    rest: List[Int] = List(2, 3, 4, 5)

You can do the same with case classes:

    case class Person(name: String, age: Int)

    val max = Person("Max", 36)
    // max: Person = Person(Max,36)

    val Person(n, a) = max
    // n: String = Max
    // a: Int = 36

And even more than that:

    scala> val p @ Person(n, a) = max
    // p: Person = Person(Max,36)
    // n: String = Max
    // a: Int = 36

In the last case you bound the whole record to variable named `p`.
By name `n` we will get the name of the person, the same as `p.name`.
By name `a` we will get the age. The same as `p.age`.

As you may see assignment is not that simple as you may see. The same
functionality is implemented in other languages, like `Erlang` or `Python`.

Pattern matching
================

## Data construction and decomposition

### Data construction
In previous chapter we looked at the different ways of list creation. We used
cons operator `::` to create a list. But we also used a very unusual construct
that doesn't employ `new` keyword. It was definitely not a constructor:

    val myArray = Array(2100, 2099)
    val myList = List(1,2,3,4,5,6,7,8,9)

And yes, it's not a constructor. It's a method call. There's an object (you may
think of them as built-in singletons) that is called `Array`and that object
has a method that is called `apply`. This method is also defined for functions,
because in `Scala` functions are objects too.

    // the same as above
    val myArray = Array.apply(2100, 2099)

More about the `apply` method you may find in [Twitter Scala School][apply]

### Data extraction
There is a couple of methods that called `unapply` and `unapplySeq`. You can
read more about extractors [here][unapply].


## Pattern matching
Pattern matching is widely used in functional languages. It allows you to extract
the data from compound object, by having an information about their structure.

From this [video][video-tutorial] you will learn a lot about construction and
deconstruction of data, you will also learn about pattern matching. We recommend
you to watch this video because it rocks. We also recommend you
[this][pm-tutor] tutorial.

As you may have noticed the assignment operator does the pattern matching.

`Scala` has a syntactic sugar for pattern matching that works inside a function
body:

    // before
    list.filter(item => item match {
      case phone: Cellphone => true
      case _ => false
    }

    // after
    list.filter {
      case phone: Cellphone => true
      case _ => false
    }

You can use pattern matching inside any `Function1`'s body. `Scala` will compile
this operation to `PartialFunction` instance, which is a subtype of `Funciton`.

You can also use pattern matching for regular expressions. You may find
more details [here][pm-regex].

[video-tutorial]: https://www.youtube.com/watch?v=1vxIRkYZfmc

[apply]: https://twitter.github.io/scala_school/basics2.html#apply
[unapply]: http://docs.scala-lang.org/tutorials/tour/extractor-objects.html
[pm-tutor]: http://docs.scala-lang.org/tutorials/tour/pattern-matching
[pm-regex]: https://www.scala-lang.org/api/2.12.x/scala/util/matching/Regex.html

Exception handling
==================

`Scala` has a very similar way of the exception production and handling. In
`Scala`, all exceptions are [unchecked][unchecked-ex].

You may read more about the exception handling in Jacob Jenkov's
[blog][jenkov-ex], where the traditional (imperative) approach is illustrated
nicely.

`Scala` also supports functional ways of exception handling, and we will come
back to them at the end of the course.

[unchecked-ex]: http://crunchify.com/better-understanding-on-checked-vs-unchecked-exceptions-how-to-handle-exception-better-way-in-java/
[jenkov-ex]: http://tutorials.jenkov.com/scala/exception-try-catch-finally.html

Types and enumerations
======================

For many of us enumerations are indispensable. Even if your language doesn't
support them. Somebody will reinvent that wheel again. `Scala` doesn't have
`enum`or `enumeration` keywords. That's why newcomers from `Java` and `C#` are
eager do something creative, like:

    // A real life example :)
    object Weekday {
      val Monday = 0
      ...
      val Sunday = 6
    }

And then those values will be used, if they were `Java` enumerations:

    if (weekday == Weekday.Friday) {
       stop(wearing, Tie)
    }

But what if somebody forget that the first day doesn't start with zero.
Assuming that Monday == 1:

    val sunday = 7

It's a bad design. `Scala` has a better way to define enumerable types. And
its much better that `Java` or `C#` approach.


## Our first enumeration

    sealed trait TrafficLight
    case object Green extends TrafficLight
    case object Yellow extends TrafficLight
    case object Red extends TrafficLight
    case object Broken extends TrafficLight

Now, let's get down to business...

## `case` keyword
before `object` tells us that the object can be used in pattern matching
operation. It also means that the object has predefined `equals`, `hashcode`,
serialization and default implementation of `toString` method.

## `sealed` keyword
`Scala` has `sealed` keyword. When used prevents further inheritance outside
of its compilation unit (in our case file). Why do we need this? Well, because
we will have information about all possible subtypes of given type at the
compile time, to throw an error. We can use `sealed abstract class` instead of
`trait`. The first option becomes pretty useful if you want to integrate your
code with `Java`. Trait seems more idiomatic.
Let's define the following function:

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
    }

And we will got:

    warning: match may not be exhaustive.
    It would fail on the following input: Broken
           def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
                                                            ^
    tellWhatTheLightIs: (tl: TrafficLight)Unit

We can also define recursive structures. You can use `case class` for this
purpose:

    sealed trait Tree
    case class Leaf(value: Int) extends Tree
    case class Node(l: Tree, r: Tree) extends Tree

Of course we could imagine that our traffic light will never get broken. We
don't care about an error (that will never happen :))

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
      case _ => println("Baby I Don't Care")
    }

But you *should not do it*. If you're willing to ignore an error, ask yourself
a question: "Do I know what I'm doing?" That's why we need to use a `sealed`
keyword.

That's it. We've just created an idiomatic `Scala` enumeration. Which actually
is not an enumeration. It's Algebraic Data Type or [ADT][adt-wiki].

Many types from the standard library are implemented as ADT's: List, Option,
Try and lot's of others. If you wish to learn more, follow the links from
this topic.

Further reading
===============
Don't be surprised if I say that you can read about Algebraic Data Types in
[Wikipedia][adt-wiki]. It's a decent article. There's also interesting
[blog post][scala-adt] about ADT's `Scala`. You may also find this
[presentation][scala-adt-2] pretty useful.

[adt-wiki]: https://en.wikipedia.org/wiki/Algebraic_data_type
[scala-adt]: https://gleichmann.wordpress.com/2011/01/30/functional-scala-algebraic-datatypes-enumerated-types/
[scala-adt-2]: http://tpolecat.github.io/presentations/algebraic_types.html#18

More on lists
=============

## Headshot
`Option` has its `get`, list has its `head`. It also has `init` and `tail`.
Here what you may get using those methods on empty list:

    // for empty list:
    init: java.lang.UnsupportedOperationException
    head: java.lang.NoSuchElementException
    last: java.lang.NoSuchElementException
    tail: java.lang.UnsupportedOperationException

Of course it won't happen if you always check your list for emptiness. And of
course it won't happen to you because you *always* check your lists. Right?
Or maybe you have a list that won't be empty because it was created that way.

Calling `list.head` its sidekicks -- is the best way to perform head-shot on
yourself.

> Do whatever possible to avoid calling list.head, list.tail and others

Calling `headOption` is much better that using `head`. Of course if you don't
mind having a redundant container. `lastOption` behaves better. If you're
somehow bound to indexes, using `isDefinedAt` that accepts integral index as its
parameter may also help. All written about assumes checks that *could not
happen*. You may also find a thousand reasons to intentionally avoid those
checks. There's an idiomatic way of dealing with this issue: use pattern
matching. List is an Algebraic Data Type, so you won't miss Nil. You
can pattern match list items, so there no need to call `head/tail` explicitly:

    def printRec(list: List[String]): Unit = list match {
      case Nil  => ()
      case x:xs => println(x)
                   printRec(xs)
    }

*Performance of lined lists*
> Scala's List that corresponds to `scala.collection.immutable.List` is a
> simple linked list. Adding a new value to the head of list is the cheapest
> operation that has algorithmic complexity O(1). If you're going to write at
> the end of that list, the complexity will be O(n). Please, keep that in mind.

## Optional list
I do read a lot of code that was created by `Scala` newbies. And I often see
the following anti-pattern, where it could be avoided: `Option[List[A]]`. And
every time I see it, I ask the writer to explain himself. List is like a single
element Option. It can also be empty. There's no need for additional container.

## ::Nil
Previously we discussed the following way to construct a list:

    val mylist = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

`::` is not a built-in operator. Its the name of class/method that is usually
called `Cons`. Moreover `::` is one of the rarest kind of operators that have
right-associative. That's why when we have `Nil` at the end of the construct
other `::`s will be appropriate because we've already created a list.
You may get more details from `Programming in scala` book. Furthermore `Nil` is
more idiomatic than `List.empty` or `List()`.

    scala> println (Nil == List())
    true

    scala> println (Nil eq List())
    true

    scala> println (Nil equals List())
    true

    scala> System.identityHashCode(Nil)
    374527572

    scala> System.identityHashCode(List())
    374527572


Further reading
===============
You may learn more about lists and other collections on the
[Twitter Scala School](tschool-col) website. Alvin Alexander wrote a bunch of
handy tutorials about lists. You may find them [here](aal1) [here](aal2) and
[here](aal3). Official [documentation](list-doc) is always useful. AN empty list
can be described with various notations, more info [here](empty-list)

[tschool-col]: https://twitter.github.io/scala_school/collections.html
[aal1]: http://alvinalexander.com/scala/scala-list-class-examples
[aal2]: http://alvinalexander.com/scala/how-create-scala-list-range-fill-tabulate-constructors
[aal3]: http://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable
[list-doc]: http://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[empty-list]: http://stackoverflow.com/questions/5981850/scala-nil-vs-list

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

Types and enumerations
======================

For many of us enumerations are indispensable. Even if your language doesn't
support them. Somebody will reinvent that wheel again. `Scala` doesn't have
`enum`or `enumeration` keywords. That's why newcomers from `Java` and `C#` are
eager do something creative, like:

    // A real life example :)
    object Weekday {
      val Monday = 0
      ...
      val Sunday = 6
    }

And then those values will be used, if they were `Java` enumerations:

    if (weekday == Weekday.Friday) {
       stop(wearing, Tie)
    }

But what if somebody forget that the first day doesn't start with zero.
Assuming that Monday == 1:

    val sunday = 7

It's a bad design. `Scala` has a better way to define enumerable types. And
its much better that `Java` or `C#` approach.


## Our first enumeration

    sealed trait TrafficLight
    case object Green extends TrafficLight
    case object Yellow extends TrafficLight
    case object Red extends TrafficLight
    case object Broken extends TrafficLight

Now, let's get down to business...

## `case` keyword
before `object` tells us that the object can be used in pattern matching
operation. It also means that the object has predefined `equals`, `hashcode`,
serialization and default implementation of `toString` method.

## `sealed` keyword
`Scala` has `sealed` keyword. When used prevents further inheritance outside
of its compilation unit (in our case file). Why do we need this? Well, because
we will have information about all possible subtypes of given type at the
compile time, to throw an error. We can use `sealed abstract class` instead of
`trait`. The first option becomes pretty useful if you want to integrate your
code with `Java`. Trait seems more idiomatic.
Let's define the following function:

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
    }

And we will got:

    warning: match may not be exhaustive.
    It would fail on the following input: Broken
           def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
                                                            ^
    tellWhatTheLightIs: (tl: TrafficLight)Unit

We can also define recursive structures. You can use `case class` for this
purpose:

    sealed trait Tree
    case class Leaf(value: Int) extends Tree
    case class Node(l: Tree, r: Tree) extends Tree

Of course we could imagine that our traffic light will never get broken. We
don't care about an error (that will never happen :))

    def tellWhatTheLightIs(tl: TrafficLight): Unit = tl match {
      case Red => println("No cars go!")
      case Green => println("Don't stop me now!")
      case Yellow => println("Ooohhh you better stop!")
      case _ => println("Baby I Don't Care")
    }

But you *should not do it*. If you're willing to ignore an error, ask yourself
a question: "Do I know what I'm doing?" That's why we need to use a `sealed`
keyword.

That's it. We've just created an idiomatic `Scala` enumeration. Which actually
is not an enumeration. It's Algebraic Data Type or [ADT][adt-wiki].

Many types from the standard library are implemented as ADT's: List, Option,
Try and lot's of others. If you wish to learn more, follow the links from
this topic.

Further reading
===============
Don't be surprised if I say that you can read about Algebraic Data Types in
[Wikipedia][adt-wiki]. It's a decent article. There's also interesting
[blog post][scala-adt] about ADT's `Scala`. You may also find this
[presentation][scala-adt-2] pretty useful.

[adt-wiki]: https://en.wikipedia.org/wiki/Algebraic_data_type
[scala-adt]: https://gleichmann.wordpress.com/2011/01/30/functional-scala-algebraic-datatypes-enumerated-types/
[scala-adt-2]: http://tpolecat.github.io/presentations/algebraic_types.html#18

More on lists
=============

## Headshot
`Option` has its `get`, list has its `head`. It also has `init` and `tail`.
Here what you may get using those methods on empty list:

    // for empty list:
    init: java.lang.UnsupportedOperationException
    head: java.lang.NoSuchElementException
    last: java.lang.NoSuchElementException
    tail: java.lang.UnsupportedOperationException

Of course it won't happen if you always check your list for emptiness. And of
course it won't happen to you because you *always* check your lists. Right?
Or maybe you have a list that won't be empty because it was created that way.

Calling `list.head` its sidekicks -- is the best way to perform head-shot on
yourself.

> Do whatever possible to avoid calling list.head, list.tail and others

Calling `headOption` is much better that using `head`. Of course if you don't
mind having a redundant container. `lastOption` behaves better. If you're
somehow bound to indexes, using `isDefinedAt` that accepts integral index as its
parameter may also help. All written about assumes checks that *could not
happen*. You may also find a thousand reasons to intentionally avoid those
checks. There's an idiomatic way of dealing with this issue: use pattern
matching. List is an Algebraic Data Type, so you won't miss Nil. You
can pattern match list items, so there no need to call `head/tail` explicitly:

    def printRec(list: List[String]): Unit = list match {
      case Nil  => ()
      case x:xs => println(x)
                   printRec(xs)
    }

*Performance of lined lists*
> Scala's List that corresponds to `scala.collection.immutable.List` is a
> simple linked list. Adding a new value to the head of list is the cheapest
> operation that has algorithmic complexity O(1). If you're going to write at
> the end of that list, the complexity will be O(n). Please, keep that in mind.

## Optional list
I do read a lot of code that was created by `Scala` newbies. And I often see
the following anti-pattern, where it could be avoided: `Option[List[A]]`. And
every time I see it, I ask the writer to explain himself. List is like a single
element Option. It can also be empty. There's no need for additional container.

## ::Nil
Previously we discussed the following way to construct a list:

    val mylist = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

`::` is not a built-in operator. Its the name of class/method that is usually
called `Cons`. Moreover `::` is one of the rarest kind of operators that have
right-associative. That's why when we have `Nil` at the end of the construct
other `::`s will be appropriate because we've already created a list.
You may get more details from `Programming in scala` book. Furthermore `Nil` is
more idiomatic than `List.empty` or `List()`.

    scala> println (Nil == List())
    true

    scala> println (Nil eq List())
    true

    scala> println (Nil equals List())
    true

    scala> System.identityHashCode(Nil)
    374527572

    scala> System.identityHashCode(List())
    374527572


Further reading
===============
You may learn more about lists and other collections on the
[Twitter Scala School](tschool-col) website. Alvin Alexander wrote a bunch of
handy tutorials about lists. You may find them [here](aal1) [here](aal2) and
[here](aal3). Official [documentation](list-doc) is always useful. AN empty list
can be described with various notations, more info [here](empty-list)

[tschool-col]: https://twitter.github.io/scala_school/collections.html
[aal1]: http://alvinalexander.com/scala/scala-list-class-examples
[aal2]: http://alvinalexander.com/scala/how-create-scala-list-range-fill-tabulate-constructors
[aal3]: http://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable
[list-doc]: http://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[empty-list]: http://stackoverflow.com/questions/5981850/scala-nil-vs-list

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

Lenses
======
You can define sophisticated structures by nesting case-classes. And you will.
But sooner or later you will have a need to change a content of that structure.
Here's the simplest case possible:

    case class Dog (name: String, age: Int)

    val fido = Dog("Fido", 6)
    val olderFido = fido.copy(age = 7)

But what will you do in the following case?

    case class Address(street: String,
                       city: String,
                       postcode: String)

    case class Person(name: String, age: Int, address: Address)

Don't you think that manual object construction and deconstruction for
each specific is not an appropriate way of dealing with this issue?
Here lenses become useful.

Why those things were named that way? Because they can focus on something
really important. In our case on the specific part of your data structure.
As the result you will have an opportunity to update that structure.

Let's say you have a person who become a one year older. How we could
react on this change?

    val person = Person("Joe Grey", 37,
                        Address("Southover Street",
                                "Brighton", "BN2 9UA"))

    // Read a field
    val age1 = ageLens.get(person) // Type inferred is Int
    // age1 == 37

    // Update a field
    val person2 = ageLens.set(person)(38)
    // person2.age == 38

The same actions could be applied to an address field. As you have noticed
it's pretty simple. Unfortunately `Scala` doesn't have built-in lenses, that's
why you should use an external library that will provide you with this
functionality. We propose you to use `shapeless`. The example above was
implemented with help of that library.

There are various implementations of lenses for `Scala`. You may use
[scalaz][scalazl] or [monocle][monocle] if you with. Monocle provides you
with more advanced optics than any other `Scala` library. We definitely
recommend you to use it in future.

`Shapeless` is already included in your `CLASSPATH`, that's why we recommend
you to use it.

Further reading
===============
[Here][lenses-smpl] you may find an example that demonstrates lenses in
`shapeless`

[lenses-smpl]: https://github.com/milessabin/shapeless/blob/master/examples/src/main/scala/shapeless/examples/lenses.scala
[scalazl]:  http://eed3si9n.com/learning-scalaz/Lens.html
[monocle]: https://github.com/julien-truffaut/Monocle

Implicits
=========

There is more than on entity that could be annotated with `implicit` keyword
in `Scala`. Those entities could be implicitly passed, converted or enriched
with additional functionality (aka `extension methods`).


## Types of implicits
 - Implicit parameters. You may read about them [here][impl-parameters]
 - Implicit conversions. [Here][impl-conversions] and [here][impl-conversions-2]
   you may read about them.
 - Implicit classes (can be used to provide extension methods). More information
   [here][impl-classes]. They are represented as an `implicit` classes and
   objects.


### Implicit lookup
[More][impl-lookup] about implicit lookup.


## Implicits with lambdas and currying
Play Framework has a very popular construct:

    Action { implicit request =>
      Ok("ok: [" + request + "]")
    }

Let's figure out what implicit does there. The rewritten version of code above:

    Action { request =>
      implicit val r = request
      Ok("ok: [" + request + "]")
    }

The signature of `Ok` class, may look like this:

    class Ok(message: String)(implicit r: Request).

The `implicit` keyword can be used only for *the last* curried argument.
It denotes the spot where an implicit parameter can be placed. That's how it
will look like without implicits:

    Action { request =>
      Ok("ok: [" + request + "]")(request)
    }


## Conclusion
In the most cases implicits are not what you want. This language construct was
intendend for `DSL` developers (Domain Specific Languages). Another usage is
extension-methods. There's an [article][pimp-my-lib] explaining that approach.

[pimp-my-lib]: http://www.artima.com/weblogs/viewpost.jsp?thread=179766
[impl-conversions]: http://docs.scala-lang.org/tutorials/tour/implicit-conversions
[impl-conversions-2]: http://baddotrobot.com/blog/2015/07/14/scala-implicit-functions/
[impl-parameters]: http://baddotrobot.com/blog/2015/07/03/scala-implicit-parameters/
[impl-classes]: http://docs.scala-lang.org/overviews/core/implicit-classes.html
[impl-lookup]: http://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

Type classes
============

Type class is a design-pattern that allows you to implement ad-hoc polymorphism
in `Scala`. In some languages (like `Haskell`) type classes are embedded inside
the language syntax. In this section we will illustrate the implementation of
type classes in `Scala`.

To make the long story short: type class gives you a capacity to describe
object's behavior (by its type) without
 - changing the object's source code
 - extending that object
Let's illustrate that by the following example:

**Inheritance polymorphism**

    trait Barks {
      def bark = "Wow!"
    }

    // Barks should be added to the class
    class Dog(tag: String) extends Animal with Barks { ... }

    // somewere in your source code
    val jason = new Dog("Jason")
    println(jason.bark)

**Ad-hoc polymorphism**

    // We won't touch that dog. It doesn't bark but still bites.
    class Dog(tag: String) extends Animal

    // using simulacrum libraty that provides us with neat syntax
    @typeclass trait Barks[D] {
      def bark: String
    }

    // implementing a method for our Dog type
    implicit object DogBarking extends Barks[Dog] {
        def bark = "Wow, ladies"
    }

    val james = new Dog("James")
    // Here's the simulacrum code that
    // allowes you to use `bark` as it was part of the object
    println(james.bark)

It means that the corresponding instance of `Barks` will be chosen accordingly
with method implementation.

Type class pattern follows the "[Open-closed principle][open-closed]" -
"Open for extension, closed for modification".


Type classes in standard library
================================
Despite the fact that in `Scala` type classes are not submitted as syntactic
construct, they are widely used as a design-pattern. [Ordering][ordering] can be
a good example. Note that `Ordering` is more idiomatic than [Ordered][ordered].
`Ordering` gives you more flexibility: you can create any order you want and
apply it where you want. Implicitly or explicitly. An instance of ordering can
be passed inside a method in the following way:

    def sort [T] (list: List[T]) (implicit ord: Ordering[T]): List[T] = {
      ...
      // calling one of ordering's methods here
      ord.gt(x,y)
      ...
    }

Defining `Ordering` for arbitrary data type:

    case class Item (id: Long, name: String, size: Dimension)

    // default ordering (that's why we've made it implicit)
    implicit val byId: Ordering[Item] = Ordering.by(_.id)

    // another option
    val byName: Ordering[Item] = Ordering.by(_.name)

And now we can sort some stuff:

    sort(items) // will be sorted by id (default case)
    sort(items)(byName) // will be sorted by name

More details about `Ordered` and `Ordering` you may get [here][both-ords].


Further reading
===============
Now, when you have a basic understanding of type classes, we recommend you to
watch the video materials listed below, where you can learn how this pattern
could be implemented in `Scala`. More theory you may find [here][tc-0].
For practical part you may use [Simulacrum][simulacrum]. You can also use the
standard type-classes from `scalaz` or `cats`.

Videos
======

1. [Tutorial: Typeclasses in Scala](https://www.youtube.com/watch?v=sVMES4RZF-8)
2. [The Typeclass Pattern - An Alternative to Inheritance](https://www.youtube.com/watch?v=CCsGHPxA9E0)

[ordering]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordering.html
[ordered]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordered.html
[both-ords]: http://like-a-boss.net/2012/07/30/ordering-and-ordered-in-scala.html
[tc-0]: https://engineering.sharethrough.com/blog/2015/05/18/type-classes-for-the-java-engineer/
[open-closed]: https://en.wikipedia.org/wiki/Open/closed_principle
[simulacrum]: https://github.com/mpilquist/simulacrum

Exceptions revisited
====================
`Scala` supports various ways of exception handling. Since 2.10 `Scala` has a
`scala.util.Try`. There's another more functional class called `Either`.

[Here][Try] you may read more about `scala.util.Try`. Here's a
[review][error-handling-in-scala] of the most popular ways of exception handling
in `Scala`.

[More][scala-either] [details][scala-either-2] about `Either`.

[Try]: http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html
[error-handling-in-scala]: https://tersesystems.com/2012/12/27/error-handling-in-scala/
[scala-either]: http://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null
[scala-either-2]: http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html

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

Lenses
======
You can define sophisticated structures by nesting case-classes. And you will.
But sooner or later you will have a need to change a content of that structure.
Here's the simplest case possible:

    case class Dog (name: String, age: Int)

    val fido = Dog("Fido", 6)
    val olderFido = fido.copy(age = 7)

But what will you do in the following case?

    case class Address(street: String,
                       city: String,
                       postcode: String)

    case class Person(name: String, age: Int, address: Address)

Don't you think that manual object construction and deconstruction for
each specific is not an appropriate way of dealing with this issue?
Here lenses become useful.

Why those things were named that way? Because they can focus on something
really important. In our case on the specific part of your data structure.
As the result you will have an opportunity to update that structure.

Let's say you have a person who become a one year older. How we could
react on this change?

    val person = Person("Joe Grey", 37,
                        Address("Southover Street",
                                "Brighton", "BN2 9UA"))

    // Read a field
    val age1 = ageLens.get(person) // Type inferred is Int
    // age1 == 37

    // Update a field
    val person2 = ageLens.set(person)(38)
    // person2.age == 38

The same actions could be applied to an address field. As you have noticed
it's pretty simple. Unfortunately `Scala` doesn't have built-in lenses, that's
why you should use an external library that will provide you with this
functionality. We propose you to use `shapeless`. The example above was
implemented with help of that library.

There are various implementations of lenses for `Scala`. You may use
[scalaz][scalazl] or [monocle][monocle] if you with. Monocle provides you
with more advanced optics than any other `Scala` library. We definitely
recommend you to use it in future.

`Shapeless` is already included in your `CLASSPATH`, that's why we recommend
you to use it.

Further reading
===============
[Here][lenses-smpl] you may find an example that demonstrates lenses in
`shapeless`

[lenses-smpl]: https://github.com/milessabin/shapeless/blob/master/examples/src/main/scala/shapeless/examples/lenses.scala
[scalazl]:  http://eed3si9n.com/learning-scalaz/Lens.html
[monocle]: https://github.com/julien-truffaut/Monocle

Implicits
=========

There is more than on entity that could be annotated with `implicit` keyword
in `Scala`. Those entities could be implicitly passed, converted or enriched
with additional functionality (aka `extension methods`).


## Types of implicits
 - Implicit parameters. You may read about them [here][impl-parameters]
 - Implicit conversions. [Here][impl-conversions] and [here][impl-conversions-2]
   you may read about them.
 - Implicit classes (can be used to provide extension methods). More information
   [here][impl-classes]. They are represented as an `implicit` classes and
   objects.


### Implicit lookup
[More][impl-lookup] about implicit lookup.


## Implicits with lambdas and currying
Play Framework has a very popular construct:

    Action { implicit request =>
      Ok("ok: [" + request + "]")
    }

Let's figure out what implicit does there. The rewritten version of code above:

    Action { request =>
      implicit val r = request
      Ok("ok: [" + request + "]")
    }

The signature of `Ok` class, may look like this:

    class Ok(message: String)(implicit r: Request).

The `implicit` keyword can be used only for *the last* curried argument.
It denotes the spot where an implicit parameter can be placed. That's how it
will look like without implicits:

    Action { request =>
      Ok("ok: [" + request + "]")(request)
    }


## Conclusion
In the most cases implicits are not what you want. This language construct was
intendend for `DSL` developers (Domain Specific Languages). Another usage is
extension-methods. There's an [article][pimp-my-lib] explaining that approach.

[pimp-my-lib]: http://www.artima.com/weblogs/viewpost.jsp?thread=179766
[impl-conversions]: http://docs.scala-lang.org/tutorials/tour/implicit-conversions
[impl-conversions-2]: http://baddotrobot.com/blog/2015/07/14/scala-implicit-functions/
[impl-parameters]: http://baddotrobot.com/blog/2015/07/03/scala-implicit-parameters/
[impl-classes]: http://docs.scala-lang.org/overviews/core/implicit-classes.html
[impl-lookup]: http://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

Type classes
============

Type class is a design-pattern that allows you to implement ad-hoc polymorphism
in `Scala`. In some languages (like `Haskell`) type classes are embedded inside
the language syntax. In this section we will illustrate the implementation of
type classes in `Scala`.

To make the long story short: type class gives you a capacity to describe
object's behavior (by its type) without
 - changing the object's source code
 - extending that object
Let's illustrate that by the following example:

**Inheritance polymorphism**

    trait Barks {
      def bark = "Wow!"
    }

    // Barks should be added to the class
    class Dog(tag: String) extends Animal with Barks { ... }

    // somewere in your source code
    val jason = new Dog("Jason")
    println(jason.bark)

**Ad-hoc polymorphism**

    // We won't touch that dog. It doesn't bark but still bites.
    class Dog(tag: String) extends Animal

    // using simulacrum libraty that provides us with neat syntax
    @typeclass trait Barks[D] {
      def bark: String
    }

    // implementing a method for our Dog type
    implicit object DogBarking extends Barks[Dog] {
        def bark = "Wow, ladies"
    }

    val james = new Dog("James")
    // Here's the simulacrum code that
    // allowes you to use `bark` as it was part of the object
    println(james.bark)

It means that the corresponding instance of `Barks` will be chosen accordingly
with method implementation.

Type class pattern follows the "[Open-closed principle][open-closed]" -
"Open for extension, closed for modification".


Type classes in standard library
================================
Despite the fact that in `Scala` type classes are not submitted as syntactic
construct, they are widely used as a design-pattern. [Ordering][ordering] can be
a good example. Note that `Ordering` is more idiomatic than [Ordered][ordered].
`Ordering` gives you more flexibility: you can create any order you want and
apply it where you want. Implicitly or explicitly. An instance of ordering can
be passed inside a method in the following way:

    def sort [T] (list: List[T]) (implicit ord: Ordering[T]): List[T] = {
      ...
      // calling one of ordering's methods here
      ord.gt(x,y)
      ...
    }

Defining `Ordering` for arbitrary data type:

    case class Item (id: Long, name: String, size: Dimension)

    // default ordering (that's why we've made it implicit)
    implicit val byId: Ordering[Item] = Ordering.by(_.id)

    // another option
    val byName: Ordering[Item] = Ordering.by(_.name)

And now we can sort some stuff:

    sort(items) // will be sorted by id (default case)
    sort(items)(byName) // will be sorted by name

More details about `Ordered` and `Ordering` you may get [here][both-ords].


Further reading
===============
Now, when you have a basic understanding of type classes, we recommend you to
watch the video materials listed below, where you can learn how this pattern
could be implemented in `Scala`. More theory you may find [here][tc-0].
For practical part you may use [Simulacrum][simulacrum]. You can also use the
standard type-classes from `scalaz` or `cats`.

Videos
======

1. [Tutorial: Typeclasses in Scala](https://www.youtube.com/watch?v=sVMES4RZF-8)
2. [The Typeclass Pattern - An Alternative to Inheritance](https://www.youtube.com/watch?v=CCsGHPxA9E0)

[ordering]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordering.html
[ordered]: http://www.scala-lang.org/api/2.12.0/scala/math/Ordered.html
[both-ords]: http://like-a-boss.net/2012/07/30/ordering-and-ordered-in-scala.html
[tc-0]: https://engineering.sharethrough.com/blog/2015/05/18/type-classes-for-the-java-engineer/
[open-closed]: https://en.wikipedia.org/wiki/Open/closed_principle
[simulacrum]: https://github.com/mpilquist/simulacrum

Exceptions revisited
====================
`Scala` supports various ways of exception handling. Since 2.10 `Scala` has a
`scala.util.Try`. There's another more functional class called `Either`.

[Here][Try] you may read more about `scala.util.Try`. Here's a
[review][error-handling-in-scala] of the most popular ways of exception handling
in `Scala`.

[More][scala-either] [details][scala-either-2] about `Either`.

[Try]: http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html
[error-handling-in-scala]: https://tersesystems.com/2012/12/27/error-handling-in-scala/
[scala-either]: http://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null
[scala-either-2]: http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html

Conclusion
==========
`Scala` -- is very sophisticated language, and it's not possible to comprise all
details of syntax and semantics of the language. Honestly, we didn't aim to do
it. The purpose of this course is to let you think functionally.

`Scala` is an object oriented language. Everything in `Scala` is an object. Even
functions, that is an instance of `FunctionN` class (where `N` is function's
arity). Scala rich its object-oriented features and we expect that you're
already familiar with concepts of `OOP`, that's why those features weren't
covered.


`Scala` is a functional language to. Some functional languages lacks a number
of features that `Scala` gives you. For example, `Scala` supports currying that
is not supported by `Erlang`.

Wish you luck in your future endeavors.


Further reading
===============
`Scala` has it's own [Scala Style Guide][style-guide]. We also recommend you
yo watch an amazing talk given by Martin Odersky (the creator of `Scala`):
[Scala with Style][scala-with-style] and [style guide][databricks-guide] by
`Databricks`. You should also read [Effective Scala][effective-scala]. It's not
that big as `Effective Java` if you're familiar with that. It's a short article
that worth reading for every `Scala` developer.

Books
=====

  - Scala for the Impatient (2nd Edition) by Cay S. Horstmann -- you may find it
  [here][scala-impatient]
  - Programming in Scala by Martin Odersky, Lex Spoon & Bill Venners.
  This book can be called a `Magnum Opus`. It explains all language details in
  a nutshell. You may find it [here][magnum-opus]

There is a great amount of decent books available. But those books didn't
keep up with changes in `2.12`. I would also recommend `Atomic Scala` when
it will be synchronized with the latest version.

[style-guide]: http://docs.scala-lang.org/style/
[effective-scala]: https://twitter.github.io/effectivescala/
[scala-with-style]: https://www.youtube.com/watch?v=kkTFx3-duc8
[databricks-guide]: https://github.com/databricks/scala-style-guide
[scala-impatient]: https://www.amazon.com/Scala-Impatient-2nd-Cay-Horstmann/dp/0134540565/
[magnum-opus]: https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687

Conclusion
==========
`Scala` -- is very sophisticated language, and it's not possible to comprise all
details of syntax and semantics of the language. Honestly, we didn't aim to do
it. The purpose of this course is to let you think functionally.

`Scala` is an object oriented language. Everything in `Scala` is an object. Even
functions, that is an instance of `FunctionN` class (where `N` is function's
arity). Scala rich its object-oriented features and we expect that you're
already familiar with concepts of `OOP`, that's why those features weren't
covered.


`Scala` is a functional language to. Some functional languages lacks a number
of features that `Scala` gives you. For example, `Scala` supports currying that
is not supported by `Erlang`.

Wish you luck in your future endeavors.


Further reading
===============
`Scala` has it's own [Scala Style Guide][style-guide]. We also recommend you
yo watch an amazing talk given by Martin Odersky (the creator of `Scala`):
[Scala with Style][scala-with-style] and [style guide][databricks-guide] by
`Databricks`. You should also read [Effective Scala][effective-scala]. It's not
that big as `Effective Java` if you're familiar with that. It's a short article
that worth reading for every `Scala` developer.

Books
=====

  - Scala for the Impatient (2nd Edition) by Cay S. Horstmann -- you may find it
  [here][scala-impatient]
  - Programming in Scala by Martin Odersky, Lex Spoon & Bill Venners.
  This book can be called a `Magnum Opus`. It explains all language details in
  a nutshell. You may find it [here][magnum-opus]

There is a great amount of decent books available. But those books didn't
keep up with changes in `2.12`. I would also recommend `Atomic Scala` when
it will be synchronized with the latest version.

[style-guide]: http://docs.scala-lang.org/style/
[effective-scala]: https://twitter.github.io/effectivescala/
[scala-with-style]: https://www.youtube.com/watch?v=kkTFx3-duc8
[databricks-guide]: https://github.com/databricks/scala-style-guide
[scala-impatient]: https://www.amazon.com/Scala-Impatient-2nd-Cay-Horstmann/dp/0134540565/
[magnum-opus]: https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687

The final assignment
====================

What about your own implementation of [tree][Tree] utility? If you're familliar with it you might think that it will be quite easy, but again: the pleasure of learning comes with a struggle.

 - If tree is run with no arguments at all it will print the whole file system tree from the current directory. 
 - if tree receives the '-L' argument with the following number given, like: 'tree -L N' it will print N levels of file system. 
 - filesystem path can be also passed in as an argument and combined with '-L'. 

You should use the library you've built in the previous project. If you'd like to have more control over your library, you're free to create an additional utility class in your current project and use extension methods to enrich it's behaviour. You will love using implicit classes for that. Write your own Immutable Tree implementation that will suit the best for file system representation. Further it will be given to FileSystemTreeRenderer class for rendering. 

Create two classes like FileSystemTreeWalker and FileSystemTreeRenderer. Use [MacWire][MacWire] to construct the instances of each. And please don't forget about unit tests. 

[Tree]: https://linux.die.net/man/1/tree
[MacWire]: https://github.com/softwaremill/macwire
