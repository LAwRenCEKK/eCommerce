import java.io.*;
import java.util.ArrayList;
public class ShoppingCart extends User{

	private ArrayList<Item> contentFromShoppingCart = new ArrayList<Item>();
	public ShoppingCart(String userName) {
		super(userName);
		// TODO Auto-generated constructor stub
		//if there this user is new, build a shopping cart for him
	}
	public void buildCart() throws IOException{
		super.getUsername();
		File file = new File("/Users/lawrence/Desktop/Database", super.getUsername() + ".txt");
		file.createNewFile(); 
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/lawrence/Desktop/Database/" + super.getUsername() + ".txt"));
	    writer.write("Shopping Cart for " + super.getUsername() + "\n");
	    writer.write("sNO  Name   Price Author Type ");
	    
	    writer.close();
	}
	public void getContents() throws IOException{
		//Show all the thing from the Cart
		FileReader filereader = new FileReader("/Users/lawrence/Desktop/Database/"+ super.getUsername() +".txt");
		String line = null;
		BufferedReader bufferedReader = new BufferedReader(filereader);
		while ((line = bufferedReader.readLine()) != null){
			System.out.println(line);
		}
		bufferedReader.close();
	}
	public void addItem(Item item, int Quantity) throws IOException{
		//String[] splitArray = itemInfo.split(",");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/lawrence/Desktop/Database/"+ super.getUsername() +".txt", true));
		
		for (int i = 0; i < Quantity; i++){
		writer.append("\n");
		writer.append(item.getInfo());
		contentFromShoppingCart.add(item);
		}
		writer.close();
	}
	
	public void Breakdown(){
		for (Item item : this.contentFromShoppingCart){
			item.show();
		}
	}
	public int checkOut(){
		int total = 0;
		for (Item item : this.contentFromShoppingCart){
			total = total + item.getPrice();
		}
		return total;
	}
}

