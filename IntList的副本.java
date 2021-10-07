/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Returns an IntList consisting of the given values. */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i++) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /** Returns the size of the list. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    /** Returns [position]th value in this list. */
    public int get(int position) {
        // TODO: YOUR CODE HERE
        IntList temp = this;
        int curr = 0;
        while(curr != position){
            temp = temp.rest;
            curr += 1;
        }
        return temp.first;
    }

    /** Returns the string representation of the list. */
    public String toString() {
        // TODO: YOUR CODE HERE
        if (rest == null) {
            return ""+ first;
        }
        return first + " " + rest.toString();
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        // TODO: YOUR CODE HERE
        if (size() != other.size() ) {
            return false;
        }
        if (first != other.first) {
            return false;
        }

        if (rest == null) {
            return true;
        }
        return true && rest.equals(other.rest) ;
    }

    public static IntList increInt(IntList L, int value){
    	if (L == null) {
    		return null;
    	}
    	return new IntList(L.first+value, increInt(L.rest, value));

    }

    public static IntList dincreInt(IntList L, int value){
    	if (L == null) {
    		return null;
    	}
    	L.first = L.first + value;
    	dincreInt(L.rest, value);
    	return L;
    }
    public static void main(String[] args) {
    	IntList a = IntList.of(1,2,3);
    	System.out.println(a);
    	IntList b = increInt(a,1);
    	System.out.println(b);
    	dincreInt(a,1);
    	System.out.println(a);
    }
}
