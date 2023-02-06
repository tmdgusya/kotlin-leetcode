import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question1470: FunSpec({

    test("case 01") {
        val nums = intArrayOf(2,5,1,3,4,7)
        val n = 3

        shuffleByBitwise(nums, n) shouldBe intArrayOf(2,3,5,4,1,7)
    }

    fun shuffle(nums: IntArray, n: Int): IntArray {
        // 한자리인 경우도 있으니 한자리인 경우는 바로 리턴하도록 추가 코드
        if (n == 1) return nums

        val results = IntArray(2*n)

        // 배열을 반절로 나누면 앞에가 [x1, x2, x3, ...], 뒤에가 [y1, y2, y3, ...]
        var pointerX = 0
        var pointerY = n
        var inputIndexOfX = 0
        var inputIndexOfY = 1

        // 투포인터로 하나씩 넣기
        while (pointerX < n) {
            results[inputIndexOfX] = nums[pointerX]
            results[inputIndexOfY] = nums[pointerY]
            inputIndexOfX += 2
            inputIndexOfY += 2
            pointerX++
            pointerY++
        }

        return results
    }
})


fun shuffleByBitwise(nums: IntArray, n: Int): IntArray {
    val allOnesForTenBits = (Math.pow(2.0, 10.0) - 1).toInt()
    // n 이 어차피 32-bit 정수고, nums 안에오는 원소 중 제일 큰 수는 500
    // 즉, 비트로 표현하면 10개비트(2^9) 이상을 못씀.
    // 따라서 32 비트 중 12 / 10(right) / 10(left) 씩 사용해서 Tuple 구조로 사용가능함
    for (i in n until n * 2) {
        val secondNum = nums[i] shl 10
        nums[i - n] = nums[i - n] or secondNum
    }

    for (i in n - 1 downTo  0) {
        val secondNum = nums[i] shr 10
        val firstNum = nums[i] and allOnesForTenBits
        nums[2 * i + 1] = secondNum
        nums[2 * i] = firstNum
    }
    return nums
}

