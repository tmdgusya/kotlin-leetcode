import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class Question148 : WordSpec({

    val solution = Solution148()

    "Given the head of a linked list, return the list after sorting it in ascending order." When  {

        "head = [4,2,1,3]" should   {
            val first = ListNode148(4)
            val second = ListNode148(2)
            val third = ListNode148(1)
            val fourth = ListNode148(3)

            first.next = second
            second.next = third
            third.next = fourth

            "Output = [1,2,3,4]" {
                // first is must be third\
                val result = solution.sortList(first)
                result shouldBe third

                println(result?.`val`)

                third.next shouldBe second
                second.next shouldBe  fourth
                fourth.next shouldBe first
            }
        }

    }

})

class ListNode148(var `val`: Int) {
    var next: ListNode148? = null
}

fun ListNode148?.mergeSort(): ListNode148? {

    if (this == null || this.next == null) return this

    // divide sector
    val pivot = this.getPivot()
    val right = pivot?.next

    // split
    pivot?.next = null

    // sort by sector
    val leftArea = this.mergeSort()
    val rightArea = right.mergeSort()

    // merge sort
    return compareSeparatedNodes(leftArea, rightArea)
}

fun compareSeparatedNodes(node1: ListNode148?, node2: ListNode148?): ListNode148? {
    var result: ListNode148? = null

    if (node1 == null) return node2
    if (node2 == null) return node1

    if (node1.`val` <= node2.`val`) {
        result = node1
        node1.next = compareSeparatedNodes(node1.next, node2)
    } else {
        result = node2
        node2.next = compareSeparatedNodes(node2.next, node1)
    }

    return result
}

fun ListNode148?.getPivot(): ListNode148? {
    if (this == null) return this

    var slow = this
    var fast = this

    while (fast?.next != null && fast.next?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    return slow
}



class Solution148 {
    fun sortList(head: ListNode148?): ListNode148? {
        return head?.mergeSort()
    }
}
