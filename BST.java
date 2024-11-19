public class BST<K extends Comparable<K>, T> {

   class BSTNode<K extends Comparable<K>, T> {
      public K key;
      public T data;
      public BSTNode<K, T> left, right;
   
      public BSTNode(K ke, T value) {
         key = ke;
         data = value;
         left = right = null;
      }
   
      public BSTNode(K ke, T value, BSTNode<K, T> lef, BSTNode<K, T> rig) {
         key = ke;
         data = value;
         left = lef;
         right = rig;
      }
   }

   BSTNode<K, T> root;
   BSTNode<K, T> current;
   int coun;

   public BST() {
      root = current = null;
      coun = 0;
   }

   public int size() {
      return coun;
   }

   public boolean empty() {
      return root == null;
   }

   public void removeAll() {
      root = current = null;
      coun = 0;
   }

   public T retrieve() {
      T data = null;
      if (current != null)
         data = current.data;
      return data;
   }

   public void update(T element) {
      if (current != null)
         current.data = element;
   }

   public boolean find(K key) {
      BSTNode<K, T> par = root;
   
      if (empty())
         return false;
   
      while(par != null) {
         if(par.key.compareTo(key) == 0) {
            current = par;
            return true;
         }
         else if(key.compareTo(par.key) < 0)
            par = par.left;
         else
            par = par.right;
      }
      return false;
   }


   public boolean insert(K key, T data) {
      if (empty()) {
         current = root = new BSTNode<K, T>(key, data);
         coun++;
         return true;
      }
   
      BSTNode<K, T> parent = null;
      BSTNode<K, T> child = root;
   
      while (child != null) {
         if(child.key.compareTo(key) == 0) {
            return false;
         }
         else if(key.compareTo(child.key) < 0)
         {
            parent = child;
            child = child.left;
         }
         else
         {
            parent = child;
            child = child.right;
         }
      }
      if (key.compareTo(parent.key) < 0) {
         parent.left = new BSTNode<K, T>(key, data);
         current = parent.left;
      } else {
         parent.right = new BSTNode<K, T>(key, data);
         current = parent.right;
      }
      coun++;
      return true;
   }

   public boolean remove(K key) {
      boolean delete = false;
      root = remove_node(key, root, delete);
   
      if (current != null && current.key.compareTo(key) == 0)
         current = root;
      if (delete)
         coun--;
   
      return delete;
   }

   private BSTNode<K, T> remove_node(K key, BSTNode<K, T> pa, boolean flag) {
      BSTNode<K, T> q, child = null;
      if (pa == null)
         return null;
   
      if(key.compareTo(pa.key ) < 0)
         pa.left = remove_node(key, pa.left, flag); //go left
      else if(key.compareTo(pa.key) > 0)
         pa.right = remove_node(key, pa.right, flag); //go right
      else {
                    
         flag = true;
         if (pa.left != null && pa.right != null)
         { //two children
            q = find_least(pa.right);
            pa.key = q.key;
            pa.data = q.data;
            pa.right = remove_node(q.key, pa.right, flag);
         }
         else 
         {
            if (pa.right == null) //one child
               child = pa.left;
            else if (pa.left == null) //one child
               child = pa.right;
            return child;
         }
      }
      return pa;
   }    
   private BSTNode<K, T> find_least(BSTNode<K, T> pr) {
      if(pr == null)
         return null;
      while(pr.left != null){
          pr=pr.left;
      }
      return pr;
   }

   public void Traverse() {
      if (root != null)
         traverse_Trees(root);
   }

   private void traverse_Trees(BSTNode<K, T> node) {
      if (node == null)
         return;
   
      traverse_Trees(node.left);
      System.out.println(node.data);
      traverse_Trees(node.right);
   }
  

   public void Traverse_Tr() {
      if (root != null)
         traverse_Trees(root);
   }
   public void viewData(){
   
   if(root != null)
      printAll_data(root);
      }
   

   public void printAll_data(BSTNode<K, T> node) {
      if (node == null)
         return;
   
      printAll_data(node.left);
      System.out.println(node.key);

   
      if (node.data instanceof Term) {
        System.out.print("the document it's:");
                boolean [] documen = ((Term)node.data).getDocs();
                for (int x= 0 ;x < 50 ; x++)
                    if (documen[x])
                        System.out.print( " " +x+ " " );
                System.out.println("");
            }
            printAll_data(node.right);
        }
}   /*
public class BST<K extends Comparable<K>, T>{

   class BSTNode<K extends Comparable<K>, T> {
        public K key;
        public T data;
        public BSTNode<K,T> left, right;

        /** Creates a new instance of BSTNode
        public BSTNode(K ke, T value) {
                key = ke;
                data = value;
                left = right = null;
        }

        public BSTNode(K ke, T value, BSTNode<K,T> lef, BSTNode<K,T> rig) {
                key = ke;
                data = value;
                left = lef;
                right = rig;
        }
    }
    
        BSTNode<K, T> root; 
        BSTNode<K, T > current;
        int coun;
                
        public BST()
        {
            root = current = null;
            coun = 0;
        }
        
        // Returns the number of elements in the map.
        public int size()
        {
            return coun;
        }

        // Return true if the tree is empty. Must be O(1).
        public boolean empty()
        {
            return root == null;
        }

        // Removes all elements in the map.
        public void removeAll() //changed
        {
            root = current = null;
            coun = 0;
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
        public void update(T element)
        {
            if (current != null)
                current.data = element;
        }

        // Search for element with key k and make it the current element if it exists.
        // If the element does not exist the current is unchanged and false is returned.
        // This method must be O(log(n)) in average.
        public boolean find(K key)
        {
            BSTNode<K,T> par = root;

            if(empty())
                    return false;

            while(par != null) {
                    if(par.key.compareTo(key) == 0) {
                            current = par;
                            return true;
                    }
                    else if(key.compareTo(par.key) < 0)
                            par = par.left;
                    else
                            par = par.right;
            }
            return false;
        }


        // Insert a new element if does not exist and return true. The current points to
        // the new element. If the element already exists, current does not change and
        // false is returned. This method must be O(log(n)) in average.
        public boolean insert(K key, T data)
        {

            if(empty())
            {
                current = root = new BSTNode <K, T> (key, data);
                coun ++;
                return true;
            }
            BSTNode<K,T> parent = null; //changed
            BSTNode<K,T> child  = root;
            
            while(child != null) {
                    if(child.key.compareTo(key) == 0) {
                            return false;
                    }
                    else if(key.compareTo(child.key) < 0)
                    {
                        parent = child;
                        child = child.left;
                    }
                    else
                    {
                        parent = child;
                        child = child.right;
                    }
            }
           
            if(key.compareTo(parent.key) < 0)
            {
                parent.left = new BSTNode <K, T> ( key, data);
                current = parent.left;
            }
            
            else
            {
                parent.right = new BSTNode <K, T> ( key, data);
                current = parent.right;
            }
            coun++;
            return true;
        }

        // Remove the element with key k if it exists and return true. If the element
        // does not exist false is returned (the position of current is unspecified
        // after calling this method). This method must be O(log(n)) in average.
                
      public boolean remove(K key)
        {
           boolean delete = false;

           // boolean delete = new boolean(false);
            BSTNode<K,T> p;
            
            p = remove_node(key, root, delete);
            root = p;
            
            if (current.key.compareTo(key) == 0)
                current = root;
            if (delete)
                coun--;
            
            return delete;
        }    
        private BSTNode<K,T> remove_node(K key, BSTNode<K,T> pa, boolean bool) //changed
        {
            BSTNode<K,T> q, child = null;
            if(pa == null)
                    return null;
            if(key.compareTo(pa.key ) < 0)
                    pa.left = remove_node(key, pa.left, bool); //go left
            else if(key.compareTo(pa.key) > 0)
                    pa.right = remove_node(key, pa.right, bool); //go right
            else {
                    
                    bool = true;
                    if (pa.left != null && pa.right != null)
                    { //two children
                            q = find_least(pa.right);
                            pa.key = q.key;
                            pa.data = q.data;
                            pa.right = remove_node(q.key, pa.right, bool);
                    }
                    else 
                    {
                            if (pa.right == null) //one child
                                    child = pa.left;
                            else if (pa.left == null) //one child
                                    child = pa.right;
                            return child;
                    }
                }
            return pa;
        }
        private BSTNode<K,T> find_least(BSTNode<K,T> pr)//change
        {
            if(pr == null)
                    return null;

            while(pr.left != null){
                    pr = pr.left;
            }
            return pr;
        }
        
        public void Traverse()
        {
            if (root != null)
                traverse_Trees(root);
        }
        
        private void traverse_Trees(BSTNode<K,T> node)
        {
            if (node == null)
                return;
            traverse_Trees(node.left);
            System.out.println(node.data);
            traverse_Trees(node.right);
            
        }
        //=========================================================================== 
       public void Traverse_Tr()
        {
            if (root != null)
                traverse_Trees(root);
        }
        
        public void traverseAll_T(BSTNode<K,T> node)
        {
            if (node == null)
                return;
            traverseAll_T(node.left);
            if (node.data instanceof BST )
            {
                System.out.println( "this is node key: "+ node.key);
                ((BST <String,Rank>)node.data).Traverse();
            }
            else
                System.out.println(node.data);
            
            traverseAll_T(node.right);
            
        }

}*/