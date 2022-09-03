import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import java.util.*

class Question49 : FunSpec({

    test("case 01") {

        val strs = arrayOf("eat","tea","tan","ate","nat","bat")

        val expected = listOf(
            listOf("bat"),
            listOf("nat", "tan"),
            listOf("ate", "eat", "tea")
        )

        val result = groupAnagrams(strs).flatten()
        expected.flatten().forEach { result shouldContain it }
    }

})

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    if (strs.isEmpty()) return emptyList()
    val map = HashMap<String, MutableList<String>>()

    for (str in strs) {
        val charArr = str.toCharArray()
        Arrays.sort(charArr)
        val key = String(charArr)

        if (!map.containsKey(key)) map[key] = mutableListOf(str)
        else map[key]!!.add(str)
    }

    return map.values.toList()
}