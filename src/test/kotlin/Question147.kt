import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class Question147: WordSpec({

    val solution = Solution147()

    "Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head." When {

        "[4,2,1,3]" should {
            val first = Solution147.ListNode(4)
            val second = Solution147.ListNode(2)
            val third = Solution147.ListNode(1)
            val fourth = Solution147.ListNode(3)

            first.next = second
            second.next = third
            third.next = fourth

            "[1,2,3,4]" {
                solution.insertionSortList(first) shouldBe third

                third.next shouldBe second
                second.next shouldBe fourth
                fourth.next shouldBe first
            }

        }

    }

})

class Solution147 {
    fun insertionSortList(head: ListNode?): ListNode? {
        if (head == null) return null

        val list = mutableListOf<ListNode>()

        // Insert all array
        var pointerNode = head
        while (pointerNode != null) {
            list.add(pointerNode)
            pointerNode = pointerNode.next
        }

        // Insertion sort
        for (i in 1.. list.lastIndex) {
            for (j in i-1 downTo  0) {
                if (list[j + 1].`val` > list[j].`val`) {
                    list[j].next = list[j + 1]
                    break
                }

                if (list[j + 1].`val` <= list[j].`val`) {
                    list[j].next = list[j+1].next
                    list[j + 1].next = list[j]

                    val temp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = temp
                }
            }
        }

        return list[0]
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}

