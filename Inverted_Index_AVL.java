public class Inverted_Index_AVL {
 
            AVLTree <String, Term> DocumentIndexAVL; 

            public Inverted_Index_AVL() {
                DocumentIndexAVL = new AVLTree <String, Term>();
            }

            public int size()
            {
               return DocumentIndexAVL.size();
            }
                
            public boolean adddocument (int documentID, String word)
            {  Term term ;
               if (DocumentIndexAVL.empty())
               {
                   term = new Term ();
                   term.setVocabulary(new Vocabulary (word));
                   term.add_documentID(documentID);
                   DocumentIndexAVL.insert(word, term);
                   return true;
               }
               else
               {
                    if (DocumentIndexAVL.find(word))
                    {
                        term = DocumentIndexAVL.retrieve();
                        term.add_documentID(documentID);
                        DocumentIndexAVL.update(term);
                        return false;
                        
                    }
                    
                    term = new Term ();
                   term.setVocabulary(new Vocabulary (word));
                   term.add_documentID(documentID);
                   DocumentIndexAVL.insert(word, term);
                    return true;
           }
        }

        public boolean found(String word)
        {
               return DocumentIndexAVL.find(word);
        }
        
        public void printDocument()
        {
            DocumentIndexAVL.Traverse();
        }

        public boolean [] AND_OR_Function (String string )
        {
        boolean [] result;
            if (! string.contains(" OR ") && ! string.contains(" AND "))
            {
                result = new boolean[50];
                string = string.toLowerCase().trim();
            
                if (this.found (string))
                    result =  this.DocumentIndexAVL.retrieve().getDocs();
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
                result = this.DocumentIndexAVL.retrieve().getDocs();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ANDs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndexAVL.retrieve().getDocs();
                
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
                 result  = this.DocumentIndexAVL.retrieve().getDocs();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ORs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndexAVL.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    result [j] = result[j] || tempResult[j];
               
            }
            return result;
        }
}
