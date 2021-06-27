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

