import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class Question22 : BehaviorSpec({

    val validator = Validator()
    val parenthesesGenerator = ParenthesesGenerator()

    given("given Input parentheses = '((()))'") {
        val parentheses = "((()))"
        `when`("execute isWellFormedParentheses Method") {
            val result = validator.isWellFormedParentheses(parentheses = parentheses)
            then("then return true") {
                result shouldBe true
            }
        }
    }

    given("given Input parentheses = '())'") {
        val parentheses = "())"
        `when`("execute isWellFormedParentheses Method") {
            val result = validator.isWellFormedParentheses(parentheses = parentheses)
            then("then return false") {
                result shouldBe false
            }
        }
    }

    given("given Input n = 1") {
        val n = 1
        `when`("execute generateParentheses Method") {
            val result = parenthesesGenerator.generate(pair = n)
            then("then return ['()']") {
                result shouldBe listOf("()")
            }
        }
    }

    given("given Input n = 2") {
        val n = 2
        `when`("execute generateParentheses Method") {
            val result = parenthesesGenerator.generate(pair = n)
                then("then return ['(())', '()()']") {
                result shouldBe listOf("(())", "()()")
            }
        }
    }

    given("given Input n = 3") {
        val n = 3
        `when`("execute generateParentheses Method") {
            val result = parenthesesGenerator.generate(pair = n)
            then("then return ['((()))','(()())','(())()','()(())','()()()']") {
                result shouldBe listOf("((()))","(()())","(())()","()(())","()()()")
            }
        }
    }

    given("[Final] given Input n = 3") {
        val solution = Solution22()
        val n = 3
        `when`("execute generateParenthesis Method") {
            val result = solution.generateParenthesis(n)
            then("then return ['((()))','(()())','(())()','()(())','()()()']") {
                result shouldBe listOf("((()))","(()())","(())()","()(())","()()()")
            }
        }
    }
})

class Solution22 {
    val parenthesesGenerator = ParenthesesGenerator()

    fun generateParenthesis(n: Int): List<String> {
         return parenthesesGenerator.generate(pair = n)
    }
}

class ParenthesesGenerator {

    private val validator = Validator()

    fun generate(pair: Int): MutableList<String> {
        val parentheses = mutableListOf<String>()
        val startWord = "("

        appendParentheses(pair = (pair * 2 - 1), depth = 0, word = startWord, parentheses = parentheses)

        return parentheses
    }

    private fun appendParentheses(pair:Int, depth: Int, word: String = "(", parentheses: MutableList<String>) {
        if (depth == pair) {
            if (validator.isWellFormedParentheses(word)) parentheses.add(word)
            return
        } else {
            appendParentheses(pair, depth + 1, "$word(", parentheses)
            appendParentheses(pair, depth + 1, "$word)", parentheses)
        }
    }
}

class Validator {
    fun isWellFormedParentheses(parentheses: String): Boolean {
        var count: Int = 0
        for (c in parentheses) {
            if (count < 0) return false
            if (c == '(') count++
            if (c == ')') count--
        }
        return count == 0
    }
}