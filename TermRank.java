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
        int x = 0, i = 0; 

        while (x < documentId.length) {
            if (i == 0 && documentId[x] != 0) {
                documents += " " + String.valueOf(x);
                i++;
            } else if (documentId[x] != 0) {
                documents += ", " + String.valueOf(x);
                i++;
            }
            x++; 
        }

        return word + "[" + documents + ']';
    }
}

