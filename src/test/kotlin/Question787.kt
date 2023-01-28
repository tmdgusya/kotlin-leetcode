import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList
import java.util.Queue

// [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
class Question787: FunSpec({
    test("case 01") {
        val flights = arrayOf<IntArray>(
            intArrayOf(0,1,100),
            intArrayOf(1,2,100),
            intArrayOf(2,0,100),
            intArrayOf(1,3,600),
            intArrayOf(2,3,200),
        )
        val n = 4
        val src = 0
        val dst = 3
        val k = 1

        // when
        val cheapestPrice = findCheapestPrice(n, flights, src, dst, k)

        // then
        cheapestPrice shouldBe 700
    }

    test("case 02") {
        val flights = arrayOf<IntArray>(
            intArrayOf(0,1,100),
            intArrayOf(1,2,100),
            intArrayOf(0,2,500),
        )
        val n = 3
        val src = 0
        val dst = 2
        val k = 1

        // when
        val cheapestPrice = findCheapestPrice(n, flights, src, dst, k)

        // then
        cheapestPrice shouldBe 200
    }

    test("case 03") {
        val flights = arrayOf<IntArray>(
            intArrayOf(0,1,100),
            intArrayOf(1,2,100),
            intArrayOf(0,2,500),
        )
        val n = 3
        val src = 0
        val dst = 2
        val k = 0

        // when
        val cheapestPrice = findCheapestPrice(n, flights, src, dst, k)

        // then
        cheapestPrice shouldBe 500
    }
})

private typealias Destination = Int
private typealias Price = Int
private typealias Flight = Pair<Destination, Price>

// https://leetcode.com/problems/cheapest-flights-within-k-stops/
fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
    val adj = flights.fold(mutableMapOf<Int, MutableList<Flight>>()) { map, flight ->
        map.apply {
            set(flight[0], getOrDefault(flight[0], mutableListOf<Flight>()).apply { add(flight[1] to flight[2]) })
        }
    }

    val dist = IntArray(n) { Int.MAX_VALUE }

    val queue: Queue<Flight> = LinkedList<Flight>()
    queue.offer(src to 0)
    var stops = 0

    while (stops <= k && queue.isNotEmpty()) {
        for (i in 0 until queue.size) {
            val flight = queue.poll()
            adj[flight.destination]?.let {
                val currentPrice = flight.price

                for (flightByNode in it) {
                    if (flightByNode.price + currentPrice < dist[flightByNode.destination]) {
                        dist[flightByNode.destination] = flightByNode.price + currentPrice
                        queue.offer(flightByNode.destination to dist[flightByNode.destination])
                    }
                }
            }
        }
        stops++
    }

    return if (dist[dst] == Int.MAX_VALUE) -1 else dist[dst]
}
val Flight.destination get() = this.first
val Flight.price get() = this.second