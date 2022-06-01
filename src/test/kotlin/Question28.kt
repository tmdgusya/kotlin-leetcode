import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Question28 : DescribeSpec({

    val soltion = Solution()

    describe("Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack") {

        it("given haystick = hello, needle = ll") {
            soltion.strStr(haystack = "hello", needle = "ll") shouldBe 2
        }

        it("given haystack = aaaaa, needle = bba") {
            soltion.strStr(haystack = "aaaaa", needle = "bba") shouldBe -1
        }
    }
})

class Solution {
    fun strStr(haystack: String, needle: String): Int {
        return haystack.customIndexOf(needle)
    }
}

fun String.customIndexOf(otherString: String): Int {
    if (this.isEmpty()) return 0
    if (otherString.isEmpty()) return -1

    for (i in this.indices) {

        if (isExceededIndex(i + otherString.length)) break

        for (j in otherString.indices) {
            if (this[i + j] notEquals otherString[j]) break
            if (j equals otherString.length - 1) return i
        }
    }

    return -1
}

fun String.isExceededIndex(idx: Int): Boolean {
    return this.length < idx
}

infix fun <T> T.equals(o: T): Boolean {
    return this == o
}

infix fun <T> T.notEquals(o: T): Boolean {
    return this != o
}
