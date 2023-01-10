import io.kotest.core.spec.style.FunSpec
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

private typealias Depth = Int
private typealias PrintResults = ArrayDeque<Depth>
private typealias Queue = LinkedList<Pair<TreeNode, Depth>>

fun mySolution(root: TreeNode?): List<List<Int>> = root?.let { root ->
    (Queue() to HashMap<Depth, PrintResults>())
        .also { (queue, _) -> queue.add(root to 1) }
        .also { (_, printNodeResultByDepth) -> printNodeResultByDepth.initAndPrint(0, root.`val`) }
        .also { (queue, printNodeResultByDepth) -> queue.traversalNodeForSavingResult(printNodeResultByDepth) }
        .let { (_, printNodeResultByDepth) -> printNodeResultByDepth.toResult() }
} ?: emptyList()

private fun HashMap<Depth, PrintResults>.toResult() =
    this.values.filter { it.isNotEmpty() }.map { it.toList() }

private fun HashMap<Depth, PrintResults>.init(depth: Depth): HashMap<Depth, PrintResults> = this.also { it[depth] = ArrayDeque() }

private fun HashMap<Depth, PrintResults>.initAndPrint(depth: Depth, value: Int) =
    this.init(depth).let { it[depth]!!.add(value) }

private fun LinkedList<Pair<TreeNode, Depth>>.traversalNodeForSavingResult(
    printNodeResultByDepth: HashMap<Depth, PrintResults>,
) = this.let { queue ->
        while (queue.isNotEmpty()) {
            this.pop().also {(_, depth) ->
                if (!printNodeResultByDepth.containsKey(depth)) printNodeResultByDepth.init(depth)
            }.let { (node, depth) ->
                node.left?.printAndTraversalNextNode(depth, printNodeResultByDepth, this)
                node.right?.printAndTraversalNextNode(depth, printNodeResultByDepth, this)
            }
        }
}

private fun TreeNode.printAndTraversalNextNode(
    depth: Int,
    printNodeResultByDepth: HashMap<Depth, PrintResults>,
    queue: LinkedList<Pair<TreeNode, Depth>>,
) = this.also {
    queue.add(it to depth + 1)
}.let {
    printNodeResultByDepth.addListByZigZag(depth, it.`val`)
}

private fun Map<Depth, PrintResults>.addListByZigZag(depth: Depth, value: Int) = if (depth % 2 == 1) {
    this[depth]!!.addFirst(value)
} else {
    this[depth]!!.add(value)
}