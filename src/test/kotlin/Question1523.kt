import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.ceil

class Question1523 : FunSpec({

    test("case 01") {
        val low = 3
        val high = 7

        anotherSolution(low, high) shouldBe 3
    }
})

private fun countOdds(low: Int, high: Int): Int {
    var count = 0
    for (i in low .. high) {
        if (i.isOdd() == 1) count++
    }
    return count
}
private fun anotherSolution(low: Int, high: Int): Int {
    if (low == high) return low.isOdd()
    var _low = low
    if (_low.isOdd() == 0) ++_low
    val oddCount = (high - low + 1).toDouble() / 2
    return ceil(oddCount).toInt()
}

fun Int.isOdd(): Int {
    return this and 0x1
}