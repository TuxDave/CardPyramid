import com.tuxdave.cardpyramid.cracker.getAllPossibleMoves
import com.tuxdave.cardpyramid.cracker.getPossibleMovesNumber
import java.util.*

fun main(args: Array<String>) {
    val state = intArrayOf(4)
    println(getPossibleMovesNumber(state))
    println(Arrays.deepToString(getAllPossibleMoves(state)))
}