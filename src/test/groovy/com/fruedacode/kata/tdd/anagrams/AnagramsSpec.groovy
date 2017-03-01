package com.fruedacode.kata.tdd.anagrams

import spock.genesis.Gen
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.regex.Pattern

class AnagramsSpec extends Specification {

    @Subject anagrams = new Anagrams()

    def "empty anagram"(){
        when: "input is empty"
        then: "should return empty set"
        anagrams.apply("").isEmpty()
    }

    def "string with length == 1 return a set with this element"(){
        when: "input length == 1"
        then: "should return a set with same element"
        !anagrams.apply("a").isEmpty()
        anagrams.apply("a").size() == 1
        anagrams.apply("a").contains("a")
    }

    def "overload constructor with an input"(){
        when: "build object user overloaded constructor"
        anagrams = new Anagrams("a")
        then: "we can check the result on result member"
        anagrams.result.contains("a")
    }

    def "input with length == 3"(){
        when: "input is abc"
        anagrams = new Anagrams("abc")
        then: "result should be this"
        anagrams.result.size() == getPossibleCombinationsCount("abc")
        anagrams.result.every { s -> ["abc", "acb", "bca", "bac", "cab", "cba"].contains(s)}
    }

    def "input with length == 3 and repeated elements"(){
        when: "input is aac"
        anagrams = new Anagrams("aac")
        then: "result should be this"
        anagrams.result.size() == getPossibleCombinationsCount("aac")
        anagrams.result.every { s -> ["aac", "aca", "caa"].contains(s)}
    }

    def "input with repeated letters"(){
        given: def input = "aa"
        when: "input has repeated"
        anagrams = new Anagrams(input)
        then: "output length must be 1 and == aa"
        anagrams.result.size() == getPossibleCombinationsCount(input)
        anagrams.result.first() == "aa"
    }

    @Unroll("Input #n")
    def "generated random input"(){
        when: "input it's between a-z with a random length between 1 and 5"
        anagrams = new Anagrams(n)
        then: "the possible combinations should be n!/(n-r)!"
        anagrams.result.size() == getPossibleCombinationsCount(n)
        where:
        n << Gen.string(Pattern.compile(/[a-z]{1,5}/)).take(20)
    }

    def static int getPossibleCombinationsCount(String input){
        def total = input.toList().groupBy {x -> x.toString()}.values().inject(1) {total, s -> factorial(s.size()) * total}
        def n = input.length()
        return factorial(n) / total
    }

    def static int factorial(int number) {
        if (number <= 1) return 1
        else return number * factorial(number - 1)
    }


}
