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
	public int compareTo(Event e) {
        return (this.title.compareToIgnoreCase(e.title));
	}
}
