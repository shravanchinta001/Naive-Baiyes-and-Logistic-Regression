import java.util.HashSet;
import java.util.Set;


// Author : Shravan Chinta
// Date : 9/30/2014
// purpose : Implementing Text classification

public class Dictionary {
	
	private static Set<String> dictionary=new HashSet<String>();
	
	
	public static Set<String> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(HashSet<String> dict) {
		dictionary=dict;
	}
	
	public static boolean dictionaryContains(String word){
		if(dictionary.contains(word))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void dictionaryAdd(String word){
		dictionary.add(word);
	}
	
	public static void dictionaryRemove(String word){
		dictionary.remove(word);
	}
	

}
