
public class Inverted_IndexRanked {
            class frequency
            {
                int docID = 0;
                int f = 0;
            }

            LinkedList <TermRank> invertedindex; 
            frequency [] freqs;
            
            public Inverted_IndexRanked() {
                invertedindex = new LinkedList <TermRank>();
                freqs = new frequency[50];
            }
            
            public int size()
            {
                return invertedindex.size();
            }

            public boolean addNew (int docID, String word)
            {
                if (invertedindex.empty())
               {
                   TermRank t = new TermRank ();
                    t.setVocabulary(new Vocabulary (word));
                    t.add_docID(docID);
                    invertedindex.insert(t);
                    return true;
               }
               else
               {
                    invertedindex.findFirst();
                    while ( ! invertedindex.last())
                    {
                        if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                        {
                            TermRank t = invertedindex.retrieve();
                            t.add_docID(docID);
                            invertedindex.update(t);
                            return false;
                        }
                       invertedindex.findNext();
                    }
                    if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                    {
                        TermRank t = invertedindex.retrieve();
                        t.add_docID(docID);
                        invertedindex.update(t);
                        return false;
                    }
                    else
                    {
                        TermRank t = new TermRank ();
                        t.setVocabulary(new Vocabulary (word));
                        t.add_docID(docID);
                        invertedindex.insert(t);
                    }
                    return true;
           }
        }

        public boolean found(String word)
        {
               if (invertedindex.empty())
                   return false;

               invertedindex.findFirst();
               for ( int i = 0 ; i < invertedindex.size ; i++)
               {
                   if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                       return true;
                  invertedindex.findNext();
               }
               return false;
        }

        public void printDocment()
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

        //=================================================================
        public void TreeFreq(String str)
        {
            str = str.toLowerCase().trim();
            String [] words = str.split(" ");
            freqs = new frequency[50];
            for ( int i = 0 ; i < 50 ; i++ )
            {
                freqs[i] = new frequency();
                freqs[i].docID = i;
                freqs[i].f = 0;
            }
            
            for ( int i = 0 ; i < words.length ; i++)
            {
                if (found (words[i]))
                {
                    int [] docs = invertedindex.retrieve().getDocs();
                    for ( int j = 0 ; j < docs.length ; j ++)
                    {
                        if (docs[j] != 0)
                        {
                            int index = j;
                            freqs[index].docID = index;
                            freqs[index].f += docs[j];
                        }
                    }
                }
            }
            
            mergesort(freqs, 0, freqs.length-1 );
            
            System.out.println("\nDocIDt\tScore");
            for ( int x = 0 ;  freqs[x].f != 0 ; x++)
                System.out.println(freqs[x].docID + "\t\t" + freqs[x].f);
        }

         //=================================================================
    public static void mergesort ( frequency [] A , int l , int r ) 
    {
        if ( l >= r )
            return;
        int m = ( l + r ) / 2;
        mergesort (A , l , m ) ;          // Sort first half
        mergesort (A , m + 1 , r ) ;    // Sort second half
        merge (A , l , m , r ) ;            // Merge
    }

    private static void merge ( frequency [] A , int l , int m , int r ) 
    {
        frequency [] B = new frequency [ r - l + 1];
        int i = l , j = m + 1 , k = 0;
    
        while ( i <= m && j <= r )
        {
            if ( A [ i ].f >= A [ j ].f)
                B [ k ++] = A [ i ++];
            else
                B [ k ++] = A [ j ++];
        }
        
        if ( i > m )
            while ( j <= r )
                B [ k ++] = A [ j ++];
        else
            while ( i <= m )
                B [ k ++] = A [ i ++];
        
        for ( k = 0; k < B . length ; k ++)
            A [ k + l ] = B [ k ];
    }


}