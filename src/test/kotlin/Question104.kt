import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class Question104 : FunSpec({
    test("case01") {
        val root = TreeNode(3)
        val _9 = TreeNode(9)
        val _20 = TreeNode(20)
        val _15 = TreeNode(15)
        val _7 = TreeNode(7)

        root.left = _9
        root.right = _20

        _20.left = _15
        _20.right = _7

        val result = maxDepth(root)

        result shouldBe 3
    }


})

fun maxDepth(root: TreeNode?): Int {
    val _root = root ?: return 0

    val queue = LinkedList<Pair<TreeNode, Int>>()

    var maxDepth = 1
    queue.add(_root to 1)

    while (queue.isNotEmpty()) {

        val (node, depth) = queue.pop()

        node.left?.let {
            maxDepth = Math.max(maxDepth, depth + 1)
            queue.add(it to depth + 1)
        }

        node.right?.let {
            maxDepth = Math.max(maxDepth, depth + 1)
            queue.add(it to depth + 1)
        }
    }

    return maxDepth
}