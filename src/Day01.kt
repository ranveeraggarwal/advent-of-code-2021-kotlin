fun main() {
    fun getLargerThanPreviouses(input: List<Int>): Int {
        var larger = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i - 1]) {
                larger += 1
            }
        }
        return larger
    }

    fun part1(input: List<String>): Int {
        return getLargerThanPreviouses(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {
        return getLargerThanPreviouses(input.map { it.toInt() }
            .windowed(size = 3, step = 1, partialWindows = false)
            .map { it.sum() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
