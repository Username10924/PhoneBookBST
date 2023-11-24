
public class test{
	static BST<Contact> tree = new BST<Contact>();
	static BST<Event> eventTree = new BST<Event>();
	public static void main(String[] args) {
		Contact c1 = new Contact("bro majed", "12133", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c2 = new Contact("ahmed ali", "421124", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		Contact c3 = new Contact("khaled yes", "554144", "akakak@dsad.com", "riyadh", "1999/10/10", "hello");
		tree.insert(c1.name, c1);
		tree.insert(c2.name, c2);
		System.out.println(tree.traverseSearch(c2));
	}

}
