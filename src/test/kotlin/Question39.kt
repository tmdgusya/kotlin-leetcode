import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Solution39 : FunSpec({

    test("case01") {
        val question = Question39()
        val candidates = intArrayOf(2, 3, 6, 7)
        val target = 7

        val result = question.combinationSum(candidates, target)

        result shouldBe listOf(listOf(2,2,3), listOf(7))
    }
})


class Question39 {

    val answerSet = mutableSetOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        recursiveSum(0, mutableListOf(), 0, candidates, target)
        return answerSet.toList()
    }

    fun recursiveSum(curIdx: Int, curComb: MutableList<Int>, curSum: Int, candidates: IntArray, target: Int) {
        if (curSum > target) {
            return
        }

        if (curSum == target) {
            answerSet.add(curComb.map { it })
            return
        }

        for (i in curIdx until candidates.size) {
            curComb.add(candidates[i])
            recursiveSum(i, curComb, curSum + candidates[i], candidates, target)
            curComb.removeAt(curComb.lastIndex)
        }
    }
}
