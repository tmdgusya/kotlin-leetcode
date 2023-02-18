import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question42 : FunSpec({
    test("case 01") {
        val height = intArrayOf(4,2,3)
        val expected = 1

        trapByDp(height) shouldBe expected
    }
})

//fun trap(height: IntArray): Int {
//    val reports = IntArray(height.size)
//
//    for (i in height.indices) {
//
//        var maxOfLeft = 0
//        var maxOfRight = 0
//        // 왼쪽 중 최고로 높은 막대
//        for (j in i downTo 0) {
//            maxOfLeft = Math.max(maxOfLeft, height[j])
//        }
//        // 오른쪽 중 최고록 높은 막대
//        for (j in i until height.size) {
//            maxOfRight = Math.max(maxOfRight, height[j])
//        }
//
//        // 결국 최고로 높은 애들중 작은 애들 - 내꺼가 내가 채울수 있는 높이임
//        reports[i] = Math.min(maxOfLeft, maxOfRight) - height[i]
//    }
//
//    return reports.sum()
//}

fun trapByDp(height: IntArray): Int = if (height.isEmpty()) 0
    else calc(height, caldDpLeft(height), calcDpRight(height))


private fun calcDpRight(height: IntArray): IntArray {
    val dpRight = IntArray(height.size)
    dpRight[height.lastIndex] = height[height.lastIndex]

    for (i in height.lastIndex - 1 downTo 0) {
        dpRight[i] = Math.max(dpRight[i + 1], height[i])
    }
    return dpRight
}

private fun caldDpLeft(height: IntArray): IntArray {
    val dpLeft = IntArray(height.size)
    dpLeft[0] = height[0]
    for (i in 1 until height.size) {
        dpLeft[i] = Math.max(dpLeft[i - 1], height[i])
    }
    return dpLeft
}

private fun calc(height: IntArray, dpLeft: IntArray, dpRight: IntArray): Int {
    var result = 0

    for (i in 1 until height.size) {
        result += Math.min(dpLeft[i], dpRight[i]) - height[i]
    }

    return result
}