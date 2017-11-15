
public class Ebook extends Readable{

	public Ebook(String Name, String authorName, String type, int price, int sNo) {
		super(Name, authorName, type, price, sNo);
		// TODO Auto-generated constructor stub
	}

	public String getType(){
		return super.type;
	}

	public int getPrice(){
		
		return (int) Math.round((super.price*1.13));
		//no need for the delivery fee
	}
	public void show(){
		System.out.print(super.Name +" : ");
		System.out.print("The base price: "+ super.price + " | ");
		System.out.print("The tax amount: "+ super.price * 0.13+ " \n");
		//System.out.print("The Shipping amount: "+ 10.0); 
	}

}
