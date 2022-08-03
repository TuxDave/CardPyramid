package com.tuxdave.cardpyramid.cracker.tree;

/**
 * classe che si occupa di memorizzare l'albero delle possibilità del gioco.<br>
 * le logiche che lo popoleranno saranno separate da esso, come anche le logiche di analisi dei risultati.
 */
public class FakeTree {
    private Node root;

    /**
     * @param game lo stato della partita da cui memorizzare l'albero delle possibilità
     */
    public FakeTree(int[] game){
        root = new Node(Node.reduce(game));
    }


}
