package com.tuxdave.cardpyramid.cracker.tree;

import com.tuxdave.cardpyramid.cracker.utils.ArrayPool;
import lombok.Getter;

import java.util.*;

/**
 * classe che si occupa di memorizzare l'albero delle possibilità del gioco.<br>
 * le logiche che lo popoleranno saranno separate da esso, come anche le logiche di analisi dei risultati.
 */
@Getter
public class FakeTree {
    private Node root;
    private Set<int[]> alreadyComputedGames;

    public ArrayPool gamesPool = new ArrayPool();

    /**
     * @param game lo stato della partita da cui memorizzare l'albero delle possibilità
     */
    public FakeTree(int[] game){
        root = new Node(Node.reduce(game));
        alreadyComputedGames = new HashSet<>();
        alreadyComputedGames.add(root.getState());
    }

    /**
     * metodo di ricerca di un nodo (riferimento al nodo) a partire da uno stato di partita.<br>
     * è una ricerca intelligente in quanto considera 2 partite uguali se hanno lo stesso numero di livelli e lo stesso
     * numero di carte per livello, non importa l'ordine dei livelli. ES: una partita 2-1-2 è uguale ad una 2-2-1
     * @param state lo stato della partita
     * @return il riferimento al nodo trovato o eventualmente null se non esiste
     */
    public Node searchNodeByState(int[] state){
        return searchNodeByState(state, root);
    }

    public Node searchNodeByState(int[] state, Node start){
        Arrays.sort(state);
        state = gamesPool.get(state);
        if(!alreadyComputedGames.contains(state)) return null;
        for(Node n : root.getSiblings()){
            if(Arrays.equals(n.getState(),  state)){
                return n;
            }else {
                Node ret = searchNodeByState(state, n);
                if (ret != null) return ret;
            }
        }
        return null;
    }
}
