import java.util.Date;
import java.util.Scanner;
public class PhoneBook {
	public static BST<Contact> listC = new BST<Contact>();
    public static LinkedListADT<Event> listE = new LinkedListADT<Event>();
    static Scanner input = new Scanner(System.in);
    
    //1) add contact
	public static boolean addContact() {
        System.out.print("Enter the contact's name: ");
            // filler solves java bug of skipping input.nextLine()
			String filler = input.nextLine();
            String name = input.nextLine();

        System.out.print("Enter the contact's phone number: ");
        String phone = input.nextLine();

        System.out.print("Enter the contact's email address: ");
        String email = input.nextLine();
        System.out.print("Enter the contact's address: ");
        String address = input.nextLine();
        System.out.print("Enter the contact's birthday: ");
        String bday = input.nextLine();
        System.out.print("Enter any notes for the contact: ");
        String notes = input.nextLine();

        Contact c = new Contact(name, phone, email, address, bday, notes);
		//check if contact exist if not then insert
        if(listC.traversePhone(phone) || !listC.insert(name, c)) {
            System.out.println("contact already exist!");
            return false;
        }
        System.out.println("Contact added successfully!");
        return true;
    }

	//2) search for a contact
	public static void searchContact() {

		System.out.println("Enter search criteria:");
		System.out.println("1.Name");
		System.out.println("2.Phone Number");
		System.out.println("3.Email Address");
		System.out.println("4.Address");
		System.out.println("5.Birthday");
		System.out.println("your choice: ");
		String choice = input.next();

		switch (choice) {
			case "1":
				System.out.println("enter the name:");
				break;
			case "2":
				System.out.println("enter the Phone Number:");
				break;
			case "3":
				System.out.println("enter the Email Address:");
				break;
			case "4":
				System.out.println("enter the Address:");
				break;
			case "5":
				System.out.println("enter the Birthday:");
				break;
			default:
				System.out.println("enter a valid number!");
				return;
		}
		String filler = input.nextLine();
		String i = input.nextLine();

		listC.printContact(i, choice);

	}
	//3) Delete a contact
	public static void DeleteContact() {
		System.out.println("Enter the name of the contact:");
		String filler = input.nextLine();
		String name = input.nextLine();

		//check if contact exist
		if(!listC.findkey(name, listC.root)) {
			System.out.println("contact does not exist!");
			return;
		}
		//loop to remove contact from events/appointments
		listE.findFirst();
		while(listE.retrieve() != null) {
			listE.retrieve().contacts.remove_key(name);
			//if no contacts left
			if(listE.retrieve().contacts.root == null){
				System.out.println("event/appointment: [" + listE.retrieve().title + "] has been cancelled due to not having any contact left!");
				listE.remove(listE.retrieve());
			}
			else
				listE.findNext();
		}

		listC.remove_key(name);
		System.out.println("contact has been deleted!");


	}
	
	// 4) Schedule an event/appointment
	public static boolean scheduleEvent() throws IllegalArgumentException {
		String cNames = null;
		Event e = new Event();
		boolean flag = true;
		
		while(flag) {
			System.out.println("Enter type: \n1.event \n2.appointment");
			String choice = input.next();
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
		//if appointment
		if(!e.isEvent) {
			System.out.println("Enter contact name: ");
			String cName = input.nextLine();
			if(!listC.findkey(cName, listC.root)) {
				System.out.println("Contact doesn't exist");
				return false;
			}
			e.contacts.insert(cName, listC.retrieve());
		}
		//if event
		else {
			System.out.println("Enter contact names seperated by commas[name1,name2]: "); 
			cNames = input.nextLine();
			String arrName[] = cNames.split(","); 
			//loop to check if all contacts exist
			for(int i = 0; i < arrName.length; i++) {
				if(!listC.findkey(arrName[i], listC.root)) {
					System.out.println(arrName[i] + " was not found");
					return false;
				}
			}
			//loop to insert all the contacts in the event
			for(int i = 0; i < arrName.length; i++) {
				listC.findkey(arrName[i], listC.root);
				e.contacts.insert(arrName[i], listC.retrieve());
			}
		}
		
		// try and catch here
		while(true) {
			try{
		System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
		e.date = new Date(input.next());
		e.time = input.next();
		input.nextLine();
			}
			catch(IllegalArgumentException ea) {
				System.out.println("enter a valid date!");
				continue;
			}
			break;
	}
		
		System.out.println("Enter event location: ");
		e.location = input.nextLine();

		boolean flag1 = false;
		//check for time conflict for appointment
		if(!e.isEvent) {
			if(!listC.retrieve().addEvent(e)) {
				System.out.println("Time conflict");
				return false;
			}
			flag1 = true;
		}
		//check for time conflict for event
		else {
			String arrName[] = cNames.split(",");
			for(int i = 0; i < arrName.length; i++) {
				listC.findkey(arrName[i], listC.root);
				if(listC.retrieve().addEvent(e))
					flag1 = true;
				else {
					System.out.println("Time conflict for " + listC.retrieve().name);
					e.contacts.remove_key(listC.retrieve().name);
				}
			}
		}
		if(flag1){
			listE.insertSort(e);
			System.out.println("Event/Appointment scheduled");
			return true;
		}
		else {
			System.out.println("Event has been cancelled due to time coflict for all contacts!");
			return false;
		}

	}

	//5) Print event details
	public static void PrintEventD() {
		System.out.println("Enter search criteria:");
		System.out.println("1. contact name");
		System.out.println("2. Event tittle");
		System.out.println("Enter your choice:");
		String choice = input.next();

		switch(choice) {
			case "1":
			System.out.println("enter the name: ");
			String filler = input.nextLine();
			String name = input.nextLine();


			if(!listC.findkey(name, listC.root)) {
				System.out.println("contact does not exist!");
				return;
			}
			listC.retrieve().printEvent();
			break;
			case "2":
			System.out.println("enter event/appointment title: ");
			String filler1 = input.nextLine();
			String title = input.nextLine();

			if(listE == null) {
				System.out.println("no events/appointments exist!");
				return;
			}
			boolean flag = true;
			//loop to print all events with the same title
			listE.findFirst();
			while(listE.retrieve() != null) {
				if(listE.retrieve().title.equalsIgnoreCase(title)) {
					System.out.println(listE.retrieve());
					flag = false;
				}
				listE.findNext();
			}
			if(flag)
				System.out.println("no event/appointment exist with this title: " + title);
			break;
			default:
				System.out.println("enter a valid number!");

		}

	}

	//6) Print contacts by first name
	public static void printContactFName() {
		System.out.println("Enter the first name: ");
		String choice = input.next();

		listC.traversePrintFName(choice);
	}
	
	//7) Print all events alphabetically
	public static void printEvents() {
			listE.findFirst();
			// a flag to check if any event exists and print appropriate message
			boolean flag = true;
			while(listE.retrieve() != null) {
				System.out.println(listE.retrieve());
				listE.findNext();
				flag = false;
			}
			if(flag)
				System.out.println("No events/appointments found");
		}
		
		public static void mainmenu() {
			System.out.println("Please choose an option: ");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event/appointment");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Exit");
			System.out.print("Enter your choice: ");
			
		}
		public static void main(String[] args) {
			String choice = " ";
			do{mainmenu();
			choice = input.next();
			switch(choice) {
			case "1":
				addContact();
				break;
			case "2":
				searchContact();
				break;
			case "3":
				DeleteContact();
				break;
			case "4":
				scheduleEvent();
				break;
			case "5":
				PrintEventD();
				break;
			case "6":
				printContactFName();
				break;
			case "7":
				printEvents();
				break;
			case "8":
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Enter a valid choice");
			}
			System.out.println();
			}while(!choice.equals("8"));
		}
}
