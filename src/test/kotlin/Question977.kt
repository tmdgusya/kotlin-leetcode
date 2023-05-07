import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question977: FunSpec({
    test("case 01") {
        val nums = intArrayOf(-4,-1,0,3,10)

        val result = sortedSquares(nums)

        result shouldBe intArrayOf(0,1,9,16,100)
    }

    test("case 02") {
        val nums = intArrayOf(-10000,-9999,-7,-5,0,0,10000)

        val result = sortedSquares(nums)

        result shouldBe intArrayOf(0,0,25,49,99980001,100000000,100000000)
    }

    test("")
})

private fun sortedSquares(nums: IntArray): IntArray {
    var left = 0
    var right = nums.lastIndex
    val result = IntArray(nums.size)
    var index = nums.lastIndex
    // loop until left smaller than right
    while (left < right) {
        val l = nums[left] * nums[left]
        val r = nums[right] * nums[right]
        if (l < r) {
            result[index--] = r
            right--
        } else {
            result[index--] = l
            left++
        }
    }

    return result
}