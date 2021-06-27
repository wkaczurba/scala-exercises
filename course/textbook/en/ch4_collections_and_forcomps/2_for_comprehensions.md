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

