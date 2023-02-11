import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question542 : FunSpec({

    test("case 01") {
        // mat = [[0,0,0],[0,1,0],[0,0,0]]
        val mat = arrayOf(
            intArrayOf(0),
            intArrayOf(0),
            intArrayOf(0),
        )

        updateMatrix(mat) shouldBe arrayOf(
            intArrayOf(0),
            intArrayOf(0),
            intArrayOf(0),
        )
    }
})

fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
    val columnLength = mat[0].size
    val dp = Array<IntArray>(mat.size) {
        IntArray(columnLength) { Int.MAX_VALUE - 1 } // 와 이거 +1 되는거 때문에 OverFlow 터져서..
    }

    for (i in mat.indices) {
        for (j in mat[i].indices) {
            if (mat[i][j] == 0) dp[i][j] = 0
            else {
                // top direction
                if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1)
                // left
                if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1)
            }
        }
    }

    for (i in mat.lastIndex downTo 0) {
        for (j in mat[i].lastIndex downTo 0) {
            if (mat[i][j] == 0) dp[i][j] = 0
            else {
                // down direction
                if (i < mat.size - 1) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1)
                // right
                if (j < mat[0].size - 1) dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1)
            }
        }
    }

    return dp
}