import kotlin.math.min

private fun readStr() = readln()
private fun readInt() = readStr().toInt()

val g = Array(26) { ArrayList<Int>() }
val used = Array(26) { 0 }
var cycle = false
val order = ArrayList<Int>()

fun dfs(v: Int) {
    used[v] = 1
    for (u in g[v]) {
        if (used[u] == 1) {
            cycle = true
        }
        if (used[u] == 0) {
            dfs(u)
        }
    }
    used[v] = 2
    order.add(v)
}

fun solve(name: Array<String>): String? {
    for (i in 0..name.size - 2) {
        val minLen = min(name[i].length, name[i + 1].length)
        for (j in 0..minLen) {
            if (j == minLen) {
                if (j < name[i].length) {
                    return null
                }
                break
            }

            if (name[i][j] != name[i + 1][j]) {
                val v = name[i][j] - 'a'
                val u = name[i + 1][j] - 'a'
                g[v].add(u)
                break
            }
        }
    }

    for (i in 0..25) {
        if (used[i] == 0) {
            dfs(i)
        }
    }

    if (cycle) {
        return null
    }
    order.reverse()
    return order.map { i -> 'a' + i }.joinToString("")
}

fun main(args: Array<String>) {
    val n = readInt()
    val name = Array(n) { "" }
    for (i in name.indices) {
        name[i] = readStr()
    }

    val order = solve(name)
    if (order == null) {
        println("Impossible")
    } else {
        println(order)
    }
}
