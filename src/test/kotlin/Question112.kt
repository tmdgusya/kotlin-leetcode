import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question112: FunSpec({
    test("case 1") {
        val root = TreeNode(1)
        val left = TreeNode(2)
        val right = TreeNode(3)

        root.left = left
        root.right = right

        val result = hasPathSum(root, 5)

        result shouldBe false
    }
})

fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    val _root = root ?: return false
    if (_root.isSingle()) return _root.`val` == targetSum
    return _root.addAll(_root.`val`, targetSum)
}

fun TreeNode.isSingle(): Boolean {
    return this.left == null && this.right == null
}

fun TreeNode.addAll(memo: Int, targetSum: Int) : Boolean {
    if (this.left == null && this.right == null) {
        return memo == targetSum
    }

    val leftResult = this.left?.addAll(memo + this.left!!.`val`, targetSum) == true
    val rightResult = this.right?.addAll(memo + this.right!!.`val`, targetSum) == true
    return leftResult || rightResult
}
