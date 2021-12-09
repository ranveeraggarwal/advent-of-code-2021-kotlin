import java.util.*

fun main() {
    fun cleanInputAndAppendNines(input: List<String>): Array<IntArray> {
        val n = input.size
        val m = input[0].length
        val vents = Array(n + 2) { IntArray(m + 2) { 9 } }

        for (i in 1..n) {
            for (j in 1..m) {
                vents[i][j] = input[i - 1][j - 1].code - '0'.code
            }
        }
        return vents
    }

    fun isDangerous(vents: Array<IntArray>, i: Int, j: Int): Boolean {
        return ((vents[i][j] < vents[i - 1][j]) and (vents[i][j] <
                vents[i + 1][j]) and (vents[i][j] < vents[i][j - 1])
                and (vents[i][j] < vents[i][j + 1]))
    }

    fun part1(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        val vents = cleanInputAndAppendNines(input)
        var sum = 0
        for (i in 1..n) {
            for (j in 1..m) {
                if (isDangerous(vents, i, j)) {
                    sum += vents[i][j] + 1
                }
            }
        }
        return sum
    }

    fun addToBfsQueue(
        bfsQueue: Queue<Pair<Int, Int>>, searched:
        Array<BooleanArray>, k: Int, l: Int
    ) {
        if (!searched[k][l]) {
            searched[k][l] = true
            bfsQueue.add(Pair(k, l))
        }
    }

    fun doBfs(
        vents: Array<IntArray>, searched: Array<BooleanArray>, i: Int,
        j: Int
    ): Int {
        val bfsQueue: Queue<Pair<Int, Int>> = LinkedList()
        bfsQueue.add(Pair(i, j))
        searched[i][j] = true
        var basin = 0
        while (!bfsQueue.isEmpty()) {
            val curr = bfsQueue.remove()
            val k = curr.first
            val l = curr.second
            if (vents[k][l] == 9) continue
            basin++
            addToBfsQueue(bfsQueue, searched, k, l + 1)
            addToBfsQueue(bfsQueue, searched, k, l - 1)
            addToBfsQueue(bfsQueue, searched, k + 1, l)
            addToBfsQueue(bfsQueue, searched, k - 1, l)
        }
        return basin
    }

    fun part2(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        val vents = cleanInputAndAppendNines(input)
        val searched = Array(n + 2) { BooleanArray(m + 2) { false } }
        val basins = mutableListOf<Int>()
        for (i in 1..n) {
            for (j in 1..m) {
                if ((!searched[i][j]) and isDangerous(vents, i, j)) {
                    basins.add(doBfs(vents, searched, i, j))
                }
            }
        }
        basins.sortDescending()
        return basins[0] * basins[1] * basins[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
