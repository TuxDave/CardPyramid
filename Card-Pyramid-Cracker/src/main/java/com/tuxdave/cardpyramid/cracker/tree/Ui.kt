package com.tuxdave.cardpyramid.cracker.tree

import com.sun.jdi.event.BreakpointEvent
import java.util.*

fun Node.display(tabs: Int = 0): String{
    return ("\t".repeat(tabs)) + Arrays.deepToString(this.state.toTypedArray())
}

fun FakeTree.display(): String{
    var c = 0
    var ret = "ROOT: ${this.root.display()}\n\n===================\nAFTER ${c++} MOVE\n"
    ret += explodeNode(root)
    return ret
}

var tabs = 0;
fun explodeNode(nodo: Node, base: String = ""): String{
    var ret = base + nodo.display(tabs++) +  "\n"
    if(nodo.siblings.size == 0){
        tabs--
    }
    var c = 0
    for(son in nodo.siblings){
        ret += explodeNode(son)
        //questo controllo per via della memoization fa attivare il controllo anche se non è effettivamente l'ultimo
        if(son == nodo.siblings.last() && c == nodo.siblings.lastIndexOf(son)){
            tabs--
        }
        c++;
    }
    return ret
}