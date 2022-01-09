import java.util.*
import kotlin.collections.HashMap

fun main() {
    fun polymerOperation(input: List<String>, times: Int): Long {
        val rules = HashMap<Pair<Char, Char>, Char>()
        input.drop(2).forEach{
            val ruleTokens = it.trim().split(" -> ")
            rules[Pair(ruleTokens[0][0], ruleTokens[0][1])] = ruleTokens[1].first()
        }

        var pairCount = HashMap<Pair<Char, Char>, Long>()
        val charCount = HashMap<Char, Long>()

        for (i in 1 until input[0].length) {
            pairCount[Pair(input[0][i-1], input[0][i])] = (pairCount[Pair(input[0][i-1], input[0][i])] ?: 0) + 1
        }
        input[0].forEach {charCount[it] = (charCount[it] ?: 0) + 1}

        repeat(times) {
            val newPairCount = HashMap<Pair<Char, Char>, Long>()
            pairCount.forEach { (key, value) ->
                val insert = rules[key]!!
                newPairCount[Pair(key.first, insert)] = (newPairCount[Pair(key.first, insert)] ?: 0) + value
                newPairCount[Pair(insert, key.second)] = (newPairCount[Pair(insert, key.second)] ?: 0) + value
                charCount[insert] = (charCount[insert] ?: 0) + value
            }
            pairCount = newPairCount
        }
        return Collections.max(charCount.values) - Collections.min(charCount.values)
    }

    fun part1(input: List<String>): Long {
        return polymerOperation(input, 10)
    }

    fun part2(input: List<String>) : Long {
        return polymerOperation(input, 40)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}
