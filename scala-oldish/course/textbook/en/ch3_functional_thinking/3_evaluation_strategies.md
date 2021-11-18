Evaluation strategies
=====================
A [wikipedia][eval-strategy] article about the subject.


Call by reference/value
========================
In `Scala` works the same way as it works for `Java` and `C#`. All subtypes of
[`AnyRef`][anyref] are passed by reference. All subtypes of [`AnyVal`][anyval]
are passed by value. All those arguments will be evaluated right before the
function call. That what makes both different from Call by name.


Call by name
============
Evaluation strategy where function's argument is evaluated each time it used
inside function's body. There's a best (in my humble opinion)
[article][call-by-name] that explains this strategy. There's also good a good
explanation [here][scala-by-name-par].


Call by need
============
Evaluation strategy where arguments are evaluated only during the first usage
inside the function body. You may think about it as [memoized][memoization]
variant of Call by name. It's not used in `Scala` implicitly but can be
emulated.


Call by future
==============
Evaluation strategy where arguments are evaluated concurrently inside the
function body. `Scala` has `Futures` so it also adopts this strategy.

[eval-strategy]: https://en.wikipedia.org/wiki/Evaluation_strategy
[anyref]: http://www.scala-lang.org/api/current/scala/AnyRef.html
[anyval]: http://www.scala-lang.org/api/current/scala/AnyVal.html
[call-by-name]: https://tpolecat.github.io/2014/06/26/call-by-name.html
[scala-by-name-par]: http://locrianmode.blogspot.ru/2011/07/scala-by-name-parameter.html
[memoization]: https://en.wikipedia.org/wiki/Memoization

