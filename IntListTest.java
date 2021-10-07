public class IntListTest{
	public static void main(String[] args) {
		IntList p = new IntList(5, new IntList(6, new IntList(7, new IntList(9, null))));
		IntList p1 = new IntList(5, new IntList(6, new IntList(8, null)));
		System.out.println(p);

		System.out.println(p.equals(p1));
	}
}