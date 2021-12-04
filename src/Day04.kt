fun main() {
    class Board(input: List<String>, startIndex: Int) {
        val boxes = Array(6) {IntArray(6)}
        val numbers = HashSet<Int>(25)
        val marked = HashSet<Int>()
        var won = false
        init {
             for (i in 0 until 5) {
                 for ((j, number) in input[i + startIndex].trim().split
                     ("\\s+".toRegex()).map { it
                     .toInt()
                 }.withIndex()) {
                     boxes[i][j] = number
                     numbers.add(number)
                 }
             }
         }

        fun maybeMark(num: Int) {
            if (num in numbers) {
                for (i in 0 until 5) {
                    for (j in 0 until 5) {
                        if (boxes[i][j] == num) {
                            marked.add(num)
                            boxes[i][5]++
                            boxes[5][j]++
                            if ((boxes[i][5] == 5) or (boxes[5][j] == 5)) {
                                won = true
                            }
                        }
                    }
                }
            }
        }

        fun getScore(): Int {
            var score = 0
            for (i in 0 until 5) {
                for (j in 0 until 5) {
                    score += if (boxes[i][j] in marked) 0 else boxes[i][j]
                }
            }
            return score
        }
    }
    fun parseBoards(input: List<String>): List<Board> {
        val boards = mutableListOf<Board>()
        for (i in 2 until input.size step 6) {
            boards.add(Board(input, i))
        }
        return boards
    }

    fun part1(input: List<String>): Int {
        val boards = parseBoards(input)
        for (num in input[0].split(",").map { it.toInt() }) {
            for (board in boards) {
                board.maybeMark(num)
                if (board.won) {
                    return board.getScore()*num
                }
            }
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        val boards = parseBoards(input)
        var numBoardsLeft = boards.size
        for (num in input[0].split(",").map { it.toInt() }) {
            for (board in boards) {
                val wasBoardWon = board.won
                board.maybeMark(num)
                if (!wasBoardWon and board.won) {
                    numBoardsLeft--
                    if (numBoardsLeft == 0) {
                        return board.getScore()*num
                    }
                }
            }
        }
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
