import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question94 : FunSpec({
    val solution = Solution94()
    test("Given the root of a binary tree, return the inorder traversal of its nodes' values.") {
        val root = TreeNode(1)
        val right = TreeNode(2)
        val right_left = TreeNode(3)

        root.right = right
        right.left = right_left

        val print = solution.inorderTraversal(root)

        print[0] shouldBe 1
        print[1] shouldBe 3
        print[2] shouldBe 2
    }
})

class Solution94 {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        if (root == null) return emptyList()
        return root.inorderTraversal(list)
    }
}

fun TreeNode.inorderTraversal(list: MutableList<Int>): List<Int> {
    this.left?.inorderTraversal(list)
    list.add(this.`val`)
    this.right?.inorderTraversal(list)
    return list.toList()
}