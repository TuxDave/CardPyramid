package com.tuxdave.cardpyramid.cracker.tree;

import com.tuxdave.cardpyramid.cracker.CrackerKt;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * classe che memorizza lo stato della partita.<br>
 * il turno di quale giocatore è deducibile dalla parità o disparità della mossa.<br>
 * il numero della mossa è deducibile dal numero di padri che questo oggetto possiede.<br>
 * questa classe memorizza soltanto i figli.<br>
 * più genitori possono avere lo stesso figlio, in modo da ottimizzare la memoria richiesta.
 */
@Getter
public class Node {
    private final int[] state;
    private final Node[] siblings;
    private int livingSiblings;

    /**
     * inizializza il nodo da uno stato preciso della partita.
     * @param state lo stato della partita, viene processato per eliminare le righe vuote della piramide
     * il numero corretto di figli viene calcolato automaticamente in base allo stato della partita
     */
    public Node(int[] state){
        this(state, CrackerKt.getPossibleMovesNumber(state));
    }

    /**
     * Inizializza ad uno stato preciso di partita il nodo
     * @param state lo stato della partita, viene processato per eliminare le righe vuote della piramide
     * @param nSiblings numero massimo di figli previsti, usare con cautela
     */
    public Node(int[] state, int nSiblings){
        this.state = reduce(state);
        this.siblings = new Node[nSiblings];
        for(int i = 0; i < nSiblings; i++){
            siblings[i] = null;
        }
        this.livingSiblings = 0;
    }

    /**
     * aggiunge un nodo come figlio al nodo corrente
     * @param node il nodo da aggiungere
     * @param ft the tree which is the node owned by, used to update some its values
     * @return true se c'è posto e lo aggiunge, false se fallisce l'aggiunta o il nodo è null
     */
    public boolean add(Node node, FakeTree ft){
        if(node == null) return false;
        Arrays.sort(node.state);
        if(livingSiblings + 1 <= siblings.length){
            siblings[livingSiblings++] = node;
            ft.getAlreadyComputedGames().add(ft.gamesPool.get(node.state));
            return true;
        }
        return false;
    }

    /**
     * rimuove un nodo dai figli
     * @param node il RIFERIMENTO al nodo da rimuovere
     * @param ft the tree which is the node owned by, used to update some of its values
     * @return true se trova il nodo da eliminare e lo elimina, false se non lo trova o è null
     */
    public boolean remove(Node node, FakeTree ft){
        if(node == null) return false;
        for(int i = 0; i < livingSiblings; i++){
            if(siblings[i] == node){
                siblings[i] = null;
                for(int j = i; j < livingSiblings-1; j++){
                    siblings[j] = siblings[j+1];
                }
                siblings[livingSiblings-1] = null;
                livingSiblings--;
                if(ft.searchNodeByState(node.state) == null) ft.getAlreadyComputedGames().remove(ft.gamesPool.get(node.state));
                return true;
            }
        }
        return false;
    }

    /**
     * riduce una partita eliminando le righe dove non ci sono più carte da togliere
     * @param state la partita
     * @return la partita semplificata
     */
    public static int[] reduce(int[] state){
        int n = 0;
        for (int k : state) {
            if (k != 0) n++;
        }
        if(n == state.length) return state;
        int[] nState = new int[n];
        n = 0;
        for (int j : state) {
            if (j != 0) {
                nState[n++] = j;
            }
        }
        return nState;
    }
}
