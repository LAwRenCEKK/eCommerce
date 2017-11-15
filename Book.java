
public class Book extends Readable{
	
	public Book(String Name, String authorName, String type, int price, int sNo) {
		super(Name, authorName, type, price, sNo);
	}
	
	public String getType(){
		return super.type;
		}

	
	public int getPrice(){
		//add the tax and delivery fee 
		
		return (int) Math.round((super.price*1.13 + 10));
	}
	public void show(){
		System.out.print(super.Name +" : ");
		System.out.print("The base price: "+ super.price + " | ");
		System.out.print("The tax amount: "+ super.price * 0.13+ " | ");
		System.out.print("The Shipping amount: "+ 10.0 + "\n"); 
	}

}
