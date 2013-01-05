## Version Class
A class to represent version information. This class cannot be inherited. This class is thread-safe as it is immutable and can be freely shared between threads without any external synchronization. 
A version object represented by this class is of the following format:
X.Y.Z.b
where,
 * X = Major component of the version.
 * Y = Minor component of the version.
 * Z = Revision component of the version.
 * b = Build component of the version.

This class is inspired from the Version class in .NET Framework 4.0.
