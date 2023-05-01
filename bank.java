import java.util.*;
import java.io.*;
class Account{
	public String name;
	public String address;
	protected String pin;

	public void deposit() {
		
	}
	public void withdraw() {
		
	}
	public void pinchange(String newpin) {
		if(newpin.length()==4) {
			if(newpin.matches("\\d+")){
				if (pin.compareTo(newpin)==0) {
					System.out.println("same pin");
				}else {
					this.pin=newpin;
					System.out.println("pin changed successfully");
				}
			}else {
				System.out.println("only digits are acceptable");
			}
		}else {
			System.out.println("lenth not matcing");
		}
	}
	
}

class saving extends Account{
	private float bal;
	saving(String name, float bal,String place, String pin){
		this.name = name;
		this.bal = bal;
		this.address = place;
		this.pin = pin;
	}
	public void viewbal() {
		if(this.bal>0) {
			System.out.println(this.bal);
		}
	}
	public void deposit(float amount) {
		if(amount>0 && amount<20000) {
			this.bal+=amount;
		}
	}
	public void withdraw(float amount) {
		if(this.bal>amount) {
		if(amount>0 && amount<10000) {
			this.bal-=amount;;
		}
		}
	}
	public void store() {
		try {
			FileWriter writer = new FileWriter("record.txt",true);
			writer.write(this.name+","+String.valueOf(this.bal)+","+this.address+","+this.pin+"\n");
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	


}
public class bank {

	
	public static void Deleterecord(File filename,String[] user) {
				File file = filename;
				BufferedReader reader = null;
				StringBuilder stringBuilder = new StringBuilder();
		
				try {
					reader = new BufferedReader(new FileReader(file));
					String line;
					int count =0;
					while ((line = reader.readLine()) != null) {
						if (line.startsWith(user[0]) && count<1) {
							line ="";
							count++;
							stringBuilder.append(line);
							continue;
						}
						stringBuilder.append(line);
						stringBuilder.append(System.lineSeparator());
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		
				FileWriter writer = null;
				try {
					writer = new FileWriter(file);
					writer.write(stringBuilder.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null) {
							writer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		
		
	
	
public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the choice(1,2):");
	int choice=sc.nextInt();
	switch(choice) {
	case 1:
		System.out.println("create account:");
		System.out.println("Enter the name");
		String name = sc.next();
		System.out.println("Enter amount:");
		float amount = sc.nextFloat();
		System.out.println("Enter the address:");
		String address = sc.next();
		System.out.println(" Enter pin number:");
		String pin = sc.next();
		System.out.println("Renter the pin number :");
		String repin = sc.next();
		if(pin.compareTo(repin)==0) {
			saving s1 = new saving(name,amount,address,pin);
		
		System.out.println("option1:view bal /n option2:withdraw /n option3:deposit /n option4:pin change /n option5:exit the account");
		int ch1 = sc.nextInt();
		while(ch1!=5) {
			if(ch1==1) {
				s1.viewbal();
			}
			else if(ch1==2) {
				System.out.println("enter the amount");
				float amt = sc.nextFloat();
				s1.withdraw(amt);
			}
			else if(ch1==3) {
				System.out.println("Enter amount to deposit");
				float depositamt = sc.nextFloat();
				s1.deposit(depositamt);
			}
			else if(ch1==4) {
				System.out.println("enter your new pin");
				String newpinn = sc.next();
				s1.pinchange(newpinn);
			}
			else {
				System.out.println("wrong input");
			}
			System.out.println("enter your choice");
			ch1=sc.nextInt();
		
		}
		s1.store();
		}
		break;
	case 2:
		System.out.println("log in account:");
		System.out.println("Enter the name:");
		String name1 = sc.next();
		// System.out.println("Account type:");
		// String type = sc.next();
		System.out.println("enter the pin1:");
		String pin1 = sc.next();
		try {
			File obj = new File("record.txt");
			Scanner Reader = new Scanner(obj);
			while(Reader.hasNextLine()) {
				String[] data = Reader.nextLine().split(",");
					if(name1.compareTo(data[0])==0) {
						if(pin1.compareTo(data[3])==0) {
							saving s1 = new saving(data[0], Float.parseFloat(data[1]),data[2],data[3]);
							System.out.println("option1:view bal /n option2:withdraw /n option3:deposit /n option4:pin change /n option5:exit the account");
							int ch1 = sc.nextInt();
							while(ch1!=5) {
								if(ch1==1) {
									s1.viewbal();
								}
								else if(ch1==2) {
									System.out.println("enter the amount");
									float amt = sc.nextFloat();
									s1.withdraw(amt);
								}
								else if(ch1==3) {
									System.out.println("Enter amount to deposit");
									float depositamt = sc.nextFloat();
									s1.deposit(depositamt);
								}
								else if(ch1==4) {
									System.out.println("enter your new pin");
									String newpinn = sc.next();
									s1.pinchange(newpinn);
								}
								else {
									System.out.println("wrong input");
								}
								System.out.println("enter your choice");
								ch1=sc.nextInt();
							
							}
							Reader.close();
							s1.store();
							Deleterecord(obj,data);
							break;
						}
					
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		break;
	default:
		System.out.println("enter correct option");
		break;
		
		}
	}
	
}
