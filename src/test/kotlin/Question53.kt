import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Question53 : DescribeSpec({

    val solution = Question53Solution()

    describe("Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.") {
        it("if given nums = [-2,1,-3,4,-1,2,1,-5,4] answers sum = 6.") {
            solution.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)) shouldBe 6
        }

        it("if given nums = [5,4,-1,7,8] answers 23.") {
            solution.maxSubArray(intArrayOf(5, 4, -1, 7, 8)) shouldBe 23
        }

        it("if given nums = [1] answers 1.") {
            solution.maxSubArray(intArrayOf(1)) shouldBe 1
        }
    }
})

internal class Question53Solution {
    fun maxSubArray(nums: IntArray): Int? {
        if (nums.none { it.isPositive() }) return nums.minOrNull()

        return nums.filter { it.isPositive() }.sum()
    }
}

fun Int.isPositive(): Boolean {
    return this >= 0
}
