Before you begin
================

Scala is the one of many languages that work on the top of Java Runtime
Environment. Scala works on JVM, uses Java classes, and smoothly interoperates
with them. That's why we should install Java first. The course materials are
designed for `Scala` 2.12, and JDK 1.8. So, some of the samples may not work
properly on earlier versions of those platforms.

## Install JDK
JDK (Java Development Kit) will be required for further `Scala` development.
You can find all the required installation instructions
[here][jdk-install-overview]. The same instructions may work for various
`JDK` versions. `Open JDK` will also work fine. So, if you are Linux user
you may easily install `Open JDK` package. And deal with it.

## Check installed version of Java
After `JDK` installation `java` and `javac` should be added to your `$PATH`

    # for windows:
    \>java –version
    Java version "1.8.0_31"
    Java (TM) SE Run Time
    Environment (build 1.8.0_31-b31)
    Java Hotspot (TM) 64-bit Server
    VM (build 25.31-b07, mixed mode)


    # forLinux
    [~] java -version
    openjdk version "1.8.0_102"
    OpenJDK Runtime Environment (build 1.8.0_102-b14)
    OpenJDK 64-Bit Server VM (build 25.102-b14, mixed mode)

All listed outputs platforms work well for us.

## Add JAVA_HOME variable to your environment
You can read [here][java-home-windows], how to make it work on Windows.
If you are proud [`Linux`][java-home-linux]or OS X user you can export it to
`bash.rc`.


## Install SdkMan!
Of course you can waste your time on manual installations. But we propose
you a better solution — a platform that can help you install and manage
different software components designed to run on JVM.
`SDKMAN!` - supports `Scala`, `Clojure`, `Groovy` and `Kotlin`. It also
supports various build systems, from `Maven` to `sbt`. For those who
familiar with ruby or python `SDKMAN!` works the same way as `RVM` or `pyenv`.

`SDKMAN!` installation guide can be found [here][sdkman].

After the installation you must restart your shell, or open a new tab. In the
example below, we're installing the latest version of `Scala` and `sbt` (Simple
build tool)

    # in your shell
    # the latest version of scala will be installed. You can also
    # specify the version you want
    $ sdk install scala

    Downloading: scala 2.12.0

    In progress...

    ############################################################## 100.0%

    Installing: scala 2.12.0
    Done installing!

    Do you want scala 2.12.0 to be set as default? (Y/n): y

    Setting scala 2.12.0 as default.


When `Scala` is installed, let's install `sbt`:

    [~] sdk install sbt
    Downloading: sbt 0.13.13

    In progress...

    ############################################################## 100.0%

    Installing: sbt 0.13.13
    Done installing!

    Do you want sbt 0.13.13 to be set as default? (Y/n): y

    Setting sbt 0.13.13 as default.

That's it the stuff is up and running.


## Which IDE I should use?
There are various `IDE`s that support `Scala`. Here I will describe pros and
cons of the most notorious of them.

IntelliJ IDEA Community Edition supports `Scala` and `Dotty`. And we do
recommend is as your default IDE. You can also use `ScalaIDE` or `ENSIME`. You
can also use `Vim`.

> If you're eager to install Scala-IDE, the easiest way to do it is do
> download pre-build IDE (by clicking 'Download IDE' button), or follow
> the link below. You can also install it as a plugin for Eclipse, if you
> know what you are doing :)

IntelliJ IDEA: https://www.jetbrains.com/idea/specials/idea/idea.html
Scala-IDE: http://scala-ide.org/download/sdk.html
Ensime: https://github.com/ensime


### Pros and Cons of your IDE choice
By choosing IntelliJ you will get the best `Scala` support ever, tons of plugins
and acceptable performance. It works faster than Eclipse (by my personal
experience). But if you want to have `Play framework` support you should
consider buying full (Ultimate) version. You can still develop play apps inside
Community version with some lack of comfort. If you're a happy owner of Ultimate
version, for you the choice is obvious.

By choosing Scala IDE you will receive Eclipse with great official Scala support.
Eclipse is a free platform that has lots of different plugins (free and
commercial). And the most important thing is: you receive the official support
for Play Framework.

ENSIME - for those who like Emacs or Vim. It has good Scala support. No more
no less.

[jdk-install-overview]: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
[java-home-windows]: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
[java-home-linux]: http://askubuntu.com/questions/175514/how-to-set-java-home-for-java/175519#175519
[sdkman]: http://sdkman.io/install.html

