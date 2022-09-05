import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question06 : FunSpec({

    test("case 01") {
        val s = "PAYPALISHIRING"
        val numRows = 3
        val expected = "PAHNAPLSIIGYIR"

        val result = convert(s, numRows)

        result shouldBe expected
    }
})

private fun convert(s: String, numRows: Int): String {

    var currentRowIndex = 0
    var isReversed = false
    val intermediateCalculateSaver = createMutableListBy(numRows)

    for (c in s) {
        intermediateCalculateSaver[currentRowIndex].add(c)
        val (row, _isReversed) = getNextInputRowIndex(
            currentRowIndex to isReversed,
            numRows - 1
        )
        currentRowIndex = row
        isReversed = _isReversed
    }

    return convertToString(intermediateCalculateSaver)
}

private fun createMutableListBy(givenNum: Int): List<MutableList<Char>> {
    val result = mutableListOf<MutableList<Char>>()

    for (i in 0 until givenNum) {
        result.add(mutableListOf())
    }

    return result
}

// The index where can place node
private fun getNextInputRowIndex(currentRowInfo: Pair<Int, Boolean>, givenNum: Int): Pair<Int, Boolean> {
    val (curRow, isReversed) = currentRowInfo
    if (givenNum == 0) return currentRowInfo

    if (curRow == givenNum) {
        return (curRow - 1) to true
    }

    if (curRow == 0) {
        return (curRow + 1) to false
    }

    if (isReversed) {
        return (curRow - 1) to true
    }

    return (curRow + 1) to false
}

private fun convertToString(list: List<MutableList<Char>>): String {
    val folded = list.flatten()
    return folded.joinToString("")
}
