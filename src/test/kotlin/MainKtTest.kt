import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest {
    @Test
    fun orderExists() {
        val name = arrayOf("aba", "ac", "b")
        val order = solve(name)
        assertNotNull(order)
        assertTrue(
            name.map { s -> s.map { c -> 'a' + order!!.indexOf(c) }.joinToString("") }.zipWithNext()
                .all { it.first <= it.second })
    }

    @Test
    fun impossible() {
        val name = arrayOf("aa", "b", "acdc")
        val order = solve(name)
        assertNull(order)
    }
}