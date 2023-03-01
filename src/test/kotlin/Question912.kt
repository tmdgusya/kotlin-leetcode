import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question912 : FunSpec({
    test("1") {
        val nums = intArrayOf(5, 2, 3, 1)
        sortArray(nums) shouldBe intArrayOf(1, 2, 3, 5)
    }
})

fun sortArray(nums: IntArray): IntArray {
    mergeSort(nums, 0, nums.size - 1)
    return nums
}

fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
    val leftPartition = m + 1 - l
    val rightPartition = r - m
    val left = createPartition(leftPartition, arr, l, l)
    val right = createPartition(rightPartition, arr, m + 1, m + 1)

    var leftPointer = 0
    var rightPointer = 0
    var pointer = l
    while (leftPointer < leftPartition || rightPointer < rightPartition) {
        if (rightPointer == rightPartition || leftPointer < leftPartition && left[leftPointer] < right[rightPointer]) arr[pointer++] = left[leftPointer++] else arr[pointer++] =
            right[rightPointer++]
    }
}

private fun createPartition(leftPartition: Int, arr: IntArray, l: Int, step: Int): IntArray {
    val left = IntArray(leftPartition)
    for (i in 0 until leftPartition) {
        left[i] = arr[step + i]
    }
    return left
}

fun mergeSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val middle = (high - low) / 2 + low
        mergeSort(arr, low, middle)
        mergeSort(arr, middle + 1, high)
        merge(arr, low, middle, high)
    }
}