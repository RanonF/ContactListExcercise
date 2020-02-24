package contactlistupdater;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class ContactListUpdater {
    
    private static final String INPUT_FILENAME = "contacts.txt";
    private static final String OUTPUT_FILENAME = "updated-contacts.txt";
    
    /**
     * Transforms a String containing a contact entry to a new format
     */
    
    public static String processContactEntry(String input) {
        // Create an array of Strings to hold the tokens that need to be shuffled.
         String[] contactInfoTokens = input.split(",\\s*");

        // Shuffle the tokens into the reordered contact data format
        input = contactInfoTokens [2] + ", " + contactInfoTokens [1] + " " + "<" + contactInfoTokens [3] + ">";    
        return input;
    }
    
    public static void main(String[] args) {
        
        // Open input/output files and process line by line
        try (Formatter output = new Formatter(OUTPUT_FILENAME)) {
            try (Scanner input = new Scanner(Paths.get(INPUT_FILENAME))) {
                
                // Process each line
                while (input.hasNextLine()) {
                    output.format("%s%n", processContactEntry(input.nextLine()));
                }
                
            }
            catch (IOException ex) {
                System.err.println("Unable to open input file: " + INPUT_FILENAME);
                ex.printStackTrace();          
            }
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Unable to open output file: " + OUTPUT_FILENAME);
            ex.printStackTrace();
        }
        
    }
    
}
