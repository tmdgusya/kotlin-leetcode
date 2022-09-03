import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList
import kotlin.math.pow

class Question1148 : FunSpec({
    test("Case2") {
        val _1 = TreeNode(3)
        val _2 = TreeNode(3)
        val _3 = TreeNode(4)
        val _4 = TreeNode(2)

        _1.left = _2
        _2.left = _3
        _2.right = _4

        val result = goodNodes(_1)

        result shouldBe 3
    }
})



fun goodNodes(root: TreeNode?): Int {
    val _root = root ?: return -1

    val queue = LinkedList<Pair<TreeNode, Int>>()

    queue.add(_root to _root.`val`)

    var goodNodes = 1

    while (queue.isNotEmpty()) {

        val (node, maxValue) = queue.pop()

        node.left?.let {
            val _maxValue = if (maxValue <= it.`val`) {
                ++goodNodes
                it.`val`
            } else maxValue

            queue.add(it to _maxValue)
        }

        node.right?.let {
            val _maxValue = if (maxValue <= it.`val`) {
                ++goodNodes
                it.`val`
            } else maxValue

            queue.add(it to _maxValue)
        }

    }

    return goodNodes
}