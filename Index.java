public class Index {
    class Document {
        int documentID;
        LinkedList<String> wordList;

        public Document() {
            documentID = 0;
            wordList = new LinkedList<String>();
        }

        public void addWord(String word) {
            wordList.insert(word);
        }

        public boolean hasWord(String word) {
            if (wordList.empty())
                return false;

            wordList.findFirst();
            int i = 0;
            while (i < wordList.size) {
                if (wordList.retrieve().compareTo(word) == 0)
                    return true;
                wordList.findNext();
                i++;
            }
            return false;
        }
    }

    // ===========================================================

    Document[] documents;

    public Index() {
        documents = new Document[50];
        int i = 0;
        while (i < documents.length) {
            documents[i] = new Document();
            documents[i].documentID = i;
            i++;
        }
    }

    public void addToDocument(int documentID, String word) {
        documents[documentID].addWord(word);
    }

    public void displayDocument(int documentID) {
        if (documents[documentID].wordList.empty())
            System.out.println("Empty Document");
        else {
            documents[documentID].wordList.findFirst();
            int i = 0;
            while (i < documents[documentID].wordList.size) {
                System.out.print(documents[documentID].wordList.retrieve() + " ");
                documents[documentID].wordList.findNext();
                i++;
            }
        }
    }

    // ============================================================
    public boolean[] retrieveDocuments(String word) {
        boolean[] matches = new boolean[50];
        int i = 0;

        while (i < matches.length) {
            matches[i] = false;
            i++;
        }

        i = 0;
        while (i < matches.length) {
            if (documents[i].hasWord(word))
                matches[i] = true;
            i++;
        }

        return matches;
    }

    // ============================================================
    public boolean[] AND_OR_Function(String query) {
        if (!query.contains(" OR ") && !query.contains(" AND ")) {
            query = query.toLowerCase().trim();
            boolean[] results = retrieveDocuments(query.toLowerCase().trim());
            return results;
        }

        if (query.contains(" OR ") && query.contains(" AND ")) {
            String[] subQueries = query.split(" OR ");
            boolean[] results = AND_Function(subQueries[0]);

            int i = 1;
            while (i < subQueries.length) {
                boolean[] subResults = AND_Function(subQueries[i]);

                int j = 0;
                while (j < 50) {
                    results[j] = results[j] || subResults[j];
                    j++;
                }
                i++;
            }
            return results;
        }

        if (query.contains(" AND ")) {
            return AND_Function(query);
        }

        return OR_Function(query);
    }

    // ============================================================
    public boolean[] AND_Function(String query) {
        String[] andParts = query.split(" AND ");
        boolean[] results = retrieveDocuments(andParts[0].toLowerCase().trim());

        int i = 1;
        while (i < andParts.length) {
            boolean[] subResults = retrieveDocuments(andParts[i].toLowerCase().trim());

            int j = 0;
            while (j < 50) {
                results[j] = results[j] && subResults[j];
                j++;
            }
            i++;
        }
        return results;
    }

    // ============================================================
    public boolean[] OR_Function(String query) {
        String[] orParts = query.split(" OR ");
        boolean[] results = retrieveDocuments(orParts[0].toLowerCase().trim());

        int i = 1;
        while (i < orParts.length) {
            boolean[] subResults = retrieveDocuments(orParts[i].toLowerCase().trim());

            int j = 0;
            while (j < 50) {
                results[j] = results[j] || subResults[j];
                j++;
            }
            i++;
        }
        return results;
    }
}