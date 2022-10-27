package codingInterview

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question45 : FunSpec ({

    test("case 01") {
        val solution = Solution45()
        val nums = intArrayOf(2, 3, 1, 1, 4)

        solution.jump(nums) shouldBe 2
    }

    test("case 02") {
        val solution = Solution45()
        val nums = intArrayOf(2,3,0,1,4)

        solution.jump(nums) shouldBe 2
    }

    test("case 03") {
        val solution = Solution45()
        val nums = intArrayOf(1,2,1,1,1)

        solution.jump(nums) shouldBe 3
    }

    test("case 04") {
        val solution = Solution45()
        val nums = intArrayOf(1,1,1,1,1)

        solution.jump(nums) shouldBe 4
    }

    test("case 05") {
        val solution = Solution45()
        val nums = intArrayOf(1,3,4,1,1,1,1)

        solution.jump2(nums) shouldBe 3
    }
})

class Solution45 {

    fun jump2(nums: IntArray): Int {
        var jumpCount = 0
        var maxLengthOfJump = 0
        var currentCalcualateResult = 0
        for (i in 0 until nums.size - 1) {
            currentCalcualateResult = calculateMaxLengthOfJump(currentCalcualateResult, i, nums)
            if (i == maxLengthOfJump) {
                jumpCount++
                maxLengthOfJump = currentCalcualateResult
            }
        }
        return jumpCount
    }

    private fun calculateMaxLengthOfJump(cur: Int, i: Int, nums: IntArray) = Math.max(cur, i + nums[i])

    var min = Int.MAX_VALUE

    fun jump(nums: IntArray): Int {
        if (nums.size == 1) return 0
        if (nums[0] >= nums.lastIndex) return 1
        searchMinimumLength(nums, 0, 0)
        return min
    }

    private fun searchMinimumLength(nums: IntArray, start: Int, jumpCount: Int) {
        for (j in start + 1 .. (start + nums[start])) {
            if (nums[start] + start >= nums.lastIndex) { // can jump by finisible
                min = Math.min(min, jumpCount + 1)
            } else {
                searchMinimumLength(nums, j, jumpCount + 1)
            }
        }
    }
}
