import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlin.math.max
import kotlin.math.min

class Question11 : DescribeSpec({

    val solution = Question11Solution()
    val calculator = Question11Solution.CalculateContainerAreaService()
    val store = Question11Solution.StoreMaxContainerVolumeService()

    describe("Given an integer array height of length n, find two lines that together with the x-axis form a container") {
        it("if given nums = [1,8,6,2,5,4,8,3,7] answers sum = 49 ") {
            solution.maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)) shouldBe 49
        }

        it("if given nums = [1,1] answers sum = 1") {
            solution.maxArea(intArrayOf(1,1)) shouldBe 1
        }
    }

    describe("[TDD-1] calculate volume to water container") {
        it("if given line1 = 1 to 8, line2 = 8 to 7, then returns 49") {
            calculator.doService(1 to 8, 8 to 7) shouldBe 49
        }

        it("if given line1 = 2 to 6, line2 = 7 to 3, then returns 49") {
            calculator.doService(2 to 6, 7 to 3) shouldBe 15
        }
    }

    describe("[TDD-2] store max container volume and can get max volume") {
        it("if save first container volume 10, second save volume 20 then get maxvalue method returns 20") {
            store.save(10)
            store.save(20)

            store.getMaxValue() shouldBe 20
        }

        it("if save first container volume 30, second save volume 20 then get maxvalue method returns 30") {
            store.save(30)
            store.save(20)

            store.getMaxValue() shouldBe 30
        }
    }
})

internal class Question11Solution {

    fun maxArea(height: IntArray): Int {
        val calculator = CalculateContainerAreaService()
        val store = StoreMaxContainerVolumeService()
        val leftPointer = 0
        val rightPointer = height.lastIndex
        return recursive(leftPointer, rightPointer, store, calculator, height)
    }

    /**
     * Recursive 훈련
     */
    fun recursive(
        leftPointer: Int,
        rightPointer: Int,
        store: StoreMaxContainerVolumeService,
        calculator: CalculateContainerAreaService,
        height: IntArray,
    ): Int {
        // end condition
        if (leftPointer >= rightPointer) return store.getMaxValue()

        val result = calculator.doService(
            line1 = leftPointer to height[leftPointer],
            line2 = rightPointer to height[rightPointer],
        )
        store.save(result)

        return if (height[leftPointer] < height[rightPointer]) {
            recursive(leftPointer + 1, rightPointer, store, calculator, height)
        } else {
            recursive(leftPointer, rightPointer - 1, store, calculator, height)
        }
    }

    class CalculateContainerAreaService {
        fun doService(line1: Pair<Int, Int>, line2: Pair<Int, Int>): Int {
            line2.first shouldBeGreaterThan line1.first

            val xLength = line2.first - line1.first
            val yLength = min(line1.second, line2.second)
            return xLength * yLength
        }
    }

    class StoreMaxContainerVolumeService(
        private var volume: Int = 0
    ) {

        fun save(volume: Int) {
            this.volume = max(volume, this.volume)
        }

        fun getMaxValue(): Int {
            return volume
        }
    }
}
