public class TermRank {
    Vocabulary word;
    int[] documentId;

    public TermRank() {
        documentId = new int[50];
        int i = 0;
        while (i < documentId.length) {
            documentId[i] = 0;
            i++;
        }
        word = new Vocabulary("");
    }

    public TermRank(String word, int[] documentId) {
        this.word = new Vocabulary(word);
        this.documentId = new int[documentId.length];
        int i = 0;
        while (i < documentId.length) {
            this.documentId[i] = documentId[i];
            i++;
        }
    }

    public void addDocumentId(int docId) {
        this.documentId[docId]++;
    }

    public void setVocab(Vocabulary word) {
        this.word = word;
    }

    public Vocabulary getVocab() {
        return word;
    }

    public int[] printAllDoc() {
        int[] check = new int[documentId.length];
        int i = 0;
        while (i < check.length) {
            check[i] = documentId[i];
            i++;
        }
        return check;
    }

    @Override
    public String toString() {
        String documents = "";
        int x = 0, i = 0; // Initialize counters for the loop

        while (x < documentId.length) {
            if (i == 0 && documentId[x] != 0) {
                documents += " " + String.valueOf(x);
                i++;
            } else if (documentId[x] != 0) {
                documents += ", " + String.valueOf(x);
                i++;
            }
            x++; // Increment the counter
        }

        return word + "[" + documents + ']';
    }
}
/*
public class TermRank {
    Vocabulary word;
    int  [] documentId ;

    public TermRank() {
        docIDS = new int [50];
        for (int i = 0 ; i < docIDS.length ; i++)
            docIDS [i] = 0;
        word = new Vocabulary("");
    }

    public TermRank(String word, int [] docIDS) {
        this.word = new Vocabulary(word);
        this.docIDS = new int [docIDS.length];
        for (int i = 0 ; i < docIDS.length ; i++)
            this.docIDS [i] = docIDS[i];

    }
    
    public void add_docID ( int docID)
    {
        this.docIDS[docID] ++;
    }

    public void setVocabulary(Vocabulary word)
    {
        this. word = word; 
    }
    
    public Vocabulary getVocabulary()
    {
         return word;
    }
    
    public int [] getDocs ()
    {
        int [] test = new int [docIDS.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = docIDS[i];
        return test;
    }
    
    @Override
    public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < docIDS.length; i++)
            if ( j == 0 && docIDS [i] != 0 )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( docIDS [i] != 0 )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }
        
        return word + "[" + docs + ']';
    }
    
    
}*/
