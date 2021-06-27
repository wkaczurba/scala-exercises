High order functions/ Currying / Closures
=========================================

## Introduction
As you may previously noticed `Scala` supports two ways of
[type definition][types-fun-vals] for functions:

 - object-oriented: `f: Function2[Int, String, String]`
 - functional way: `f: (Int, String) => String`

Usage of the specific type depends on the situation. However reading and
understanding the type is complicated in both cases. It's a common practice to
alias widely used types. Like:

    type Action = () => Unit

Above you may see a function that doesn't accept any argument nor doesn't return
any value. If you're using generics it's OK to use functional style for
`Function1` typed objects:

    def map[B](f: A => B) = ...

Type denotation makes sense for functional languages because is possible to
pass functions as arguments, and return them as values. We will cover this a bit
later.

## About good functions and their composition
A function (in lambda-calculus) must accept only one argument and return a
single value. No more, no less. It means that every function must have
[arity][arity] equal to 1. That's why some of functional languages lacks
functions that theoretically accept more then one argument. But practically they
do. Further this topic we will talk about functions of arity 1, because of their
properties.

[Function composition][fun-comp] is the simplest and the most fundamental way
of function combination. It's a main building block of functional programming.
The concept is simple output of the first function will be input for the second.
Let's take look at the functions below

    def inc (x: Int) = x + 1
    def dup (x: Int) = x * 2

We have the following aim -- create a function that accepts an integral number
adds one to it and then doubles that result.

    def incDup(x: Int) = dup(inc(x))

So, that was an example of function composition. Many functional languages
like `Haskell` support a special type of syntax for function composition:

    -- some code in Haskell. Defining the functions.
    Prelude> let inc x = x + 1
    Prelude> let dup x = x * 2

    -- A new function that is result of composition of previous functions
    -- syntax assumes that you are implicitly pass a single argument
    Prelude> let incDup = dup . inc

    -- let's check the result
    Prelude> incDup 3
    8

Let's take a look how the same concept was implemented in `SML`:

    (* Defining the functions *)
    - fun inc(x: int) = x + 1;
    val inc = fn : int -> int

    - fun dup(x: int) = x * 2;
    val dup = fn : int -> int

    (* Implicit argument passsage is assumed by language's syntax *)
    - val incDup = dup o inc;
    val incDup = fn : int -> int

    (* Checking the result *)
    - incDup 3;
    val it = 8 : int

`Scala` is not an exception. It also has the same operation which is applicable
only to `Function1` type. Let's rewrite the `incDup` function in `Scala`:

    // now Scala
    val incDup = dup _ compose inc _

    // Quite informative :)
    incDup: Int => Int = scala.Function1$$Lambda$1098/1854577712@5d01ea21

    // Checking the result
    scala> incDup(3)
    res0: Int = 8

As you may have noticed those constructions must be read from right to left.
For some people it may be confusing. You can use `andThen` method that changes
the order of function application:

    scala> val incDup2 = inc _ andThen dup _
    incDup2: Int => Int = scala.Function1$$Lambda$1112/312470853@23592946

    // Проверяем еще раз
    scala> incDup2(3)
    res4: Int = 8


## Anonymous functions aka lambda expressions
[Here][lambda-0] you may read about [anonymous][lambda-1] functions (aka
lambdas). All modern programming languages support those constructs. That's why
we assume that you have a basic understanding of the concept. It's quite popular
to use `Underscore syntax` in lambda expressions (which in the most cases is
not good practice). You may read about underscore syntax
[here][underscore-syntax].


## High order functions
A function can be called [high order][high-order-0] if it satisfies at least one
of the given conditions:

 - it accepts another function as an argument
 - returns another function

Let's take a closer look at the first case. Imagine that we should write a small
application that draws a plot for a given function. It may look like this:

    type Point = (Double, Double)

    sealed trait Funname
    case object Sinus extends Funname
    case boject Cosinus extends Funname

    val xs = -10.0 to 10.0 by 0.1

    // Imagine that we have a function that draws a chart by given set of points
    def plot(points: Seq[Point]) = ???

    def plotFunction(name: Funname) = name match {
      case Sinus =>
        // get list of evaluated values
        val ys = (xs map math.sin)
        // glue xs and ys together
        val coords = xs zip ys
        // plot them
        plot(coords)
      case Cosinus =>
        // the same should be done here...
    }

Having a `switch` statement is worst idea ever. To much duplicating code. If
only we could pass a function itself...

At first, let's define a signature for `plot` function. In our case it will
accept the only argument:

    type Plotfun = Double => Double

    // That's it
    def plotFunction(fun: Plotfun): Unit = {
      val ys = xs map fun
      val coords = xs zip ys
      plot(coords)
    }

    // let's create a stub for plot function that will print the results
    def plot(coords: Seq[Point]) =
      coords.take(5) foreach println

    // and check our results
    scala> plotFunction(math.sin)
    (-10.0,0.5440211108893698)
    (-9.5,0.0751511204618093)
    (-9.0,-0.4121184852417566)
    (-8.5,-0.7984871126234903)
    (-8.0,-0.9893582466233818)

    scala> plotFunction(math.cos)
    (-10.0,-0.8390715290764524)
    (-9.5,-0.9971721561963784)
    (-9.0,-0.9111302618846769)
    (-8.5,-0.6020119026848236)
    (-8.0,-0.14550003380861354)

As you may see the `math.cos` and `math.sin` functions work in this `plot`.
The code will accept any function that satisfies the `Double => Double` type.

When should we return another function?
The most common case is currying, which will be explained later in this topic.
More examples can be found [here][high-order-2]. You may also get more
information about high order functions [here][high-order-1].


## Currying
A [technique][curry-0], that represents multi-argument function as a chain of
single argument functions. In some functional languages like `Haskell` all
functions are curried by default. Currying is quite complicated and it may time
some time of object-oriented programmer to get used to it. But trust me it's
simple and worth every minute of your time. It's not mandatory to use this
technique everywhere you can. But there are many libraries out there which code
you may use. And sometimes you have to understand that code. Currying is pretty
popular among library designers.

> Currying represents the following process: A single multi-argument function
> is segregated to chained single argument function calls. Let's say that out
> multi-argument function accepts n arguments. So every next call we take -1
> argument: n-1 for the first case till the last one when a single element left.

Before you perform a deep-dive into currying we'd like to show you an
alternative way to describe function signatures in `Scala` and other functional
languages:

    // This function has String => String type
    // function accepts a single string argument and returns another string
    def sayMyName(name: String): String = "Your name: name"

    // Type of this function is (String, String) => String
    def concat(first: String, second: String) = first + second

Any function that accepts more then one element can be represented as a chain
of function that accept one argument:

    // Let's rewrite this function by using currying notation
    // Type of this function should be String => String => String
    def concat(first: String)(second: String) = first + second

    // And let's check whether it works.
    сoncat("Hello")(" world")
    // res1: String = Hello world

Currying saves us from writing lots of duplicates.

    // Says "Hello" to any logged in user:
    def sayHello(name: String): String = ???

And of course we can implement this function like:

    def sayHello(name: String) = "Hello " + name

but we can use a more generic function which is called `concat`:

    val greet = concat("Hello ")_
    // greet: String => String = $$Lambda$1170/364266169@518bfd90

What did we get? Instead of `(String, String) => String` we obtained
`String => String`

    greet("Robert")
    // res8: String = Hello Robert

There are many posts and articles about currying, but we would recommend
[this one][curry-1]. A good explanation can be found [here][curry-2],
[here][curry-3] and [here][curry-5]. [Here][curry-4] You may read how it's
implemented in byte code.


## Closures
You may read about `Scala` closures [here][closures-0] and [here][closures-1].
There's an [article][closures-2] that you may find useful.


Further reading
===============
 - More about function composition you may read on [Twitter Scala School][ss-pm]
   website


[types-fun-vals]: http://docs.scala-lang.org/style/types.html#function-values
[arity]: https://en.wikipedia.org/wiki/Arity
[ss-pm]: http://twitter.github.io/scala_school/pattern-matching-and-functional-composition.html#composition
[fun-comp]: https://en.wikipedia.org/wiki/Function_composition_(computer_science)
[underscore-syntax]: http://stackoverflow.com/a/7678951/1655785

[curry-0]: https://en.wikipedia.org/wiki/Currying
[curry-1]: http://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html
[curry-2]: https://en.wikibooks.org/wiki/Scala/Currying
[curry-3]: http://docs.scala-lang.org/tutorials/tour/currying.html
[curry-4]: http://alvinalexander.com/scala/scala-curried-partially-applied-functions-how-compiled-scalac
[curry-5]:  http://fruzenshtein.com/scala-currying-functions/

[closures-0]: http://alvinalexander.com/scala/how-to-use-closures-in-scala-fp-examples
[closures-1]: http://www.slideshare.net/knoldus/functions-closures
[closures-2]: http://openhome.cc/eGossip/Blog/UnderstandingLambdaClosure4.html

[lambda-0]: http://docs.scala-lang.org/tutorials/tour/anonymous-function-syntax.html
[lambda-1]: https://en.wikipedia.org/wiki/Anonymous_function

[high-order-0]: https://en.wikipedia.org/wiki/Higher-order_function
[high-order-1]: http://docs.scala-lang.org/tutorials/tour/higher-order-functions
[high-order-2]: http://fruzenshtein.com/scala-higher-order-anonymous-functions/

