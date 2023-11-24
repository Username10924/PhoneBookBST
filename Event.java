import java.util.Date;

public class Event implements Comparable<Event>{
	public boolean isEvent;
	public BST<Contact> contacts;
	public String title;
	public Date date;
	public String time;
	public String location;
	public Event() {

	}
	public int compareTo(Event e) {
        return (this.title.compareToIgnoreCase(e.title));
	}
}
