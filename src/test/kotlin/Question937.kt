import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
class Question937 : FunSpec({

    test("case01") {
        val logs = arrayOf("dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero")
        val expected = arrayOf("let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6")

        val result = reorderLogFiles(logs)

        result shouldBe expected
    }

    test("case02") {
        val logs = arrayOf("1 n u", "r 527", "j 893", "6 14", "6 82")
        val expected = arrayOf("1 n u","r 527","j 893","6 14","6 82")

        val result = reorderLogFiles(logs)

        result shouldBe expected
    }

    test("is Digit Method Test") {
        "1".isDigit() shouldBe true
    }

    test("zzzz") {
        "testetIIIdig2".indexOf("III") shouldBe 6
    }

})

fun reorderLogFiles(logs: Array<String>): Array<String> {
    val comparator = Comparator<String> { o1, o2 ->
        val dividedIndex1= o1.indexOf(" ")
        val dividedIndex2= o2.indexOf(" ")

        val o1Identifier = o1.substring(0, dividedIndex1 + 1)
        val o1Content = o1.substring(dividedIndex1 + 1, o1.length)
        val o2Identifier = o2.substring(0, dividedIndex2 + 1)
        val o2Content = o2.substring(dividedIndex2 + 1, o2.length)

        if (o1Content.isNotDigit() && o2Content.isNotDigit()) {
            val firstComp = o1Content.compareTo(o2Content)
            if (firstComp != 0) return@Comparator firstComp
            else return@Comparator o1Identifier.compareTo(o2Identifier)
        }

        if (o1Content.isNotDigit() && o2Content.isDigit()) {
            return@Comparator -1
        }

        if (o2Content.isNotDigit() && o1Content.isDigit()) {
            return@Comparator 1
        }

        return@Comparator 0
    }
    logs.sortWith(comparator)
    return logs
}

private fun String.isNotDigit(): Boolean {
    return !this.isDigit()
}

private fun String.isDigit(): Boolean {
    if (this.isEmpty()) return true

    return this[0].isDigit()
}