import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class Question33 : BehaviorSpec({
    given("There is an integer array nums sorted in ascending order") {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 0
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 4") {
                expected shouldBe 4
            }
        }
    }

    given("nums = [4,5,6,7,0,1,2], target = 3") {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 3
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is -1") {
                expected shouldBe -1
            }
        }
    }

    given("nums = [1,3], target = 0") {
        val nums = intArrayOf(1, 3)
        val target = 0
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is -1") {
                expected shouldBe -1
            }
        }
    }

    given("nums = [1,3,5], target = 1") {
        val nums = intArrayOf(1, 3, 5)
        val target = 1
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 0") {
                expected shouldBe 0
            }
        }
    }
    given("nums = [1,3,5], target = 2") {
        val nums = intArrayOf(1, 3, 5)
        val target = 2
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is -1") {
                expected shouldBe -1
            }
        }
    }

    given("nums = [4,5,6,7,0,1,2], target = 2") {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 2
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 6") {
                expected shouldBe 6
            }
        }
    }

    given("nums = [4,5,6,7,0,1,2], target = 0") {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 0
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 4") {
                expected shouldBe 4
            }
        }
    }

    given("nums = [4,5,6,7,0,1,2], target = 4") {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 4
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 0") {
                expected shouldBe 0
            }
        }
    }

    given("nums = [3,4,5,6,1,2], target = 2") {
        val nums = intArrayOf(3, 4, 5, 6, 1, 2)
        val target = 2
        val solution = Solution33()
        `when`("find a target") {
            val expected = solution.search(nums = nums, target = target)
            then("index of target is 5") {
                expected shouldBe -1
            }
        }
    }
})

private class Solution33 {
    fun search(nums: IntArray, target: Int): Int {
        val pivot = nums.lastIndex / 2
        if (nums.size <= 3) return nums.indexOf(target)
        if (nums[pivot] == target) return pivot

        var result = -1

        if (nums[pivot + 1] > nums[pivot] && nums[pivot] > nums[pivot - 1]) { // 회전이 안된경우
            return binarySearch(nums, left = 0, right = nums.lastIndex, target = target)
        }
        // pivot 왼쪽이 pivot 보다 큰지 확인한다.
        if (nums[pivot - 1] < nums[pivot] && nums[pivot - 1] > nums[pivot + 1]) {
            val index = binarySearch(nums, left = 0, right = pivot - 1, target = target)
            result = index.coerceAtLeast(result)
        }
        if (nums[pivot + 1] < nums[pivot] && nums[pivot - 1] > nums[pivot + 1]) {
            val index = binarySearch(nums, left = pivot + 1, right = nums.lastIndex, target = target)
            result = index.coerceAtLeast(result)
        }

        return result
    }

    private fun binarySearch(nums: IntArray, left: Int, right: Int, target: Int): Int {
        if (nums.size == 1) return nums.indexOf(target)
        if (left >= right) return -1

        val pivot = (left + right) / 2

        if (pivot == left) {
            if (nums[left] == target) {
                return left
            }
            if (nums[right] == target) {
                return right
            }
            return -1
        }

        if (nums[pivot] == target) return pivot

        return if (target > nums[pivot]) {
            binarySearch(
                nums = nums,
                left = pivot,
                right = nums.lastIndex,
                target = target
            )
        } else {
            binarySearch(
                nums = nums,
                left = left,
                right = pivot,
                target = target
            )
        }
    }
}
