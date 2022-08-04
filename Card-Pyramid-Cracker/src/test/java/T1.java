import com.tuxdave.cardpyramid.cracker.CrackerKt;
import com.tuxdave.cardpyramid.cracker.tree.FakeTree;
import com.tuxdave.cardpyramid.cracker.tree.UiKt;
import com.tuxdave.cardpyramid.cracker.tree.Node;

import java.util.Arrays;

public class T1 {
    public static void main(String[] args) {
        FakeTree tree = CrackerKt.crack(3);
        System.out.println(UiKt.display(tree));
    }
}
