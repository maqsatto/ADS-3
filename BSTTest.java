import models.*;

public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        
        tree.put(50, "Fifty");
        tree.put(30, "Thirty");
        tree.put(70, "Seventy");
        tree.put(20, "Twenty");
        tree.put(40, "Forty");
        tree.put(60, "Sixty");
        tree.put(80, "Eighty");

        System.out.println("Size: " + tree.size());
;
        for (BST<Integer, String>.Entry elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        
        System.out.println("\nDelete 30");
        tree.delete(30);

        System.out.println("New Size " + tree.size());

        for (BST<Integer, String>.Entry elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
