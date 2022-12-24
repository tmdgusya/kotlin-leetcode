package codingInterview

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question70: FunSpec({
    test("case 01") {
        // given
        val case = 3

        // when && then
        climbStairs(3) shouldBe 3
    }

    test("case 02") {
        // given
        val case = 4

        // when && then
        climbStairs(4) shouldBe 5
    }

    test("case 05") {
        // given
        val case = 5

        // when && then
        climbStairs(case) shouldBe 8
    }
})

fun climbStairsForIter(n: Int): Int =
    if (n <= 2) n
    else {
        var acc1 = 1
        var acc2 = 2
        (3 .. n).forEach { _ ->
            val temp = acc2
            acc2 += acc1
            acc1 = temp
        }
        acc2
    }

/**
 *
 *
 * @param n
 * @param acc2 둘다 초기값은 1 (입력값이 1이상이였기 때문)
 * @param acc1 둘다 초기값은 1 (입력값이 1이상이였기 때문)
 */
tailrec fun climbStairs(n: Int, acc1: Int = 1, acc2: Int = 2): Int = when (n) {
    1 -> acc1
    2 -> acc2
    else -> climbStairs(n - 1, acc2, acc2 + acc1)
}
