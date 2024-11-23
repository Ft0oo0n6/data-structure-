

import java.io.File;
import java.util.Scanner;


public class Search_Engine {
   int tokens = 0;
   int vocab = 0;
    
   Index index;
   Inverted_Index DocumentIndex;
   Inverted_Index_BST DocumentIndexBST;
   Inverted_Index_AVL DocumentIndexAVL;
    
   Inverted_Index_AVLRanked  invertedindexAVLranked;
   IndexRanked  indexranked;
   Inverted_IndexRanked  invertedindexranked;
   Inverted_Index_BSTRanked  invertedindexBSTranked;

   public Search_Engine ()
   {
      index = new Index();
      DocumentIndex = new Inverted_Index();
      DocumentIndexBST = new Inverted_Index_BST ();
      DocumentIndexAVL = new Inverted_Index_AVL();
   
      invertedindexAVLranked = new Inverted_Index_AVLRanked();
      indexranked = new IndexRanked();
      invertedindexBSTranked = new Inverted_Index_BSTRanked();
      invertedindexranked = new Inverted_IndexRanked();
   }
    

   public void LoadData(String stopsFile, String dataset){
      try{
         File stopsfile = new File (stopsFile);
         Scanner scanner = new Scanner (stopsfile).useDelimiter("\\Z");
         String stops = scanner.next();
         stops = stops.replaceAll("\n", " ");
                
         File documentsfile = new File(dataset);
         Scanner scanner2 = new Scanner (documentsfile);
         String lines = scanner2.nextLine(); 
       
         for ( int documentID = 0 ; documentID <50 ; documentID ++ ) 
         {
            lines = scanner2.nextLine().toLowerCase();
                    
            int position = lines.indexOf(',');
            int documentId = Integer.parseInt( lines .substring(0, position));
            String data = lines.substring(position+1, lines.length() - position).trim();
            data = data.substring(0, data.length()-1);
            data = data.toLowerCase();
            data =  data.replaceAll("[\']", "");

            data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;
         
            String [] words = data.split(" "); 
         
            for (int i = 0; i < words.length ; i++)
            {
               String word = words[i].trim(); 
                
               if ( word.compareToIgnoreCase("") != 0)
                  tokens ++;
            
               if (!stops.contains(word + " "))
               {
                            
                  { 
                  this.index.addToDocument(documentId, word);
                     this.DocumentIndex.adddocument(documentId, word);
                     this.DocumentIndexBST.adddocument(documentId, word);
                     this.DocumentIndexAVL.adddocument(documentId, word);
                  
                     this.indexranked.add_document (documentId, word);
                     this.invertedindexranked.add_document(documentId, word);
                     this.invertedindexBSTranked.add_document(documentId, word);
                     this.invertedindexAVLranked.adddocument(documentId, word);
                            /*
                                this.index.addDocument(documentId, wd);
                                this.DocumentIndex.adddocument(documentId, wd);
                                this.invertedindexBST.adddocument(documentId, wd);
                                this.invertedindexAVL.adddocument(documentId, wd);
                                this.invertedindexAVLranked.adddocument(documentId, wd);*/
                  }
               }
            }
         
                    //this.index.displayDocument(documentId);
         }
                //this.DocumentIndex.printDocment();
                //this.invertedindexBST.printDocument();
                //this.invertedindexAVL.printDocument();
                //this.invertedindexAVLranked.printDocument();
                
         vocab = DocumentIndexAVL.DocumentIndexAVL.size();
         System.out.println("tokens " + tokens);
         System.out.println("vocabs " + vocab);    
         scanner.close();
         scanner2.close();
      }
      catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
    
   public boolean []  Retrieval(String string , int DatasetSType) // be sure
   {
      boolean [] documents = new boolean [50] ;
      for (int i = 0 ; i < documents.length ; i++)
         documents[i] = false;
   
     if (DatasetSType == 1) {
    System.out.println("Boolean_Retrieval using index list");
    documents = this.index.AND_OR_Function(string);
} else if (DatasetSType == 2) {
    System.out.println("Boolean_Retrieval using inverted index list");
    documents = this.DocumentIndex.AND_OR_Function(string);
} else if (DatasetSType == 3) {
    System.out.println("Boolean_Retrieval using BST");
    documents = this.DocumentIndexBST.AND_OR_Function(string);
} else if (DatasetSType == 4) {
    System.out.println("Boolean_Retrieval using AVL");
    documents = this.DocumentIndexAVL.AND_OR_Function(string);
} else {
    System.out.println("Bad data structure");
}
      return documents;
   }
        
   public void  RetrievalRanked(String string)
   {
      this.invertedindexAVLranked.TreeFreq(string);
   }
   public void IndexRanked(String str)
   {
      this.indexranked.TreeFreq(str);
   }
   public void RetrievalRankedBST(String str)
   {
      this.invertedindexBSTranked.TreeFreq(str);
   }

   public void RetrievalRankedAVL(String str)//name changed
   {
      this.invertedindexAVLranked.TreeFreq(str);
   }
   public boolean []  RetrievalTerm(String str , int DSType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;

       if (DSType == 1) {
    docs = index.retrieveDocuments(str);
} else if (DSType == 2) {
    if (DocumentIndex.found(str)) {
        docs = DocumentIndex.DocumentIndex.retrieve().getDocs();
    }
} else if (DSType == 3) {
    if (DocumentIndexBST.found(str)) {
        docs = DocumentIndexBST.DocumentindexBST.retrieve().getDocs();
    }
} else if (DSType == 4) {
    if (DocumentIndexAVL.found(str)) {
        docs = DocumentIndexAVL.DocumentIndexAVL.retrieve().getDocs();
    }
} else {
    System.out.println("Bad data structure");
}
        return docs;
    }
  public void IndexedDocuments()
    {
        System.out.println("All Documents with the number of words in them ");
        for ( int i = 0 ; i < 50 ; i++ )
        {
            int size = index.documents[i].wordList.size();
            System.out.println("Document# " + i +"  with size(" +  size + ")"  );
        }
        
    }
    
    public void IndexedTokens()
    {
        System.out.println("All tokens with the documents appear in it ");
        DocumentIndexBST.DocumentindexBST.viewData();
        DocumentIndexAVL.DocumentIndexAVL.PrintData();
    }
  
}
