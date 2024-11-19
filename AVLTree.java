public class AVLTree<K extends Comparable<K>, T>{

        /*==================================================================
            class BSTMapNode
        ==================================================================*/
        class AVLNode<K extends Comparable<K>, T> {
                public K key;
                public T data;
                AVLNode<K,T> parent; // pointer to the parent
                AVLNode<K,T> leftchild; // pointer to left child //changed
                AVLNode<K,T> rightchild; // pointer to right child//changed
                int balanceF; // balance factor of the node //changed

                    /** Creates a new instance of BTNode */
                    public AVLNode() {
                            this.key = null;  
                            this.data = null;
                            this.parent = this.leftchild = this.rightchild = null;
                            this.balanceF = 0;
                    }

                    public AVLNode(K key, T data) {
                            this.key = key  ;  
                            this.data = data;
                            this.parent = this.leftchild = this.rightchild = null;
                            this.balanceF = 0;
                    }

                    public AVLNode(K key, T data, AVLNode<K,T> p, AVLNode<K,T> l, AVLNode<K,T> r){
                            this.key = key  ;  
                            this.data = data;
                            leftchild = l;
                            rightchild = r;
                            parent = p;
                            balanceF =0;
                    }

                    public AVLNode<K,T> getLeftchild()
                    {
                        return leftchild;
                    }

                    public AVLNode<K,T> getRightchild()
                    {
                        return rightchild;
                    }

                    public T getData()
                    {
                        return data;
                    }
                    
                    @Override
                    public String toString() {
                        return "AVL Node{" + "key=" + key + ", data =" + data + '}';
                    }
            }

        //=============================================================================    
        private AVLNode<K,T> root;
        private AVLNode<K,T>  current;
        private int counter;
        
        public AVLTree() {
                root = current = null;
                counter = 0;
        }

        public boolean empty() {
            return root == null;
        }

        public int size() {
            return counter;
        }


         // Removes all elements in the map.
        public void clear()
        {
            root = current = null;
            counter = 0;
        }

        // Return the key and data of the current element
        public T retrieve()
        {
            T data =null;
            if (current != null)
                data = current.data;
            return data;
        }

        // Update the data of current element.
        public void update(T e)
        {
            if (current != null)
                current.data = e;
        }

        //searches for the key in the AVL, returns the data or null (if not found).
        private T searchTreeHelper(AVLNode<K,T> node, K key) {
                   // Place your code here\\
                    if (node == null)
                        return null;
                    else if (node.key.compareTo(key) ==0) 
                    {
                        current = node;
                        return node.data;
                    } 
                    else if (node.key.compareTo(key) >0)
                         return searchTreeHelper(node.leftchild, key);
                    else
                         return searchTreeHelper(node.rightchild, key);
        }
        
        // update the balance factor the node
       /* private void updateBalance(AVLNode<K,T> node) {
                if (node.balanceF < -1 || node.balanceF > 1) {
                        rebalance(node);
                        return;
                }

                if (node.parent != null) {
                        if (node == node.parent.leftchild) {
                                node.parent.balanceF -= 1;
                        } 

                        if (node == node.parent.rightchild) {
                                node.parent.balanceF += 1;
                        }

                        if (node.parent.balanceF != 0) {
                                updateBalance(node.parent);
                        }
                }
        }*/
        private void updateBalance(AVLNode<K, T> node) { //changed to while loop
    while (node != null) {
        if (node.balanceF < -1 || node.balanceF > 1) {
            rebalance(node);
            return; // Stop after rebalancing
        }

        // Update the parent's balance factor
        if (node.parent != null) {
            if (node == node.parent.leftchild) {
                node.parent.balanceF -= 1;
            } else if (node == node.parent.rightchild) {
                node.parent.balanceF += 1;
            }

            // If the parent's balance factor is zero, stop the loop
            if (node.parent.balanceF == 0) {
                break;
            }
        }

        // Move up to the parent node
        node = node.parent;
    }
}


        // rebalance the tree
        void rebalance(AVLNode<K,T> node) {
                if (node.balanceF > 0) {
                        if (node.rightchild.balanceF < 0) {
                                rightRotate(node.rightchild);
                                leftRotate(node);
                        } else {
                                leftRotate(node);
                        }
                } else if (node.balanceF < 0) {
                        if (node.leftchild.balanceF > 0) {
                                leftRotate(node.leftchild);
                                rightRotate(node);
                        } else {
                                rightRotate(node);
                        }
                }
        }

        public boolean find(K key) {
                T data = searchTreeHelper(this.root, key);
                if ( data != null)
                    return true;
                return false;
        }

        // rotate left at node x
        void leftRotate(AVLNode<K,T> x) {
            AVLNode<K,T> y = x.rightchild;
            x.rightchild = y.leftchild;
            if (y.leftchild != null) {
                    y.leftchild.parent = x;
            }
            
            y.parent = x.parent;
            if (x.parent == null) {
                    this.root = y;
            } else if (x == x.parent.leftchild) {
                    x.parent.leftchild = y;
            } else {
                    x.parent.rightchild = y;
            }
            y.leftchild = x;
            x.parent = y;

            // update the balance factor
            x.balanceF = x.balanceF - 1 - Math.max(0, y.balanceF);
            y.balanceF = y.balanceF - 1 + Math.min(0, x.balanceF);
        }

        // rotate right at node x
        void rightRotate(AVLNode<K,T> x) {
                AVLNode<K,T> y = x.leftchild;
                x.leftchild = y.rightchild;
                if (y.rightchild != null) {
                        y.rightchild.parent = x;
                }
                y.parent = x.parent;
                if (x.parent == null) {
                        this.root = y;
                } else if (x == x.parent.rightchild) {
                        x.parent.rightchild = y;
                } else {
                        x.parent.leftchild = y;
                }
                y.rightchild = x;
                x.parent = y;

                // update the balance factor
                x.balanceF = x.balanceF + 1 - Math.min(0, y.balanceF);
                y.balanceF = y.balanceF + 1 + Math.max(0, x.balanceF);
        }

        
        
        public boolean insert(K key, T data) {
            AVLNode<K,T> node = new AVLNode<K,T>(key, data);

            AVLNode<K,T> p = null;
            AVLNode<K,T> currentI = this.root; //current of insert

            while (currentI != null) {
                    p = currentI;
                    if (node.key.compareTo(currentI.key) ==0) {
                            return false;
                    }else if (node.key.compareTo(currentI.key) <0 ) {
                            currentI = currentI.leftchild;
                    } else {
                            currentI = currentI.rightchild;
                    }
            }
            // p  is parent of current
            node.parent = p;
            if (p == null) {
                    root = node;
                    currentI = node;
            } else if (node.key.compareTo(p.key) < 0 ) {
                    p.leftchild = node;
            } else {
                    p.rightchild = node;
            }
            counter ++;

            //  re-balance the node if necessary
            updateBalance(node);
            return true;        
        }
        
    public boolean remove(K key) {
        K k1 = key;
        AVLNode<K,T>  p = root;
        AVLNode<K,T>  q = null; // Parent of p

        while (p != null) 
        {
                if (k1.compareTo(p.key) <0)
                {
                    q =p;
                    p = p.leftchild;
                } 
                else if (k1.compareTo(p.key) >0)
                {    
                    q = p;
                    p = p.rightchild;
                }
                else 
                { // Found the key
                    // Check the three cases
                    if ((p.leftchild != null) && (p.rightchild != null)) 
                    { 
                        // Case 3: two children
                        // Search for the min in the right subtree
                        AVLNode<K,T> min = p.rightchild;
                        q = p;
                        while (min.leftchild != null) 
                        {
                            q = min;
                            min = min.leftchild;
                        }
                        p.key = min.key;
                        p.data = min.data;
                        k1 = min.key;
                        p = min;
                        // Now fall back to either case 1 or 2
                    }
                    // The subtree rooted at p will change here
                    if (p.leftchild != null) 
                    { 
                        // One child
                        p = p.leftchild;
                    } 
                    else 
                    { 
                        // One or no children
                        p = p.rightchild;
                    }
                    if (q == null)
                    { 
                        // No parent for p, root must change
                        root = p;
                        this.updateBalance(p);
                    } 
                    else 
                    {
                        if (k1.compareTo(q.key) <0)
                            q.leftchild = p;
                        else 
                            q.rightchild = p;
                        this.updateBalance(q);
                    }
                    counter--;
                    current = root;
                    return true;    
            } 
        } // end while (p != null) 
        return false;
    }

       //============================================================================
       public void Traverse()
        {
            if (root != null)
                traverseTree(root);
        }
        
        private void traverseTree (AVLNode<K,T> node  )
        {
            if (node == null)
                return;
            traverseTree( node.leftchild);
            System.out.println(node.data);
            traverseTree( node.rightchild);
            
        }

        
       //=========================================================================== 
       public void TraverseT()
        {
            if (root != null)
                traverseTreeT(root);
        }
        
        private void traverseTreeT (AVLNode<K,T> node)
        {
            if (node == null)
                return;
            traverseTreeT( node.leftchild );
            if (node.getData() instanceof AVLTree )
            {
                System.out.println( "Node key ==== "+ node.key);
                ((AVLTree <String,Rank>) node.getData()).Traverse();
            }
            else
                System.out.println(node.data);
            
            traverseTreeT( node.rightchild);
            
        }
        
       //=========================================================================== 
       public void PrintData()
        {
            if (root != null)
                PrintData_(root);
        }
        
        private void PrintData_ (AVLNode<K,T> node)
        {
            if (node == null)
                return;
            PrintData_( node.leftchild );
           
           System.out.print(node.key);
            if (node.getData() instanceof Term )
            {
                System.out.print("   docs: ");
                boolean [] docs = ((Term) node.data).getDocs();
                for ( int i  = 0 ; i < 50 ; i++)
                    if ( docs[i])
                        System.out.print( " " + i + " " );
                System.out.println("");
            }
                
            
            PrintData_( node.rightchild);
            
        }

        
        
}