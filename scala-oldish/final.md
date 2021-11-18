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
