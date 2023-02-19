import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question40 : FunSpec({

    test("case 01") {
        val candidates = intArrayOf(1, 2, 3, 4)
        val target = 2
        combinationSum2(candidates, target) shouldBe listOf(
            listOf(2)
        )
    }
})

fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> =
    mutableListOf<MutableList<Int>>().also {
        backtrack(mutableListOf(), target, 0, makeCounterTuples(candidates), it)
    }

private fun makeCounterTuples(candidates: IntArray): MutableList<Pair<Int, Int>> {
    val counter: MutableMap<Int, Int> = mutableMapOf<Int, Int>()

    for (candidate in candidates) {
        if (counter.containsKey(candidate)) {
            val freq = checkNotNull(counter[candidate])
            counter[candidate] = freq + 1
        } else counter[candidate] = 1
    }

    return counter.toTuples()
}

private fun backtrack(
    comb: MutableList<Int>,
    remain: Int,
    curr: Int,
    counter: MutableList<Pair<Int, Int>>,
    results: MutableList<MutableList<Int>>,
) {
    if (remain <= 0) {
        if (remain == 0) {
            results.add(ArrayList(comb))
        }
        return
    }

    for (i in curr until counter.size) {
        val polled = counter[i]
        val candidate = polled.first
        val freq = polled.second

        if (freq <= 0) continue

        comb.add(candidate)
        counter[i] = candidate to freq - 1

        backtrack(comb, remain - candidate, i, counter, results)

        counter[i] = candidate to freq
        comb.removeAt(comb.lastIndex)
    }
}

private fun MutableMap<Int, Int>.toTuples(): MutableList<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()
    for (ele in this) {
        list.add(ele.key to ele.value)
    }
    return list
}

