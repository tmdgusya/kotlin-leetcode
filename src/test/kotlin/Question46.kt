import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Collections

class Question46 : FunSpec({

    test("case 01") {
        val given = intArrayOf(1,2,3)

        val result = permute(given)

        result shouldBe listOf(
            listOf(1,2,3),
            listOf(1,3,2),
            listOf(2,1,3),
            listOf(2,3,1),
            listOf(3,2,1),
            listOf(3,1,2)
        )
    }

})

fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    val tempList = mutableListOf<Int>()
    for (num in nums) tempList.add(num)
    backTracking(nums.size, result, tempList, 0)
    return result
}

fun backTracking(
    size: Int,
    result: MutableList<MutableList<Int>>,
    tempList: MutableList<Int>,
    start: Int) {
    if (start == size) result.add(ArrayList(tempList))

    for (i in start until size) {
        // start Index 값이 제일 앞으로 오도록 변경
        Collections.swap(tempList, start, i)
        backTracking(size, result, tempList, start + 1)
        Collections.swap(tempList, start, i)
    }
}