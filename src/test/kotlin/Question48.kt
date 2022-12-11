import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question48: FunSpec({

    test("case 01") {
        val matrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
        val output = arrayOf(intArrayOf(7, 4, 1), intArrayOf(8, 5, 2), intArrayOf(9, 6, 3))

        rotate(matrix)

        matrix shouldBe output
    }

    test("case 02") {
        val matrix = arrayOf(intArrayOf(5,1,9,11), intArrayOf(2,4,8,10), intArrayOf(13,3,6,7), intArrayOf(15,14,12,16))
        val output = arrayOf(intArrayOf(15,13,2,5), intArrayOf(14,3,4,1), intArrayOf(12,6,8,9), intArrayOf(16,7,10,11))

        rotate(matrix)

        matrix shouldBe output
    }
})

private fun rotate(matrix: Array<IntArray>): Unit {
    makeStore(matrix)
        .forEachIndexed { index, value ->
            matrix[index / matrix.size][index % matrix.size] = value
        }
}

private fun makeStore(matrix: Array<IntArray>): IntArray {
    val store = mutableListOf<Int>()
    for (i in 0 .. matrix.lastIndex) {
        for (j in matrix.lastIndex downTo 0) {
            store.add(matrix[j][i])
        }
    }
    return store.toIntArray()
}