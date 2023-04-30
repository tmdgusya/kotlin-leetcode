import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question167: FunSpec({
    test("case 01") {
        val given = intArrayOf(2,7,11,15)
        val target = 9

        val result = twoSum(given, target)

        result shouldBe intArrayOf(1,2)
    }
})

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var pointer_1 = 0
    var pointer_2 = numbers.lastIndex

    while (pointer_1 < pointer_2) {
        val sum = numbers[pointer_1] + numbers[pointer_2]

        when {
            sum == target -> return intArrayOf(pointer_1 + 1, pointer_2 + 1)
            sum < target -> pointer_1++
            sum > target -> pointer_2--
        }
    }
    return intArrayOf(-1, -1)
}