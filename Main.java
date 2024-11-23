import java.util.Scanner;


public class Main {
    
       
     public static Scanner input = new Scanner (System.in);
     public static Search_Engine SE = new Search_Engine();
     
   
    public static int menu()
    {
        System.out.println("1. Term Retrieval. ");
        System.out.println("2. Boolean Retrieval. ");
        System.out.println("3. Ranked Retrieval.");
        System.out.println("4. Indexed Documents.");
        System.out.println("5. Indexed Tokens.");
        System.out.println("6. Exist.");
        
        System.out.println("enter choice");
        int choice = input.nextInt();
        return choice;
    }

    public static void printBoolean(boolean [] result)
    {
        Term t = new Term ("", result);
        System.out.println(t);
    }

    public static void Retrieval_Term()
    {
        int choice1 ;
        System.out.println("------------- Retrieval Term -------------");
        
        System.out.println("1. index");
        System.out.println("2. inverted index");
        System.out.println("3. inverted index using BST");
        System.out.println("4. inverted index using AVL");
        System.out.println("enter your choice");
        choice1 = input.nextInt();
        
        System.out.println("Enter Term :");
        String str = "";
        str = input.next();
        
        System.out.print("Result doc IDs: ");
        printBoolean(SE.RetrievalTerm(str.trim().toLowerCase(), choice1 ));
        System.out.println("\n");

    }
    
    public static void Boolean_Retrieval_menu()
    {
        System.out.println("------------- Boolean Retrieval -------------");
        System.out.println("1. inde6x");
        System.out.println("2. inverted index");
        System.out.println("3. inverted index using BST");
        System.out.println("4. inverted index using AVL");
        System.out.println("enter your choice");
        int choice1 = input.nextInt();

        System.out.println("Enter boolean term ( AND / OR) : ");
        String str = input.nextLine();
        str = input.nextLine();
            
        System.out.print("Q#: ");
        System.out.println(str);

        System.out.print("Result doc IDs: ");
        printBoolean(SE.Retrieval(str.trim().toUpperCase(), choice1 ));
        System.out.println("\n");
    
    }

    public static void Ranked_Retrieval_menu()
    {
        System.out.println("----- Ranked Retrieval ----- ");
        System.out.println("1. index");
        System.out.println("2. inverted index");
        System.out.println("3. inverted index using BST");
        System.out.println("4. inverted index using AVL");
        System.out.println("enter your choice");
        int choice1 = input.nextInt();

        System.out.print("enter term: ");
        String str = input.nextLine();
        str = input.nextLine();

        System.out.println("## Q: " + str);
        System.out.println("DocIDt\tScore");
        switch (choice1)
        {
            case 1:
                System.out.println("get ranked from index list");
                SE.IndexRanked(str);
                break;
            case 2:
                System.out.println("get ranked from inverted index list");
                SE.RetrievalRanked(str);
                break;
            case 3:
                System.out.println("get ranked from BST");
                SE.RetrievalRankedBST(str);
                break;
            case 4:
                System.out.println("get ranked from AVL");
                SE.RetrievalRankedAVL(str);
                break;
        }
        System.out.println("\n");
    }
    
    public static void Indexed_Documents_menu()
    {
        System.out.println("-----Indexed Documents ----- ");
        System.out.println("Indexed Documents " );
        SE.IndexedDocuments();
        System.out.println("");
    }
    
    public static void Indexed_Tokens_menu()
    {
        System.out.println("----- Indexed Tokens ----- ");
        System.out.println("tokens " );
        SE.IndexedTokens();
        System.out.println("");
    }
    
    public static void main(String[] args) {

        SE.LoadData("stop.txt", "dataset.CSV");

      
             int choice;

        do {
             choice = menu();

                    if (choice == 1) {
            
                          Retrieval_Term();
                    } else if (choice == 2) {

                         Boolean_Retrieval_menu();
                    } else if (choice == 3) {
        
                         Ranked_Retrieval_menu();
                    } else if (choice == 4) {
       
                         Indexed_Documents_menu();
                    } else if (choice == 5) {
        
                         Indexed_Tokens_menu();
                    } else if (choice == 6) {
      
                  break;
                        } else {
       
                      System.out.println("Bad choice, try again!");
                        }                   
            } while (choice != 6);
        }
    }
