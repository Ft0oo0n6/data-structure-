import java.util.function.Function;

public class Inverted_Index_AVLRanked {
    class frequency {
        int documentId = 0;
        int freq = 0;
    }

    AVLTree<Integer, AVLTree<String, Rank>> AVLrank;
    frequency[] freqs = new frequency[50];

    public Inverted_Index_AVLRanked() {
        AVLrank = new AVLTree<Integer, AVLTree<String, Rank>>();
    }

    public boolean adddocument(int documentId, String wod) {
        if (AVLrank.empty()) {
            AVLTree<String, Rank> min_Rank = new AVLTree<String, Rank>();
            min_Rank.insert(wod, new Rank(wod, 1));
            AVLrank.insert(documentId, min_Rank);
            return true;
        } else {
            if (AVLrank.find(documentId)) {
                AVLTree<String, Rank> min_Rank = AVLrank.retrieve();
                if (min_Rank.find(wod)) {
                    // document available, word available // rank ++
                    Rank rank = min_Rank.retrieve();
                    rank.add_Rank();
                    min_Rank.update(rank);
                    AVLrank.update(min_Rank);
                    return false;
                }
                // document available, word unavailable
                min_Rank.insert(wod, new Rank(wod, 1));
                AVLrank.update(min_Rank);
                return true;
            }
            // document unavailable
            AVLTree<String, Rank> min_Rank = new AVLTree<String, Rank>();
            min_Rank.insert(wod, new Rank(wod, 1));

            AVLrank.insert(documentId, min_Rank);
            return true;
        }
    }

    public boolean found_doc(int documentId, String wod) {
        if (AVLrank.find(documentId))
            if (AVLrank.retrieve().find(wod))
                return true;
        return false;
    }

    public int getrank(int documentId, String wod) {
        int value = 0;
        if (AVLrank.find(documentId))
            if (AVLrank.retrieve().find(wod))
                return AVLrank.retrieve().retrieve().getRank();
        return value;
    }

    public void printAllDoc() {
        AVLrank.TraverseT();
    }

    // =================================================================
    public void TreeFreq(String ste) {
        ste = ste.toLowerCase().trim();
        String[] words = ste.split(" ");
        int index = 0;

        for (int docId = 0; docId < 50; docId++) {
            int count = 0;

            for (int j = 0; j < words.length; j++) {
                count += this.getrank(docId, words[j]);
            }

            if (count > 0) {
                freqs[index] = new frequency();
                freqs[index].documentId = docId;
                freqs[index].freq = count;
                index++;
            }
        }

        merge__sort(freqs, 0, index - 1);

        for (int i = 0; i < index; i++) {
            System.out.println(freqs[i].documentId + "\t\t" + freqs[i].freq);
        }
    }

    // =================================================================
    public static void merge__sort(frequency[] f, int lef, int rig) {
        if (lef >= rig)
            return;
        int mer = (lef + rig) / 2;
        merge__sort(f, lef, mer); // Sort first half
        merge__sort(f, mer + 1, rig); // Sort second half
        merge(f, lef, mer, rig); // Merge
    }

    private static void merge(frequency[] f, int lef, int mer, int rig) {
        frequency[] fr = new frequency[rig - lef + 1];
        int l = lef, m = mer + 1, i = 0;

        for (; l <= mer && m <= rig; ) {
            if (f[l].freq >= f[m].freq)
                fr[i++] = f[l++];
            else
                fr[i++] = f[m++];
        }

        for (; l <= mer; ) {
            fr[i++] = f[l++];
        }

        for (; m <= rig; ) {
            fr[i++] = f[m++];
        }

        for (i = 0; i < fr.length; i++) {
            f[i + lef] = fr[i];
        }
    }
}
