import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class Question242 : BehaviorSpec({

    val solution = Solution242()

    given("given Input s = 'anagram' and t = 'nagaram'") {
        val s = "anagram"
        val t = "nagaram"
        `when`("if execute isAnagram Method") {
            val result = solution.isAnagram(
                s = s,
                t = t
            )
            then("return true") {
                result shouldBe true
            }
        }
    }

})

class Solution242 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val hashTable = mutableMapOf<Char, Boolean>()

        val spells = s.toCharArray()

        for (spell in spells) {
            if (hashTable.containsKey(spell)) {
                continue
            }

            // spell does not exist in hash table
            // Save spell count exist in T
            val countOfHasSpellInT = t.count { it == spell }
            val countOfHasSpellInS = s.count { it == spell }

            // return false if countOfHasSpellInS and countOfHasSpellInT is not equals
            if (countOfHasSpellInS != countOfHasSpellInT) return false

            // save history
            hashTable[spell] = true
        }
        return true
    }
}