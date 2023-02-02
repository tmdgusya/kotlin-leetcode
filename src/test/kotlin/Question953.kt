import io.kotest.core.spec.style.FunSpec

class Question953: FunSpec({
    test("")
})

fun isAlienSorted(words: Array<String>, order: String): Boolean {
    // 에일리언 언어의 우선순위 배열을 통해 { 알파벳 to 점수 } 사전을 만듬 (예외 케이스를 위한, ("none" to -1) 도 추가)

    // 단어 두개를 넣고 순서가 맞는지 체크하는 함수가 있음
    // fun validate(string1: String, string2: String)

    // 이 함수안에서 체크할때 for 문을 돌림
    // for - loop (i in 0 untli string1.length)
    // if (alienOrders[string1[i]] > alienOrders[string2[i]]) return false
    val alienOrdersDict = createAlienOrdersDict(order)
    for (i in 1 until words.size) {
        if (validate(words[i-1], words[i], alienOrdersDict) == false
    }
    return true
}

fun createAlienOrdersDict(order: String): Map<Char, Int> {
    val dict = mutableMapOf<Char, Int>()
    for (i in 0 until  order.length) {
        dict[order[i]] = i
    }
    return dict
}

// 단어가 달라지는 순간만 대솟값을 비교하면 됨.
fun validate(string1: String, string2: String, alienOrdersDict: Map<Char, Int>): Boolean {
    for (i in 0 until string1.length) {
        val firstOfPoint = alienOrdersDict[string1.getOrNull(i)] ?: -1
        val secondOfPoint = (alienOrdersDict[string2.getOrNull(i)] ?: -1)
        if (firstOfPoint == secondOfPoint) continue
        return firstOfPoint <= secondOfPoint
    }
    return true
}