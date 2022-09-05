import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Question2214 : FunSpec({

    test("case 01") {
        val damage = intArrayOf(2,7,4,3)
        val armor = 4

        val result = minimumHealth(damage, armor)

        result shouldBe 13
    }
})

fun minimumHealth(damage: IntArray, armor: Int): Long {
    var sum = 0L
    var max = 0
    for (d in damage) {
        sum += d
        max = Math.max(d, max)
    }

    val protectedDamage = if (max > armor) {
        armor
    } else { max }

    return (sum - protectedDamage) + 1
}