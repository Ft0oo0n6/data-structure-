
public class Term {
    Vocabulary word;
    boolean [] documentIDS ;

    public Term() {
       documentIDS = new boolean [50];
       int i = 0;
       while (i < documentIDS.length) {
         documentIDS[i] = false;
         i++;
}
        word = new Vocabulary("");
    }

    public Term(String word, boolean [] documentIDS) {
        this.word = new Vocabulary(word);
        this.documentIDS = new boolean [documentIDS.length];
        int i = 0;
        while (i < documentIDS.length) {
        this.documentIDS[i] = documentIDS[i];
        i++;
}    }
    
    public boolean add_documentID(int documentID) {
    boolean wasAdded = !documentIDS[documentID];
    if (wasAdded) {
        documentIDS[documentID] = true;
    }
    return wasAdded;
}

    public void setVocabulary(Vocabulary word)
    {
        this. word = word; 
    }
    
    public Vocabulary getVocabulary()
    {
         return word;
    }
    
    public boolean [] getDocs ()
    {
        boolean [] copy = new boolean [documentIDS.length];
        for ( int i = 0 ; i < copy.length ; i++)
            copy[i] = documentIDS[i];
        return copy;
    }
    
    
    public String toString() { 
    String documents = "";
    boolean firstEntry = true;

    for (int i = 0; i < documentIDS.length; i++) {
        if (documentIDS[i]) {
            if (firstEntry) {
                documents += " " + i;
                firstEntry = false;
            } else {
                documents += ", " + i;
            }
        }
    }

    return word + "[" + documents + ']';
}
    
    
}