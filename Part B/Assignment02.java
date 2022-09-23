/*
* Assignment02
* Christian Shih
* Date: 09/14/2022
*/

import java.util.*;

public class Assignment02 {
    public static void main(String[] args) {
        int inputCount = 0;     //Tracks how many of inputs from user
        int keywords = 0;      //Tracks how many words loaded into the Hashmap
        int definitions = 0;   //Tracks how many definitions loaded into the Hashmap
        boolean looprun = true;    //Used to determine if loop should continue running

        Scanner put = new Scanner(System.in);
        HashMap<String, ArrayList<Dictionary>> hashamp = new HashMap<String, ArrayList<Dictionary>>();           //HashMap to replicate dictionary where string holds words and the Arraylist holds definitions such as Word,PartsOfSpeech,Descriptions
        ArrayList<String> partOfSpeeches = new ArrayList<String>(Arrays.asList("noun", "verb", "adjective", "adverb", "pronoun", "preposition", "conjunction", "interjection"));  //Holds ArrayList of parts of speech
        System.out.println("! Loading data...");

        for (Dictionary entry : Dictionary.values()) {      //Loop for Enum to be copied into the Hashmap
            String word = entry.getWord().toLowerCase();
            ArrayList<Dictionary> defines;

            if (hashamp.containsKey(word)) {                    //Checks if word is already in Dictionary(HashMap)
                defines = hashamp.get(word);
                defines.add(entry);
            } else {                                         // If it is adds definitions and increases keyword count
                defines = new ArrayList<Dictionary>();
                defines.add(entry);
                keywords++;
            }
            hashamp.put(word, defines);
            definitions++;
        }

        System.out.println("! Loading completed..." + "\n");

        System.out.println("===== DICTIONARY 340 JAVA =====");
        System.out.println("----- Keywords: " + keywords);
        System.out.println("----- Definitions: " + definitions + "\n");

        do {                       // Loop to allow user search
            String userInput;      // Used to store user input with word and parameters
            String word;           // Stores words
            String parameter;          //Stores parameters Parts of Speech, Distinct, Reverse
            String[] inputSplit;     //Splits up userInput to allow easy access to parameters, it breaks up into small little arraylist values, [0][1][2][3]...etc
            boolean itshouldPrint = true;
            inputCount++;

            System.out.print("Search [" + inputCount + "]: ");
            userInput = put.nextLine();
            inputSplit = userInput.split(" ");
            if (inputSplit.length > 0) {               //Checks to make sure the length is larger than 0, 0 = word
                word = inputSplit[0].toLowerCase();
            } else {                                    //Else it'll just give user the one word
                word = userInput.toLowerCase();
            }

            if (userInput.equalsIgnoreCase("!help") || userInput.equals("") || userInput.equals(" ")) {      //Checks if user input requests help or is blank
                System.out.println("   |");
                System.out.println("   PARAMETER HOW-TO, please enter:" + "\n"
                        + "   1. A search key -then 2. An optional part of speech -then" + "\n"
                        + "   3. An optional 'distinct' -then 4. An optional 'reverse'"
                );
                System.out.println("   |");
            } else if (userInput.equalsIgnoreCase("!q") || userInput.equalsIgnoreCase("!quit")) {          //end program
                looprun = false;
                System.out.println("-----THANK YOU-----");
                break;
                
            } else if (hashamp.containsKey(word)) {                                                           //Checks to make sure word is in the Hashmap
                ArrayList<Dictionary> pull = hashamp.get(word);                                            //Pull the definitions and stores in new ArrayList

                if (inputSplit.length > 1 && itshouldPrint) {                                        //Checks if there is more than 2 parameters
                    parameter = inputSplit[1].toLowerCase();

                    if (partOfSpeeches.contains(parameter)) {                                                //determined if list need to inputted parts of speech
                        pull = Arraylist.returnSamePartsOfSpeech(pull, parameter);
                    }
                    if (parameter.contains("reverse")) {                                                     //determined if list need to reverse the ArrayList
                        pull = Arraylist.reverseList(pull);
                    }
                    if (parameter.contains("distinct")) {                                                    //determined if list need to remove all duplicate values
                        pull = Arraylist.distinctRemove(pull);
                    } else if (!partOfSpeeches.contains(parameter) && !parameter.contains("reverse") && !parameter.contains("distinct")) {                                                                                                                   //if it does not contain any of the given parameters it will return errors and disregard it
                        System.out.println("   |");
                        System.out.println("   <The entered 2nd parameter '" + parameter + "' is NOT a part of speech.>");
                        System.out.println("   <The entered 2nd parameter '" + parameter + "' is NOT 'distinct'.>");
                        System.out.println("   <The entered 2nd parameter '" + parameter + "' is NOT 'reverse'.>");
                        System.out.println("   <The entered 2nd parameter '" + parameter + "' was disregarded.>");
                        System.out.println("   <The 2nd parameter should be a part of speech or 'distinct' or 'reverse'.>");
                        System.out.println("   |");
                    }
                }
                if (inputSplit.length > 2 && itshouldPrint) {                                                      //Checks to make sure its only Distinct or Reverse, or show error message
                    parameter = inputSplit[2].toLowerCase();
                    if (parameter.contains("reverse")) {
                        pull = Arraylist.reverseList(pull);
                    }
                    if (parameter.contains("distinct")) {
                        pull = Arraylist.distinctRemove(pull);
                    } else if (!parameter.contains("reverse") && !parameter.contains("distinct")) {
                        System.out.println("   |");
                        System.out.println("   <The entered 3rd parameter '" + parameter + "' is NOT 'distinct'.>");
                        System.out.println("   <The entered 3rd parameter '" + parameter + "' is NOT 'reverse'.>");
                        System.out.println("   <The entered 3rd parameter '" + parameter + "' was disregarded.>");
                        System.out.println("   <The 3rd parameter should be 'distinct' or 'reverse'.>");
                        System.out.println("   |");
                    }
                }
                if (inputSplit.length > 4 ) {                                                                             //Checks if there is more than 4 parameters, can not have more than 4 so it will show that
                    System.out.println("   |");
                    System.out.println("   PARAMETER HOW-TO, please enter:" + "\n"
                            + "   1. A search key -then 2. An optional part of speech -then" + "\n"                            // I DID NOT SWAP 4 AND 3 PLACE SO I STUCK HERE FOR ANOTHER 1 HOUR...
                            + "   3. An optional 'distinct' -then 4. An optional 'reverse'"
                    );
                    System.out.println("   |");
                    itshouldPrint = false;                                                                                //Sets shouldPrint so that the following statements will not be system error and will not print definitions.
                }

                if (inputSplit.length > 3 && itshouldPrint) {                                                      //Checks to make sure its only Reverse, or show error message
                    parameter = inputSplit[3].toLowerCase();
                    if (parameter.contains("reverse")) {
                        pull = Arraylist.reverseList(pull);
                    } else if (!parameter.contains("reverse")) {
                        System.out.println("   |");
                        System.out.println("   <The entered 4th parameter '" + parameter + "' was disregarded.>");          //WHEN I AM NOT SWAP THOSE 2 BLOKCS IT KEEPS PRINT 4TH WHEN THERE IS 5 PARAMTERS ALREADY
                        System.out.println("   <The 4th parameter '" + parameter + "' is NOT 'reverse'.>");
                        System.out.println("   <The 4th parameter should be 'reverse'.>");
                        System.out.println("   |");
                    }
                }


                if (!pull.isEmpty() && itshouldPrint ) {                                                            //Checks to make sure arraylist isnt empty
                    System.out.println("   |");
                    for (Dictionary data : pull) {                                                                       //Loops through the ArrayList for the definitions and prints them
                        System.out.println("   " + data);                                                               // see if functions ok to print out
                    }
                    System.out.println("   |");
                } else if (pull.isEmpty() && itshouldPrint) {                                                        //If empty prints not found message
                    System.out.println("   |");
                    System.out.println("   <NOT FOUND> To be considered for the next release. Thank you.");
                    System.out.println("   |");
                    System.out.println("   |");
                    System.out.println("   PARAMETER HOW-TO, please enter:" + "\n"
                            + "   1. A search key -then 2. An optional part of speech -then" + "\n"
                            + "   3. An optional 'distinct' -then 4. An optional 'reverse'"
                    );
                    System.out.println("   |");
                }
            } else if (!hashamp.containsKey(word)) {                                                                          //If word is not in Hashmap it will print not found
                System.out.println("   |");
                System.out.println("   <NOT FOUND> To be considered for the next release. Thank you.");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   PARAMETER HOW-TO, please enter:" + "\n"
                        + "   1. A search key -then 2. An optional part of speech -then" + "\n"
                        + "   3. An optional 'distinct' -then 4. An optional 'reverse'"
                );
                System.out.println("   |");
            }
        } while (looprun);
    }
}

