/**
 *
 * @author ftoon
 */
public class Rank {
    private Vocabulary term;
    private int frequency;

    public Rank() {
        this.frequency = 0;
        this.term = new Vocabulary("");
    }

    public Rank(String term, int frequency) {
        this.term = new Vocabulary(term);
        this.frequency = frequency;
    }
    
    // Method to increment the rank (frequency) of the term
    public int incrementFrequency() {
        return ++frequency;
    }
    
       
    // Getter for frequency
    public int getFrequency() {
        return this.frequency;
    }
    
    // Getter for Vocabulary object
    public Vocabulary getTerm() {
        return term;
    }

    
    // Setter for Vocabulary object
    public void setTerm(Vocabulary term) {
        this.term = term;
    }
    
    @Override
    public String toString() {
        return "(" + term + ", " + frequency + ")";
    }
}

