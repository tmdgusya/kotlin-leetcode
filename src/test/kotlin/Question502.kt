import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Collections
import java.util.PriorityQueue

class Question502 : FunSpec({
    test("case 01") {
        val k = 2
        val w = 0
        val profits = intArrayOf(1,2,3)
        val capital = intArrayOf(0,9,10)

        findMaximizedCapital(k, w, profits, capital) shouldBe 1
    }
})

// Leetcode 는 IPO 전에 주식 불리기를 위해 K 개의 프로젝트를 진행하려고 한다.
// N 개의 프로젝트가 주어진다.
// capital 은 프로젝트 시작할 수 있는 최대 자본금
// Project 가 끝나면 capital 에 얻은 이윤이 추가된다.
// sudo-code : if (project == FINISH)  w += profits[i]
//fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
//    if (profits.isEmpty() || capital.isEmpty()) return 0
//    var myCapital = w
//    val profitsAndCapitals = profits.zip(capital).sortedBy { it.second }
//    val projectMaxCount = Math.min(k, profits.size)
//    val board = BooleanArray(capital.size) { false }
//    for (i in 1 .. projectMaxCount) {
//        var maxProfit = 0
//        var mostValuableProjectIndex = -1
//        for (j in profitsAndCapitals.indices) {
//            if (profitsAndCapitals[j].second > myCapital) break
//            val isCanStartProject = myCapital >= profitsAndCapitals[j].second
//            if (isCanStartProject && !board[j]) {
//                if (profitsAndCapitals[j].first >= maxProfit) {
//                    mostValuableProjectIndex = j
//                    maxProfit = profitsAndCapitals[j].first
//                }
//            }
//        }
//        if (mostValuableProjectIndex != -1) board[mostValuableProjectIndex] = true
//        myCapital += maxProfit
//    }
//
//    return myCapital
//}

fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
    if (profits.isEmpty() || capital.isEmpty()) return 0
    var myCapital = w
    val profitsAndCapitals = profits.zip(capital).sortedBy { it.second }
    val queue = PriorityQueue<Int>(profits.size, Collections.reverseOrder())
    var ptr = 0
    for (i in 0 until k) {
        while (ptr < profits.size && profitsAndCapitals[ptr].second <= myCapital) {
            queue.add(profitsAndCapitals[ptr++].first)
        }
        if (queue.isEmpty()) break
        myCapital += queue.poll()
    }

    return myCapital
}