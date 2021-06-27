Initialization strategies
==========================
There are two [initialization strategies][init]: eager and [lazy][lazy].
Many object oriented languages support "Delayed Initialization" design
[pattern][delayed]. This pattern is built inside `Scala` and you can activate
it with `lazy` keyword. Eager initialization is the default one. There are some
lazy languages (languages with lazy initialization by default), `Haskell` is a
great example.


Further reading
===============
A very generic [article][impl-laziness] about lazy computations. What's under
the hood of `Scala`'s implementation you may learn [here][lazy-vals-under-hood].

[init]: https://en.wikipedia.org/wiki/Initialization_(programming)
[lazy]: https://en.wikipedia.org/wiki/Lazy_initialization
[delayed]: http://www.martinfowler.com/bliki/LazyInitialization.html

[impl-laziness]: http://matt.might.net/articles/implementing-laziness/
[lazy-vals-under-hood]: https://blog.codecentric.de/en/2016/02/lazy-vals-scala-look-hood/

