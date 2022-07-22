import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Question03 : DescribeSpec({

    val solution = Solution03()

    describe("Given a string s, find the length of the longest substring without repeating characters.") {

        it("Input = abcabcbb, output is 3.") {
            solution.lengthOfLongestSubstring("abcabcbb") shouldBe 3
        }

        it("Input = bbbbb, output is 1.") {
            solution.lengthOfLongestSubstring("bbbbb") shouldBe 1
        }

        it("Input = pwwkew, output is 3.") {
            solution.lengthOfLongestSubstring("pwwkew") shouldBe 3
        }

        it("Input = blank, output is 1.") {
            solution.lengthOfLongestSubstring(" ") shouldBe 1
        }

        it("Input = au, output is 2") {
            solution.lengthOfLongestSubstring("au") shouldBe 2
        }
    }

})

private class Solution03 {

    fun lengthOfLongestSubstring(s: String): Int {
        var max = 0
        if (s.length == 1) return 1
        for (i in 0 until s.lastIndex) {
            max = max.coerceAtLeast(
                checkSamewordOnSide(
                    index = i,
                    s = s,
                    count = 1,
                    joined = s[i].toString()
                )
            )
        }
        return max
    }

    fun checkSamewordOnSide(index: Int, s: String, count: Int, joined: String): Int {
        return if (index < s.lastIndex && !joined.contains(s[index + 1])) {
            checkSamewordOnSide(
                index = index + 1,
                s = s,
                count = count + 1,
                joined = joined.plus(s[index + 1].toString())
            )
        } else {
            return count
        }
    }

}