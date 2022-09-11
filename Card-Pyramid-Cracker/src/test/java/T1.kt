import com.tuxdave.cardpyramid.cracker.getAllPossibleMoves
import com.tuxdave.cardpyramid.cracker.getPossibleMovesNumber
import java.util.*

var n: Int = 0
@Synchronized
fun inc(){
    n++
}

private class Summer : Thread(){

    var finish = false
    override fun run() {
        for(i in 0 until 10000){
            inc()
        }
        finish = true
    }
}
fun main(args: Array<String>) {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    print(stack)
}