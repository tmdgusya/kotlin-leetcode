import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question108 : FunSpec({

    test("case 01") {
        val nums = intArrayOf(-10,-3,0,5,9)

        val result = sortedArrayToBST(nums)

        assert(result != null)
        result!!.`val` shouldBe 0

        result.left?.`val` = -10
        result.right?.`val` = 5

        result.left?.right?.`val` shouldBe -3
        result.right?.right?.`val` shouldBe 9
    }

})

fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if (nums.isEmpty()) return null
    return createHierachyByBinary(nums, 0, nums.lastIndex)
}

fun createHierachyByBinary(nums: IntArray, left: Int, right: Int): TreeNode? {

    if (left > right) {
        return null
    }

    val middle = (left + right) / 2
    val node = TreeNode(nums[middle])

    node.left = createHierachyByBinary(nums, left, middle -1)
    node.right = createHierachyByBinary(nums, middle + 1, right)

    return node
}