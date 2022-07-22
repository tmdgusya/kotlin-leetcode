internal class Question100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return p.isSameArch(q)
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun TreeNode?.isSameArch(t: TreeNode?): Boolean {
    if (this == null && t == null) return true
    if (this == null && t != null) return false
    if (t == null && this != null) return false
    return this!!.isEqual(t) && this.left.isSameArch(t?.left) && this.right.isSameArch(t?.right)
}

fun TreeNode.isEqual(t: TreeNode?): Boolean {
    return this.`val` == t?.`val` && this.left?.`val` == t.left?.`val` && this.right?.`val` == t.right?.`val`
}

/**
 * Runtime: 128 ms, faster than 100.00% of Kotlin online submissions for Same Tree.
 * Memory Usage: 33.8 MB, less than 81.72% of Kotlin online submissions for Same Tree.
 */
