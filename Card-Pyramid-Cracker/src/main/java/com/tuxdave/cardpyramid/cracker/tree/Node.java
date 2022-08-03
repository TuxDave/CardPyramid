package com.tuxdave.cardpyramid.cracker.tree;

import lombok.Getter;
import lombok.Setter;

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
    @Setter
    private Node[] siblings;
    private int livingSiblings;

    /**
     * inizializza il nodo da uno stato preciso della partita.
     * @param state lo stato della partita, viene processato per eliminare le righe vuote della piramide
     * il numero corretto di figli viene calcolato automaticamente in base allo stato della partita
     */
    public Node(int[] state){
        this(state, 0); //TODO: inserire un corretto numero di figli, calcolabile tramite un algoritmo che verrà sviluppato succesivamente
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
     * @return true se c'è posto e lo aggiunge, false se fallisce l'aggiunta o il nodo è null
     */
    public boolean add(Node node){
        if(node == null) return false;
        if(livingSiblings + 1 <= siblings.length){
            siblings[livingSiblings++] = node;
            return true;
        }
        return false;
    }

    /**
     * rimuove un nodo dai figli
     * @param node il RIFERIMENTO al nodo da rimuovere
     * @return true se trova il nodo da eliminare e lo elimina, false se non lo trova o è null
     */
    public boolean remove(Node node){
        if(node == null) return false;
        for(int i = 0; i < livingSiblings; i++){
            if(siblings[i] == node){
                siblings[i] = null;
                for(int j = i; j < livingSiblings-1; j++){
                    siblings[j] = siblings[j+1];
                }
                siblings[livingSiblings-1] = null;
                livingSiblings--;
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
    static int[] reduce(int[] state){
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
