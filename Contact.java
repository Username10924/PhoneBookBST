
public class Contact implements Comparable<Contact>{
	public String name;
	public String phoneNumber;
	public String email;
	public String address;
	public String birthday;
	public String notes;
	public LinkedListADT<Event> events;
	//public Event appointment;
	public Contact() {
		this.name = " ";
		this.phoneNumber = " ";
		this.email = " ";
		this.address = " ";
		this.birthday = " ";
		this.notes = " ";
		events = new LinkedListADT<Event>();
	}
	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
		events = new LinkedListADT<Event>();
	}
	
	public boolean addEvent(Event e) {		
		if(events.isEmpty()) {
			events.insertSort(e);
			return true;
		}
		events.findFirst();
		while(events.retrieve() != null) {
			if(events.retrieve().date.compareTo(e.date) == 0)
				if(events.retrieve().time.compareTo(e.time) == 0) 
					return false;
			events.findNext();
		}
		events.insertSort(e);
		return true;
	}
	
	/*public boolean addAppointment(Event e) {
		if(e.isEvent || appointment != null)
			return false;
		appointment = e;
		return true;
	}*/

	
	public String toString() {

		return "\nName: " + name + 
		"\nPhone number: " + phoneNumber + 
		"\nEmail address: " + email + 
		"\nAddress: " + address +
		 "\nBirthday: " + birthday +
		  "\nNotes: " + notes + "\n"; 

	}

	public void printEvent() {

		if(events == null) {
			System.out.println("no events exist!");
			return;
		}

		events.findFirst();
		while(events.retrieve() != null) {
			System.out.println(events.retrieve());
			events.findNext();
		}
	}
	public int compareTo(Contact o) {   
        return (this.name.compareTo(o.name));   
	}
}
