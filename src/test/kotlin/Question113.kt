import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList
import java.util.Stack

class Question113 : FunSpec({

    val root = TreeNode(5)

    val _1l = TreeNode(4)
    val _1r = TreeNode(8)

    root.left = _1l
    root.right = _1r

    val _2l = TreeNode(11)

    _1l.left = _2l

    val _3l = TreeNode(7)
    val _3r = TreeNode(2)

    _2l.left = _3l
    _2l.right = _3r

    val _2rl = TreeNode(13)
    val _2rr = TreeNode(4)

    _1r.left = _2rl
    _1r.right = _2rr

    val _3rl = TreeNode(5)
    val _3rr = TreeNode(1)

    _2rr.left = _3rl
    _2rr.right = _3rr


    test("case 1") {
        val result = pathSumOptimize(root, 22)

        result shouldBe listOf(
            listOf(5,4,11,2),
            listOf(5,8,4,5)
        )
    }

})

fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val _root = root ?: return result
    if (_root.isSingle() && _root.`val` == targetSum) return listOf<List<Int>>(listOf<Int>(_root.`val`))
    val list = mutableListOf<Int>()

    list.add(_root.`val`)
    // leftAddAll()
    _root.left?.addAll(_root.`val`, targetSum, list, result)
    // clear
    list.clear()

    list.add(_root.`val`)
    // rightAddAll()
    _root.right?.addAll(_root.`val`, targetSum, list, result)

    return result.toList()
}

fun pathSumOptimize(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val _root = root ?: return result

    val _tmp = Triple(_root, _root.`val`, mutableListOf(_root.`val`))
    val queue = LinkedList<Triple<TreeNode, Int, MutableList<Int>>>()

    queue.add(_tmp)

    while (queue.isNotEmpty()) {
        val (node, value, _result) = queue.pop()

        if (node.isLeaf() && value == targetSum) {
            result.add(_result)
        }

        node.left?.let {
            val tmp = _result + mutableListOf(it.`val`)
            queue.add(Triple(it, value + it.`val`, tmp.toMutableList()))
        }
    }
    return result
}

fun pathSumOptimize2(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val _root = root ?: return result

    val _tmp = Triple(_root, _root.`val`, mutableListOf(_root.`val`))
    val queue = Stack<Triple<TreeNode, Int, MutableList<Int>>>()

    queue.add(_tmp)

    while (queue.isNotEmpty()) {
        val (node, value, _result) = queue.pop()

        if (node.isLeaf() && value == targetSum) {
            result.add(_result)
        }

        node.right?.let {
            val tmp = _result + mutableListOf(it.`val`)
            queue.add(Triple(it, value + it.`val`, tmp.toMutableList()))
        }

        node.left?.let {
            val tmp = _result + mutableListOf(it.`val`)
            queue.add(Triple(it, value + it.`val`, tmp.toMutableList()))
        }
    }
    return result
}

fun TreeNode.addAll(memo: Int, targetSum: Int, list: MutableList<Int>, result: MutableList<List<Int>>) {
    list.add(this.`val`)

    val _memo = memo + this.`val`

    if (this.isLeaf() && _memo == targetSum) {
        result.add(list.toList())
    }

    this.left?.addAll(_memo, targetSum, list.deepCopy(), result)
    this.right?.addAll(_memo, targetSum, list.deepCopy(), result)
}

private fun TreeNode.isSingle(): Boolean {
    return this.left == null && this.right == null
}


private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
}

private fun List<Int>.deepCopy(): MutableList<Int> {
    return this.map { it }.toMutableList()
}