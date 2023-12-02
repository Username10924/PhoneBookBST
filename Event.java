import java.util.Date;
public class Event implements Comparable<Event>{
	public boolean isEvent;
	public BST<Contact> contacts;
	public String title;
	public Date date;
	public String time;
	public String location;
	public Event() {
		contacts = new BST<Contact>();
	}
	public Event(String title, String date, String time, String location, boolean isEvent) {
		super();
		this.title = title;
		this.date = new Date(date);
		this.time = time;
		this.location = location;
		contacts = new BST<Contact>();
		this.isEvent = isEvent;
	}

	public String toString() {
		if(isEvent)
			System.out.println("\nEvent: ");
		else
			System.out.println("\nAppointment: ");
		System.out.println("title: " + title);
		System.out.println("contacts involved: ");
		contacts.traversePrint();	
        return"date and time (MM/DD/YYYY HH:MM): " + date + " " + time +
       "\nlocation: " + location + "\n";
    }

	public int compareTo(Event e) {
        return (this.title.compareToIgnoreCase(e.title));
	}
}
