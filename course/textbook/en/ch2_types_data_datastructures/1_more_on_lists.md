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

