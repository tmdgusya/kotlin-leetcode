internal class Question101 {
    fun isSameTree(root: TreeNode?): Boolean {
        return root?.left?.isSameMirrorArch(root.right) ?: false
    }
}

fun TreeNode?.isSameMirrorArch(t: TreeNode?): Boolean {
    if (this == null && t == null) return true
    if (this == null && t != null) return false
    if (t == null && this != null) return false
    return this!!.isMirrorEqual(t) && this.left.isSameArch(t?.right) && this.right.isSameArch(t?.left)
}

fun TreeNode.isMirrorEqual(t: TreeNode?): Boolean {
    return this.`val` == t?.`val` && this.left?.`val` == t.right?.`val` && this.right?.`val` == t.left?.`val`
}
