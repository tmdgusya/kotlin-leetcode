import com.sun.source.tree.Tree
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question543 : FunSpec({

    test("case1") {
        val solution = Solution543()

        val _1 = TreeNode(1)
        val _2 = TreeNode(2)
        val _3 = TreeNode(3)
        val _4 = TreeNode(4)
        val _5 = TreeNode(5)

        _1.left = _2
        _1.right = _3

        _2.left = _4
        _2.right = _5

        val result = solution.diameterOfBinaryTree(_1)

        result shouldBe 3
    }

})

class Solution543 {

    var diameter = 0

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        getLongestPath(root)
        return diameter
    }


    fun getLongestPath(root: TreeNode?): Int {
        if (root == null) return 0

        val leftMax = getLongestPath(root.left)
        val rightMax = getLongestPath(root.right)

        diameter = Math.max(diameter, leftMax + rightMax)

        return Math.max(leftMax,rightMax) + 1
    }
}