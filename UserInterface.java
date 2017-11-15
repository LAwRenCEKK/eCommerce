import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
	
	public ArrayList<String> readable = new ArrayList<String>();
	

	public void getReadables() throws FileNotFoundException{
		
		Scanner bookFile = new Scanner(new File("/Users/lawrence/Desktop/Database/Book.txt"));
		Scanner ebookFile = new Scanner(new File("/Users/lawrence/Desktop/Database/eBook.txt"));
		//ArrayList<String> readable = new ArrayList<String>();
		while (bookFile.hasNextLine()){
			this.readable.add(bookFile.nextLine() + ", Book" );
			}
		while (ebookFile.hasNextLine()){
			this.readable.add(ebookFile.nextLine() + ", Ebook");
			}
		
		bookFile.close();
		ebookFile.close();
		}
	
	
	public String[] showReadable(){
		System.out.println("sNO,Name Item,Author,Price,Quantity,Type");
		String info_readable[] = new String[this.readable.size()];
		int j = 0;
		for (String i : this.readable){
			info_readable[j] = i;
			System.out.println(info_readable[j]);
			j++;
			}	
		return info_readable;
	}
	
	
}
