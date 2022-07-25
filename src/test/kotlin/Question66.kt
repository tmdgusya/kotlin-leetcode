import io.kotest.core.spec.style.StringSpec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import java.util.Arrays

class Question66: WordSpec({

    val solution = Solution66()

    "Increment the large integer by one and return the resulting array of digits." When  {

        "head =  [1,2,3]" should   {
            val given = intArrayOf(1,2,3)
            "Output = [1,2,4]" {
                solution.plusOne(digits = given) shouldBe intArrayOf(1,2,4)
            }
        }

        "head =  [9]" should   {
            val given = intArrayOf(9)
            "Output = [1,0]" {
                solution.plusOne(digits = given) shouldBe intArrayOf(1,0)
            }
        }

    }
})


class Solution66 {
    fun plusOne(digits: IntArray): IntArray {
        return addOne(digits, digits.lastIndex)
    }

    private fun addOne(digits: IntArray, targetIndex: Int): IntArray {
        // extend array
        if (targetIndex == -1) {
            val extendArray = IntArray(size = digits.size + 1, init = {int -> int})

            for (i in 0 .. extendArray.lastIndex) {
                if (i == 0) {
                    extendArray[i] = 1
                    continue
                }
                extendArray[i] = digits[i-1]
            }

            return extendArray
        }

        val sumResult = digits[targetIndex] + 1

        return if (sumResult == 10) {
            digits[targetIndex] = 0

            // if exceed 10 when add one, then plus one to previous index
            addOne(digits, targetIndex -1 )
        } else {
            digits[targetIndex] = digits[targetIndex] + 1
            digits
        }
    }
}