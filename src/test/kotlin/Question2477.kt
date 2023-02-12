import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.ceil

class Question2477: FunSpec({
    test("given roads = [[0,1],[0,2],[0,3]], seats = 5, then output = 3") {
        val roads = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 2),
            intArrayOf(0, 3)
        )
        val seats = 5
        val expected = 3L
        val actual = minimumFuelCost(roads, seats)
        actual shouldBe expected
    }

    test("given roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2, then output = 7") {
        val roads = arrayOf(
            intArrayOf(3, 1),
            intArrayOf(3, 2),
            intArrayOf(1, 0),
            intArrayOf(0, 4),
            intArrayOf(0, 5),
            intArrayOf(4, 6)
        )
        val seats = 2
        val expected = 7L
        val actual = minimumFuelCost(roads, seats)
        actual shouldBe expected
    }

    //given roads = [[0,1],[1,2],[1,3],[1,4],[1,5],[1,6]], seats = 2, then output = 6
    test("given roads = [[0,1],[1,2],[1,3],[1,4],[1,5],[1,6]], seats = 10, then output = 6") {
        val roads = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(1, 5),
            intArrayOf(1, 6),
            intArrayOf(6, 7)
        )
        val seats = 2
        val expected = 10L
        val actual = minimumFuelCost(roads, seats)
        actual shouldBe expected
    }
})

private var fuel: Long = 0

fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
    // 인접 자식을 구한다.
    val adjacencyOfNodes = calculateAdjacency(roads)
    dfs(startNode = 0, parentNode = -1, adjacencyOfNodes = adjacencyOfNodes, seats = seats)
    return fuel
}

fun dfs(startNode: Int, parentNode: Int, adjacencyOfNodes: MutableMap<Int, MutableList<Int>>, seats: Int): Long {
    var representatives = 1L
    if (adjacencyOfNodes[startNode] == null) return representatives

    for (child in checkNotNull(adjacencyOfNodes[startNode])) {
        if (child != parentNode) {
            representatives += dfs(child, startNode, adjacencyOfNodes, seats)
        }
    }

    if (startNode != 0) {
        fuel += ceil(representatives.toDouble() / seats).toLong()
    }

    return representatives
}

fun calculateAdjacency(roads: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
    val map = mutableMapOf<Int, MutableList<Int>>()
    for (road in roads) {
        map.computeIfAbsent(road[0]) { mutableListOf<Int>() }.add(road[1])
        map.computeIfAbsent(road[1]) { mutableListOf<Int>() }.add(road[0])
    }
    return map
}