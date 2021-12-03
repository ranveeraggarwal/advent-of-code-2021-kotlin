fun main() {

    fun getVotes(
        input: List<String>, invalidStrings: BooleanArray, index:
        Int, majority: Boolean
    ): Char {
        var votes = 0
        for (i in input.indices) {
            if (invalidStrings[i]) continue
            votes += (if (input[i][index] == '1') 1 else -1)
        }
        return if (majority) {
            if (votes >= 0) '1' else '0'
        } else {
            if (votes >= 0) '0' else '1'
        }
    }

    fun part1(input: List<String>): Int {
        val numBits = input[0].length
        val invalidStrings = BooleanArray(input.size)  // Stub
        var gamma = ""
        var epsilon = ""
        for (i in 0 until numBits) {
            val majority = getVotes(input, invalidStrings, i, true)
            gamma += majority
            epsilon += if (majority == '1') '0' else '1'
        }
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun getValidString(input: List<String>, invalidStrings: BooleanArray):
            String {
        for (i in input.indices) {
            if (!invalidStrings[i]) {
                return input[i]
            }
        }
        return ""
    }

    fun getRating(input: List<String>, majorityStrategy: Boolean): String {
        val numBits = input[0].length
        val invalidStrings = BooleanArray(input.size)
        var numValid = input.size

        for (index in 0 until numBits) {
            val majority =
                getVotes(input, invalidStrings, index, majorityStrategy)
            for (i in input.indices) {
                if (!invalidStrings[i]) {
                    if (majority != input[i][index]) {
                        invalidStrings[i] = true
                        numValid--
                    }
                    if (numValid == 1) {
                        return getValidString(input, invalidStrings)
                    }
                }
            }
        }
        return ""
    }

    fun part2(input: List<String>): Int {
        // Could compress these two calls into one since we're doing a linear
        // iteration of the string every time anyway
        return Integer.parseInt(getRating(input, true), 2) *
                Integer.parseInt(getRating(input, false), 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
