
public class Readable extends Item {
	protected String authorName;
	protected String Name;
//ß	protected String type;
	public Readable(String Name, String authorName, String type, int price, int sNo) {
		this.authorName = authorName;
		this.Name = Name;
		super.type = type;
		super.price = price;
		super.sNo = sNo;
	}
	public String getInfo(){
		return super.sNo+ " " + this.Name +" "+ super.price+ " " + this.authorName +" "+ this.type;
	}
	public int getPrice(){
		return super.price;
	}
	public String getType(){
		return this.type;
	}
	public void show(){
		System.out.println("Show price");
	}

}
