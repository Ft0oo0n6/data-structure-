public class Vocabulary {
    private String term;

    public Vocabulary() {
        this.term = "";
    }

    public Vocabulary(String term) {
        this.term = term;
    }

    // Setter for term
    public void setTerm(String term) {
        this.term = term;
    }
    
    
    // Getter for term
    public String getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return term;
    }
}
