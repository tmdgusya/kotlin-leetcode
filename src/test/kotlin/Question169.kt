import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question169 : FunSpec({
    test("case 01") {
        val nums = intArrayOf(3,2,3)
        val expected = 3

        majorityElement(nums) shouldBe 3
    }
})

fun majorityElement(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()

    for (i in nums) {
        if (map[i] == null) {
            map[i] = 0
        }
        map[i] = map[i]!! + 1
    }

    var max = 0
    var result = nums[0]
    for ((key, value) in map) {
        if (value > max) {
            max = value
            result = key
        }
    }

    return result
}