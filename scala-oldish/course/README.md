Functional programming principles in Scala
==========================================

## What this course is all about?
This course is not only about Scala. Scala is complicated language: which means
that it will be impossible to cover all syntactic features in the scope of this
course. And we're not even trying. If you wish to learn all syntactic elements
of the language, you'd better find a huge book for the job.

The target of this course is to teach you some basics of functional thinking. Because it is
the most sophisticated thing you may possibly face with, while programming in Scala.
Scala is widely adopted language in the industry. It resembles parts of classical
Object-oriented programming and elements of Functional programming. It has glide learning curve.
That's why Scala is used as a host language for this course.


## Preconditions
We assume that you learned the basics of Object-Oriented programming. We expect
you to know the differences between classes and objects, abstract classes and
interfaces, knowledge of some of the "Gang of four" patterns like singleton or factory.
It could be great if you already know what is lambda expression or high-order
functions. But this knowledge is not required.
If you know the various differences between different types of morphisms. If you
know differences between isomorphism and catamorphism, you better to check
another course. And yes. It was the last time I used those words. Trust me!


## The course structure and organization
The course consists of three parts: textbook, codebook and this page which contains studying plan.
Textbook contains a number of chapters that are represented as markdown files.
Each chapter contains a number of parts that contain theoretical materials. Some chapters contain
assignments.

  1. the Textbook consists of
    - a number of topics that contain theoretical materials that will be enough
      to complete the practical part of the course.
    - hyperlinks to various resources where you can read more about the topic
  2. Code book contains a set of examples and stubs for the first two assignments.


## The timeline
It shouldn't take more than 2-3 weeks to complete the course with it's assignments.
The Studying plan contains a bird eye's view on the whole program, please check it
first.

## How to view the course materials.
To view `Markdown` text you can:

  - View rendered text on `Github`
  - Read those documents as `plain text`. Markdown is human-readable format, so
    I don't think that it will be problematic.
  - You can also find an [offline viewer][markdown-tools] that suit your needs.

You can also use the script that is located inside `assembly` directory to
build HTML file. But you must have UNIX environment and `pandoc` installed.

## Exercises
Each assignment should be arranged in a separate project. Code book doesn't assume
that you will continue your work there. It should stay immutable :)


## The studying plan
Before you start completing the exercises you should definitely read the course
material. We scheduled all the materials by weeks:

Week 1:
  - [Chapter 0. Beginning Scala](textbook/en/ch0_beginning_scala)
  - [Assignment 1: The word counter](textbook/en/ch0_beginning_scala/assignment.md)
  - [Chapter 1. Pattern matching](textbook/en/ch1_pattern_matching)
  - [Chapter 2. Types, Data, Data Structures](textbook/en/ch2_types_data_datastructures)
  - [Chapter 3. Functional Thinking](textbook/en/ch3_functional_thinking)
  - [Chapter 4. Collections and Comprehensions](textbook/en/ch4_collections_and_forcomps)
  - [Assignment 2: Your small collection library](textbook/en/ch4_collections_and_forcomps/assignment.md)

Week 2:
  - [Chapter 5. Out of standard library](textbook/en/ch5_out_of_standard_library)
  - [Chapter 6. Conclusion](textbook/en/ch6_conclusion)
  - [Assignment 3: Trees](textbook/en/ch6_conclusion/assignment.md)

And now, let's take a closed look to each chapter:

### Chapter 0. Beginning Scala
Here you will learn the basics of Scala. And I am talking about the syntax. When you
done, you can implement a simple and quite famous `CLI` application.

### Chapter 1. Pattern Matching
This chapter will give you better understanding of the most powerful syntactic
features of `Scala` (and other functional languages). For many of you it may
look like a simple variation of `switch/case` statement that behaves like an
expression. It is true and false. Pattern matching is much more complicated than
you might think.

### Chapter 2. Types, Data, Data Structures
Lots of languages contain compound data structures, like lists or tuples.
`Scala` is not an exception. You will also learn about Scala's version of
records. They used to be called `case class`.

### Chapter 3. Functional Thinking
That's the place where you will learn about currying, immutability, function
composition and other basic concepts of functional programming

### Chapter 4. Collections and Comprehensions
In this chapter you will learn more about Scala's collections library. This
knowledge will help you to master `Apache Spark` which can be a
pretty useful tool in your arsenal. You will also learn more about
list-comprehensions aka generators. And at the end of this chapter there'll be an
assignment for you.

### Chapter 5. Out of standard library
In this chapter you will learn functional concepts which are not represented in
the Scala's standard library, like lenses, type classes or h-lists.
Here you will meet `Shapeless`, `cats` and `scalaz`. At least those will be
mentioned.

### Chapter 6. Conclusion
Contains a number of links with sources for further reading and the final
assignment.


## Feedback
If you have any questions you can ask them directly - just open an issue and we
will discuss it. I will be also glad to receive your pull-requests.

[markdown-tools]: http://mashable.com/2013/06/24/markdown-tools

