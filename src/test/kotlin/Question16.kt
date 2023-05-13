import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question16 : FunSpec({
    test("case 01") {
        val given = intArrayOf(-1, 2, 1, -4)
        val target = 1

        val result = threeSumClosest(given, target)

        result shouldBe 2
    }
})

fun threeSumClosest(nums: IntArray, target: Int): Int {
    nums.sort()
    var acc = Int.MAX_VALUE

    for (i in 0 .. nums.size - 2) {
        var left = i + 1
        var right = nums.lastIndex
        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]
            if (sum == target) {
                return sum
            } else if (sum > target) {
                right--
            } else left++

            if (Math.abs(target - acc) > Math.abs(target - sum)) {
                acc = sum
            }
        }
    }

    return acc
}