package math

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList
import java.util.Queue

class Comb: FunSpec({
    test("case 01") {
        val input = intArrayOf(1,2,3)
        val expected: List<IntArray> = listOf(
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(3),
            intArrayOf(1,2),
            intArrayOf(1,3),
            intArrayOf(2,3),
            intArrayOf(2,1),
            intArrayOf(3,1),
            intArrayOf(3,2),
            intArrayOf(1,2,3),
            intArrayOf(1,3,2),
            intArrayOf(2,3,1),
            intArrayOf(2,1,3),
            intArrayOf(3,1,2),
            intArrayOf(3,2,1),
        )

        comb(input) shouldBe expected
    }

    test("case 02") {
        val input = intArrayOf(1,2)
        val expected: List<IntArray> = listOf(
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(1,2),
            intArrayOf(2,1),
        )

        comb(input) shouldBe expected
    }

    test("case 03") {
        val input = intArrayOf()
        val expected: List<IntArray> = listOf()

        comb(input) shouldBe expected
    }
})

fun comb(list: IntArray): List<IntArray> {
    val result = mutableListOf<IntArray>()
    // make a queue
    val remains: Queue<Int> = LinkedList<Int>()
    for (i in list.indices) {
        remains.add(list[i])
    }

    comb(mutableListOf(), LinkedList(remains), result)

    return result.sortedBy { it.count() }
}

fun comb(parentState: MutableList<Int>, remains: Queue<Int>, result: MutableList<IntArray>) {
    for (i in remains.indices) {
        val value = remains.poll()
        val currentState = parentState.toMutableList()
        currentState.add(value)
        comb(currentState, LinkedList(remains), result)
        remains.add(value)
        result.add(currentState.toIntArray())
    }
}