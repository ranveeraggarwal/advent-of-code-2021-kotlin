fun main() {
    fun part1(input: List<String>): Int {
        var larger = 0
        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i-1].toInt()) {
                larger += 1
            }
        }
        return larger
    }

    fun part2(input: List<String>): Int {
        var larger = 0
        for (i in 3 until input.size) {
            if (input[i-3].toInt() + input[i-2].toInt() + input[i-1].toInt() < input[i-2].toInt() + input[i-1].toInt() + input[i].toInt()) {
                larger += 1
            }
        }
        return larger
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
