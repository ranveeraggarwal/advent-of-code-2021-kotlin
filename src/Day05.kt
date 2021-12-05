import kotlin.math.abs

fun main() {
    fun printVents(vents: HashMap<String, Int>) {
        var splat = ""
        for (i in 0..9) {
            for (j in 0..9) {
                splat += vents.getOrDefault("$j,$i", ".")
            }
            splat += "\n"
        }
        print(splat)
    }

    fun getDangerousVents(input: List<String>, diagonal: Boolean): Int {
        val points = input.map { line -> line.split("->").map {pointString ->
            pointString.trim().split(",").map {  it.toInt() } } }
        val vents = HashMap<String, Int>()
        for (point in points) {
            if (point[0][0] == point[1][0]) {
                val y1 = if (point[0][1] > point[1][1]) point[1][1] else
                    point[0][1]
                val y2 = if (point[0][1] > point[1][1]) point[0][1] else
                    point[1][1]
                for (i in y1..y2) {
                    val key = point[0][0].toString() + "," + i.toString()
                    vents[key] = vents.getOrDefault(key, 0) + 1
                }
            } else if (point[0][1] == point[1][1]) {
                val x1 = if (point[0][0] > point[1][0]) point[1][0] else
                    point[0][0]
                val x2 = if (point[0][0] > point[1][0]) point[0][0] else
                    point[1][0]
                for (i in x1..x2) {
                    val key = i.toString() + "," + point[0][1].toString()
                    vents[key] = vents.getOrDefault(key, 0) + 1
                }
            } else if (diagonal and ((abs(point[0][1] - point[1][1])/ abs
                    (point[0][0] - point[1][0])) == 1)) {
                var x1 = point[0][0]
                var x2 = point[1][0]
                var y1 = point[0][1]
                var y2 = point[1][1]
                if (x1 > x2) {
                    x1 = point[1][0]
                    x2 = point[0][0]
                    y1 = point[1][1]
                    y2 = point[0][1]
                }
                var direction = 1
                if (y1 > y2) direction = -1
                for (i in x1..x2) {
                    val key = "$i,$y1"
                    vents[key] = vents.getOrDefault(key, 0) + 1
                    y1 += direction
                }
            }
        }
        var dangerousVents = 0
        vents.forEach{ (_, value) -> if (value > 1) dangerousVents++}

        return dangerousVents
    }

    fun part1(input: List<String>): Int {
        return getDangerousVents(input, false)
    }

    fun part2(input: List<String>): Int {
        return getDangerousVents(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
