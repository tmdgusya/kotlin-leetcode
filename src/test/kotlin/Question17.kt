import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.StringBuilder

class Question17 : FunSpec({

    test("Case 01") {
        val case = "23"

        val result = letterCombinations(case)
        val expected = listOf("ad","ae","af","bd","be","bf","cd","ce","cf")

        result shouldBe expected
    }

})

fun letterCombinations(digits: String): List<String> {
    if (digits.isBlank()) return emptyList()
    val index = createLetterOfPhoneNumber()
    val comb = mutableListOf<String>()

    backTracking(0, StringBuilder(), digits, comb, index)
    return comb
}

fun backTracking(index: Int, builder: StringBuilder, digits: String, comb: MutableList<String>, map: Map<Char, List<Char>>) {
    if (builder.length == digits.length) {
        comb.add(builder.toString())
        return
    }

    val lockLetter = map[digits[index]]
    for (letter in lockLetter!!) {
        builder.append(letter)
        backTracking(index + 1, builder, digits, comb, map)
        builder.deleteCharAt(builder.lastIndex) // remove last string for backtracking
    }
}


private fun createLetterOfPhoneNumber(): Map<Char, List<Char>> {

    val map = HashMap<Char, List<Char>>()

    map['2'] = listOf('a', 'b', 'c')
    map['3'] = listOf('d', 'e', 'f')
    map['4'] = listOf('g', 'h', 'i')
    map['5'] = listOf('j', 'k', 'l')
    map['6'] = listOf('m', 'n', 'o')
    map['7'] = listOf('p', 'q', 'r', 's')
    map['8'] = listOf('t', 'u', 'v')
    map['9'] = listOf('w', 'x', 'y', 'z')

    return map
}