import com.sun.source.tree.Tree
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class Question102 : FunSpec({

    test("case 01") {
        val _3 = TreeNode(3)
        val _9 = TreeNode(9)
        val _20 = TreeNode(20)
        val _15 = TreeNode(15)
        val _7 = TreeNode(7)

        _3.left = _9
        _3.right = _20

        _20.left = _15
        _20.right = _7

        val result = levelOrder(_3)
        val expected = listOf(
            listOf(3),
            listOf(9, 20),
            listOf(15, 7)
        )
        result shouldBe expected
    }


})

fun levelOrder(root: TreeNode?): List<List<Int>> {
    val _root = root ?: return emptyList()
    return _root.levelOrderTraversal()
}

private fun TreeNode.levelOrderTraversal(): List<List<Int>> {

    val startDepth = 0
    // node and depth
    val queue = LinkedList<Pair<TreeNode, Int>>()
    val result = mutableListOf<MutableList<Int>>()

    queue.add(this to startDepth)

    while (queue.isNotEmpty()) {

        val (node, depth) = queue.pop()

        // first node of level
        if (result.size - 1 < depth) {
            result.add(mutableListOf(node.`val`))
        } else {
            // exist other node in level
            result[depth].add(node.`val`)
        }

        node.left?.let {
            //adding depth
            queue.add(it to depth + 1)
        }

        node.right?.let {
            queue.add(it to depth + 1)
        }

    }

    return result.toList()
}