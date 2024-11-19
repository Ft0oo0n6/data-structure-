
public class Inverted_Index_BST {
            BST < String , Term> DocumentIndexBST; 
      

            public Inverted_Index_BST() {
                DocumentIndexBST = new BST< String , Term> ();
            }

            public int size()
            {
                return DocumentIndexBST.size();
            }
            
            public boolean adddocument(int docID, String word)
            {Term term;
               if (DocumentIndexBST.empty())
               {
               
                    term = new Term ();
                   term.setVocabulary(new Vocabulary (word));
                   term.add_documentID(docID);
                   DocumentIndexBST.insert(word, term);
                   return true;
               }
               else
               {
                    if (DocumentIndexBST.find(word))
                    {
                         term = this.DocumentIndexBST.retrieve();
                        term.add_documentID(docID);
                        DocumentIndexBST.update(term);
                        return false;
                        
                    }
                    
                
                   term = new Term ();
                   term.setVocabulary(new Vocabulary (word));
                   term.add_documentID(docID);
                   DocumentIndexBST.insert(word, term);
                    return true;
           }
        }

        public boolean found(String word)
        {
               return DocumentIndexBST.find(word);
        }
        
        public void printDocument()
        {
            DocumentIndexBST.Traverse();
        }

        public boolean [] AND_OR_Function (String string )
        {
        boolean [] result;
            if (! string.contains(" OR ") && ! string.contains(" AND "))
            {
                result = new boolean[50];
                string = string.toLowerCase().trim();
            
                if (this.found (string))
                    result =  this.DocumentIndexBST.retrieve().getDocs();
                return result;
            }
            
            else if (string.contains(" OR ") && string.contains(" AND "))
            {
                String [] AND_ORs = string.split(" OR ");
              result = AND_Function (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] tempResult =AND_Function (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        result [j] = result[j] || tempResult[j];
                }
                return result;
            }
            
               return string.contains(" AND ") ? AND_Function(string) : OR_Function(string);        }
        
        public boolean [] AND_Function (String string)
        {
            String [] ANDs = string.split(" AND ");
            boolean [] result = new boolean [50];
            
            if (this.found (ANDs[0].toLowerCase().trim()))
                result = this.DocumentIndexBST.retrieve().getDocs();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ANDs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndexBST.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    result [j] = result[j] && tempResult[j];
            }                

            return  result;
        }
        
        public boolean [] OR_Function (String string)
        {
            String [] ORs = string.split(" OR ");
            boolean []  result  = new boolean [50];
            
            if (this.found (ORs[0].toLowerCase().trim()))
                 result  = this.DocumentIndexBST.retrieve().getDocs();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ORs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndexBST.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    result [j] = result[j] || tempResult[j];
               
            }
            return result;
        }
}

