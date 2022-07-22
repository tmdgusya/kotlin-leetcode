import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class Question75 : BehaviorSpec({

    given("Given the arrays nums = [2,0,2,1,1,0]") {
        val nums = intArrayOf(2, 0, 2, 1, 1, 0)
        `when`("sort") {
            nums.bubbleSort()
            then("result is [0,0,1,1,2,2]")
            nums shouldBe intArrayOf(0, 0, 1, 1, 2, 2)
        }
    }

    given("Given the arrays nums = [2,0,1]") {
        val nums = intArrayOf(2, 0, 1)
        `when`("sort") {
            nums.bubbleSort()
            then("result is [0,1,2]")
            nums shouldBe intArrayOf(0, 1, 2)
        }
    }
})

fun IntArray.bubbleSort(): IntArray {
    for (i in 0 until this.lastIndex) {
        for (j in 0 until this.lastIndex - i) {
            if (this[j] > this[j + 1]) {
                val tmp = this[j]
                this[j] = this[j + 1]
                this[j + 1] = tmp
            }
        }
    }

    return this
}
