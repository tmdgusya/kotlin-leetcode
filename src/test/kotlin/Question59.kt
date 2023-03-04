import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question59 : FunSpec({
   test("01") {
       val expected = arrayOf(
           intArrayOf(1,2,3),
           intArrayOf(8,9,4),
           intArrayOf(7,6,5)
       )
       generateMatrix(2) shouldBe expected
   }
})

fun generateMatrix(n: Int): Array<IntArray> {
    var count = 1
    val matrix = Array<IntArray>(n) { IntArray(n) }
    var limitCount = n

    var leftCount = 0
    var rightCount = 0
    var downCount = 0
    var upCount = 0
    var curX = 0
    var curY = -1
    while (count != (n * n) + 1) {
        // to the right until n
        while (rightCount != limitCount) {
            if (count + 1 > (n * n) + 1) break
            matrix[curX][++curY] = count++
            rightCount++
        }
        limitCount -= 1
        rightCount = 0


       // down to
        while (downCount != limitCount) {
            if (count + 1 > (n * n) + 1) break
           matrix[++curX][curY] = count
           downCount++
           count++
       }
       downCount = 0


       while (leftCount != limitCount) {
           if (count + 1 > (n * n) + 1) break
           matrix[curX][--curY] = count
           leftCount++
           count++
       }
        leftCount = 0
        limitCount -= 1

        while (upCount != limitCount) {
            if (count + 1 > (n * n) + 1) break
            matrix[--curX][curY] = count
            upCount++
            count++
        }
        upCount = 0
    }
    return matrix
}