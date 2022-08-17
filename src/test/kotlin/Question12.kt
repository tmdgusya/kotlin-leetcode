import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException
import java.util.StringJoiner

class Question12 : DescribeSpec({

    val solution = Solution12()

    describe("Given a string s convert to roman string.") {

        it("Input = 3, output is III.") {
            solution.intToRoman(3) shouldBe "III"
        }

        it("Input = 58, output is LVIII.") {
            solution.intToRoman(58) shouldBe "LVIII"
        }

        it("Input = 1994, output is MCMXCIV.") {
            solution.intToRoman(1994) shouldBe "MCMXCIV"
        }
    }

    describe("[TDD-1] Save roman words to map data structure") {
        val romanWordMap = solution.createRomanWordMap()


        romanWordMap[1] shouldBe "I"
        romanWordMap[5] shouldBe "V"
        romanWordMap[10] shouldBe "X"
        romanWordMap[50] shouldBe "L"
        romanWordMap[100] shouldBe "C"
        romanWordMap[500] shouldBe "D"
        romanWordMap[1000] shouldBe "M"
    }

    describe("[TDD-2] divide given string using greedy algorithm") {
        it("if given input 105 then returns 5 because it was divided 100") {
            val calculator = Solution12.Calculator(solution.createRomanWordMap())
            val input = 105

            calculator.divide(input) shouldBe (5 to "C")
        }
    }

    describe("[TDD-3] Append string of divide result until given number was became zero") {
        it("if given input 105 then returns 5 because it was divided 100") {
            val calculator = Solution12.Calculator(solution.createRomanWordMap())
            val input = 105

            calculator.convertToRoman(input) shouldBe "CV"
        }
    }

})

class Solution12 {
    fun intToRoman(num: Int): String {
        val calculator = Calculator(createRomanWordMap())
        return calculator.convertToRoman(num)
    }

    fun createRomanWordMap(): Map<Int, String> {
        return mapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I",
        )
    }

    class Calculator(
        private val romanMap: Map<Int, String>
    ) {
        fun divide(input: Int): Pair<Int, String> {
            for (romanWord in romanMap) {
                if (input >= romanWord.key) {
                    println(romanWord)
                    return (input - romanWord.key) to romanWord.value
                }
            }
            throw IllegalArgumentException()
        }

        fun convertToRoman(input: Int): String {
            var copyInput = input
            return buildString {
                while (copyInput > 0) {
                    val (value, roman) = divide(copyInput)
                    append(roman)
                    copyInput = value
                }
            }
        }
    }
}