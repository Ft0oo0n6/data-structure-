
import java.util.function.Function;


public class Inverted_Index_AVLRanked {
            class frequency
            {
                int dID = 0;//docID
                int f = 0;
            }
    
           AVLTree <Integer, AVLTree <String,Rank>> AVLrank; 
           frequency [] frequencies = new frequency[50];//freqs
            
            
            
            public Inverted_Index_AVLRanked() {
                AVLrank = new AVLTree <Integer, AVLTree <String,Rank>>();
                
            }

            public boolean addNew (int dID, String wrd)//docID word
            {
               if (AVLrank.empty())
               {
                   AVLTree <String,Rank> leastRank= new AVLTree <String,Rank>();//miniRank
                   leastRank.insert(wrd, new Rank (wrd,1));
                   
                   AVLrank.insert(dID, leastRank);
                   return true;
               }
               else
               {
                    if (AVLrank.find(dID))
                    {
                        AVLTree <String,Rank> leastRank= AVLrank.retrieve();
                        if (leastRank.find(wrd))
                        {
                            // document available , word avialble // rank ++
                            Rank R = leastRank.retrieve();//rank
                            R.add_Rank();
                            leastRank.update(R);
                            AVLrank.update(leastRank);
                            return false;
                        }
                        //  document available , word unavailable 
                        leastRank.insert(wrd, new Rank (wrd , 1));
                        AVLrank.update(leastRank);
                        return true;
                    }
                    // document unavailable 
                   AVLTree <String,Rank> leastRank= new AVLTree <String,Rank>();
                   leastRank.insert(wrd, new Rank (wrd,1));
                   
                   AVLrank.insert(dID, leastRank);
                   return true;
               }
        }

        public boolean found(int dID, String wrd)//docID word
        {
               if (AVLrank.find(dID) )
                  if (AVLrank.retrieve().find(wrd))
                      return true;
               return false;
        }
        
        public int getrank (int dID, String wrd)//docID word
        {
            int value = 0;
               if (AVLrank.find(dID) )
                  if (AVLrank.retrieve().find(wrd))
                      return AVLrank.retrieve().retrieve().getRank();
               return value;
            
        }
        public void print()//printDocument
        {
                AVLrank.TraverseT(); //?
        }

        //=================================================================
        public void TF(String string)//str
        {
            string = string.toLowerCase().trim();
            String [] wrds = string.split(" ");//words
            
            int index = 0;
            for ( int dID = 0 ; dID < 50 ; dID++ )
            {
                int c = 0 ;//count
                for ( int j = 0 ;j < wrds.length ; j++ )
                    c += this.getrank(dID, wrds[j]);
                if (c > 0)
                {
                    frequencies[index] = new frequency();
                    frequencies[index].dID = dID;
                   frequencies[index].f = c;
                    index ++;
                }
            }
            
            mergesort(frequencies, 0, index-1 );
                
            for ( int x = 0 ; x < index ; x++)
                System.out.println(frequencies[x].dID + "\t\t" + frequencies[x].f);
        }

         //=================================================================
    public static void mergesort ( frequency [] F1 , int left , int right ) // (A,int l,int r)??
    {
        if ( left >= right )
            return;
        int mrg = ( left + right ) / 2;//m
        mergesort (F1 , left , mrg ) ;          // Sort first half
        mergesort (F1 , mrg + 1 , right ) ;    // Sort second half
        merge (F1 , left , mrg , right ) ;            // Merge
    }

    private static void merge ( frequency [] F1 , int left , int mrg , int right ) 
    {
        frequency [] F2 = new frequency [ right - left + 1];//B
        int i =   left , j = mrg + 1 , k = 0;
    
        while ( i <= mrg && j <= right )
        {
            if ( F1 [ i ].f >= F1 [ j ].f)
                F2 [ k ++] = F1 [ i ++];
            else
                F2 [ k ++] = F1 [ j ++];
        }
        
        if ( i > mrg )
            while ( j <= right )
               F2 [ k ++] = F1 [ j ++];
        else
            while ( i <= mrg )
                F2 [ k ++] = F1 [ i ++];
        
        for ( k = 0; k < F2 . length ; k ++)
            F1 [ k + left ] = F2 [ k ];
    }

}
