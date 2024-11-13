
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Majdi
 */
public class Search_Engine {
    int tokens = 0;
    int vocabulary = 0;//vocab
    
    Index index;//index
    Inverted_Index invertedindex;
    
    Inverted_Index_BST invertedindexBST;
    Inverted_Index_AVL invertedindexAVL;
    
    Inverted_Index_AVLRanked  invertedindexAVLranked;
    
    /*=================================================================================
    constractor 
    */
    public Search_Engine ()
    {
        index = new Index();
        invertedindex = new Inverted_Index();
        
        invertedindexBST = new Inverted_Index_BST ();
        invertedindexAVL = new Inverted_Index_AVL();
        
        invertedindexAVLranked = new Inverted_Index_AVLRanked();
    }
    
    /*=================================================================================
    Document Processing: 
    o Read documents from a CSV file. 
    o Split the text into a list of words based on whitespace. 
    o Convert all text to lowercase 
    o Preprocess the text by removing stop-words (e.g., "the," "is," "and"). The list of 
    stop-words will be given to you. 
    o Remove all punctuations and non-alphanumerical characters 
    o Proceed to build the index     
     */
    public void LoadData (String doneF, String fName)//(stopfile,filename)??
    {
            try{
                File doneF = new File (doneF);
                Scanner reader = new Scanner (doneF).useDelimiter("\\Z");
                String done = reader.next();//stops
                
                done = done.replaceAll("\n", " ");
                
                File dfile = new File(fName);//docsfile
                Scanner reader1 = new Scanner (dfile);
                String line = reader1.nextLine();
                
                for ( int lineID = 0 ; lineID <50 ; lineID ++ ) 
                {
                    line = reader1.nextLine().toLowerCase();
                    
                    int position = line.indexOf(',');//pos
                    int dID = Integer.parseInt( line .substring(0, position));

                    String data = line.substring(position+1, line.length() - position).trim();
                    data = data.substring(0, data.length() -2);

                    data = data.toLowerCase();
                    data =  data.replaceAll("[\']", "");
                    data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;

                    String [] wrds = data.split(" "); // --1  //words

                    for (int i = 0; i < wrds.length ; i++)
                    {
                        String wrd = wrds[i].trim(); //--2  //word
                
                        if ( wrd.compareToIgnoreCase("") != 0)
                            tokens ++;

                        if ( ! done.contains(wrd + " ")) //--3
                         {
                            //if (word.length() >= 1)
                            { 
                                this.index.addDocument(dID, wrd);
                                this.invertedindex.addNew(dID, wrd);
                                this.invertedindexBST.addNew(dID, wrd);
                                this.invertedindexAVL.addNew(dID, wrd);
                                this.invertedindexAVLranked.addNew(dID, wrd);
                            }
                        }
                    }

                    //this.index.printDocment(docID);
                }
                //this.invertedindex.printDocment();
                //this.invertedindexBST.printDocument();
                //this.invertedindexAVL.printDocument();
                //this.invertedindexAVLranked.printDocument();
                
                vocabulary = invertedindexAVL.invertedindexAVL.size();
      
                System.out.println("tokens " + tokens);
                System.out.println("vocabs " + vocabulary);
                
                reader.close();
                reader1.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public boolean []  Boolean_Retrieval(String string , int DSType)//str ,
    {
        boolean [] documents = new boolean [50] ;//docs
        for ( int i = 0 ; i < documents.length ; i++)
            documents[i] = false;
  
        switch (DSType)
        {
            case 1 :
                documents = this.invertedindex.AND_OR_Function(string);
                break;
            case 2:
                documents = this.invertedindexAVL.AND_OR_Function(string);
                break;
            default :
                System.out.println("Bad data structure");
                
        }
        return documents;
    }
        
    public void Ranked_Retrieval(String string)//str
    {
        this.invertedindexAVLranked.TF(string);
    }
    

}
