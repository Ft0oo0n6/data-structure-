/**
 *
 * @author ftoon
 */
public class AVLTree <K extends Comparable<K>, V> {
    class AVLNode<K extends Comparable<K>, V> {
        public K id; // Key of the node (e.g., student ID)
        public V value; // Data associated with the key (e.g., student info)
        AVLNode< K, V> parent; // Pointer to the parent node
        AVLNode< K, V> leftChild; // Pointer to the left child
        AVLNode< K, V> rightChild; // Pointer to the right child
        int balanceFactor; // Balance factor of the node

        // Default constructor
        public AVLNode() {
            this.id = null;
            this.value = null;
            this.parent = this.leftChild = this.rightChild = null;
            this.balanceFactor = 0;
        }

        // Constructor with key and value
        public AVLNode(K id, V value) {
            this.id = id;
            this.value = value;
            this.parent = this.leftChild = this.rightChild = null;
            this.balanceFactor = 0;
        }

        // Constructor with full parameters
        public AVLNode(K id, V value, AVLNode<K, V> parent, AVLNode<K, V> leftChild, AVLNode<K, V> rightChild) {
            this.id = id;
            this.value = value;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.balanceFactor = 0;
        }

        public AVLNode<K, V> getLeftChild() {
            return leftChild;
        }

        public AVLNode<K, V> getRightChild() {
            return rightChild;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "AVLNode { id=" + id + ", value=" + value + " }";
        }
    }

    private AVLNode<K, V> root;
    private AVLNode<K, V> currentNode;
    private int nodeCount;

    // Constructor for AVLTree
    public AVLTree() {
        root = currentNode = null;
        nodeCount = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Removes all nodes in the tree
    public void clear() {
        root = currentNode = null;
        nodeCount = 0;
    }

    // Return the value of the current node
    public V getCurrentValue() {
        return currentNode != null ? currentNode.value : null;
    }
    
    
    public int getSize() {
        return nodeCount;
    }

    // Update the value of the current node
    public void updateCurrentValue(V newValue) {
        if (currentNode != null) currentNode.value = newValue;
    }


    // Method to search for a node by key
    public boolean find(K id) {
        return searchHelper(this.root, id) != null;
    }

    // Update balance factors and re-balance if needed
    private void updateBalance(AVLNode<K, V> node) {
        if (node.balanceFactor < -1 || node.balanceFactor > 1) {
            rebalance(node);
            return;
        }
        if (node.parent != null) {
            if (node == node.parent.leftChild) {
                node.parent.balanceFactor -= 1;
            } else {
                node.parent.balanceFactor += 1;
            }
            if (node.parent.balanceFactor != 0) {
                updateBalance(node.parent);
            }
        }
    }
    
        // Helper method to search for a key in the tree
    private V searchHelper(AVLNode<K, V> node, K id) {
        if (node == null) return null;
        if (node.id.compareTo(id) == 0) {
            currentNode = node;
            return node.value;
        } else if (node.id.compareTo(id) > 0) {
            return searchHelper(node.leftChild, id);
        } else {
            return searchHelper(node.rightChild, id);
        }
    }

    // Rebalance the AVL tree
    private void rebalance(AVLNode<K, V> node) {
        if (node.balanceFactor > 1) {
            if (node.rightChild.balanceFactor < 0) {
                rightRotate(node.rightChild);
            }
            leftRotate(node);
        } else if (node.balanceFactor < -1) {
            if (node.leftChild.balanceFactor > 0) {
                leftRotate(node.leftChild);
            }
            rightRotate(node);
        }
    }

    // Left rotation
    private void leftRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = x.rightChild;
        x.rightChild = y.leftChild;
        if (y.leftChild != null) y.leftChild.parent = x;
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.leftChild) {
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;
        x.balanceFactor = x.balanceFactor - 1 - Math.max(0, y.balanceFactor);
        y.balanceFactor = y.balanceFactor - 1 + Math.min(0, x.balanceFactor);
    }

    // Right rotation
    private void rightRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = x.leftChild;
        x.leftChild = y.rightChild;
        if (y.rightChild != null) y.rightChild.parent = x;
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.rightChild) {
            x.parent.rightChild = y;
        } else {
            x.parent.leftChild = y;
        }
        y.rightChild = x;
        x.parent = y;
        x.balanceFactor = x.balanceFactor + 1 - Math.min(0, y.balanceFactor);
        y.balanceFactor = y.balanceFactor + 1 + Math.max(0, x.balanceFactor);
    }

    // Insert a new node
    public boolean insert(K id, V value) {
        AVLNode<K, V> newNode = new AVLNode<>(id, value);
        AVLNode<K, V> parent = null;
        AVLNode<K, V> current = this.root;

        while (current != null) {
            parent = current;
            if (newNode.id.compareTo(current.id) == 0) return false;
            else if (newNode.id.compareTo(current.id) < 0) current = current.leftChild;
            else current = current.rightChild;
        }

        newNode.parent = parent;
        if (parent == null) {
            root = currentNode = newNode;
        } else if (newNode.id.compareTo(parent.id) < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        nodeCount++;
        updateBalance(newNode);
        return true;
    }

    // Method to traverse and print the AVL Tree (In-Order)
    public void traverse() {
        if (root != null) traverseInOrder(root);
    }

    private void traverseInOrder(AVLNode<K, V> node) {
        if (node == null) return;
        traverseInOrder(node.leftChild);
        System.out.println(node.value);
        traverseInOrder(node.rightChild);
    }

    // Nested AVL Tree Traversal (if the value is another AVL Tree)
    public void traverseNestedTree() {
        if (root != null) traverseNested(root);
    }

    private void traverseNested(AVLNode<K, V> node) {
        if (node == null) return;
        traverseNested(node.leftChild);
        if (node.getValue() instanceof AVLTree) {
            System.out.println("Node ID: " + node.id);
            ((AVLTree< String , ? >) node.getValue()).traverse();
        } else {
            System.out.println(node.value);
        }
        traverseNested(node.rightChild);
    }
}
