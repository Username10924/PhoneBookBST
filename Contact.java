
public class Contact implements Comparable<Contact>{
	public String name;
	public String phoneNumber;
	public String email;
	public String address;
	public String birthday;
	public String notes;
	public Contact() {
		this.name = " ";
		this.phoneNumber = " ";
		this.email = " ";
		this.address = " ";
		this.birthday = " ";
		this.notes = " ";
	}
	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
	}
	public String toString() {

		return "\nName: " + name + 
		"\nPhone number: " + phoneNumber + 
		"\nEmail address: " + email + 
		"\nAddress: " + address +
		 "\nBirthday: " + birthday +
		  "\nNotes: " + notes + "\n"; 

	}
	public int compareTo(Contact o) {   
        return (this.name.compareTo(o.name));   
	}
}
