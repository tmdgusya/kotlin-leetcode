import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question2104 : FunSpec({

    test("case 01") {

        val result = subArrayRanges(
            intArrayOf(1, 2, 3)
        )

        result shouldBe 4
    }
})

fun subArrayRanges(nums: IntArray): Long {
    var result: Long = 0L
    for (i in 0 until nums.size) {
        var max = nums[i] // 2
        var min = nums[i] // 2
        for (j in i until nums.size) {
            if (i == j) continue
            max = Math.max(max, nums[j])
            min = Math.min(min, nums[j])
            result += max - min
        }
    }
    return result
}