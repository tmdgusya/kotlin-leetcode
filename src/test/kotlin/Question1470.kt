import io.kotest.core.spec.style.FunSpec

class Question1470: FunSpec({
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

