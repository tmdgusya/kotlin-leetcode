package codingInterview

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question01 : FunSpec({
    test("You are given an array of positive numbers from 1 to n, such that all numbers from 1 to n are present except one number x") {
        val input = listOf(3,7,1,2,8,4,5)

        // x only single value

        val result = find_missing(input)

        result shouldBe 6
    }
})


// Time: O(N) / Space: O(N) Solution
fun find_missing(input: List<Int>): Int {

    val combination = (1..input.size + 1)
    val indicator = BooleanArray(input.size + 1) { false }

    for (ele in input) {
        if (combination.contains(ele)) {
            indicator[ele - 1] = true
        }
    }

    for (i in indicator.indices) {
        if (!indicator[i]) {
            return i + 1
        }
    }

    return -1
}