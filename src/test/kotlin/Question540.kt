import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question540 : FunSpec({
    test("case 01") {
        val nums = intArrayOf(1,1,2,3,3,4,4,8,8)

        singleNonDuplicate(nums) shouldBe 2
    }
})

fun singleNonDuplicate(nums: IntArray): Int {
    var start = 0
    while (start < nums.size) {
        val cur = nums[start]
        val next = nums.getOrNull(start + 1)
        if (cur != next) return cur
        else start += 2
    }
    return 0
}