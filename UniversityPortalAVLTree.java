class Node {
    int id, height;
    Node left, right;

    Node(int id) {
        this.id = id;
        height = 1;
    }
}

public class UniversityPortalAVLTree {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int id) {

        if (node == null)
            return new Node(id);

        if (id < node.id)
            node.left = insert(node.left, id);
        else if (id > node.id)
            node.right = insert(node.right, id);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && id < node.left.id)
            return rightRotate(node);

        // RR
        if (balance < -1 && id > node.right.id)
            return leftRotate(node);

        // LR
        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(Node node, int id) {

        if (node == null)
            return false;

        if (node.id == id)
            return true;

        if (id < node.id)
            return search(node.left, id);

        return search(node.right, id);
    }

    void inorder(Node node) {

        if (node != null) {
            inorder(node.left);
            System.out.print(node.id + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        UniversityPortalAVLTree tree =
                new UniversityPortalAVLTree();

        int[] students = {
                1010, 1020, 1025, 1030, 1035,
                1040, 1050, 1055, 1060,
                1065, 1070, 1075, 1080
        };

        System.out.println(
                "UNIVERSITY PORTAL AVL STUDENT INDEX");
        System.out.println(
                "----------------------------------");

        System.out.println("\nStudent IDs Inserted:");

        for (int id : students) {
            System.out.print(id + " ");
            tree.root = tree.insert(tree.root, id);
        }

        System.out.println("\n\nFINAL AVL TREE (In-order):");
        tree.inorder(tree.root);

        System.out.println(
                "\n\nTree Height: " +
                        tree.height(tree.root));

        System.out.println(
                "\nSearching Student ID 1030...");

        if (tree.search(tree.root, 1030))
            System.out.println("Student Found");
        else
            System.out.println("Student Not Found");

        System.out.println(
                "\nSearching Student ID 1090...");

        if (tree.search(tree.root, 1090))
            System.out.println("Student Found");
        else
            System.out.println("Student Not Found");

        System.out.println(
                "\nTime Complexity:");
        System.out.println(
                "Insert : O(log n)");
        System.out.println(
                "Search : O(log n)");
        System.out.println(
                "Delete : O(log n)");
    }
}