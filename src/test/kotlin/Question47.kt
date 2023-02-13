import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question47: FunSpec({
    // Input: nums = [1,1,2] Output:[[1,1,2],[1,2,1],[2,1,1]]
    test("given nums = [1,1,2], then result is [[1,1,2],[1,2,1],[2,1,1]]") {
        // given
        val nums = intArrayOf(1, 1, 2)

        // when
        val actual = permuteUnique(nums)

        // then
        actual shouldBe listOf(
            listOf(1, 1, 2),
            listOf(1, 2, 1),
            listOf(2, 1, 1)
        )
    }
})

fun permuteUnique(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    permu(0, 0, nums, result)
    return result.toList()
}

fun permu(i: Int, pointer: Int, nums: IntArray, result: MutableSet<List<Int>>) {
    for (j in i until nums.size) {
        // swap
        val temp = nums[pointer]
        nums[pointer] = nums[j]
        nums[j] = temp
        // add result
        result.add(nums.toList())
        permu(i + 1, pointer + 1, nums.copyOf(), result)
    }
}