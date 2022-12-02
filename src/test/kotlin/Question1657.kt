import Collection
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question1657: FunSpec({
    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        val word1Collection = word1.collect()
        val word2Collection = word2.collect()

        return word1Collection.isConvertable(word2Collection)
    }

    test("case 01") {
        // given
        val word1 = "abc"
        val word2 = "bca"

        // when
        val isClose = closeStrings(word1, word2)

        // then
        isClose shouldBe true
    }

    test("case 02") {
        // given
        val word1 = "a"
        val word2 = "aa"

        // when
        val isClose = closeStrings(word1, word2)

        // then
        isClose shouldBe false
    }

    test("case 03") {
        val word1 = "cabbba"
        val word2 = "abbccc"

        // when
        val isClose = closeStrings(word1, word2)

        // then
        isClose shouldBe true
    }

    test("case 04") {
        val word1 = "uau"
        val word2 = "ssx"

        // when
        val isClose = closeStrings(word1, word2)

        // then
        isClose shouldBe false
    }
})

typealias Word = String
typealias Collection = Map<Char, Int>

fun Word.collect(): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    for (char in this) {
        map[char] = map.getOrDefault(char, 0) + 1
    }
    return map
}

fun Collection.isConvertable(other: Collection): Boolean {
    val sortedThisCollectionByCount = this.values.sortedDescending()
    val sortedOtherCollectionByCount = other.values.sortedDescending()

    val sortedThisCollectionByChar = this.keys.sortedDescending()
    val sortedOtherCollectionByChar = other.keys.sortedDescending()

    return sortedThisCollectionByCount == sortedOtherCollectionByCount && sortedThisCollectionByChar == sortedOtherCollectionByChar
}