import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class Question103 : FunSpec({

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

        val result = mySolution(_3)

        result shouldBe listOf(
            listOf(3),
            listOf(20, 9),
            listOf(15, 7)
        )
    }
})

fun mySolution(root: TreeNode?): List<List<Int>> {
    val _root = root ?: return emptyList()

    val queue = LinkedList<Pair<TreeNode, Int>>()
    val resultList = mutableListOf<List<Int>>()
    val map = HashMap<Int, ArrayDeque<Int>>()

    queue.add(_root to 1)

    // init
    resultList.add(listOf(_root.`val`))


    while (queue.isNotEmpty()) {

        val (node, depth) = queue.pop()
        if (!map.containsKey(depth)) map[depth] = ArrayDeque()

        node.left?.let {
            queue.add(it to depth + 1)
            addListByZigZag(map, depth, it.`val`)
        }

        node.right?.let {
            queue.add(it to depth + 1)
            addListByZigZag(map, depth, it.`val`)
        }

        if (map[depth]!!.isNotEmpty() && resultList.lastIndex < depth) resultList.add(map[depth]!!.toList())
        if (map[depth]!!.isNotEmpty() && resultList.lastIndex >= depth) resultList[depth] = map[depth]!!.toList()

    }
    return resultList
}

private fun addListByZigZag(map: Map<Int,  ArrayDeque<Int>>, depth: Int, value: Int) {
    // addLast
    if (depth % 2 == 1) {
        map[depth]!!.addFirst(value)
    } else {
        map[depth]!!.add(value)
    }
}