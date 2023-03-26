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

    test("case 02") {
        val given = intArrayOf(1,2,3,4)

        val result = permute(given)
        val expected = listOf(
            listOf(1,2,3,4),
            listOf(1,2,4,3),
            listOf(1,3,2,4),
            listOf(1,3,4,2),
            listOf(1,4,2,3),
            listOf(1,4,3,2),
            listOf(2,1,3,4),
            listOf(2,1,4,4),
            listOf(2,3,1,4),
            listOf(2,3,4,1),
            listOf(2,4,1,3),
            listOf(2,4,3,1),
            listOf(3,2,1),
            listOf(3,1,2)
        )
        println(result)
        println(expected.size)

        result shouldBe expected
    }

})

//fun permute(nums: IntArray): List<List<Int>> {
//    val result = mutableListOf<MutableList<Int>>()
//    val tempList = mutableListOf<Int>()
//    for (num in nums) tempList.add(num)
//    backTracking(nums.size, result, tempList, 0)
//    return result
//}
//
//fun backTracking(
//    size: Int,
//    result: MutableList<MutableList<Int>>,
//    tempList: MutableList<Int>,
//    start: Int) {
//    if (start == size) result.add(ArrayList(tempList))
//
//    for (i in start until size) {
//        // start Index 값이 제일 앞으로 오도록 변경
//        Collections.swap(tempList, start, i)
//        backTracking(size, result, tempList, start + 1)
//        Collections.swap(tempList, start, i)
//    }
//}

fun permute(nums: IntArray): List<List<Int>> {
    val resultSet: MutableSet<MutableList<Int>> = mutableSetOf()
    val max = nums.size
    comb(nums, mutableListOf(), resultSet, 0, max)
    return resultSet.toList()
}

fun comb(array: IntArray, acc: MutableList<Int>, resultSet: MutableSet<MutableList<Int>>, depth: Int, max: Int) {
    if (depth == max) {
        resultSet.add(acc)
        return
    }

    for (ele in array) {
        if (ele in acc) continue
        val copied = acc.toMutableList()
        copied.add(ele)
        comb(array, copied, resultSet, depth + 1, max)
    }
}