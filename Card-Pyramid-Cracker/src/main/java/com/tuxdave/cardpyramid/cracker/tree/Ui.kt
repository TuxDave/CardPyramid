package com.tuxdave.cardpyramid.cracker.tree

import java.util.*

fun Node.display(tabs: Int = 0): String{
    return ("  ".repeat(tabs)) + Arrays.deepToString(this.state.toTypedArray())
}

fun FakeTree.display(): String{
    var c = 0
    var ret = "ROOT: ${this.root.display()}\n\n===================\nAFTER ${c++} MOVE\n"
    ret += explodeNode(root)
    return ret
}

var tabs = 0;
fun explodeNode(nodo: Node, base: String = ""): String{
    var ret = base + nodo.display(tabs) +  "\n"
    for(son in nodo.siblings){
        ret += explodeNode(son, ret) //TODO capire la ragione del OverFlow
    }
    ret += "\n"
    return ret
}