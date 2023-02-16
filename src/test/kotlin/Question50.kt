import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.text.DecimalFormat

class Question50 : FunSpec({
    test("case 01") {
        val given = 2.00000
        val n = 10

        val expected = 1024.00000

        myPow(given, n) shouldBe expected
    }

    test("case 03") {
        val x = 34.00515
        val n = -3

        val expected = 0.00002543
        val df = DecimalFormat("#.#####")

        df.format(myPow(x, n)) shouldBe df.format(expected)
    }
})

fun myPow(x: Double, n: Int): Double {
    val _x = if (n < 0) 1/x else x
    val _n = if (n < 0) -n else n
    return halfPow(_n.toLong(), _x)
}

private fun halfPow(n: Long, x: Double): Double {
    if (n == 0L) return 1.0
    var result = halfPow(n / 2L, x)
    result *= if (n % 2 == 0L) result else result * x
    return result
}

//private tailrec fun pow(x: Double, n: Long, acc: Double = 1.0): Double {
//    if (n == 0L) return acc
//    return pow(x, n - 1, acc * x)
//}
