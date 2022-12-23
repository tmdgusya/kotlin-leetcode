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

    test("case 05") {
        // given
        val case = 5

        // when && then
        climbStairs(case) shouldBe 8
    }
})

tailrec fun climbStairs(n: Int, acc1: Int = 1, acc2: Int = 1): Int = if (n <= 1) n * acc1 else climbStairs(n - 1, acc1 + acc2, acc1)