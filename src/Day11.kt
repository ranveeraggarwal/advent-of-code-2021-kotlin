fun main() {
    fun cleanInputAndAugmentArray(input: List<String>): Array<IntArray> {
        val octopuses = Array(12) { IntArray(12) { Int.MIN_VALUE } }

        for (i in 1..10) {
            for (j in 1..10) {
                octopuses[i][j] = input[i - 1][j - 1].code - '0'.code
            }
        }
        return octopuses
    }

    fun flash(octopuses: Array<IntArray>, flashed: Array<BooleanArray>, i:
    Int, j: Int): Int {
        if ((flashed[i][j]) or (octopuses[i][j] <= 9)) return 0
        var flashes = 1
        flashed[i][j] = true
        octopuses[i-1][j-1]++
        flashes += flash(octopuses, flashed, i - 1, j - 1)
        octopuses[i-1][j]++
        flashes += flash(octopuses, flashed, i - 1, j)
        octopuses[i-1][j+1]++
        flashes += flash(octopuses, flashed, i - 1, j + 1)
        octopuses[i][j-1]++
        flashes += flash(octopuses, flashed, i, j - 1)
        octopuses[i][j+1]++
        flashes += flash(octopuses, flashed, i, j + 1)
        octopuses[i+1][j-1]++
        flashes += flash(octopuses, flashed, i + 1, j - 1)
        octopuses[i+1][j]++
        flashes += flash(octopuses, flashed, i + 1, j)
        octopuses[i+1][j+1]++
        flashes += flash(octopuses, flashed, i + 1, j + 1)

        return flashes
    }

    fun part1(input: List<String>): Int {
        val octopuses = cleanInputAndAugmentArray(input)
        var flashes = 0
        for (steps in 1..100) {
            val flashed = Array(12) { BooleanArray(12) { false } }
            // Augment
            for (i in 1..10) {
                for (j in 1.. 10) {
                    octopuses[i][j]++
                }
            }
            // Flash
            for (i in 1..10) {
                for (j in 1.. 10) {
                    flashes += flash(octopuses, flashed, i, j)
                }
            }
            // Cleanup
            for (i in 1..10) {
                for (j in 1.. 10) {
                    if (octopuses[i][j] > 9) {
                        octopuses[i][j] = 0
                    }
                }
            }
        }

        return flashes
    }

    fun part2(input: List<String>): Int {
        val octopuses = cleanInputAndAugmentArray(input)
        for (step in 1..1000) {
            val flashed = Array(12) { BooleanArray(12) { false } }
            // Augment
            for (i in 1..10) {
                for (j in 1.. 10) {
                    octopuses[i][j]++
                }
            }
            // Flash
            for (i in 1..10) {
                for (j in 1.. 10) {
                    if(flash(octopuses, flashed, i, j) == 100)
                        return step
                }
            }
            // Cleanup
            for (i in 1..10) {
                for (j in 1.. 10) {
                    if (octopuses[i][j] > 9) {
                        octopuses[i][j] = 0
                    }
                }
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
