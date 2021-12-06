fun main() {
    val zeroFishChildrenOnDay = LongArray(256) { -1L }

    fun getChildrenForZeroFishOnDay(day: Int): Long {
        if (day <= 0) return 0
        if (zeroFishChildrenOnDay[day] != -1L) {
            return zeroFishChildrenOnDay[day]
        }
        zeroFishChildrenOnDay[day] =
            1 + getChildrenForZeroFishOnDay(day - 7) +
                    getChildrenForZeroFishOnDay(day - 9)
        return zeroFishChildrenOnDay[day]
    }

    fun calculateTotalFish(input: String, days: Int): Long {
        return input.trim().split(",").fold(0L) { sum, element ->
            sum + 1 + getChildrenForZeroFishOnDay(days - element.toInt())
        }
    }

    fun part1(input: List<String>): Long {
        return calculateTotalFish(input[0], 80)
    }

    fun part2(input: List<String>): Long {
        return calculateTotalFish(input[0], 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
