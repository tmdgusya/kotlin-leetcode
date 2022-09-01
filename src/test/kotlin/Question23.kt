import io.kotest.core.spec.style.FunSpec

class Question23 : FunSpec({
    test("case1") {
        val _1 = ListNode(1)
        val _2 = ListNode(4)
        val _3 = ListNode(5)

        _1.next = _2
        _2.next = _3

        val _4 = ListNode(1)
        val _5 = ListNode(3)
        val _6 = ListNode(4)

        _4.next = _5
        _5.next = _6

        val _7 = ListNode(2)
        val _8 = ListNode(6)

        _7.next = _8

        val node = mergeKLists(arrayOf(_1, _4, _7))

        assert(node != null)
        node!!.`val` = 1

        assert(node.next != null)
        node.next!!.`val` = 1
    }

})

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/**
 * Brute-force solution
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null

    val nodes = mutableListOf<ListNode>()

    for (node in lists) {
        var n = node
        while (n != null) {
            nodes.add(n)
            n = n.next
        }
    }

    val comparator: Comparator<ListNode> = compareBy { it.`val` }
    val sortedList = nodes.sortedWith(comparator)

    for (i in 0 until sortedList.size - 1) {
        val firstNode = sortedList[i]
        val secondNode = sortedList[i + 1]

        firstNode.next = secondNode
    }

    if (sortedList.isEmpty()) return null

    return sortedList[0]
}