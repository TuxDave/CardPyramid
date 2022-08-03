public class T1 {
    public static void main(String[] args) {
        int livingSiblings = 5;
        Integer[] siblings = new Integer[]{1,2,3,4,5};
        Integer node = siblings[2];
        for(int i = 0; i < livingSiblings; i++){
            if(siblings[i] == node){
                siblings[i] = null;
                for(int j = i; j < livingSiblings-1; j++){
                    siblings[j] = siblings[j+1];
                }
                siblings[livingSiblings-1] = null;
                livingSiblings--;
            }
        }
        node = siblings[2];
        for(int i = 0; i < livingSiblings; i++){
            if(siblings[i] == node){
                siblings[i] = null;
                for(int j = i; j < livingSiblings-1; j++){
                    siblings[j] = siblings[j+1];
                }
                siblings[livingSiblings-1] = null;
                livingSiblings--;
            }
        }
        System.out.println(siblings[4]);
    }
}
