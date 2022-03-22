# SimpleITK-JAVA-Natives

Packages the SimpleITK native libraries v2.2.0rc2.post35

The Windows and Mac libraries have been downloaded from https://github.com/SimpleITK/SimpleITK/releases

https://simpleitk.readthedocs.io/en/master/index.html

> SimpleITK is a simplified, open source, interface to the Insight Toolkit (ITK), a C++ open source image analysis toolkit which is widely used in academia and industry. SimpleITK is available for eight programming languages including C++, Python, R, Java, C#, Lua, Ruby, and TCL. Binary distributions of SimpleITK are currently available for all three major operating systems (Linux, OS X, and Windows).

The file `Main.java` in the directory `src/main/java/main` contains an example of how itk is initialized and used. 

### Supported Platforms
- Windows
- Linux
- MacOS

### Java Library Path

This native package currently only work by adding the path of the library to the JVM options.

Best is to run it the first time and the error output will tell you what path you should add.

On Linux it is in ~/.nativelibs/itknativelibs-0.1/. So the argument will be:

`-Djava.library.path=~/.nativelibs/itknativelibs-0.1/`