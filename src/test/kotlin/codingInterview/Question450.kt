package codingInterview

import TreeNode
import io.kotest.core.spec.style.FunSpec
import java.util.LinkedList

class Question450 : FunSpec({

    test("case 01") {

        val _5 = TreeNode(5)
        val _3 = TreeNode(3)
        val _6 = TreeNode(6)
        val _2 = TreeNode(2)
        val _4 = TreeNode(4)
        val _7 = TreeNode(7)

        _5.left = _3
        _5.right = _6

        _6.right = _7

        _3.left = _2
        _3.right = _4

        val root = deleteNode(_5, 3)

        assert(root != null)
        root?.left?.`val` = 4
        root?.left?.left?.`val` = 2
    }

})

fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    val _root = root ?: return null

    // delegate left if key is smaller than this
    if (_root.`val` > key) {
        deleteNode(_root.left, key)
    }

    // delegate right if key is smaller than this
    if (_root.`val` < key) {
        deleteNode(_root.right, key)
    }

    // need delete
    if (_root.`val` == key) {

        if (_root.hasMightOnlyLeftSubTree()) {
            return _root.left
        }

        if (_root.hasMightOnlyRightSubTree()) {
            return _root.right
        }

        // getMinimumValue of Right Nodes
        val minNode = getMinimumValue(_root.right!!)
        _root.`val` = minNode.`val`

        // delete swapped node
        _root.right = deleteNode(_root.right, minNode.`val`)
    }

    return _root
}

fun getMinimumValue(node: TreeNode): TreeNode {
    var _node = node
    while (_node.left != null)  {
        _node = _node.left!!
    }
    return _node
}


private fun TreeNode.hasMightOnlyLeftSubTree(): Boolean {
    return this.right == null
}

private fun TreeNode.hasMightOnlyRightSubTree(): Boolean {
    return this.left == null
}