import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question520: FunSpec({

    test("case 01") {
        val given = "USA"

        val result = detectCapitalUse(given)

        result shouldBe true
    }

    test("case 02") {
        val given = "FlaG"

        val result = detectCapitalUse(given)

        result shouldBe false
    }
})

/**
 * 만족하는 조건
 *
 * 1. 모든 문자가 소문자
 * 2. 모든 문자가 대문자
 * 3. 문자열의 첫번째만 대문자.
 * @param word
 * @return
 */
fun detectCapitalUse(word: String): Boolean = if (word[0].isLowerCase()) {
        word.checkAllLowerCase()
    } else {
        word.checkAllCapitalOrCamelCase()
    }

private fun String.checkAllLowerCase(): Boolean = this.toCharArray().asSequence().all { it.isLowerCase() }
private fun String.checkAllUpperCase(): Boolean = this.toCharArray().asSequence().all { it.isUpperCase() }

private fun String.checkAllCapitalOrCamelCase(): Boolean = if (this.lastIndex >= 1 && this[1].isLowerCase()) {
    this.substring(1, this.length).checkAllLowerCase()
} else this.substring(1, this.length).checkAllUpperCase()
