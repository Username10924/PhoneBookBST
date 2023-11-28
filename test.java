
public class test{
	static BST<Contact> tree = new BST<Contact>();
	static BST<Event> eventTree = new BST<Event>();
	public static void main(String[] args) {
		Contact c1 = new Contact("bro majed", "12133", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c2 = new Contact("ahmed ali", "421124", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c3 = new Contact("khaled yes", "554144", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		tree.insert(c1.name, c1);
		tree.insert(c2.name, c2);
		Event e1 = new Event("graduation", "2022/10/10", "10:00", "ksu", true);
		Event e2 = new Event("sss", "2022/10/10", "11:00", "ksu", false);
		Event e3 = new Event("sss", "2022/10/10", "11:00", "ksu", false);
		System.out.println(c1.addAppointment(e1));
		System.out.println(c1.addEvent(e1));
		System.out.println(c1.addAppointment(e2));
		System.out.println(c1.addAppointment(e3));
		e1.contacts.insert(c1.name, c1);
		e1.contacts.insert(c1.name, c1);
		e1.contacts.insert(c2.name, c2);
		e1.contacts.traversePrint();
	}

}
