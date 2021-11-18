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
