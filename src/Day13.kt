import kotlin.math.max

fun main() {
    fun parseInputAndGetDimensions(input: List<String>) : IntArray {
        var maxX = 0
        var maxY = 0
        for ((i, line) in input.withIndex()) {
            if (line == "") return intArrayOf(i, maxX, maxY)
            maxX = max(maxX, line.trim().split(",")[0].toInt())
            maxY = max(maxY, line.trim().split(",")[1].toInt())
        }
        return intArrayOf(0)
    }

    fun foldX(matrix: Array<BooleanArray>, x: Int, limitX: Int, limitY: Int) {
        for (i in 0 until limitY) {
            for (j in x+1 until limitX) {
                matrix[i][x - (j - x)] = matrix[i][x - (j - x)] or matrix[i][j]
            }
        }
    }

    fun foldY(matrix: Array<BooleanArray>, y: Int, limitX: Int, limitY: Int) {
        for (i in y+1 until limitY) {
            for (j in 0 until limitX) {
                matrix[y - (i - y)][j] = matrix[y - (i - y)][j] or matrix[i][j]
            }
        }
    }

    fun parseInstruction(instruction: String): Pair<Boolean, Int> {
        return Pair((instruction.trim().split("=")[0].last() == 'x'),
            instruction.trim().split("=")[1].toInt() )
    }

    fun visibleDots(matrix: Array<BooleanArray>, limitX: Int, limitY: Int):
            Int {
        var count = 0
        for (i in 0 until limitY) {
            for (j in 0 until limitX) {
                count += if (matrix[i][j]) 1 else 0
            }
        }
        return count
    }

    fun printMatrix(matrix: Array<BooleanArray>, limitX: Int, limitY: Int) {
        for (i in 0 until limitY) {
            for (j in 0 until limitX) {
                print(if (matrix[i][j]) "#" else ".")
            }
            println()
        }
    }

    fun part1(input: List<String>): Int {
        val parsedInputDeets = parseInputAndGetDimensions(input)
        val matrix = Array(parsedInputDeets[2] + 1) {BooleanArray(parsedInputDeets[1] + 1) {false} }
        for (i in 0 until parsedInputDeets[0]) {
            matrix[input[i].trim().split(",")[1].toInt()][input[i].trim()
                .split(",")[0].toInt()] = true
        }
        val instruction = parseInstruction(input[parsedInputDeets[0] + 1])
        var limitX = matrix[0].size
        var limitY = matrix.size
        if (instruction.first) {
            foldX(matrix, instruction.second, limitX, limitY)
            limitX = instruction.second
        } else {
            foldY(matrix, instruction.second, limitX, limitY)
            limitY = instruction.second
        }
        return visibleDots(matrix, limitX, limitY)
    }

    fun part2(input: List<String>) {
        val parsedInputDeets = parseInputAndGetDimensions(input)
        val matrix = Array(parsedInputDeets[2] + 1) {BooleanArray(parsedInputDeets[1] + 1) {false} }
        for (i in 0 until parsedInputDeets[0]) {
            matrix[input[i].trim().split(",")[1].toInt()][input[i].trim()
                .split(",")[0].toInt()] = true
        }
        var limitX = matrix[0].size
        var limitY = matrix.size
        for (i in parsedInputDeets[0] + 1 until input.size) {
            val instruction = parseInstruction(input[i])
            if (instruction.first) {
                foldX(matrix, instruction.second, limitX, limitY)
                limitX = instruction.second
            } else {
                foldY(matrix, instruction.second, limitX, limitY)
                limitY = instruction.second
            }
        }
        printMatrix(matrix, limitX, limitY)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    println(part1(testInput))
    part2(testInput)

    val input = readInput("Day13")
    println(part1(input))
    part2(input)
}
