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

