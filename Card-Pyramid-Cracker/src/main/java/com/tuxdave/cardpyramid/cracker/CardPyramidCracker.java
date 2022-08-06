package com.tuxdave.cardpyramid.cracker;

import com.tuxdave.cardpyramid.cracker.tree.FakeTree;
import com.tuxdave.cardpyramid.cracker.tree.UiKt;

public class CardPyramidCracker {
    public static void main(String[] args) {
        FakeTree tree = CrackerKt.crack(4);
        System.out.println(UiKt.display(tree));
    }
}
