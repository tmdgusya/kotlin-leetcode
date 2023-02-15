import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList


class Question989 : FunSpec({

    test("case 01") {
        // num = [1,2,0,0], k = 34
        val num = intArrayOf(1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,0,6,3)
        val k = 516

        addToArrayForm(num, k) shouldBe listOf(1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,5,7,9)
    }
})

fun addToArrayForm(num: IntArray, k: Int): List<Int>? {
    var acc = k
    val res = mutableListOf<Int>()
    for (i in num.indices.reversed()) {
        res.add(0, (num[i] + acc) % 10)
        acc = (num[i] + acc) / 10
    }
    while (acc > 0) {
        res.add(0, acc % 10)
        acc /= 10
    }
    return res
}



// 밑에 풀이는 OverFlow 나서 안됨 ㅠ;
fun ULong.toList(): List<Int> {
    val acc = mutableListOf<Int>()
    var result: ULong = 0u
    var i = 0
    val final = Math.log10(this.toDouble()).toInt()
    while (i <= final + 1) {
        result = Math.pow(10.0, i.toDouble()).toULong()
        val index: ULong = when {
            i == final + 1 -> this / result.div(10u)
            i == 0 -> {
                i++
                this % 10u
            }
            else -> {
                (this % result) / Math.pow(10.0, (i-1).toDouble()).toULong()
            }
        }
        acc.add(0, index.toInt())
        i++
    }
    return acc
}

fun IntArray.toLong(): Long {
    var acc = 0.0
    for (i in this.lastIndex downTo 0) {
        acc += this[i] * Math.pow(10.0, (this.lastIndex - i).toDouble())
    }
    return acc.toLong()
}