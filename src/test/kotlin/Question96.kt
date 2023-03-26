import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question96 : FunSpec({
    test("Unique Binary Search Trees") {
        numTrees(3) shouldBe 5
    }
})


/**
 * optimize memoized solution
 *
 * @param n
 * @return
 */
fun g(n: Int): Int {
    val memo = Array<Int>(size = n + 1) { 0 }
    memo[0] = 1
    memo[1] = 1
    for (i in 2 .. n) {
        for (j in 1 .. i) {
            memo[i] += memo[j - 1] * memo[i - j]
        }
    }
    return memo[n]
}
fun numTrees(n: Int): Int = when(n) {
    0 -> 1
    1 -> 1
    else -> g_(n)
}

fun g_(n: Int): Int {
    if (n == 1 || n == 0) return 1
    var result = 0
    for (i in 1 .. n) {
        result += f(i, n)
    }
    return result
}

fun f(i: Int, n: Int) = g_(i - 1) * g_(n - i)