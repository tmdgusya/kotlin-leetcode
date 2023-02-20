import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question35 : FunSpec({
    test("case 01") {
        val nums = intArrayOf(1,2,4,6,8,9,10)
        val target = 10

        searchInsert(nums, target) shouldBe 6
    }
})

//O(N)
//fun searchInsert(nums: IntArray, target: Int): Int {
//    for (i in nums.indices) {
//        if (nums[i] >= target) {
//            return if (i != 0) i
//            else 0
//        }
//    }
//    return nums.size
//}

// O(logN)
fun searchInsert(nums: IntArray, target: Int): Int {
    return searchInsert(nums, target, 0)
}

fun searchInsert(nums: IntArray, target: Int, pointer: Int): Int {
    if (nums.isEmpty()) return pointer
    if (nums.size == 1 && target > nums[0]) return pointer + 1
    if (nums.size == 1 && target <= nums[0]) return pointer
    // 중간 값을
    val middleIndex = nums.size / 2
    val middle = nums[middleIndex]

    return when {
        middle == target -> middleIndex
        middle < target -> searchInsert(
            nums.slice(IntRange(middleIndex + 1, nums.lastIndex)).toIntArray(),
            target,
            pointer + middleIndex + 1
        )
        else -> searchInsert(nums.slice(IntRange(0, middleIndex - 1)).toIntArray(), target, pointer)
    }
}
