import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question904: FunSpec({

    test("case 01") {
        val fruits = intArrayOf(1,2,1)

        totalFruit(fruits) shouldBe 3
    }

})

fun totalFruit(fruits: IntArray): Int {
    // stop condition
    // 1. only two baskets
    // 2. must pick one fruit from starting point and move right
    // 3. if cannot pick up fruits, then must stop

    val baskets = HashMap<Int, Boolean>()
    var start = 0
    var end = 0
    var max = 0
    var sum = 0

    while (end < fruits.size) {
        val fruit = fruits[end]
        // check condition (can i pick up the fruits)
        if (baskets.size == 2 && !baskets.containsKey(fruit)) {
            // calc max
            max = Math.max(max, sum)
            // clear baskets
            baskets.clear()
            // reset sliding window
            start++
            sum = 0
            end = start
        } else {
            baskets[fruit] = true
            sum++
            end++
        }
    }

    max = Math.max(max, sum)
    return max
}

// optimize

fun totalFruit2(fruits: IntArray): Int {
    val counts = IntArray(fruits.size)
    var max = 0
    var curr = 0
    var countOfType = 0
    var start = 0
    for (i in fruits.indices) {
        if (++counts[fruits[i]] == 1) countOfType++
        while (countOfType > 2) {
            if (--counts[fruits[start++]] == 0) countOfType--
            curr--
        }
        max = Math.max(max, ++curr)
    }
    return max
}