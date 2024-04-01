import java.util.Scanner; // Import Scanner from Java
public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print("\n" + prompt + ": "); // Show prompt add space
            retString = pipe.nextLine(); // Get user input
        }
        while(retString.length() == 0); // Loop and keep retrieving input until it isn't blank
        return retString; // Return user input (string)
    }
    public static int getInt(Scanner pipe, String prompt) {
        int num = 0; // Initialize a variable for the integer (user input)
        String trash = ""; // Initialize trash variable for any invalid input
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        do {
            System.out.println(prompt + ": "); // Output prompt
            if(pipe.hasNextInt()) { // If the next input can be read as an integer
                num = pipe.nextInt(); // Read integer value because valid input
                pipe.nextLine(); // Clear the newline from the buffer
                done = true; // Set done to true because we are done - valid input
            }
            else { // Otherwise
                trash = pipe.nextLine(); // Read the input as a String because input cannot be read as an integer
                System.out.println("You entered "+trash+". Please enter a valid integer value!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return num; // Return the integer value from the user input
    }
    public static double getDouble(Scanner pipe, String prompt) {
        double num = 0; // Initialize a variable for the double (user input)
        String trash = ""; // Initialize trash variable for any invalid input
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        do {
            System.out.println(prompt + ": "); // Output prompt
            if(pipe.hasNextDouble()) { // If the next input can be read as a double
                num = pipe.nextDouble(); // Read double value because valid input
                pipe.nextLine(); // Clear the newline from the buffer
                done = true; // Set done to true because we are done - valid input
            }
            else { // Otherwise
                trash = pipe.nextLine(); // Read the input as a String because input cannot be read as a double
                System.out.println("You entered "+trash+". Please enter a valid double value!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return num; // Return the double value from the user input
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int num = 0; // Initialize a variable for the ranged integer (user input)
        String trash = ""; // Initialize trash variable for any invalid input
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        do {
            System.out.println(prompt + " [" + low + "-" + high + "]: "); // Output prompt, mentioning the valid range
            if(pipe.hasNextInt()) { // If the next input can be read as an integer
                num = pipe.nextInt(); // Read double value because valid type (integer)
                pipe.nextLine(); // Clear the newline from the buffer
                if(num >= low && num <= high) { // If the integer is within the valid range, inclusive
                    done = true; // Set done to true because we are done - valid input (valid type and range)
                }
                else { // Otherwise
                    System.out.println("You entered "+num+", which is not in the range "+low+"-"+high+". Please enter an integer in range!"); // Outputs the invalid input and tells user to enter a valid input in range
                }
            }
            else { // Otherwise
                trash = pipe.nextLine(); // Read the input as a String because input cannot be read as an integer
                System.out.println("You entered "+trash+". Please enter a valid integer value!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return num; // Return the integer value from the user input
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double num = 0; // Initialize a variable for the ranged integer (user input)
        String trash = ""; // Initialize trash variable for any invalid input
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        do {
            System.out.println(prompt + " [" + low + "-" + high + "]: "); // Output prompt, mentioning the valid range
            if(pipe.hasNextDouble()) {  // If the next input can be read as a double
                num = pipe.nextDouble(); // Read double value because valid type (double)
                pipe.nextLine(); // Clear the newline from the buffer
                if(num >= low && num <= high) { // If the double is within the valid range, inclusive
                    done = true; // Set done to true because we are done - valid input (valid type and range)
                }
                else { // Otherwise
                    System.out.println("You entered "+num+", which is not in the range "+low+"-"+high+". Please enter a double in range!"); // Outputs the invalid input and tells user to enter a valid input in range
                }
            }
            else { // Otherwise
                trash = pipe.nextLine(); // Read the input as a String because input cannot be read as a double
                System.out.println("You entered "+trash+". Please enter a valid double value!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return num; // Return the double value from the user input
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String str = ""; // Initialize a variable for the yes or no string (user input)
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        boolean bool = true; // Initialize a boolean variable that is true or false depending on whether the user enters yes or no
        do {
            System.out.println(prompt + ": "); // Output prompt
            str = pipe.nextLine(); // Clear the newline from the buffer
            if(str.equalsIgnoreCase("Y")) { // If the string equals Y, ignoring case
                // The boolean is already set to true, so no need to make a variable statement
                done = true; // Set done to true because we are done - valid input
            }
            else if(str.equalsIgnoreCase("N")) { // If the string equals N, ignoring case
                bool = false; // Set the boolean variable to false because the user entered no
                done = true; // Set done to true because we are done - valid input
            }
            else { // Otherwise
                System.out.println("You entered "+str+". Please enter a valid input!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return bool; // Returns the boolean value
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String value = ""; // Initialize a variable for the string (user input)
        boolean done = false; // Initialize done variable for the do-while loop, initially false. Set to true when valid input
        do {
            System.out.println(prompt + ": "); // Output prompt
            value = pipe.nextLine(); // Retrieve string
            if(value.matches(regEx)) { // If the string matches the regEx pattern
                done = true; // Set done to true because we are done - valid input
            }
            else { // Otherwise
                System.out.println("Invalid input: " + value+". Please enter a valid input that matches the regEx pattern!"); // Outputs the invalid input and tells user to enter a valid input
            }
        }
        while (!done); // Keep looping until the user gives a valid input (done becomes true and then ends loop)
        return value; // Returns the boolean value
    }
    public static void prettyHeader(String msg) {
        for(int i = 0; i < 60; i++) { // Print 60 stars *
            System.out.print("*");
        }
        System.out.print("\n"); // Newline
        for(int i = 0; i < 3; i++) { // Print 3 stars *
            System.out.print("*");
        }
        int spaces = (54-msg.length())/2; // Find number of spaces between the three stars and the message
        for(int i = 0; i < spaces; i++) { // Print spaces
            System.out.print(" ");
        }
        System.out.print(msg); // Print the message
        for(int i = 0; i < spaces; i++) { // Print spaces
            System.out.print(" ");
        }
        if(!(msg.length() % 2 == 0)) { // If message has an odd number of characters, then print an extra space
            System.out.print(" ");
        }
        for(int i = 0; i < 3; i++) { // Print 3 stars *
            System.out.print("*");
        }
        System.out.print("\n"); // Newline
        for(int i = 0; i < 60; i++) { // Print 60 stars *
            System.out.print("*");
        }
    }
}