import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question99 : FunSpec({

    test("case 01") {
        val _1 = TreeNode(1)
        val _3 = TreeNode(3)
        val _2 = TreeNode(2)

        _1.left = _3
        _3.right = _2

        recoverTree(_1)

        _1.`val` shouldBe 3
        _1.left?.`val` shouldBe 1
        _3.right?.`val` shouldBe 2
    }

})

fun recoverTree(root: TreeNode?): Unit {
    if (root == null) return
    val nodes = mutableListOf<TreeNode>()

    inOrderTraversal(root, nodes)
    findAndSwapTwoNodesThatNeededSwap(nodes)
}

// almost sorted
fun inOrderTraversal(root: TreeNode?, nodes: MutableList<TreeNode>) {
    if (root == null) return

    inOrderTraversal(root.left, nodes)
    nodes.add(root)
    inOrderTraversal(root.right, nodes)
}

fun findAndSwapTwoNodesThatNeededSwap(nodes: MutableList<TreeNode>) {
    var isSwapped = false
    var node1: TreeNode? = null
    var node2: TreeNode? = null
    for (i in 0 until nodes.lastIndex) {
        if (nodes[i].`val` > nodes[i + 1].`val`) {
            node1 = nodes[i + 1]

            if (!isSwapped) {
                node2 = nodes[i]
                isSwapped = true
            }
        }
    }

    if (node1 != null && node2 != null) {
        val tmp = node1!!.`val`
        node1.`val` = node2!!.`val`
        node2.`val` = tmp
    }
}