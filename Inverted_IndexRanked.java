public class Inverted_IndexRanked {
    class frequency {
        int doumentId = 0;
        int fr = 0;
    }

    LinkedList<TermRank> invertedindex;
    frequency[] fre;

    public Inverted_IndexRanked() {
        invertedindex = new LinkedList<TermRank>();
        fre = new frequency[50];
    }

    public int size() {
        return invertedindex.size();
    }

    public boolean add_document(int doumentId, String wd) {
        TermRank termR;
        if (invertedindex.empty()) {
            termR = new TermRank();
            termR.setVocab(new Vocabulary(wd));
            termR.addDocumentId(doumentId);
            invertedindex.insert(termR);
            return true;
        } else {
            invertedindex.findFirst();
            while (!invertedindex.last()) {
                if (invertedindex.retrieve().word.word.compareTo(wd) == 0) {
                    termR = invertedindex.retrieve();
                    termR.addDocumentId(doumentId);
                    invertedindex.update(termR);
                    return false;
                }
                invertedindex.findNext();
            }
            if (invertedindex.retrieve().word.word.compareTo(wd) == 0) {
                termR = invertedindex.retrieve();
                termR.addDocumentId(doumentId);
                invertedindex.update(termR);
                return false;
            } else {
                termR = new TermRank();
                termR.setVocab(new Vocabulary(wd));
                termR.addDocumentId(doumentId);
                invertedindex.insert(termR);
            }
            return true;
        }
    }

    public boolean found_doc(String wd) {
        if (invertedindex.empty())
            return false;

        invertedindex.findFirst();
        int i = 0;
        while (i < invertedindex.size()) {
            if (invertedindex.retrieve().word.word.compareTo(wd) == 0)
                return true;
            invertedindex.findNext();
            i++;
        }
        return false;
    }

    public void printAllDoc() {
        if (this.invertedindex.empty())
            System.out.println("it's empty Inverted Index!!");
        else {
            this.invertedindex.findFirst();
            while (!this.invertedindex.last()) {
                System.out.println(invertedindex.retrieve());
                this.invertedindex.findNext();
            }
            System.out.println(invertedindex.retrieve());
        }
    }

    public void TreeFreq(String word) {
        word = word.toLowerCase().trim();
        String[] wd = word.split(" ");
        fre = new frequency[50];
        int i = 0;
        while (i < 50) {
            fre[i] = new frequency();
            fre[i].doumentId = i;
            fre[i].fr = 0;
            i++;
        }

        i = 0;
        while (i < wd.length) {
            if (found_doc(wd[i])) {
                int[] docume = invertedindex.retrieve().printAllDoc();
                int j = 0;
                while (j < docume.length) {
                    if (docume[j] != 0) {
                        int index = j;
                        fre[index].doumentId = index;
                        fre[index].fr += docume[j];
                    }
                    j++;
                }
            }
            i++;
        }

        merge__sort(fre, 0, fre.length - 1);

        System.out.println("\n DocumentID: t\t Scor:e");
        int x = 0;
        while (fre[x].fr != 0) {
            System.out.println(fre[x].doumentId + "\t\t" + fre[x].fr);
            x++;
        }
    }

    public static void merge__sort(frequency[] f, int lef, int rig) {
        int i;
        if (lef >= rig)
            return;
        i = (lef + rig) / 2;
        merge__sort(f, lef, i); // Sort first half
        merge__sort(f, i + 1, rig); // Sort second half
        merge(f, lef, i, rig); // Merge
    }

    private static void merge(frequency[] f, int lef, int i, int rig) {
        frequency[] fe = new frequency[rig - lef + 1];
        int x = lef, j = i + 1, c = 0;

        while (x <= i && j <= rig) {
            if (f[x].fr >= f[j].fr)
                fe[c++] = f[x++];
            else
                fe[c++] = f[j++];
        }

        while (j <= rig) {
            fe[c++] = f[j++];
        }

        while (x <= i) {
            fe[c++] = f[x++];
        }

        c = 0;
        while (c < fe.length) {
            f[c + lef] = fe[c];
            c++;
        }
    }
}

/*public class Inverted_IndexRanked {
            class frequency
            {
                int doumentId = 0;
                int fr= 0;
            }

            LinkedList <TermRank> invertedindex; 
            frequency [] fre;
            
            public Inverted_IndexRanked() {
                invertedindex = new LinkedList <TermRank>();
                fre = new frequency[50];
            }
            
            public int size()
            {
                return invertedindex.size();
            }

            public boolean add_document(int doumentId, String wd)
            { TermRank termR;
                if (invertedindex.empty())
               {
                    termR = new TermRank ();
                    termR.setVocabulary(new Vocabulary (wd));
                    termR.add_docID(doumentId);
                    invertedindex.insert(termR);
                    return true;
               }
               else
               {
                    invertedindex.findFirst();
                    while ( ! invertedindex.last())
                    {
                        if ( invertedindex.retrieve().word.word.compareTo(wd) == 0)
                        {
                            termR = invertedindex.retrieve();
                            termR.add_docID(doumentId);
                            invertedindex.update(termR);
                            return false;
                        }
                       invertedindex.findNext();
                    }
                    if ( invertedindex.retrieve().word.word.compareTo(wd) == 0)
                    {
                        termR = invertedindex.retrieve();
                        termR.add_docID(doumentId);
                        invertedindex.update(termR);
                        return false;
                    }
                    else
                    {
                        termR = new TermRank ();
                        termR.setVocabulary(new Vocabulary (wd));
                        termR.add_docID(doumentId);
                        invertedindex.insert(termR);
                    }
                    return true;
           }
        }

        public boolean found(String wd)
        {
               if (invertedindex.empty())
                   return false;

               invertedindex.findFirst();
               for ( int i = 0 ; i < invertedindex.size ; i++)
               {
                   if ( invertedindex.retrieve().word.word.compareTo(wd) == 0)
                       return true;
                  invertedindex.findNext();
               }
               return false;
        }

        public void printDocment()
        {
            if (this.invertedindex.empty())
                System.out.println("it's empty Inverted Index!!");
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
        public void TreeFreq(String word)
        {
            word = word.toLowerCase().trim();
            String [] wd = word.split(" ");
            fre = new frequency[50];
            for ( int i = 0 ; i < 50 ; i++ )
            {
                fre[i] = new frequency();
                fre[i].doumentId = i;
                fre[i].fr = 0;
            }
            
            for ( int i = 0 ; i < wd.length ; i++)
            {
                if (found(wd[i]))
                {
                    int [] docs = invertedindex.retrieve().getDocs();
                    for ( int j = 0 ; j < docs.length ; j ++)
                    {
                        if (docs[j] != 0)
                        {
                            int index = j;
                            fre[index].doumentId = index;
                            fre[index].fr += docs[j];
                        }
                    }
                }
            }
            
            merge__sort(fre, 0,fre.length-1);
            
            System.out.println("\nDocIDt\tScore");
            for ( int x = 0 ;  fre[x].fr != 0 ; x++)
                System.out.println(fre[x].doumentId + "\t\t" + fre[x].fr);
        }

         //=================================================================
    public static void merge__sort(frequency[] f, int lef, int rig) 
    {
      int i;
        if (lef >= rig)
            return;
        i =(lef+ rig) / 2;
        merge__sort(f ,lef , i) ;          // Sort first half
        merge__sort(f,i + 1 ,rig) ;    // Sort second half
        merge(f,lef,i,rig) ;            // Merge
    }

    private static void merge (frequency[] f, int lef,int i,int rig)
    {
        frequency [] fe = new frequency [rig-lef+ 1];
        int x = lef , j = i + 1 , c = 0;
    
        while ( x <= i && j <= rig )
        {
            if (f[x].fr >= f[j].fr)
                fe[c++] =f[x++];
            else
                fe[c++] = f[ j ++];
        }
        
        if (x> i)
            while ( j <= rig )
                fe[c++] =f[j++];
        else
            while (x<= i )
               fe[c++] = f[x++];
        
        for (c= 0;c<fe. length ;c++)
           f[c+lef ] =fe[c];
    }


}*/
