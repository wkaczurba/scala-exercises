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

