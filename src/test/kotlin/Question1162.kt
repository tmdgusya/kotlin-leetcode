import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

typealias Pointer = Pair<Int, Int>


class Question1162: FunSpec({

    test("case 01") {
        // grid = [[1,0,1],[0,0,0],[1,0,1]]
        val grid = arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 1)
        )

        maxDistance(grid) shouldBe 2
    }

})

private fun maxDistance(grid: Array<IntArray>): Int {
    val pointsOfLand = mutableListOf<Pointer>()
    val pointsOfWater = mutableListOf<Pointer>()
    var maximumDistance = -1

    for (i in grid.indices) {
        for (j in grid.indices) {
            if (grid[i][j] == 1) {
                pointsOfLand.add(i + 1 to j + 1)
            } else pointsOfWater.add(i + 1 to j + 1)
        }
    }

    if (pointsOfLand.isEmpty() || pointsOfWater.isEmpty()) return -1

    for (pointOfWater in pointsOfWater) {
        var minimumLengthFromLand = Int.MAX_VALUE
        for (pointOfLand in pointsOfLand) {
            minimumLengthFromLand = Math.min(minimumLengthFromLand, pointOfWater.distance(pointOfLand))
        }
        maximumDistance = Math.max(minimumLengthFromLand, maximumDistance)
    }

    return maximumDistance
}

private fun Pointer.distance(other: Pointer) = abs(this.first - other.first) + abs(this.second - other.second)

private fun abs(value: Int) = if (value < 0) -1 * value else value