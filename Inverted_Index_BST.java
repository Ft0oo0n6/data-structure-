/*
Inverted Index with BSTs: Enhance the implementation of Inverted Index by using BSTs 
instead of Lists 
Create the BST Inverted Index which will enhance the search for items whether in 
terms or documents IDs. . 
 */

/**
 *
 * @author Manal Alhihi
 */

public class Inverted_Index_BST {
            BST < String , Term> invertedindexBST; 
            int c = 0;//count

            public Inverted_Index_BST() {
                invertedindexBST = new BST< String , Term> ();//?
            }

            public int size()
            {
                return invertedindexBST.count;
            }
            
            public boolean addNew (int dID, String wrd)
            {
               if (invertedindexBST.empty())
               {
                   c ++;
                   Term trm = new Term ();
                   trm.setVocabulary(new Vocabulary (wrd));
                   trm.add_dID(dID);
                   invertedindexBST.insert(wrd, trm);
                   return true;
               }
               else
               {
                    if (invertedindexBST.find(wrd))
                    {
                        Term trm = this.invertedindexBST.retrieve();
                        trm.add_dID(dID);
                        invertedindexBST.update(trm);
                        return false;
                        
                    }
                    
                   c ++;
                   Term trm = new Term ();
                   trm.setVocabulary(new Vocabulary (wrd));
                   trm.add_dID(dID);
                   invertedindexBST.insert(wrd, trm);
                    return true;
           }
        }

        public boolean found(String wrd)
        {
               return invertedindexBST.find(wrd);
        }
        
        public void print()
        {
            invertedindexBST.Traverse();//in past file it was TraverseT!!??
        }
       //=====================================================================
        public boolean [] AND_OR_Function (String string )
        {
            if (! string.contains(" OR ") && ! string.contains(" AND "))
            {
                boolean [] R = new boolean[50];//r1
                string = string.toLowerCase().trim();
            
                if (this.found (string))
                    R =  this.invertedindexBST.retrieve().getDocs();
                return R;
            }
            
            else if (string.contains(" OR ") && string.contains(" AND "))
            {
                String [] AND_ORs = string.split(" OR ");
                boolean []  R = AND_Function (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] r =AND_Function (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        R [j] = R[j] || r[j];
                }
                return R;
            }
            
            else  if (string.contains(" AND "))
                return AND_Function (string);
            
            return OR_Function (string);
        }
        
        public boolean [] AND_Function (String string)
        {
            String [] ANDs = string.split(" AND ");
            boolean [] B = new boolean [50];
            
            if (this.found (ANDs[0].toLowerCase().trim()))
                B = this.invertedindexBST.retrieve().getDocs();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] b = new boolean [50];
                if (this.found (ANDs[i].toLowerCase().trim()))
                    b = this.invertedindexBST.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    B [j] = B[j] && b[j];
            }
            return B;
        }
        
        public boolean [] OR_Function (String str)
        {
            String [] ORs = str.split(" OR ");
            boolean [] B = new boolean [50];
            
            if (this.found (ORs[0].toLowerCase().trim()))
                B = this.invertedindexBST.retrieve().getDocs();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] b = new boolean [50];
                if (this.found (ORs[i].toLowerCase().trim()))
                    b = this.invertedindexBST.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    B [j] = B[j] || b[j];
               
            }
            return B;
        }
   
    
}
