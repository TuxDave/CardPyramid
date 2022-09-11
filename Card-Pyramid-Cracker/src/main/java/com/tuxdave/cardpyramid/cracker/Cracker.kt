/**
 * file che raggruppa la procedura PROCEDURALE che permette di calcolare l'albero di crack della piramide di
 * carte o di una porzione di essa.
 */
package com.tuxdave.cardpyramid.cracker

import com.tuxdave.cardpyramid.cracker.tree.FakeTree
import com.tuxdave.cardpyramid.cracker.tree.Node
import com.tuxdave.cardpyramid.cracker.tree.display
import java.lang.Thread.sleep
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.math.max

/**
 * extension function per sommare gli elementi di un array di interi
 */
fun IntArray.sum(): Int {
    var r = 0
    for (n in this) {
        r += n
    }
    return r
}

fun Int.factorial(): Int {
    if (this == 1) return 1
    return this * (this - 1).factorial()
}

/**
 * fornisce il numero di rami possibili che si aprono da ogni situazione di partita (distanza 1)
 * @param state lo stato del campo da calcolare
 */
fun getPossibleMovesNumber(state: IntArray): Int {
    return state.sum() - if (state.size == 1) 1 else 0
}

/**
 * calcola lo stato del campo di gioco dopo ogni possibile mossa applicata a distanza 1
 */
fun getAllPossibleMoves(state: IntArray): Array<IntArray> {
    val ret: Array<IntArray?> = arrayOfNulls<IntArray>(getPossibleMovesNumber(state))
    var nRet = 0;
    val less = if (state.size == 1) 1 else 0
    for (layer in 0 until state.size) {
        val cards = state[layer] - less//======================================================
        for (card in 1..cards) {
            ret[nRet] = state.clone()
            ret[nRet]!![layer] -= card
            ret[nRet] = Node.reduce(ret[nRet])
            nRet++
        }
    }
    return ret.requireNoNulls();
}

fun genGame(n: Int): IntArray {
    val ret: MutableList<Int> = mutableListOf()
    for (i in 0 until n) {
        ret.add(i + 1);
    }
    return ret.toIntArray()
}

fun isGameFinished(state: IntArray): Boolean {
    return getPossibleMovesNumber(state) == 0
}

private class Worker : Thread() {
    var finish: Boolean = true
        get() = field
        private set(value) {
            field = value
        }
    var result: Node? = null
        get() = field
        private set(value) {
            field = value
        }
    var startNode: Node? = null
    var fakeTree: FakeTree? = null

    private fun addCasesToTree(ft: FakeTree, father: Node) {
        val cases: Array<IntArray> = getAllPossibleMoves(father.state)
        for (i in 0 until cases.size) {
            cases[i] = ft.gamesPool.get(Node.reduce(cases[i]))
        }
        for (case in cases) {
            val nodo: Node
            if (case in ft.alreadyComputedGames) {
                nodo = ft.gamesPool.getMemoized(case)//ft.searchNodeByState(ft.gamesPool.get(case))
            } else {
                nodo = Node(case)
                this.addCasesToTree(ft, nodo)
                ft.gamesPool.set(case, nodo)
            }
            father.add(nodo, ft)
        }
    }

    override fun run() {
        finish = false
        if (fakeTree != null && startNode != null) {
            this.addCasesToTree(fakeTree!!, startNode!!)
            result = startNode
            fakeTree!!.root.add(result, fakeTree)
        }
        finish = true
    }

    fun reset(): Unit {
        finish = true
        result = null
        startNode = null
        fakeTree = null
    }

    init {
        reset()
    }
}

private val workers: Stack<Worker> = Stack<Worker>()
private val activeWorkers = mutableListOf<Worker>()
val workersManager = thread(true) {
    while (true) {
        sleep(100)
        val n = activeWorkers.size
        for (c in 0 until n) {
            if (activeWorkers[c].result != null) {
                val w = activeWorkers[c]
                w.reset()
                activeWorkers.remove(w)
                workers.push(w)
                break
            }
        }
    }
}

fun addCasesToTree(ft: FakeTree, father: Node) {
    val cases: Array<IntArray> = getAllPossibleMoves(father.state)
    for (i in 0 until cases.size) {
        cases[i] = ft.gamesPool.get(Node.reduce(cases[i]))
    }
    for (case in cases) {
        val nodo: Node
        if (case in ft.alreadyComputedGames) {
            nodo = ft.gamesPool.getMemoized(case)
            father.add(nodo, ft)
            continue
        } else {
            nodo = Node(case)
            while (workers.size == 0) {
                Thread.sleep(100)
            }
            val w = workers.pop()
            w.startNode = nodo
            w.fakeTree = ft
            w.start()
            activeWorkers.add(w)
        }
    }
    while (activeWorkers.size != 0) {
        Thread.sleep(100)
    }
    workersManager.interrupt()
}

fun crack(n: Int): FakeTree {
    //setup threads
    val cores = max(Runtime.getRuntime().availableProcessors() - 1, 1)
    for (i in 0 until cores) {
        workers.push(Worker())
    }


    val ft = FakeTree(genGame(n));
    addCasesToTree(ft, ft.root)
    //println("ALBERO CALCOLATO")
    return ft
}