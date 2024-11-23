import java.util.function.Function;

public class Inverted_Index_BSTRanked {
    class frequency {
        int documentId = 0;
        int fr = 0;
    }

    BST<Integer, BST<String, Rank>> BSTrank;
    frequency[] freq = new frequency[50];

    public Inverted_Index_BSTRanked() {
        BSTrank = new BST<Integer, BST<String, Rank>>();
    }

    public boolean add_document(int documentId, String wd) {
        if (BSTrank.empty()) {
            BST<String, Rank> minimum_Rank = new BST<String, Rank>();
            minimum_Rank.insert(wd, new Rank(wd, 1));

            BSTrank.insert(documentId, minimum_Rank);
            return true;
        } else {
            if (BSTrank.find(documentId)) {
                BST<String, Rank> minimum_Rank = BSTrank.retrieve();
                if (minimum_Rank.find(wd)) {
                    
                    Rank rank = minimum_Rank.retrieve();
                    rank.add_Rank();
                    minimum_Rank.update(rank);
                    BSTrank.update(minimum_Rank);
                    return false;
                }
          
                minimum_Rank.insert(wd, new Rank(wd, 1));
                BSTrank.update(minimum_Rank);
                return true;
            }
        
            BST<String, Rank> minimum_Rank = new BST<String, Rank>();
            minimum_Rank.insert(wd, new Rank(wd, 1));

            BSTrank.insert(documentId, minimum_Rank);
            return true;
        }
    }

    public boolean found_doc(int documentId, String wd) {
        if (BSTrank.find(documentId))
            if (BSTrank.retrieve().find(wd))
                return true;
        return false;
    }

    public int getRank(int documentId, String wd) {
        int value = 0;
        if (BSTrank.find(documentId))
            if (BSTrank.retrieve().find(wd))
                return BSTrank.retrieve().retrieve().getRank();
        return value;
    }

    public void printAllDoc() {
        BSTrank.Traverse_Tr();
    }

   
    public void TreeFreq(String word) {
        word = word.toLowerCase().trim();
        String[] wd = word.split(" ");

        int doc = 0;
        int indx = 0;
        while (doc < 50) {
            int j = 0;
            int count = 0;
            while (j < wd.length) {
                count += this.getRank(doc, wd[j]);
                j++;
            }
            if (count > 0) {
                freq[indx] = new frequency();
                freq[indx].documentId = doc;
                freq[indx].fr = count;
                indx++;
            }
            doc++;
        }

        merge__sort(freq, 0, indx - 1);

        int x = 0;
        while (x < indx) {
            System.out.println(freq[x].documentId + "\t\t" + freq[x].fr);
            x++;
        }
    }

  
    public static void merge__sort(frequency[] f, int lef, int rig) {
        int i;
        if (lef >= rig)
            return;
        i = (lef + rig) / 2;
        merge__sort(f, lef, i); 
        merge__sort(f, i + 1, rig); 
        merge(f, lef, i, rig); 
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

