
public class test{
	static BST<Contact> tree = new BST<Contact>();
	public static void main(String[] args) {
		Contact c1 = new Contact("bro majed", "0551404", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c2 = new Contact("ahmed ali", "0551404", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c3 = new Contact("khaled yes", "0551404", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		tree.insert(c1.name, c1);
		tree.insert(c2.name, c2);
		tree.insert(c3.name, c3);
		tree.traversePrint();
	}

}
