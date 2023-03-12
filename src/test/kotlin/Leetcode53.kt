import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.max

class Leetcode53 : FunSpec({

        test("case 01") {
            val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val expected = 6
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 02") {
            val nums = intArrayOf(1)
            val expected = 1
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 03") {
            val nums = intArrayOf(5, 4, -1, 7, 8)
            val expected = 23
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 04") {
            val nums = intArrayOf(-2, 1)
            val expected = 1
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 05") {
            val nums = intArrayOf(-2, -1)
            val expected = -1
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 06") {
            val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val expected = 6
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 07") {
            val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val expected = 6
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 08") {
            val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val expected = 6
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }

        test("case 09") {
            val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
            val expected = 6
            val actual = maxSubArray(nums)
            actual shouldBe expected
        }
})

fun maxSubArray(nums: IntArray): Int {
    var maxSub = nums[0]
    var curSum = 0

    for (num in nums) {
        if (curSum < 0) curSum = 0
        curSum += num
        maxSub = Math.max(maxSub, curSum)
    }

    return maxSub
}

//fun maxSubArray(nums: IntArray): Int {
//    var start = 0
//    val end = nums.size - 1
//    var max = Int.MIN_VALUE
//
//    while (start <= end) {
//        var pointer = start
//        var acc = 0
//        while (pointer <= end) {
//            acc += nums[pointer]
//            max = if (max < acc) acc else max
//            pointer++
//        }
//        start++
//    }
//
//    return max
//}