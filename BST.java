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
         pa.left = remove_node(key, pa.left, flag); 
      else if(key.compareTo(pa.key) > 0)
         pa.right = remove_node(key, pa.right, flag); 
      else {
                    
         flag = true;
         if (pa.left != null && pa.right != null)
         { 
            q = find_least(pa.right);
            pa.key = q.key;
            pa.data = q.data;
            pa.right = remove_node(q.key, pa.right, flag);
         }
         else 
         {
            if (pa.right == null) 
               child = pa.left;
            else if (pa.left == null) 
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
}
