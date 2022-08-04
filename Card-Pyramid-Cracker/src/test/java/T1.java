import com.tuxdave.cardpyramid.cracker.tree.FakeTree;
import com.tuxdave.cardpyramid.cracker.tree.Node;

import java.util.Arrays;

public class T1 {
    public static void main(String[] args) {
        FakeTree ft = new FakeTree(new int[]{1,2,3,4,5});
        ft.getRoot().add(new Node(new int[]{1,2,3,4,4}), ft);
        ft.getRoot().add(new Node(new int[]{1,2,3,4,4}), ft);
        System.out.println(ft.getAlreadyComputedGames().size());
        for(int[] s : ft.getAlreadyComputedGames()){
            for(int k : s){
                System.out.print(k + " "); //TODO: Capire perchè
            }
            System.out.println();
        }
        System.out.println(ft.searchNodeByState(new int[]{1,2,4,4,3}));
    }
}
