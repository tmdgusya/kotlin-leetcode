import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question438: FunSpec({
    test("case 01") {
        val s = "cbaebabacd"
        val p = "abc"

        val expected = listOf<Int>(0, 6)

        findAnagrams(s, p) shouldBe expected
    }
})

fun findAnagrams(s: String, p: String): List<Int> {
    val result = mutableListOf<Int>()
    // make window what frequency of characters of p
    val frequencyOfP = IntArray(26) { 0 }
    for (char in p) {
        frequencyOfP[char - 'a']++
    }

    // make window that frequency of characters of s (sliding window)
    val slidingWindow = IntArray(26) { 0 }
    var start = 0
    var end = 0
    while (end < s.length) {
        slidingWindow[s[end] - 'a']++
        val slidingWindowSize = end - start + 1
        if (slidingWindowSize == p.length) {
           if (frequencyOfP.isSame(slidingWindow)) result.add(start)
        }

        if (slidingWindowSize < p.length) end++
        else {
            slidingWindow[s[start] - 'a']--
            start++
            end++
        }
    }
    return result
}

private fun IntArray.isSame(other: IntArray): Boolean {
    if (this.size != other.size) return false
    for (i in indices) {
        if (this[i] != other[i]) return false
    }
    return true
}