import java.util.Date;

public class BST <T extends Comparable<T>>{
	BSTNode<T> root, current;
	
	public BST() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	
	public T retrieve () {
		return current.data;
	}
	
	public boolean findkey(String k, BSTNode<T> r) {
		if(r == null)
			return false;
		if(r.key.compareTo(k) == 0) {
			current = r;
			return true;
		}
		current = r;
		if(r.key.compareTo(k) < 0)
			return findkey(k, r.right);
		return findkey(k, r.left);
	}
	
	public boolean insert(String k, T data) {
		BSTNode<T> p, q = current;
		
		if(findkey(k, root)) {
			current = q;
			return false;
		}
		
		p = new BSTNode<T>(k, data);
		if(empty()) {
			current = root = p;
			return true;
		}
		else {
			if(current.key.compareTo(k) > 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}
	
	private BSTNode<T> find_min(BSTNode<T> p){
		if(p == null)
			return null;
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}
	private BSTNode<T> remove_aux(String k, BSTNode<T> p, Boolean flag){
		BSTNode<T> q, child = null;
		if(p == null)
			return null;
		if(p.key.compareTo(k) > 0)
			p.left = remove_aux(k, p.left, flag);
		else if(p.key.compareTo(k) < 0)
			p.right = remove_aux(k, p.right, flag);
		else {
			flag.setBoolean(true);
			if(p.left != null && p.right != null) {
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
			else {
				if(p.right == null)
					child = p.left;
				else if(p.left == null)
					child = p.right;
				return child;
			}
		}
		return p;
	}
	public boolean remove_key(String key) {
		Boolean removed = new Boolean(false);
		BSTNode<T> p;
		p = remove_aux(key, root, removed);
		current = root = p;
		return removed.getBoolean();
	}
	
	public boolean update(String k, T data) {
		remove_key(current.key);
		return insert(k, data);
	}
	
	private void traverse(BSTNode<T> t, Operation op, T var, Boolean found, String FName) {
		if(t == null)
			return;
		traverse(t.left, op, var, found, FName);
		if(op == Operation.PRINT)
			System.out.println(t.data);
		else if(op == Operation.SEARCH) {
			if(t.data.compareTo(var) == 0)
				found.setBoolean(true);
		}
		else {
			String split[] = ((Contact) t.data).name.split(" ");
			if(split[0].equalsIgnoreCase(FName)) {
				if(found.getBoolean()) {
					found.setBoolean(false);
					System.out.println("Contact found");
				}
				System.out.println(t.data);
			}
		}
		traverse(t.right, op, var, found, FName);
	}
	public void traversePrint() {
		traverse(root, Operation.PRINT, null, null, null);
	}
	public boolean traverseSearch(T var) {
		Boolean found = new Boolean();
		traverse(root, Operation.SEARCH, var, found, null);
		return found.getBoolean();
	}
	public void traversePrintFName(String FName) {
		Boolean found = new Boolean(true);
		traverse(root, Operation.FNAME, null, found, FName);
		if(found.getBoolean())
			System.out.println("Contact does not exist");
	}
	
	// * Start of finding phone conflict methods (Contact BST only)*
	private void traversePhone(BSTNode<Contact> t, String phone, Boolean flag) {
		if(t == null)
			return;
		traversePhone(t.left, phone, flag);
		if(t.data.phoneNumber.equals(phone))
			flag.setBoolean(true);
		traversePhone(t.right, phone, flag);
	}
	public boolean traversePhone(String phone) {
		Boolean flag = new Boolean();
		traversePhone((BSTNode<Contact>) root, phone, flag);
		return flag.getBoolean();
	}
	// * End of finding phone conflict methods *
	

}
