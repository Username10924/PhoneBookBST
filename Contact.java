
public class Contact implements Comparable<Contact>{
	public String name;
	public String phoneNumber;
	public String email;
	public String address;
	public String birthday;
	public String notes;
	public BST<Event> events;
	public Contact() {
		this.name = " ";
		this.phoneNumber = " ";
		this.email = " ";
		this.address = " ";
		this.birthday = " ";
		this.notes = " ";
		events = new BST<Event>();
	}
	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
		events = new BST<Event>();
	}
	
	public boolean addEvent(Event e) {
		if(!e.isEvent)
			return false;
		if(events.empty()) {
			events.insert(e.title, e);
			return true;
		}
		if(events.traverseDateTime(e.date, e.time))
			return false;
		events.insert(e.title, e);
		return true;
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
