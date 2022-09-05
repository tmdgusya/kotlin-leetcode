import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays
import java.util.LinkedList

class Question200 : FunSpec({

    test("case 01") {
        val grid = arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0'),
        )

        val expected = 1

        val result = numIslands(grid)

        result shouldBe expected
    }
})

fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty()) return 0

    var islandNumber = 0
    val visited = createVisitedMap(grid.size, grid[0].size)

    for (i in grid.indices) {
        for (j in 0 until grid[0].size) {
            if (!visited[i][j] && grid[i][j] == '1') {
                drawIslandArea(
                    index = i to j,
                    visited = visited,
                    grid = grid
                )
                ++islandNumber
            }
        }
    }

    for (map in visited) {
        println(Arrays.toString(map))
    }

    return islandNumber
}

fun createVisitedMap(row: Int, size: Int): Array<BooleanArray> {
    val map = mutableListOf<BooleanArray>()

    for (i in 0 until row) {
        map.add(BooleanArray(size))
    }

    return map.toTypedArray()
}

fun drawIslandArea(index: Pair<Int, Int>, visited: Array<BooleanArray>, grid: Array<CharArray>) {
    val queue = LinkedList<Pair<Int, Int>>()
    // start
    queue.add(index)

    while (queue.isNotEmpty()) {

        val (x, y) = queue.pop()
        if (visited[x][y]) continue

        visited[x][y] = true

        // go up
        if (x > 0 && y >= 0) {
            if (!visited[x - 1][y] && grid[x - 1][y] != '0') queue.add(x - 1 to y)
        }

        // go down
        if (x < grid.lastIndex && y >= 0) {
            if (!visited[x + 1][y] && grid[x + 1][y] != '0') queue.add(x + 1 to y)
        }

        // go left
        if (x >= 0 && y > 0) {
            if (!visited[x][y - 1] && grid[x][y - 1] != '0') queue.add(x to y - 1)
        }

        // go right
        if (x >= 0 && y < grid[0].lastIndex) {
            if (!visited[x][y + 1] && grid[x][y + 1] != '0') queue.add(x to y + 1)
        }
    }
}