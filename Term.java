/**
 *
 * @author Manal Alhihi
 */
public class Term {
    Vocabulary wrd;//word
    boolean [] dIDS ;//docIDS

    public Term() {
        dIDS = new boolean [50];
        for (int i = 0 ; i < dIDS.length ; i++)
            dIDS [i] = false;
        wrd = new Vocabulary("");
    }

    public Term(String wrd, boolean [] dIDS) { //word,docIDS
        this.wrd = new Vocabulary(wrd);
        this.dIDS = new boolean [dIDS.length];
        for (int i = 0 ; i < dIDS.length ; i++)
            this.dIDS [i] = dIDS[i];

    }
    
    public boolean add_docID ( int dID)//docID
    {
        if (! dIDS[dID])
        {
            this.dIDS[dID] = true;
            return true;
        }
        return false;
    }

    public void setVocabulary(Vocabulary wrd)//word
    {
        this. wrd = wrd; 
    }
    
    public Vocabulary getVocabulary()
    {
         return wrd;
    }
    
    public boolean [] getDocs ()
    {
        boolean [] doc = new boolean [dIDS.length];//test
        for ( int i = 0 ; i < doc.length ; i++)
            doc[i] = dIDS[i];
        return doc;
    }
    
    @Override
    public String toString() {
        String documents = "";//docs
        for (int i = 0, j = 0 ; i < dIDS.length; i++)
            if ( j == 0 && dIDS [i]==true )
            {
                documents += " " + String.valueOf(i) ;
                j++;
            }
            else if ( dIDS [i]==true )
            {
                documents += ", " + String.valueOf(i) ;
                j++;
            }
        
        return wrd + "[" + documents + ']';
    }
    
    
}
