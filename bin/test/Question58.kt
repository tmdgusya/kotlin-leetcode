import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/**
 * ./gradlew test -i --tests "Question58"
 */
internal class Question58: DescribeSpec({

    val solution = Solution58()

    describe("Given a string s consisting of words and spaces, return the length of the last word in the string.") {
        it("s = Hello World, output = 2") {
            solution.lengthOfLastWord("Hello World") shouldBe 5
        }

        it("s =    fly me   to   the moon  , output = 4") {
            solution.lengthOfLastWord("   fly me   to   the moon  ") shouldBe 4
        }
    }
})

private class Solution58 {

    fun lengthOfLastWord(s: String): Int {
        // delete blant at last index
        val sub = deleteBlank(s)
        // split string base on space(blank)
        val split = sub.split(" ")
        // return lastSubStringLength
        return split[split.lastIndex].length
    }

    fun deleteBlank(s: String): String {
        val chars = s.toCharArray()
        var base: Int = chars.lastIndex
        for (i in chars.lastIndex downTo  0) {
            if (chars[i] == ' ') {
                --base
            }
            if (chars[i] != ' ') {
                break
            }
        }
        return s.substring(0, base + 1)
    }

}