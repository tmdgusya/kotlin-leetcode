import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Question05 : DescribeSpec({

    val soltion = Solution05()

    describe("Given a string s, return the longest palindromic substring in s.") {

        it("given string = babad, output = bab") {
            soltion.longestPalindrome(s = "babad") shouldBe "bab"
        }

        it("given string = cbbd, output = bb") {
            soltion.longestPalindrome(s = "cbbd") shouldBe "bb"
        }

        it("if given single word, then early return that") {
            soltion.longestPalindrome(s = "a") shouldBe "a"
        }
    }

    describe("[TDD STEP-1] Is can verify palindromic string?") {

        it("given aba is true") {
            soltion.isPalindrom(listOf('a', 'b', 'a')) shouldBe true
        }

        it("given cbbc is true") {
            soltion.isPalindrom(listOf('c', 'b', 'b', 'c')) shouldBe true
        }

        it("given cbbd is false") {
            soltion.isPalindrom(listOf('c', 'b', 'b', 'd')) shouldBe false
        }

        it("return true if given word is empty true") {
            soltion.isPalindrom(listOf()) shouldBe true
        }

        it("return true if given word is single true") {
            soltion.isPalindrom(listOf('a')) shouldBe true
        }
    }

    describe("[TDD STEP-2] Is can store max length substring?") {

        val store = Solution05.Store()

        it("given aba, abad, roach answers roach") {
            store.storeMaxSubString("aba")
            store.storeMaxSubString("abad")
            store.storeMaxSubString("roach") shouldBe "roach"
        }

        it("given aba, roach, abad answers roach 2") {
            store.storeMaxSubString("aba")
            store.storeMaxSubString("roach")
            store.storeMaxSubString("abad") shouldBe "roach"
        }
    }
})

class Solution05 {
    val store = Store()

    fun longestPalindrome(s: String): String {
        for (i in s.indices) {
            val result = mutableListOf(s[i])
            for (j in i until s.length) {
                if (i != j) result.add(s[j])
                if (isPalindrom(result)) {
                    store.storeMaxSubString(result.joinToString(""))
                }
            }
        }
        return store.getMaxSubString()
    }

    fun isPalindrom(word: List<Char>): Boolean {
        if (word.isEmpty() || word.size == 1) return true

        var backPointer = word.lastIndex

        for (i in 0 until word.size / 2) {
            if ((word[i] == word[backPointer]).not()) {
                return false
            }
            backPointer--
        }

        return true
    }

    class Store(
        private var string: String = ""
    ) {
        fun storeMaxSubString(s: String): String {
            if (this.string.length < s.length) {
                this.string = s
            }
            return this.string
        }

        fun getMaxSubString(): String {
            return this.string
        }
    }
}

/**
 * LeetCode Solution
 */
internal class Solution05Co {
    fun longestPalindrome(s: String?): String {
        if (s.isNullOrEmpty()) return ""
        var start = 0
        var end = 0
        for (i in s.indices) {
            val len1 = expandAroundCenter(s, i, i)
            val len2 = expandAroundCenter(s, i, i + 1)
            val len = Math.max(len1, len2)
            if (len > end - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }
        return s.substring(start, end + 1)
    }

    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var L = left
        var R = right
        while (L >= 0 && R < s.length && s[L] == s[R]) {
            L--
            R++
        }
        return R - L - 1
    }
}