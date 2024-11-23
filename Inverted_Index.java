public class Inverted_Index {

            LinkedList <Term> DocumentIndex;

            public Inverted_Index() {
                DocumentIndex = new LinkedList <Term>();
            }
            
            public int size()
            {
                return DocumentIndex.size();
            }

            public boolean adddocument(int documentID, String word)
            {  Term term;
                if (DocumentIndex.empty())
               {
                   term = new Term ();
                    term.setVocabulary(new Vocabulary (word));
                    term.add_documentID(documentID);
                    DocumentIndex.insert(term);
                    return true;
               }
               else
               {
                    DocumentIndex.findFirst();
                    while ( ! DocumentIndex.last())
                    {
                        if ( DocumentIndex.retrieve().word.word.compareTo(word) == 0)
                        {
                             term = DocumentIndex.retrieve();
                            term.add_documentID(documentID);
                            DocumentIndex.update(term);
                            return false;
                        }
                       DocumentIndex.findNext();
                    }
                    if ( DocumentIndex.retrieve().word.word.compareTo(word) == 0)
                    {
                        term = DocumentIndex.retrieve();
                        term.add_documentID(documentID);
                        DocumentIndex.update(term);
                        return false;
                    }
                    else
                    {
                   term = new Term ();
                        term.setVocabulary(new Vocabulary (word));
                        term.add_documentID(documentID);
                        DocumentIndex.insert(term);
                    }
                    return true;
           }
        }
public boolean found(String word) {
    if (DocumentIndex.empty()) {
        return false;
    }

    DocumentIndex.findFirst();
    while (!DocumentIndex.last()) {
        if (DocumentIndex.retrieve().word.word.compareTo(word) == 0) {
            return true;
        }
        DocumentIndex.findNext();
    }


    if (DocumentIndex.retrieve().word.word.compareTo(word) == 0) {
        return true;
    }

    return false;
}


        
        public boolean [] AND_OR_Function (String string )
        {
        boolean [] result;
            if (! string.contains(" OR ") && ! string.contains(" AND "))
            {
                result = new boolean[50];
                string = string.toLowerCase().trim();
            
                if (this.found (string))
                    result =  this.DocumentIndex.retrieve().getDocs();
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
                result = this.DocumentIndex.retrieve().getDocs();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ANDs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndex.retrieve().getDocs();
                
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
                 result  = this.DocumentIndex.retrieve().getDocs();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] tempResult = new boolean [50];
                if (this.found (ORs[i].toLowerCase().trim()))
                    tempResult = this.DocumentIndex.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    result [j] = result[j] || tempResult[j];
               
            }
            return result;
        }

        public void printDocment()
        {
            if (this.DocumentIndex.empty())
                System.out.println("Empty Inverted Index");
            else
            {
                this.DocumentIndex.findFirst();
                while ( ! this.DocumentIndex.last())
                {
                    System.out.println(DocumentIndex.retrieve());
                    this.DocumentIndex.findNext();
                }
                System.out.println(DocumentIndex.retrieve());
            }
      }
}
