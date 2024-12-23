
public class IndexRanked {
    class frequency {
        int documentId = 0;
        int fr = 0;
    }

    class Document {
        int documentId;
        LinkedList<String> indx;

        public Document() {
            documentId = 0;
            indx = new LinkedList<String>();
        }

        public void addDocumentId(String wd) {
            indx.insert(wd);
        }

        public boolean found_doc(String wd) {
            if (indx.empty())
                return false;

            indx.findFirst();
            int i = 0;
            while (i < indx.size) {
                if (indx.retrieve().compareTo(wd) == 0)
                    return true;
                indx.findNext();
                i++;
            }
            return false;
        }
    }

  
    Document[] index;
    frequency[] fre;

    public IndexRanked() {
        fre = new frequency[50];
        index = new Document[50];
        int i = 0;
        while (i < index.length) {
            index[i] = new Document();
            index[i].documentId = i;
            i++;
        }
    }

    public void add_document(int documentId, String value) {
        index[documentId].addDocumentId(value);
    }

    public void printAllDoc(int documentId) {
        if (index[documentId].indx.empty()) {
            System.out.println("it's empty document !");
        } else {
            index[documentId].indx.findFirst();
            int i = 0;
            while (i < index[documentId].indx.size) {
                System.out.print(index[documentId].indx.retrieve() + " ");
                index[documentId].indx.findNext();
                i++;
            }
        }
    }

 
    public boolean[] printAllDocument(String word) {
        boolean[] outcome = new boolean[50];
        int i = 0;
        while (i < outcome.length) {
            outcome[i] = false;
            i++;
        }

        i = 0;
        while (i < outcome.length) {
            if (index[i].found_doc(word))
                outcome[i] = true;
            i++;
        }

        return outcome;
    }

  
    public void TreeFreq(String wd) {
        wd = wd.toLowerCase().trim();
        String[] word = wd.split(" ");
        fre = new frequency[50];
        int i = 0;
        while (i < 50) {
            fre[i] = new frequency();
            fre[i].documentId = i;
            fre[i].fr = 0;
            i++;
        }

        int doc = 0;
        while (doc < 50) {
            int j = 0;
            while (j < word.length) {
                index[doc].indx.findFirst();
                int coun = 0;
                int x = 0;
                while (x < index[doc].indx.size()) {
                    if (index[doc].indx.retrieve().compareTo(word[j]) == 0)
                        coun++;
                    index[doc].indx.findNext();
                    x++;
                }

                if (coun > 0)
                    fre[doc].fr += coun;

                j++;
            }
            doc++;
        }

        merge__sort(fre, 0, fre.length - 1);

        System.out.println("\n DocID: t\t Score:");
        int k = 0;
        while (fre[k].fr != 0) {
            System.out.println(fre[k].documentId + "\t\t" + fre[k].fr);
            k++;
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

