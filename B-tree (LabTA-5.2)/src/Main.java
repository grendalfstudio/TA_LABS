import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Btree btree = new Btree();
        btree.add(1);
        btree.add(2);
        btree.add(3);
        btree.add(4);
        btree.add(5);
        btree.add(6);
        btree.add(7);
        btree.add(8);
        btree.add(9);
        btree.add(10);
        btree.add(11);
        btree.add(12);
        btree.add(13);
        btree.add(14);
    }
}
// Node class
class Node {
    public static final int TFactor = 2;  // Parameter of B-tree

    public int[] keys;              // Massive of keys
    public Node[] children;         // Massive of pointers to the child subtrees
    public Node parent;             // Pointer to the parent node
    public int size;                // Number of keys (number of children is 'size' + 1)
    public boolean isLeaf;          // Shows is it a leaf

    Node() {
        keys = new int[2 * TFactor - 1];
        children = new Node[2 * TFactor];
        size = 0;
        isLeaf = false;
    }
}
// B-tree class
class Btree {
    private Node root;
    // Splits the node into two
    private Node splitNode(Node current, int key) {
        Node leftNode = new Node();
        Node rightNode = new Node();
        Node parent = current.parent;
        int middle = current.keys[Node.TFactor - 1];
        // Filling 'leftNode'
        leftNode.isLeaf = current.isLeaf;
        leftNode.size = Node.TFactor - 1;
        leftNode.parent = parent;
        for (int i = 0; i < Node.TFactor - 1; i++)
        {
            leftNode.keys[i] = current.keys[i];
            leftNode.children[i] = current.children[i];
        }
        leftNode.children[Node.TFactor - 1] = current.children[Node.TFactor - 1];
        // Filling 'rightNode'
        rightNode.isLeaf = current.isLeaf;
        rightNode.size = Node.TFactor - 1;
        rightNode.parent = parent;
        for (int i = Node.TFactor; i < 2 * Node.TFactor - 1; i++)
        {
            rightNode.keys[i - Node.TFactor] = current.keys[i];
            rightNode.children[i - Node.TFactor] = current.children[i];
        }
        rightNode.children[Node.TFactor - 1] = current.children[2 * Node.TFactor - 1];
        // Filling the parent
        parent.size = parent.size + 1;
        parent.children[parent.size] = parent.children[parent.size - 1];	// It relocates in any case
        int i;		// Iterator to the elements in the 'parent'
        for (i = parent.size - 1; i >= 1; i--)
        {
            if (middle >= parent.keys[i - 1])		// If we found a place where 'middle' should be placed
            {
                break;								// Go to the insertion of the 'middle'
            }
            else
            {
                parent.keys[i] = parent.keys[i - 1];			// Relocating keys
                parent.children[i] = parent.children[i - 1];	// Relocating children
            }
        }
        parent.keys[i] = middle;
        parent.children[i] = leftNode;
        parent.children[i + 1] = rightNode;	// Replaces the pointer to the 'current'
        // Step down
        return (key < middle ? leftNode : rightNode);
    }

    public Btree() {
        root = new Node();
        root.isLeaf = true;	/// root is a leaf at start
    }
    public void add(int key) {
        Node current = root;		//Iterator to the B-tree nodes
        // First checks the 'root' whether it is full (special case)
        if (current.size == 2 * Node.TFactor - 1) {
            Node newRoot = new Node();
            newRoot.children[0] = root;
            root.parent = newRoot;
            current = splitNode(current, key);
            root = newRoot;
        }
        // Going down the B-tree
        while (current.isLeaf == false)
        {
            if (current.size == 2 * Node.TFactor - 1){
                current = splitNode(current, key);
            }
            else {
                int i;		// Iterator to the elements in the 'current'
                for (i = 0; i < current.size; i++)			// Searching for the necessary child
                {
                    if (key < current.keys[i])
                    {
                        break;
                    }
                }
                current = current.children[i];
            }
        }
        // Pushing and arranging
        if (current.size == 2 * Node.TFactor - 1) {
            current = splitNode(current, key);
        }
        current.keys[current.size] = key;
        current.size++;
        sort(current);
    }
    public Node sort(Node node) {
        for (int i = 0; i < node.size; i++) {
            int Min = 100_000_000;
            for (int j = i; j < node.size; j++) {
                if(node.keys[j] < Min){
                    Min = node.keys[j];
                }
            }
            node.keys[i] = Min;
        }
        return node;
    }
    public boolean less(int first, int second)
    {
        return (first <= second ? true : false);
    }
};