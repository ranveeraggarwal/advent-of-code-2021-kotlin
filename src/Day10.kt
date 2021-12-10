import java.util.*

fun main() {
    fun getScoreForIncompleteToken(c: Char): Long {
        when (c) {
            '(' -> return 1
            '[' -> return 2
            '{' -> return 3
            '<' -> return 4
        }
        return 0L
    }

    fun getScoreForIllegalToken(c: Char): Long {
        when (c) {
            ')' -> return 3
            ']' -> return 57
            '}' -> return 1197
            '>' -> return 25137
        }
        return 0L
    }

    fun getScore(input: String, completeSequence: Boolean): Long {
        val stack: Stack<Char> = Stack()
        for (c in input) {
            if (c in charArrayOf(')', '}', ']', '>')) {
                if (stack.empty()) {
                    return if (completeSequence) 0L
                    else getScoreForIllegalToken(c)
                } else if ((stack.peek() == '(') and (c == ')')) {
                    stack.pop()
                } else if ((stack.peek() == '{') and (c == '}')) {
                    stack.pop()
                } else if ((stack.peek() == '[') and (c == ']')) {
                    stack.pop()
                } else if ((stack.peek() == '<') and (c == '>')) {
                    stack.pop()
                } else {
                    return if (completeSequence) 0L
                    else getScoreForIllegalToken(c)
                }
            } else {
                stack.push(c)
            }
        }
        if (!completeSequence) return 0L
        var score = 0L
        while (!stack.empty()) {
            score = score * 5L + getScoreForIncompleteToken(stack.pop())
        }
        return score
    }

    fun part1(input: List<String>): Long {
        return input.fold(0L) { sum, element -> sum + getScore(element, false) }
    }

    fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        for (line in input) {
            val score = getScore(line, true)
            if (score != 0L) {
                scores.add(score)
            }
        }
        scores.sort()
        return scores[scores.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
