package ie.gmit.sw;
import java.io.*;
import java.util.*;
import java.net.*;
public class Menu {
	
	private Scanner s = new Scanner(System.in);
	private String FileName = " ";
	private String URLName = " ";
	public String newFileName = "image";
	public int maxWords = 10;
	boolean keepGoing = true;
	boolean checkFile = false;
	boolean checkURL = false;
	public int isFile = 0;
	public int numWords = 0;
	String file = null;
	String url = null;
	String userChoice = null;
	String option;
	String URL;
	
	Parser parser = new Parser();
	

	public void show() throws Exception {
		parser.fileIgnoreParse();
		while (keepGoing) {
			printMenu();
			System.out.println("Please enter which option you want to use ");
			option = s.next();
			handle(option);
		}
	}
	
	public void printMenu() {
		System.out.println("<><><><><><><><><><>Word Cloud<><><><><><><><><><><>");
		System.out.println("<>  1) Enter Image Name                           <>");
		System.out.println("<>  2) Enter Number Of Words To Display           <>");
		System.out.println("<>  3) Select File or URL                         <>");
		System.out.println("<>  4) Exit                                       <>");
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
	}
	
	public void handle(String option) throws FileNotFoundException, Exception {
		if (option.equals("4")) {
			keepGoing = false;
		}
		else if (option.equals("1")) {
			System.out.println("Please enter the Name for the image");
			newFileName = s.next();
			System.out.println(newFileName);
			
			
			
		}
		else if (option.equals("2")) {
			System.out.println("Please enter the Max Words for the Image");
			maxWords = s.nextInt();
			System.out.println(maxWords);
		}
		else if (option.equals("3")) {
			System.out.println("Please choose ether (f) for File or (u) for URL");
			userChoice = s.next().toLowerCase();
			
			if (userChoice.equals("f"))
			{
				isFile = 1;
				while (!checkFile) {
					System.out.println("Please enter the File Name");
					file = s.nextLine();
					if (new File(file).isFile()) {
						checkFile = true;
						break;
					}
					else
					{
						System.out.println("File could not be found please try again");
					}
				}
				FileName = file;
			}
			else if (userChoice.equals("u")) {
				isFile =0;
				while (!checkURL) {
					System.out.println("Please enter the URL Name");
					url = s.nextLine();
					if (isValid(url)) {
						checkURL = true;
						break;
					}
					else
					{
						System.out.println("URL could not be found please try again");
					}
				}
				URLName = url;
			}
			if (isFile == 1) {
				parser.fileInputParse(new FileInputStream(getFile()),newFileName,maxWords);
			} 
			else if(isFile == 0) {
				URL = getURL();
				parser.URLInputParser(URL,newFileName,maxWords);
			}
		}
	}
	
	

	public String getFile()
	{
		return FileName;
	}
	public String getURL()
	{
		return URLName;
	}
	public String getNewName() {
		
		return newFileName;
	}
	public int getMaxWords() {
		return maxWords;
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//URL validation , this has been taken from https://www.geeksforgeeks.org/check-if-url-is-valid-or-not-in-java/
	//No author to be seen
	/* Returns true if url is valid */
    public static boolean isValid(String url) 
    { 
        /* Try creating a valid URL */
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
        // If there was an Exception 
        // while creating URL object 
        catch (Exception e) { 
            return false; 
        } 
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
	
	

