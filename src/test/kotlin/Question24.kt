import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class Question24 : BehaviorSpec({

    given("head = [1,2,3,4]") {
        val head = ListNode24(`val` = 1)
        val ele1 = ListNode24(`val` = 2)
        val ele2 = ListNode24(`val` = 3)
        val ele3 = ListNode24(`val` = 4)

        head.next = ele1
        ele1.next = ele2
        ele2.next = ele3

        val solution = Solution24()
        `when`("execute solution()") {
            val result = solution.swapPairs(head)
            then("returns [2,1,4,3]") {
                result?.`val` shouldBe 2
                result?.next?.`val` shouldBe 1
            }
        }
    }

})

class Solution24 {
    fun swapPairs(head: ListNode24?): ListNode24? {
        if (head == null) return null
        return saveToArray(head)
    }
}


// do not modify the values in the list nodes
/**
 * Swap Solution
 *
 * 1. Change `next` of nodeA to `next` of nodeB
 * 2. Change `next` value of nodeB to nodeA
 */
fun swap(list: MutableList<ListNode24>, i: Int) {
    val temp = list[i - 1]
    list[i - 1] = list[i]
    list[i] = temp
}

/**
 * Swap Condition
 *
 * 1. Swap two elements, if index of element is odd.
 */
fun isSwap(index: Int): Boolean {
    return index % 2 != 0
}

/**
 * Store to Array
 *
 * 1. Loop to linkedList while `next` of element is not null
 * 2. save element to list each iteration
 */
fun saveToArray(node: ListNode24?): ListNode24? {
    val list = mutableListOf<ListNode24>()
    var _node = node
    while (true) {
        list.add(_node!!)
        if (_node.next == null) {
            break
        }
        _node = _node.next
    }
    for (i in list.indices) {
        if (isSwap(i)) {
           swap(list, i)
        }
    }
    connect(list)
    list.printList()
    return list[0]
}

/**
 * Connect Node
 *
 * 1. For-looping for list
 * 2. Set up `next` value of current Node to next node each Iteration
 */
fun connect(list: List<ListNode24>) {
    for (i in list.indices) {
        if (i != list.lastIndex) {
            list[i].next = list[i + 1]
        } else {
            list[i].next = null
        }
    }
}

class ListNode24(var `val`: Int) {
    var next: ListNode24? = null
}

fun List<ListNode24>.printList() {
    for (ele in this) {
        println(ele.`val`)
    }
}