package com.tuxdave.cardpyramid.cracker;

import com.tuxdave.cardpyramid.cracker.tree.FakeTree;
import com.tuxdave.cardpyramid.cracker.tree.UiKt;

public class CardPyramidCracker {
    public static void main(String[] args) {
        FakeTree tree = CrackerKt.crack(Integer.parseInt(args[0]));
        System.out.println("Albero calcolato");
        System.out.println(UiKt.display(tree));
    }
}
