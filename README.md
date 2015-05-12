# ml-params
Prototypes for handling params in MLlib

#Issues

I tried to use self-bounding for `Params` in order to make `copy` returns the right type
automatically. However, it complicates the class hierachy and it also has Java compatibility issues:

1. Some methods with default implementation disapear in the Java class.
2. Java classs cannot tell that `Params[A]` implements `Params[B]` if `A` extends `B`.
