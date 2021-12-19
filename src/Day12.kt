fun main() {
    // This is so brute forced that it feels like it's going to stab Caesar
    // in the back at some point. But I am late by like a week and this needs
    // to go on ^_^
    fun part1(input: List<String>): Int {
        val caveMap = mutableMapOf<String, MutableList<String>>()

        fun buildTree(input: List<String>) {
            for (line in input) {
                val nodes = line.split("-")
                if (nodes[1] != "start") {
                    if (caveMap.containsKey(nodes[0])) {
                        caveMap[nodes[0]]!!.add(nodes[1])
                    } else {
                        caveMap[nodes[0]] = mutableListOf(nodes[1])
                    }
                }
                if (nodes[0] != "start") {
                    if (caveMap.containsKey(nodes[1])) {
                        caveMap[nodes[1]]!!.add(nodes[0])
                    } else {
                        caveMap[nodes[1]] = mutableListOf(nodes[0])
                    }
                }
            }
            caveMap["end"] = mutableListOf()
        }

        fun getPaths(
            root: String, caveMap: Map<String, List<String>>,
            currPath: MutableList<String>
        ): List<List<String>> {
            currPath.add(root)
            if (root == "end") {
                return listOf(currPath)
            }
            val paths = mutableListOf<List<String>>()
            for (cave in caveMap.getOrDefault(root, listOf())) {
                if ((cave[0].isLowerCase()) and (cave in currPath)) {
                    continue
                } else {
                    paths += getPaths(cave, caveMap, currPath.toMutableList())
                }
            }
            return paths
        }

        buildTree(input)
        return getPaths("start", caveMap, mutableListOf()).size

    }

    fun part2(input: List<String>): Int {
        val caveMap = mutableMapOf<String, MutableList<String>>()

        fun buildTree(input: List<String>) {
            for (line in input) {
                val nodes = line.split("-")
                if (nodes[1] != "start") {
                    if (caveMap.containsKey(nodes[0])) {
                        caveMap[nodes[0]]!!.add(nodes[1])
                    } else {
                        caveMap[nodes[0]] = mutableListOf(nodes[1])
                    }
                }
                if (nodes[0] != "start") {
                    if (caveMap.containsKey(nodes[1])) {
                        caveMap[nodes[1]]!!.add(nodes[0])
                    } else {
                        caveMap[nodes[1]] = mutableListOf(nodes[0])
                    }
                }
            }
            caveMap["end"] = mutableListOf()
        }

        fun getPaths(
            root: String, caveMap: Map<String, List<String>>,
            currPath: MutableList<String>, repeatable: String,
            isRepeated: Boolean
        ):
                List<List<String>> {
            currPath.add(root)
            if (root == "end") {
                return listOf(currPath)
            }
            val paths = mutableListOf<List<String>>()
            for (cave in caveMap.getOrDefault(root, listOf())) {
                paths += if ((cave[0].isLowerCase()) and (cave in currPath)) {
                    if (!isRepeated and (cave == repeatable)) {
                        getPaths(
                            cave, caveMap, currPath
                                .toMutableList(), repeatable, true
                        )
                    } else {
                        continue
                    }
                } else {
                    getPaths(
                        cave,
                        caveMap,
                        currPath.toMutableList(),
                        repeatable,
                        isRepeated
                    )
                }
            }
            return paths
        }

        buildTree(input)
        val finalPaths = mutableListOf<List<String>>()
        for (node in caveMap.keys) {
            if ((node != "start") and (node != "end") and (node[0]
                    .isLowerCase())
            ) {
                finalPaths += getPaths(
                    "start", caveMap, mutableListOf(), node,
                    false
                )
            }
        }
        return finalPaths.toSet().size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
