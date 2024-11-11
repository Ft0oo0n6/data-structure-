/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructure;

/**
 *
 * @author ftoon
 */
import java.util.Scanner;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);
    public static SearchEngine searchEngine = new SearchEngine();
    
    /**
     * @param args the command line arguments
     */
    
    public static int displayMenu() {
        System.out.println("1. Boolean Retrieval");
        System.out.println("2. Ranked Retrieval");
        System.out.println("3. Indexed Documents");
        System.out.println("4. Indexed Tokens");
        System.out.println("5. Exit");
        
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        return choice;
    }

    public static void printBooleanResults(boolean[] searchResults) {
        Term term = new Term("", searchResults);
        System.out.println(term);
    }

    public static void booleanRetrievalMenu() {
        String[] queries = { 
            "market AND sports",
            "weather AND warming",
            "business AND world",
            "weather OR warming",
            "market OR sports",
            "market OR sports AND warming"
        };

        System.out.println("------- Boolean Retrieval -------");
        
        for (String query : queries) {
            System.out.println("Query: " + query);
            System.out.print("Result doc IDs: ");
            printBooleanResults(searchEngine.booleanRetrieval(query, 2));
            System.out.println("\n");
        }
    }

    public static void rankedRetrievalMenu() {
        String[] queries = { 
            "market sports",
            "weather warming",   
            "business world market"
        };

        System.out.println("-------Ranked Retrieval------- ");
        
        for (String query : queries) {
            System.out.println("## Query: " + query);
            System.out.println("DocID\tScore");
            searchEngine.rankedRetrieval(query);
            System.out.println("\n");
        }
    }
    
    public static void indexedDocumentsMenu() {
        System.out.println("------- Indexed Documents ------- ");
        System.out.println("Number of Indexed Documents: " + searchEngine.index.numberOfDocuments);
    }
    
    public static void indexedTokensMenu() {
        System.out.println("-------Indexed Tokens ------- ");
        System.out.println("Number of Indexed Tokens: " + searchEngine.totalTokens);
    }
    
    public static void main(String[] args) {

        searchEngine.loadData("C:\\Users\\Majdi\\Downloads\\data\\stop.txt","C:\\Users\\Majdi\\Downloads\\data\\dataset.CSV");

        int userChoice;
        
        do {
            userChoice = displayMenu();
            switch (userChoice) {
                // Boolean Retrieval: returns a set of unranked documents based on a Boolean query
                case 1:
                    booleanRetrievalMenu();
                    break;
                    
                // Ranked Retrieval: returns a ranked list of documents with their scores based on a query
                case 2:
                    rankedRetrievalMenu();
                    break;
                
                // Indexed Documents: shows the number of documents in the index
                case 3:
                    indexedDocumentsMenu();
                    break;
                
                // Indexed Tokens: shows the number of vocabulary and tokens in the index
                case 4:
                    indexedTokensMenu();
                    break;
                
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        } while (userChoice != 5);
    }
}

