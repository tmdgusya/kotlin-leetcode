import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LeetCode211 : FunSpec({
    test("Word Dictionary") {
        val wordDictionary = WordDictionary()
        wordDictionary.addWord("bad")
        wordDictionary.addWord("dad")
        wordDictionary.addWord("mad")
        wordDictionary.search("pad") shouldBe false
        wordDictionary.search("bad") shouldBe true
        wordDictionary.search(".ad") shouldBe true
        wordDictionary.search("b..") shouldBe true
    }
})

private data class TrieNode(
    var isLeaf: Boolean = false,
    val children: Array<TrieNode?> = Array(26) { null }
)

private class WordDictionary {
    private val root = TrieNode()

    fun addWord(word: String) {
        var curr: TrieNode = root
        for (char in word) {
            if (curr.children[char - 'a'] == null) {
                curr.children[char - 'a'] = TrieNode()
            }
            curr = checkNotNull(curr.children[char - 'a'])
        }
        // marking isLeaf is true (leaf node)
        curr.isLeaf = true
    }

    fun search(word: String): Boolean {
        return dfs(word, 0, root)
    }

    fun dfs(word: String, index: Int, curr: TrieNode): Boolean {
        if (index == word.length) {
            return curr.isLeaf
        }

        return word[index].let {
            if (it == '.') {
                for (child in curr.children) {
                    if (child != null && dfs(word, index + 1, child)) {
                        return true
                    }
                }
                return false
            } else {
                curr.children[it - 'a']?.let {
                    dfs(word, index + 1, it)
                } ?: return false
            }
        }
    }
}
