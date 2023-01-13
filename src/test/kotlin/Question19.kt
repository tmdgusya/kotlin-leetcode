import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question19 : FunSpec({

    test("case 01") {
        val head = ListNode(1)
        val _2 = ListNode(2)
        val _3 = ListNode(3)
        val _4 = ListNode(4)
        val _5 = ListNode(5)

        head.next = _2
        _2.next = _3
        _3.next = _4
        _4.next = _5

        removeNthFromEndWithDummy(head, 2)

        _3.next shouldBe _5
    }

    test("case 02") {
        val head = ListNode(1)

        val result = removeNthFromEndWithDummy(head, 1)

        result shouldBe null
    }

    test("case 03") {
        val head = ListNode(1)
        val _2 = ListNode(2)

        head.next = _2

        val result = removeNthFromEnd(head, 1)

        result?.next shouldBe null
        result?.`val` shouldBe 1
    }

    test("case 04") {
        val head = ListNode(1)
        val _2 = ListNode(2)

        head.next = _2

        val result = removeNthFromEnd(head, 2)

        result?.next shouldBe null
        result?.`val` shouldBe 2
    }
})

private class Storage(
    private val nodeMap: MutableMap<Int, ListNode?>
) {
    fun print() {
        println(nodeMap)
    }

    fun deleteNode(idx: Int) {
        val reversingSize = (nodeMap.size - idx) + 1
        // 해당 노드의 앞 노드를 꺼낸다.
        val beforeNode = nodeMap[reversingSize - 1]

        // 해당 노드의 다음 노드를 꺼낸다
        val afterNode = nodeMap[reversingSize + 1]

        // beforeNode 의 next 에 afterNode 를 연결한다.
        beforeNode?.next = afterNode
        nodeMap[reversingSize] = null
    }

    fun saveNode(idx: Int, node: ListNode) {
        nodeMap[idx] = node
    }

    val first get() = nodeMap.values.firstOrNull { it != null }
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val storage = Storage(HashMap<Int, ListNode?>())
    var loopNode: ListNode? = head
    var count = 1

    while (loopNode != null) {
        storage.saveNode(count, loopNode)
        count++
        loopNode = loopNode.next
    }

    storage.deleteNode(n)

    return storage.first
}

fun removeNthFromEndWithDummy(head: ListNode?, n: Int): ListNode? {
    if (head == null) return null
    val dummy = ListNode(0)
    val length = head.length()
    dummy.next = head

    var delIdx = length - n
    var node: ListNode? = dummy
    while (delIdx != 0) {
        delIdx--;
        node = node?.next
    }

    node?.next = node?.next?.next
    return dummy.next
}

fun ListNode.length(): Int {
    var length = 0
    var node: ListNode? = this
    while (node != null) {
        length++
        node = node.next
    }
    return length
}
