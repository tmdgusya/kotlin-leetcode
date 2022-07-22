import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.sorted

/**
 * Given the head of a linked list and a value x, 
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 */

internal class Question86: DescribeSpec({

    val solution = Solution86()

    describe("Given a string s consisting of words and spaces, return the length of the last word in the string.") {
        it("s = Hello World, output = 2") {
            val first = ListNode86(1)
            val second = ListNode86(4)
            val third = ListNode86(3)
            val fourth = ListNode86(2)
            val fifth = ListNode86(5)
            val six = ListNode86(2)

            first.next = second
            second.next = third
            third.next = fourth
            fourth.next = fifth
            fifth.next = six

            solution.partition(first, 3)
        }
    }
})

class Solution86 {
    fun partition(head: ListNode86?, x: Int): ListNode86? {

        if (head == null) return  null
        var pointer = head

        // x is pivot

        val leftIndex = ListNode86(-1)
        val rightIndex = ListNode86(-1)


        while (pointer != null) {
            if (pointer.`val` < x) {
                leftIndex.setValue(pointer)
            } else {
                rightIndex.setValue(pointer)
            }
            pointer = pointer.next
        }


        // merge leftIndex And rightIndex
        val realRight = rightIndex.next
        if (realRight != null) {
            leftIndex.merge(realRight)
        }

        return leftIndex.next
    }
}

class ListNode86(var `val`: Int) {
    var next: ListNode86? = null
}

fun ListNode86.setValue(node :ListNode86) {
    val copy = ListNode86(node.`val`)
    copy.next = null

    recursion(this, copy)
}

fun ListNode86.merge(node :ListNode86) {
    recursion(this, node)
}

fun ListNode86.deepCopy(): ListNode86 {
    val copyNode = ListNode86(this.`val`)
    copyNode.next = this.next
    return copyNode
}

private fun recursion(node: ListNode86, target: ListNode86) {
    if (node.next == null) {
        node.next = target
        return
    } else {
        recursion(node.next!!, target)
    }
}

/**
 * Runtime: 302 ms, faster than 6.06% of Kotlin online submissions for Partition List.
 * Memory Usage: 35.3 MB, less than 54.55% of Kotlin online submissions for Partition List.
 */