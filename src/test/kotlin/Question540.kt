import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question540 : FunSpec({
    test("case 01") {
        val nums = intArrayOf(1,1,2,2,3,3,4,4,5,8,8)
        val nums2 = intArrayOf(1,1,2,2,0)

        singleNonDuplicate(nums2) shouldBe 0
    }
})

// O(N)
//fun singleNonDuplicate(nums: IntArray): Int {
//    var start = 0
//    while (start < nums.size) {
//        val cur = nums[start]
//        val next = nums.getOrNull(start + 1)
//        if (cur != next) return cur
//        else start += 2
//    }
//    return 0
//}

//O(logN)
fun singleNonDuplicate(nums: IntArray): Int {
    var left= 0
    var right = nums.lastIndex

    while (left < right) {
        // 무조건 짝수 자리면 +1 자리에 같은 수가 있음
        var middle = (left + right) / 2
        if (middle.isOdd()) middle--
        if (nums[middle] != nums[middle + 1]) {
            right = middle
        } else left += 2
    }
    return nums[left]
}

//fun singleNonDuplicate(nums: IntArray): Int {
//    var left = 0
//    var right = nums.size - 1
//    while (left < right) {
//        var mid = (left + right) / 2
//        if (mid % 2 == 1) {
//            mid--
//        }
//        if (nums[mid] != nums[mid + 1]) {
//            right = mid
//        } else {
//            left = mid + 2
//        }
//    }
//    return nums[left]
//}

private fun Int.isOdd(): Boolean {
    return (this and 0x1) == 1
}