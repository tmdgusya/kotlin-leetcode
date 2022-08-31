import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.Stack

class Question114 : FunSpec({

    test("case1") {
        val root = TreeNode(1)

        val _2 = TreeNode(2)
        val _3 = TreeNode(3)
        val _4 = TreeNode(4)

        val _5 = TreeNode(5)
        val _6 = TreeNode(6)

        root.left = _2
        root.right = _5

        _2.left = _3
        _2.right = _4

        _5.right = _6

        flatten(root)

        root.left shouldBe null
        root.right shouldBe _2

        _2.left shouldBe null
        _2.right shouldBe _3
    }

})

fun flatten(root: TreeNode?): Unit {
    val _root = root ?: return

    val stack = Stack<TreeNode>()
    val result = mutableListOf<TreeNode>()

    stack.add(_root)

    while (stack.isNotEmpty()) {

        val node = stack.pop()
        result.add(node)

        node.right?.let { stack.add(it) }
        node.left?.let { stack.add(it) }
    }

    for (i in 0 until result.size - 1) {
        result[i].left = null
        result[i].right = result[i + 1]
    }
}