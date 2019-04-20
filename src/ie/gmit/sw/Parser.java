package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.net.*;

public class Parser {

	private static final String IGNORE_WORDS = "ignorewords.txt";
	
	private ArrayList<String> ignore = new ArrayList<>();
	private ArrayList<String> FileInput = new ArrayList<>();
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Parser For The File>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//Parser that reads in input from a text file and adds it to an Array List
	public void fileInputParse(InputStream in,String newFileName,int MaxWords ) throws Exception
	{
		System.out.println(MaxWords);
		System.out.println(newFileName);
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String next = null;
		//while loop that adds the value from the text file to an array string that is then passed into a method called updateArray
		while ((next = br.readLine())!= null) {
				String[] words = next.replaceAll("[^A-Za-z0-9 ]", "").split(" ");
				updateArray(words);
		}
		System.out.println("time (ms):" + (System.currentTimeMillis()-start));
		System.out.println("size of file" + FileInput.size());
		WordCloud printWC = new WordCloud();
		//creating a Word Cloud object to a create an Image
		printWC.GenerateImage(ignore,FileInput,MaxWords,newFileName);	
		
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Ignore Words Parser>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//method that reads in words from ignorewords.txt and adds them to an arrayList
	public void fileIgnoreParse() throws IOException {
		long start = System.currentTimeMillis();
			BufferedReader ignoreFile = new BufferedReader(new FileReader(IGNORE_WORDS));
			String next = null;

			while ((next = ignoreFile.readLine()) != null) {

				if (next.isEmpty())
					continue;
				// Adding to an ArrayList is O(1)
				ignore.add(next.toLowerCase()); 
				//System.out.println(ignore.contains(next));
			}
			System.out.println("Ignore Words Parsed time (ms):" + (System.currentTimeMillis()-start));
			ignoreFile.close();
	}	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL Parser>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//Parser that reads in input from a url file and adds it to an Array List
	public void URLInputParser(String url,String newFileName,int MaxWords) throws Exception {
		URL urlLink = new URL(url);
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlLink.openStream()));
		String next = null;

		while ((next = br.readLine())!= null) {
			
				String[] words = next.replaceAll("[^A-Za-z0-9 ]", "").split(" ");
				updateArray(words);
		}
		System.out.println("time (ms):" + (System.currentTimeMillis()-start));
		System.out.println("size of file" + FileInput.size());
		//creating a Word Cloud object to a create an Image
		WordCloud printWC = new WordCloud();
		printWC.GenerateImage(ignore,FileInput,MaxWords,newFileName);	
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Populating the Map>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void updateArray(String[] words) {
		for (String word : words) {
			// Searching an ArrayList is 0(n)
			if (!ignore.contains(word)) { // Only add words that aren't blacklisted.					
					FileInput.add(word);//Adding to an Array List is O(1)
					
		}
	}
	}
	
}
		
	

