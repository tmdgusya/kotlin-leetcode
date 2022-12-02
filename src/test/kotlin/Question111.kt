import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class Question111 : FunSpec({

    test("case 01") {
        val root = TreeNode(3)

        val _l1 = TreeNode(9)
        val _r1 = TreeNode(20)

        val l2 = TreeNode(15)
        val r2 = TreeNode(7)

        root.left = _l1
        root.right = _r1

        _r1.left = l2
        _r1.right = r2

        val result = minDepth(root)

        result shouldBe 2
    }

    test("case 02") {
        val root = TreeNode(2)

        val _r1 = TreeNode(3)
        val _r2 = TreeNode(4)
        val _r3 = TreeNode(5)
        val _r4 = TreeNode(6)

        root.right = _r1
        _r1.right = _r2
        _r2.right = _r3
        _r3.right = _r4

        val result = minDepth(root)

        result shouldBe 5
    }
})

fun minDepth(root: TreeNode?): Int {
    return root?.findMinDepth(1) ?: 0
}

private fun TreeNode.findMinDepth(depth: Int): Int {
    var leftDepth = Int.MAX_VALUE
    var rightDepth = Int.MAX_VALUE

    if (this.isFinished()) {
        return depth
    }

    if (this.left != null) {
        leftDepth = Math.min(this.left!!.findMinDepth(depth + 1), leftDepth)
    }

    if (this.right != null) {
        rightDepth = Math.min(this.right!!.findMinDepth(depth + 1), rightDepth)
    }

    return leftDepth.coerceAtMost(rightDepth)
}

private fun bfs(root: TreeNode): Int {
    val queue = LinkedList<Pair<TreeNode, Int>>();

    queue.add(root to 1)

    var currentDepth = 0
    while (queue.isNotEmpty()) {
        val nodeData = queue.poll()
        val currentNode = nodeData.first
        currentDepth = nodeData.second

        if (currentNode.left != null) {
            queue.add(currentNode.left!! to currentDepth + 1)
        }

        if (currentNode.right != null) {
            queue.add(currentNode.right!! to currentDepth + 1)
        }
    }

    return currentDepth
}

private fun TreeNode.isFinished(): Boolean {
    return this.left == null && this.right == null
}