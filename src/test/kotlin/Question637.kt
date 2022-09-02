import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class Question637 : FunSpec({

    test("case1") {

        val _1 = TreeNode(3)
        val _9 = TreeNode(9)
        val _20 = TreeNode(20)
        val _15 = TreeNode(15)
        val _7 = TreeNode(7)

        _1.left = _9
        _1.right = _20

        _20.left = _15
        _20.right = _7

        val result = averageOfLevels(_1)

        result shouldBe doubleArrayOf(3.00000,14.50000,11.00000)
    }

})

fun averageOfLevels(root: TreeNode?): DoubleArray {
    // left + right 합이 depth 의 값임.
    val _root = root ?: return DoubleArray(0)

    val result = mutableListOf<Pair<Double, Int>>()

    val queue = LinkedList<Pair<TreeNode, Int>>()

    result.add(_root.`val`.toDouble() to 1)
    queue.add(_root to 1)

    while (queue.isNotEmpty()) {

        val (node, depth) = queue.pop()

        node.left?.let {
            if (result.lastIndex >= depth) {
                result[depth] = result[depth].first + it.`val`.toDouble() to result[depth].second + 1
            } else { result.add(it.`val`.toDouble() to 1) }
            queue.add(it to depth + 1)
        }

        node.right?.let {
            if (result.lastIndex >= depth) {
                result[depth] = result[depth].first + it.`val`.toDouble() to result[depth].second + 1
            } else { result.add(it.`val`.toDouble() to 1) }
            queue.add(it to depth + 1)
        }
    }

    return result.map { it.first / it.second.toDouble() }.toDoubleArray()
}