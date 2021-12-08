fun main() {
    // Yet another horrible brute force ...
    val kPrintDisplayAndNumbers = false

    fun subtract(string1: CharArray, string2: CharArray): CharArray {
        return string1.subtract(string2.toSet()).toCharArray()
    }

    fun intersect(string1: CharArray, string2: CharArray): CharArray {
        return string1.intersect(string2.toSet()).toCharArray()
    }

    fun deduceSegment(input: List<String>): Array<CharArray> {
        val numbers = Array(10){ charArrayOf('|') }
        val display = CharArray(7)
        numbers[1] = input.filter { it.length == 2 }[0].toCharArray()
        numbers[4] = input.filter { it.length == 4 }[0].toCharArray()
        numbers[7] = input.filter { it.length == 3 }[0].toCharArray()
        numbers[8] = input.filter { it.length == 7 }[0].toCharArray()

        display[0] = subtract(numbers[7], numbers[1])[0]

        // Get the uncommon elements between 689 (lightning bolt)
        val delta069 = mutableListOf<Char>()
        val append069 = input.filter { it.length == 6 }.joinToString("")
        for (c in append069.toSet()) {
            var cCount = 0
            append069.forEach { if (it == c) cCount++ }
            if (cCount == 2) {
                delta069.add(c)
            }
        }
        val diff069 = delta069.toCharArray()

        display[2] = intersect(diff069, numbers[1])[0]
        display[5] = subtract(numbers[1], charArrayOf(display[2]))[0]

        numbers[6] = input.filter { (it.length == 6) and (intersect(it
            .toCharArray(),
            charArrayOf(display[2]))).isEmpty() }[0].toCharArray()

        display[1] = subtract(subtract(numbers[4], numbers[1]), diff069)[0]
        display[3] = subtract(subtract(numbers[4], numbers[1]), charArrayOf(display[1]))[0]
        display[4] = subtract(diff069, charArrayOf(display[2], display[3]))[0]
        display[6] = subtract(numbers[8], charArrayOf(display[0], display[1],
            display[2], display[3], display[4], display[5]))[0]

        numbers[0] = charArrayOf(display[0], display[1], display[2],
            display[4], display[5], display[6])
        numbers[2] = charArrayOf(display[0], display[2], display[3], display[4], display[6])
        numbers[3] = charArrayOf(display[0], display[2], display[3], display[5], display[6])
        numbers[5] = charArrayOf(display[0], display[1], display[3], display[5], display[6])
        numbers[9] = charArrayOf(display[0], display[1], display[2], display[3], display[5], display[6])

        if (kPrintDisplayAndNumbers) {
            println("Display")
            display.forEach { print("$it ") }
            println("Numbers")
            numbers.forEach { print("${it.joinToString("")} ") }
            print("\n")
        }

        return numbers
    }

    fun getDigit(input: String, deducedNumbers: List<String>): Int {
        when (input.length) {
            7 -> return 8
            3 -> return 7
            4 -> return 4
            2 -> return 1
        }
        if (deducedNumbers.isEmpty()) return -1
        when (input.toCharArray().sorted().joinToString("")) {
            deducedNumbers[5] -> return 5
            deducedNumbers[2] -> return 2
            deducedNumbers[3] -> return 3
            deducedNumbers[9] -> return 9
            deducedNumbers[6] -> return 6
            deducedNumbers[0] -> return 0
        }
        return -1
    }

    fun part1(input: List<String>): Int {
        val simpleValues = intArrayOf(1, 4, 7, 8)
        val digitalOutput = input.map { it.split("|")[1].trim().split(" ") }
        var sum = 0
        for (line in digitalOutput) {
            for (element in line) {
                if (getDigit(element, listOf()) in simpleValues) {
                    sum ++
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        var sum = 0L
        for (signal in input) {
            val jumbledInput = signal.split("|")[0].trim().split(" ")
            val jumbledOutput = signal.split("|")[1].trim().split(" ")
            val deducedNumbers = deduceSegment(jumbledInput).map { it.sorted().joinToString("") }

            var num = ""
            for (element in jumbledOutput) {
                num += getDigit(element, deducedNumbers)
            }
            sum += num.toLong()
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
