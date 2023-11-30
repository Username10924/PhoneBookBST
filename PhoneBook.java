import java.util.Date;
import java.util.Scanner;
public class PhoneBook {
	public static BST<Contact> listC = new BST<Contact>();
    public static LinkedListADT<Event> listE = new LinkedListADT<Event>();
    static Scanner input = new Scanner(System.in);
    
    
	public static boolean addContact() {
        System.out.print("Enter the contact's name: ");
            // filler solves java bug of skipping input.nextLine()
            String name = input.nextLine();

        System.out.println("Enter the contact's phone number: ");
        String phone = input.nextLine();

        System.out.println("Enter the contact's email address: ");
        String email = input.nextLine();
        System.out.println("Enter the contact's address: ");
        String address = input.nextLine();
        System.out.println("Enter the contact's birthday: ");
        String bday = input.nextLine();
        System.out.println("Enter any notes for the contact: ");
        String notes = input.nextLine();

        Contact c = new Contact(name, phone, email, address, bday, notes);
        if(listC.traversePhone(phone) || !listC.insert(name, c)) {
            System.out.println("contact already exist!");
            return false;
        }
        System.out.println("Contact added successfully!");
        return true;
    }
	
	// adds event/appointment for contact and event
	public static boolean scheduleEvent() {
		String cNames = null;
		Event e = new Event();
		System.out.println("Enter type: \n1.event \n2.appointment");
		String choice = input.next();
		boolean flag = true;
		
		while(flag) {
			switch(choice) {
			case "1":
				flag = false;
				e.isEvent = true;
				break;
			case "2":
				flag = false;
				e.isEvent = false;
				break;
			default:
				System.out.println("Wrong input");
			}
		}
		
		System.out.println("Enter event title: ");
		// filler solves java bug of skipping input.nextLine()
		String filler = input.nextLine();
		e.title = input.nextLine();
		
		if(!e.isEvent) {
			System.out.println("Enter contact name: ");
			String cName = input.nextLine();
			if(!listC.findkey(cName, listC.root)) {
				System.out.println("Contact doesn't exist");
				return false;
			}
			e.contacts.insert(cName, listC.retrieve());
		}
		else {
			System.out.println("Enter contact names seperated by commas: "); // Ahmed, Faisal, Khaled
			cNames = input.nextLine();
			String arrName[] = cNames.split(","); // [Ahmed, Faisal, Khaled]
			for(int i = 0; i < arrName.length; i++) {
				if(!listC.findkey(arrName[i], listC.root)) {
					System.out.println(arrName[i] + " was not found");
					return false;
				}
			}
			for(int i = 0; i < arrName.length; i++) {
				listC.findkey(arrName[i], listC.root);
				e.contacts.insert(arrName[i], listC.retrieve());
			}
		}
		
		// try and catch here
		
		System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
		e.date = new Date(input.next());
		e.time = input.next();
		input.nextLine();
		
		System.out.println("Enter event location: ");
		e.location = input.nextLine();
		if(!e.isEvent) {
			if(!listC.retrieve().addEvent(e)) {
				System.out.println("Time conflict");
				return false;
			}
		}
		
		else {
			String arrName[] = cNames.split(",");
			for(int i = 0; i < arrName.length; i++) {
				listC.findkey(arrName[i], listC.root);
				if(!listC.retrieve().addEvent(e))
					System.out.println("Time conflict for " + listC.retrieve().name);
			}
		}
		listE.insertSort(e);
		
		System.out.println("Event scheduled");
		return true;
	}
	
		
	public static void main(String[] args) {
		addContact();
		addContact();
		addContact();
		listC.traversePrintFName("ahmed");

	}

}
