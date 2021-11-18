Scala beans
===========

`Scala` has a good interoperability with `Java`. It also could ease your job
when you even designing Java beans. For those who are not familiar with `Java`,
you may read about beans [here][java-beans]. There are some features of `Scala`
that will make your life much easier.


## Getters and setters
`Scala` has its own mechanism similar to [Project Lombok][project-lombok]. The
mechanism is called `BeanProperty` and it's built into the standard library.
All you need to do is to create a bean and add `@BeanProperty` each field that
should have its own getter and setter.
[Here][bean-property-alvin] you will learn about that property. The
`BeanProperty` documentation can be found [here][bean-property-doc].

If you want to generate getter and setter for boolean variable.
[This][bool-prop] class may come handy. As the result you will get a method that
looks like this one: `isProperty`.


## Uninitialized values
`Scala` also supports uninitialized values. You may need this feature when
creating your beans. Let's take a look at `Java` example below:

    class MyClass {
        // By default any subtype of Object is initialized to null
        // primitive types are initialized with their default values
        String uninitialized;
    }

The same stuff can be applied to `Scala`:


    class {
      // underscore syntax tells scala that the field will not be initialized
      var uninitialized: String = _
    }

But please **Don't do this**. Initialize values were you can. This feature must
be used only if you forced by library or framework you are using. You may
receive lots of `NullPointerException`s as the result. You should know about
this feature because it might save you some time. If you want to delay the
initialization you should use `lazy`.

[java-beans]: https://en.wikipedia.org/wiki/JavaBeans
[project-lombok]: https://projectlombok.org/
[bean-property-doc]: https://www.scala-lang.org/api/2.12.0/scala/beans/BeanProperty.html
[bean-property-alvin]: http://alvinalexander.com/scala/how-to-create-scala-javabeans-beanproperty-java-libraries
[bean-property-illustrated]: https://daily-scala.blogspot.ru/2009/09/beanproperties.html
[bool-prop]: http://www.scala-lang.org/api/2.12.0/scala/beans/BooleanBeanProperty.html

