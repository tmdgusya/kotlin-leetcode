import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question567 : FunSpec({
    test("case 01") {
        val given = "ab"
        val s2 = "eidbaooo"

        val expected = true

        checkInclusion(given, s2) shouldBe expected
    }

    test("case 02") {
        val given = "ab"
        val s2 = "eidboaoo"

        val expected = false

        checkInclusion(given, s2) shouldBe expected
    }
})

fun checkInclusion(s1: String, s2: String): Boolean {
    // sliding windows approach

    // create windows frequency of characters of s1 and s2
    val frequencyOfCharactersOfS1 = IntArray(26) { 0 }
    val frequencyOfCharactersOfS2 = IntArray(26) { 0 }

    for (char in s1) frequencyOfCharactersOfS1[char - 'a']++

    // s2 sliding window pointers
    var start = 0
    var end = 0

    while (end < s2.length) {
        frequencyOfCharactersOfS2[s2[end] - 'a']++
        println("${Arrays.toString(frequencyOfCharactersOfS1)}, ${Arrays.toString(frequencyOfCharactersOfS2)}")
        val windowSize = end - start + 1

        // check is equal frequency of chars of s1 with frequency of chars of s2 if current window size is same as length of s1
        if (windowSize == s1.length) {
            if (isSameFrequencies(frequencyOfCharactersOfS1, frequencyOfCharactersOfS2)) return true
        }

        // grow current window size if smaller than s1.length
        if (windowSize < s1.length) end++
        // move pointer of current window
        else {
            frequencyOfCharactersOfS2[s2[start] - 'a']--
            start++
            end++
        }
    }
    return false
}

private fun isSameFrequencies(frequencyOfCharactersOfS1: IntArray, frequencyOfCharactersOfS2: IntArray): Boolean {
    for (i in 0 until 26) {
        if (frequencyOfCharactersOfS1[i] != frequencyOfCharactersOfS2[i]) return false
    }
    return true
}
