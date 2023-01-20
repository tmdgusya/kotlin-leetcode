package explore.binarytree

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BinaryTreePreorderTraversal: FunSpec({
    test("given [1, null, 2, 3], then [1,2,3]") {
        val root = TreeNode(1)
        val _2 = TreeNode(2)
        val _3 = TreeNode(3)

        root.right = _2
        _2.left = _3

        val result = preorderTraversal(root)

        result shouldBe listOf(1,2,3)
    }
})

private class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

private fun preorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    root?.preorderTraversal(result)
    return result.toList()
}

private fun TreeNode.preorderTraversal(travelMap: MutableList<Int>) {
    travelMap.add(this.`val`)
    this.left?.preorderTraversal(travelMap)
    this.right?.preorderTraversal(travelMap)
}