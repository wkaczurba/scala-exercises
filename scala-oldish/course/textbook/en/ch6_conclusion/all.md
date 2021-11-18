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
