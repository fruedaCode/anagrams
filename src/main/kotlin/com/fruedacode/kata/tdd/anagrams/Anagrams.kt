package com.fruedacode.kata.tdd.anagrams

data class Anagrams(private val input: String? = ""){

    var result : Collection<String> = apply(input!!)

    fun apply(input: String) : MutableSet<String>{
        var anagrams = mutableSetOf<String>()
        if(input.isNotBlank()){
            anagrams = getCombinations(input).toMutableSet()
        }
        result = anagrams
        return anagrams
    }

    fun getCombinations(input: String): MutableCollection<String>{
        if(input.length == 1){
            return mutableListOf<String>(input)
        }else{
            var list = mutableListOf<String>()
            var result = ""
            for((index, c) in input.withIndex()){

                val nextPosition = index + 1
                var rightString = (input + input).substring(nextPosition, nextPosition + input.length - 1)

                for(c1 in getCombinations(rightString)){
                    list.add("$c$c1")
                }
            }
            return list
        }
    }

}
