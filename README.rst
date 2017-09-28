It is best if you read the `javadocs <http://javadocs.io/doc/thehellings.com/agglutinator/>`_.

What is this?
=============

The Agglutinator project is a small library that will combine a collection of sources together into a single, larger
string. These sources can be backed by anything, but most often will be backed by something such as a file. The process
of agglutination is often the first step in the minification process of building web sources, such as CSS or JavaScript
files, which are developed in many small files but should be concatenated together for consumption in the browser.

At its base, this library accomplishes very little that is not able to be done elsewhere. However, the budnled sources
include some basic functionality to handle things like refreshing the content of the source every time it is requested
when in development mode vs caching the result in memory during production mode to avoid multiple disk hits. These can
be exapnded to cache output results in an external mechanism such as memcached, redis, or the like.

Similarly, sources can be expanded to include dynamically created sources such as the output of SCSS or LESS compiling,
the results of database queries, or any other such type of source.

Feel free to offer pull requests and code to this repository if you want to expand its capabilities. Contributions are
always welcome!