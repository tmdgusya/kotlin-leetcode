import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question67 : FunSpec({

    test("case 01") {
        var a = "1010"
        var b = "1011"

        addBinary(a, b) shouldBe "10101"
    }
})
private fun addBinary(a: String, b: String): String {
    val first = if (a.length >= b.length) a else b
    val second = if (first != a) grow(b, a) else grow(a, b)

    val test = mutableListOf<Int>()

    var carry = 0

    for (i in first.lastIndex downTo 0) {
        val one = first[i]
        val _second = second[i]
        var result = one.plus(_second)

        val pair = add(carry, result)

        carry = pair.first
        result = pair.second

        test.add(0, result)
    }

    for (i in 0 until carry) {
        test.add(0, 1)
    }

    return test.join()
}

private fun add(carry: Int, result: Int): Pair<Int, Int> {
    var carry1 = carry
    var result1 = result
    if (carry1 == 1 && result1 == 1) {
        result1 = 0
        carry1 = 1
    } else if (result1 == 0 && carry1 == 1) {
        result1 = 1
        carry1 = 0
    } else if (result1 == 2 && carry1 == 1) {
        result1 = 1
        carry1 = 1
    } else if (result1 == 2) {
        carry1 = 1
        result1 = 0
    }
    return Pair(carry1, result1)
}

private fun grow(a: String, b: String): CharArray {
    val array = CharArray(a.length)
    val diff = a.length - b.length
    if (diff == 0) b.toCharArray()
    for (i in array.indices) {
        if (i <= diff - 1) {
            array[i] = '0'
        } else array[i] = b[i - diff]
    }
    return array
}

private fun List<Int>.join() = buildString {
    for (ele in this@join) {
        append(ele)
    }
}

private fun Char.plus(other: Char): Int = when {
    this == '1' && other == '1' -> 2
    this == '1' && other == '0' -> 1
    this == '0' && other == '1' -> 1
    else -> 0
}