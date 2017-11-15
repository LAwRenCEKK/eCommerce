import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.io.Console;
public class Main {

	//public static UserInterface userInterface;
	public static void main(String[] args) throws IOException {
		UserInterface userInterface = new UserInterface();
		Page1(userInterface);

	}

	private static void Page1(UserInterface userInterface) throws IOException{
		System.out.println("welcome to the easy shopping website");
		System.out.println("Press 1 to login: ");
		System.out.println("Press 2 to up signup: ");
		Scanner scanner = new Scanner( System.in );
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("1")){
			System.out.println("You chose the login option");
			Page2(userInterface);
			//Page 2 would be the login page
		}else if(input.equalsIgnoreCase("2")) {
			Page3(userInterface);
		}else{
			System.out.println("Invalid input, try again\n");
			Page1(userInterface);
		}

		//return 3;//page 3 would be the sign up page

	}
	
	
	//Login Page 
	//also handle Get Password/username back functions
	//Applied Java IO
	//reach out to Page3() and Page4() 
	private static void Page2(UserInterface userInterface) throws IOException{
		 
		System.out.println("welcome to the login page");
		System.out.println("Please type in your username: ");
		Scanner scanner = new Scanner( System.in );
		String userName = scanner.nextLine();
		BufferedReader br = new BufferedReader(new FileReader("/Users/lawrence/Desktop/Database/UserDatabase.txt"));

		try {
			String line = br.readLine();

			//while loop go through each row of data in the text file 
			while (line != null) {
				String names = line;
				String[] namesList = names.split(";");

				if (namesList[0].equalsIgnoreCase(userName)){
					System.out.println("Hi " + namesList[0] + " Please enter you passwords");
					String passWord = scanner.nextLine();
					if (namesList[1].equalsIgnoreCase(passWord)){
						System.out.println("Login Successfully");
						getShoppingCart(namesList[0]);
						Page5(userInterface,getShoppingCart(namesList[0]));
					}else{
						System.out.println("Wrong Password");
						System.out.println("Press 1 to get back the password");
						System.out.println("Press 2 to try again");
						System.out.println("Press 3 to sign up ");
						String choice = scanner.nextLine();
						switch (choice){
						case "1" :Page4(userInterface);		//System.out.println("Go to Page 4 to get back the userName");
						break;
						case "2" : continue;
						case "3" : Page3(userInterface);     //System.out.println("Go to Page 3 to sign up");
						break;
						default:
							System.out.println("Invalid input");
							continue;
						}
					}
				}else {
					line = br.readLine();
				}
			}

			System.out.println("Can not find " + userName );
			System.out.println("Press 1 to get back the userName");
			System.out.println("Press 2 to try again");
			System.out.println("Press 3 to sign up ");
			String choice = scanner.nextLine();
			switch (choice){
			case "1" : Page4(userInterface);	//System.out.println("Go to Page 4 to get back the userName");
			break;
			//Recursion
			case "2" : Page2(userInterface);
			case "3" : Page3(userInterface);     //System.out.println("Go to Page 3 to sign up");
			break;
			default:
				System.out.println("Invalid input");
				Page2(userInterface);
			}
		}


		finally{
			br.close();
		}
	}

	
	//Sign up Page
	//Load the new user information into the database 
	//reach out to Page5()
	private static void Page3(UserInterface userInterface) throws IOException {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("User Sign Up Page: ");
		System.out.println("Enter the username");
		Scanner scanner = new Scanner( System.in );
		String userName = scanner.nextLine();
		System.out.println("Enter the passwords");
		String passWord = scanner.nextLine();
		System.out.println("Enter the email address");
		String email = scanner.nextLine();

		BufferedWriter br = new BufferedWriter(new FileWriter("/Users/lawrence/Desktop/Database/UserDatabase.txt", true));
		br.append(userName +";"+passWord +";"+email+ "\n");
		br.close();

		Page5(userInterface, getShoppingCart(userName));
	}

	
	//Forget userName or passWords
	//Applied recursion
	//Reach out to Page3()
	private static void Page4(UserInterface userInterface) throws IOException {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter the registered email to retrive the password or username:");
		Scanner scanner = new Scanner(System.in);
		String email = scanner.nextLine();
		BufferedReader br = new BufferedReader(new FileReader("/Users/lawrence/Desktop/Database/UserDatabase.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			if (email.equalsIgnoreCase(line.split(";")[2])){
				System.out.println("The username is "+ line.split(";")[0]);
				System.out.println("The password is "+ line.split(";")[1]);
				System.out.println("Press any to login");
				if (scanner.nextLine() != ""){
					for (int i = 0; i < 50; ++i) System.out.println();
					Page1(userInterface);
				}
			}else{
				System.out.println("Can not find the given email");
				System.out.println("Press 1 to try again");
				System.out.println("Press 2 to sign up");
				String input = scanner.nextLine();
				if (input == "1"){
					Page4(userInterface);
				}else if (input == "2"){
					Page3(userInterface);
				}else{
					System.out.println("Invalid Input");
					Page4(userInterface);
				}
			}
		}
	}

	
	//Contents page
	//Page6() Page7() 
	private static void Page5(UserInterface userInterface, ShoppingCart theCart) throws IOException{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome " + theCart.getUsername());

		System.out.println("1. View Items By Category");
		System.out.println("2. View Shopping Cart");
		System.out.println("3. Sign out");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		switch(choice){
		case "1" : Page6(userInterface, theCart);
		break;
		case "2" : Page9(userInterface, theCart);
		break;
		case "3": System.out.println("Sign out succefully");
		Page1(userInterface);
		break;
		default:
			System.out.println("Invalid input");
			Page5(userInterface, theCart);
			break;
		}
	}

	
	//Readable or Audio
	//Page8() page7() Page5(return)
	private static void Page6(UserInterface userInterface, ShoppingCart theCart) throws IOException{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1. Readables");
		System.out.println("2. Audio");
		System.out.println("Choose your option:");
		System.out.println("Press -1 to return previous page");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		switch(choice){
		case "1" : Page8(userInterface, theCart);
		break;
		case "2" :// 2. Audio Page7(userInterface, theCart);
			break;
		case "-1": Page5(userInterface,theCart);
		break;
		default:
			System.out.println("Invalid input");
			Page6(userInterface, theCart);
			break;
		}

	}

	
	//Readable
	//Inheritance Polymorphism (dynamic bonding)
	private static void Page8(UserInterface userInterface, ShoppingCart theCart) throws IOException{

		userInterface.getReadables();
		String[] readableInfo = userInterface.showReadable();
		System.out.println("Choose your option: ");
		System.out.println("Press -1 to return previous page");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		int size = readableInfo.length;

		if (!input.equalsIgnoreCase("-1")){

			for (int i=0 ; i < size; i++){
				String[] forEachItem = readableInfo[i].split(",");
				if(input.equalsIgnoreCase(forEachItem[0].trim())){
					switch (forEachItem[5].trim()){
					case "Ebook" : Ebook theEbook = new Ebook(forEachItem[1].trim(),forEachItem[2].trim(),
							forEachItem[5].trim(),Integer.parseInt(forEachItem[3].trim()),
							Integer.parseInt(forEachItem[0].trim()));
					while(true){
						System.out.println("Enter the quantity: ");
						int qty = scanner.nextInt();
						if (qty > 0 && qty <= Integer.parseInt(forEachItem[4].trim())){
							System.out.println(qty + " of " + forEachItem[1].trim() +" added to the cart");
							
							/////////////////////////////
							theCart.addItem(theEbook, qty);
							/////////////////////////////
							System.out.println("Press 0 to Check out \nPress any to continue shopping");
							Scanner scanner2 = new Scanner(System.in);
							String input2 = scanner2.nextLine();
							if(input2.equalsIgnoreCase("0")){
								//go to check out page
								Page9(userInterface,theCart);
								
							}else{//continue shopping
								userInterface.readable.clear();
								Page6(userInterface, theCart);
							}
							break;
						}else{
							System.out.println("The quantity you entered is invalid");
							continue;
						}
					}

					break;
					
					
					
					case "Book" : Book theBook = new Book(forEachItem[1].trim(),forEachItem[2].trim(),
							forEachItem[5].trim(),Integer.parseInt(forEachItem[3].trim()),
							Integer.parseInt(forEachItem[0].trim()));
					while(true){
						System.out.println("Enter the quantity: ");
						int qty = scanner.nextInt();
						if (qty > 0 && qty <= Integer.parseInt(forEachItem[4].trim())){
							System.out.println(qty + " of " + forEachItem[1].trim() +" added to the cart");
							
							////////////////////////////
							theCart.addItem(theBook, qty);
							/////////////////////////////
							System.out.println("Press 0 to Check out \nPress any to continue shopping");
							Scanner scanner2 = new Scanner(System.in);
							String input2 = scanner2.nextLine();
							if(input2.equalsIgnoreCase("0")){
								//go to check out page
								Page9(userInterface,theCart);
								
							}else{//continue shopping
								userInterface.readable.clear();
								Page6(userInterface, theCart);
							}
							break;
						}else{
							System.out.println("The quantity you entered is invalid");
							continue;
						}
					}
					break;
					default:
					}
				}
			}
		}else{
			userInterface.readable.clear();
			Page6(userInterface, theCart);
			}
	}	

	private static void Page7(UserInterface userInterface, ShoppingCart Cart) throws IOException{

	}

	//Check out page
	private static void Page9(UserInterface userInterface, ShoppingCart Cart) throws IOException{
	//go to the check out page
	Cart.getContents();
	System.out.println("Please confirm by pressing 1\nPress any to continue shopping");
	Scanner scanner = new Scanner(System.in);
	String input = scanner.nextLine();
	if (input.equalsIgnoreCase("1")){
		int total = Cart.checkOut();
		System.out.println("The total payment is " + total);
		System.out.println("Press 1 to place order or press 2 to see the breakdown");
		String input3 = scanner.nextLine();
		if (input3.equalsIgnoreCase("1")){
			System.out.println("The order is placed");
			Page1(userInterface);
		}else if (input3.equalsIgnoreCase("2")){
			System.out.println("Here is breakdown");
			Page10(userInterface, Cart);
		}
	}else{
		Page5(userInterface,Cart);
	}

}

	//show the break down page
	private static void Page10(UserInterface userInterface, ShoppingCart Cart) throws IOException{
	 
		Cart.Breakdown();
		System.out.println("Press 1 to confirm and place order");


	}

	public static ShoppingCart getShoppingCart(String userName) throws IOException{
	ShoppingCart cart = new ShoppingCart(userName);
	cart.buildCart();
	return cart;

}
}
