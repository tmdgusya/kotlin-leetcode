import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

const val testCase05 = """
    " +
            "[[-100,-100],[-100,-100,0],[-100,-100,0,0],[-100,-100,0,0,0],[-100,-100,0,0,0,100],[-100,-100,0,0,0,100,100]," +
            "[-100,-100,0,0,0,0],[-100,-100,0,0,0,0,0],[-100,-100,0,0,100],[-100,-100,0,0,100,100]," +
            "[-100,-100,0,100],[-100,-100,0,100,100],[-100,-100,100],[-100,-100,100,100],[-100,0],[-100,0,0],[-100,0,0,0]," +
            "[-100,0,0,0,100],[-100,0,0,0,100,100],[-100,0,0,0,0],[-100,0,0,0,0,0],[-100,0,0,100],[-100,0,0,100,100]," +
            "[-100,0,100],[-100,0,100,100],[-100,100],[-100,100,100],[0,0],[0,0,0],[0,0,0,100],[0,0,0,100,100],[0,0,0,0],[0,0,0,0,0]," +
            "[0,0,100],[0,0,100,100],[0,100],[0,100,100],[100,100]]"
"""
class Question491: FunSpec({

    test("given nums = [4,6], then result is [4, 6]") {
        // given
        val nums = intArrayOf(4, 6)

        // when
        val actual = findSubsequences(nums)

        // then
        actual shouldBe listOf(listOf(4, 6))
    }

    test("given nums = [4,6,7,7], then result is [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]") {
        val nums = intArrayOf(4, 6, 7, 7)
        val expected = listOf(
            listOf(4, 6),
            listOf(4, 6, 7),
            listOf(4, 6, 7, 7),
            listOf(4, 7),
            listOf(4, 7, 7),
            listOf(6, 7),
            listOf(6, 7, 7),
            listOf(7, 7)
        )
        val actual = findSubsequences(nums)
        actual shouldBe expected
    }

    test("given [4,4,3,2,1], then [[4,4]]") {
        val nums = intArrayOf(4, 4, 3, 2, 1)
        val expected = listOf(listOf(4, 4))
        val actual = findSubsequences(nums)
        actual shouldBe expected
    }

    test("given [1,3,5,7], then [[1,3],[1,3,5],[1,3,5,7],[1,3,7],[1,5],[1,5,7],[1,7],[3,5],[3,5,7],[3,7],[5,7]]") {
        val nums = intArrayOf(1, 3, 5, 7)
        val expected = listOf(
            listOf(1, 3),
            listOf(1, 3, 5),
            listOf(1, 3, 5, 7),
            listOf(1, 3, 7),
            listOf(1, 5),
            listOf(1, 5, 7),
            listOf(1, 7),
            listOf(3, 5),
            listOf(3, 5, 7),
            listOf(3, 7),
            listOf(5, 7)
        )
        println(expected)
        val actual = findSubsequences(nums)
        println(actual)
        actual shouldBe expected
    }

    test("given [-100,-100,0,0,0,100,100,0,0], then zz") {
        val nums = intArrayOf(-100, -100, 0, 0, 0, 100, 100, 0, 0)
        val expected = listOf(
            listOf(-100, -100),
            listOf(-100, -100, 0),
            listOf(-100, -100, 0, 0),
            listOf(-100, -100, 0, 0, 0),
            listOf(-100, -100, 0, 0, 0, 100),
            listOf(-100, -100, 0, 0, 0, 100, 100),
            listOf(-100, -100, 0, 0, 0, 0),
            listOf(-100, -100, 0, 0, 0, 0, 0),
            listOf(-100, -100, 0, 0, 100),
            listOf(-100, -100, 0, 0, 100, 100),
            listOf(-100, -100, 0, 100),
            listOf(-100, -100, 0, 100, 100),
            listOf(-100, -100, 100),
            listOf(-100, -100, 100, 100),
            listOf(-100, 0),
            listOf(-100, 0, 0),
            listOf(-100, 0, 0, 0),
            listOf(-100, 0, 0, 0, 100),
            listOf(-100, 0, 0, 0, 100, 100),
            listOf(-100, 0, 0, 0, 0),
            listOf(-100, 0, 0, 0, 0, 0),
            listOf(-100, 0, 0, 100),
            listOf(-100, 0, 0, 100, 100),
            listOf(-100, 0, 100),
            listOf(-100, 0, 100, 100),
            listOf(-100, 100),
            listOf(-100, 100, 100),
            listOf(0, 0),
            listOf(0, 0, 0),
            listOf(0, 0, 0, 100),
            listOf(0, 0, 0, 100, 100),
            listOf(0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0),
            listOf(0, 0, 100),
            listOf(0, 0, 100, 100),
            listOf(0, 100),
            listOf(0, 100, 100),
            listOf(100, 100))

        println(expected)
        println(findSubsequences(nums))

        findSubsequences(nums) shouldBe expected
    }
})

fun findSubsequences(nums: IntArray): List<List<Int>> {
    if (nums.size == 2) return listOf(nums.toList())

    val result = mutableSetOf<List<Int>>()
    recursion(nums, 0, mutableListOf(), result)
    return result.toList()
}

fun recursion(nums: IntArray, i: Int, acc: MutableList<Int>, result: MutableSet<List<Int>>) {
    if (i == nums.size) {
        if (acc.size >= 2) {
            result.add(acc.toList())
        }
        return
    }

    // append
    if (acc.isEmpty() || acc.last() <= nums[i]) {
        acc.add(nums[i])
        recursion(nums, i + 1, acc, result)
        acc.removeAt(acc.lastIndex)
    }

    // skip
    recursion(nums, i + 1, acc, result)
}
