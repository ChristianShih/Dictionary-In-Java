import java.util.*;
public class Arraylist
{
    public static ArrayList<Dictionary> distinctRemove(ArrayList<Dictionary> data)
    {
        ArrayList<Dictionary> sortedData = new ArrayList<Dictionary>();
        ArrayList<Dictionary> temporary;

        ArrayList<String> partOfSpeeches = new ArrayList<String>(Arrays.asList("noun", "verb", "adjective", "adverb", "pronoun", "preposition", "conjunction", "interjection")); //   Holds ArrayList of parts of speech to compare to

        for(String list : partOfSpeeches) {
            temporary = new ArrayList<Dictionary>();                                                                         // distinct is pretty much the function to remove duplicates, words that has same define and part of speech will be remove
            temporary = returnSamePartsOfSpeech(data, list);                                                                 // loop through part of speech, call to get define with same POS and put into new arraylist, repeat will run through all the POS
            temporary = removesDuplicates(temporary);
            sortedData.addAll(temporary);
        }
        Collections.sort(sortedData);
        return sortedData;                                                                                              // return it
    }

    public static ArrayList<Dictionary> removesDuplicates(ArrayList<Dictionary> data) {                               //Removes all duplicate definitions from given list
        ArrayList<Dictionary> sortedData;
        String definitions;
        HashMap<String, Dictionary> hashmap = new HashMap<String, Dictionary>();

        for(Dictionary list : data) {
            definitions = list.getDefinition();
            if(!hashmap.containsKey(definitions))
            {
                hashmap.put(definitions, list);
            }
        }
        sortedData = new ArrayList<Dictionary>(hashmap.values());
        Collections.sort(sortedData);
        return sortedData;
    }

    public static ArrayList<Dictionary> returnSamePartsOfSpeech(ArrayList<Dictionary> data, String partOfSpeech) {  //Returns all definitions with same parts of speech
        ArrayList<Dictionary> sortedData = new ArrayList<Dictionary>();

        for(Dictionary list : data) {
            if(list.getPartOfSpeech().equalsIgnoreCase(partOfSpeech)) {
                sortedData.add(list);
            }
        }
        return sortedData;
    }

    public static ArrayList<Dictionary> reverseList(ArrayList<Dictionary> data) {                                   //Reverses all values in the list
        ArrayList<Dictionary> sortedData = new ArrayList<Dictionary>();
        for(int i = data.size() -1 ; i >= 0 ; i--) {
            sortedData.add(data.get(i));
        }
        return sortedData;
    }
}
