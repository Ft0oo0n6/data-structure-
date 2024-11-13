/*
Inverted Index: A mapping from terms (unique words) to a list of documents containing 
those terms. Both of Index and Inverted Index will be implemented using list of lists.  */

/**
 *
 * @author Manal Alhihi
 */
  
 
public class Inverted_Index {

            LinkedList <Term> invertedindex; 

            public Inverted_Index() {
                invertedindex = new LinkedList <Term>();
            }
            
            public int size()
            {
                return invertedindex.size();
            }

            public boolean addNew (int dID, String wrd)// (docID,word)
            {
                if (invertedindex.empty())
               {
                   Term t = new Term ();//(trm)
                    t.setVocabulary(new Vocabulary (wrd));
                    t.add_dID(dID);
                    invertedindex.insert(t);
                    return true;
               }
               else
               {
                    invertedindex.findFirst();
                    while ( ! invertedindex.last())
                    {
                        if ( invertedindex.retrieve().word.word.compareTo(wrd) == 0)
                        {
                            Term t = invertedindex.retrieve();//(trm)
                            t.add_dID(dID);
                            invertedindex.update(t);
                            return false;
                        }
                       invertedindex.findNext();
                    }
                    if ( invertedindex.retrieve().word.word.compareTo(wrd) == 0)
                    {
                        Term t = invertedindex.retrieve();//(trm)
                        t.add_dID(dID);
                        invertedindex.update(t);
                        return false;
                    }
                    else
                    {
                        Term t = new Term ();
                        t.setVocabulary(new Vocabulary (wrd));
                        t.add_dID(dID);
                        invertedindex.insert(t);
                    }
                    return true;
           }
        }

        public boolean found(String wrd) //(word)
        {
               if (invertedindex.empty())
                   return false;

               invertedindex.findFirst();
               for ( int i = 0 ; i < invertedindex.size ; i++)
               {
                   if ( invertedindex.retrieve().wrd.wrd.compareTo(wrd) == 0)
                       return true;
                  invertedindex.findNext();
               }
               return false;
        }
        //=====================================================================
        public boolean [] AND_OR_Function (String string )//(str)
        {
            if (! string.contains(" OR ") && ! string.contains(" AND "))
            {
                boolean [] R = new boolean[50]; //(r1)
                string = string.toLowerCase().trim();
            
                if (this.found (string))
                    R =  this.invertedindex.retrieve().getDocs();
                return R;
            }
            
            else if (string.contains(" OR ") && string.contains(" AND "))
            {
                String [] AND_ORs = string.split(" OR ");
                boolean []  R = AND_Function (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] r =AND_Function (AND_ORs[i]);//r2
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        R [j] = R[j] || r2[j];
                }
                return R;
            }
            
            else  if (string.contains(" AND "))
                return AND_Function (string);
            
            return OR_Function (string);
        }
        
        public boolean [] AND_Function (String string)//(str)
        {
            String [] ANDs = string.split(" AND ");
            boolean [] B = new boolean [50];//b1
            
            if (this.found (ANDs[0].toLowerCase().trim()))
                B = this.invertedindex.retrieve().getDocs();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] b = new boolean [50];
                if (this.found (ANDs[i].toLowerCase().trim()))
                    b = this.invertedindex.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    B [j] = B[j] && b[j];
            }                

            return B;
        }
        
        public boolean [] OR_Function (String string)
        {
            String [] ORs = string.split(" OR ");
            boolean [] B = new boolean [50];//b1
            
            if (this.found (ORs[0].toLowerCase().trim()))
                B = this.invertedindex.retrieve().getDocs();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] b = new boolean [50];//b2
                if (this.found (ORs[i].toLowerCase().trim()))
                    b = this.invertedindex.retrieve().getDocs();
                
                for ( int j = 0 ; j < 50 ; j++)
                    B [j] = B[j] || b[j];
               
            }
            return B;
        }

        public void print()//printDocment()
        {
            if (this.invertedindex.empty())
                System.out.println("Empty Inverted Index");
            else
            {
                this.invertedindex.findFirst();
                while ( ! this.invertedindex.last())
                {
                    System.out.println(invertedindex.retrieve());
                    this.invertedindex.findNext();
                }
                System.out.println(invertedindex.retrieve());
            }
        }
}