import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question1770 : FunSpec({

    test("test case 01") {
        val given = intArrayOf(1,2,3)
        val multipliers = intArrayOf(3, 2, 1)

        val result = maximumScore(given, multipliers)

        result shouldBe 14
    }

    test("test case 02") {
        val given = intArrayOf(-5,-3,-3,-2,7,1)
        val multipliers = intArrayOf(-10,-5,3,4,6)

        val result = maximumScore(given, multipliers)

        result shouldBe 102
    }


})

private fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
    val cache = mutableMapOf<String, Int>()
    return dynamicSolution(nums, multipliers, 0, nums.lastIndex, 0, cache)
}

// pair 쓰면 Time Exceed Time Limit 가 뜸.. Pair 성능이 별로인가?
private fun dynamicSolution(nums: IntArray, multipliers: IntArray, left: Int, right: Int, op: Int, cache: MutableMap<String, Int>): Int {
    if (op >= multipliers.size) {
        return 0
    }

    val key = "$left|$right"
    if (cache.containsKey(key)) {
        return cache[key]!!
    }

    val leftValue = nums[left] * multipliers[op] + dynamicSolution(nums, multipliers, left + 1, right, op + 1, cache)
    val rightValue = nums[right] * multipliers[op] + dynamicSolution(nums, multipliers, left, right - 1, op + 1, cache)
    val result = leftValue.coerceAtLeast(rightValue)
    cache[key] = result

    return result
}

private fun bruteForthWithLopping(nums: IntArray, multipliers: IntArray): Int {
    var op = 0
    var leftPointer = 0
    var rightPointer = nums.lastIndex
    var result = 0

    var leftResult = 0
    var rightResult = 0

    while (op < multipliers.size) {
        leftResult += nums[leftPointer] * multipliers[op]
        rightResult += nums[rightPointer] * multipliers[op]

        op += 1
        leftPointer += 1
        rightPointer -= 1
    }

    result = leftResult.coerceAtLeast(rightResult)
    return result
}