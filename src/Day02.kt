fun main() {

    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        for (line in input) {
            line.split(" ")
                .let { (direction, X) ->
                    when (direction) {
                        "forward" -> horizontal += X.toInt()
                        "down" -> depth += X.toInt()
                        "up" -> depth -= X.toInt()
                    }
                }
        }
        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0
        for (line in input) {
            line.split(" ")
                .let { (direction, X) ->
                    when (direction) {
                        "forward" -> {
                            horizontal += X.toInt()
                            depth += aim * X.toInt()
                        }
                        "down" -> aim += X.toInt()
                        "up" -> aim -= X.toInt()
                    }
                }
        }
        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
