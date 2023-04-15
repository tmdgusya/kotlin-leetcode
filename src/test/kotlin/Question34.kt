import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question34: FunSpec({

    test("case 01") {
        val nums = intArrayOf(5,7,7,8,8,10)
        val target = 8
        val expected = intArrayOf(3,4)

        searchRange(nums, target) shouldBe expected
    }

})

fun searchRange(nums: IntArray, target: Int): IntArray {
    val first = search(nums, target, true)
    val second = search(nums, target, false)

    return intArrayOf(first, second)
}

fun search(nums: IntArray, target: Int, isFirstWrite: Boolean): Int {
    var start = 0
    var end = nums.lastIndex

    while (start <= end) {
        val middle = (start + end) / 2
        when {
            nums[middle] > target -> end = middle - 1
            nums[middle] < target -> start = middle + 1
            nums[middle] == target -> {
                if (isFirstWrite) {
                    // 반드시 체크해야 함 middle - 1 을 하려면
                    if (middle == start || nums[middle - 1] != target) {
                        return middle
                    }

                    end = middle - 1
                } else {
                    // 반드시 체크해야 함 middle + 1 을 하려면
                    if (middle == end || nums[middle + 1] != target) {
                        return middle
                    }

                    start = middle + 1
                }
            }
        }
    }
    return -1
}