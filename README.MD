= Anagram TDD

This is an example of TDD with spock framework and kotlin implementation

== What we want to do?
We want to implement a function that should return a set of all _unique_ anagrams of the argument string.
If given the empty string the function should return an empty set.

For example:
anagrams("")    >> []
anagrams("ab")  >> ["ab", "ba"]
anagrams("abc") >> ["abc", "acb", "bca", "bac", "cab", "cba"]
anagrams("aac") >> ["aac", "aca", "caa"]

== Requirements

* JDK 8
* Git

== Get started

----
git clone https://github.com/fuedacode/anagrams.git
cd anagrams
../gradlew
----