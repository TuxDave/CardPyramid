import com.tuxdave.cardpyramid.cracker.crack
import com.tuxdave.cardpyramid.cracker.tree.display
import java.util.*

fun main(args: Array<String>) {
    val tree = crack(1)
    tree.gamesPool[intArrayOf(1, 2, 3)]
    tree.gamesPool[intArrayOf(1, 2, 3)]
    for (v in tree.gamesPool.pool.keys) {
        println(v)
    }
    //println(tree.display())
}