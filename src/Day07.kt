import kotlin.math.abs
import kotlin.math.min

fun main() {
    // Haha brute force go brrr..
    fun part1(input: List<String>): Int {
        val crabs = input[0].trim().split(",").map { it.toInt() }
        var minFuel = Int.MAX_VALUE
        for (i in (crabs.minOrNull() ?: 0)..(crabs.maxOrNull() ?: 0)) {
            var currFuel = 0
            for (crab in crabs) {
                currFuel += abs(crab - i)
            }
            minFuel = min(minFuel, currFuel)
        }
        return minFuel
    }

    fun part2(input: List<String>): Long {
        val crabs = input[0].trim().split(",").map { it.toLong() }
        var minFuel = Long.MAX_VALUE
        for (i in (crabs.minOrNull() ?: 0)..(crabs.maxOrNull() ?: 0)) {
            var currFuel = 0L
            for (crab in crabs) {
                val steps = abs(crab - i)
                currFuel += steps*(steps + 1)/2
            }
            minFuel = min(minFuel, currFuel)
        }
        return minFuel
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
