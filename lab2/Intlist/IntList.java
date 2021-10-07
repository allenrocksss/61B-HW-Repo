import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
    /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /** 1
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {
        //No need to return things. No need to create new IntList
        //Simply modify each item in this linked list
        //Even though I'm not using a recursion, I still need the base-like condition to move forward to every rest
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /** 2
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
    /*Goal: Make an identical of L with all item is squared. 1. Static method 2. returns IntList 3. Iteration
      Strategy: Make 2 pointers - a pointer of L that moves one item forward everytime
                                - a pointer of identical that moves one item forward everytime
                                - a holder of identical on the stack that holds all the items */
        //1. See if the L passed in this method invocation is null
        if (L == null) {
            return null;
        }

        //2. 'res' is a chain holder that holds the whole chain of the identical.
        //--->The pointer of L now points at the first item in L chain. Thus, we can access and square the 'first' in the first item
        IntList res = new IntList(L.first * L.first, null);

        //3. 'ptr' is a pointer of res chain (a pointer and a holder both are pointing to the same item at the first moment
        //--->'ptr' now points at the first item in res chain.
        IntList ptr = res;

        //4. Move the pointer L to the next item in L chain
        L = L.rest;

        while (L != null) {
            /* Loop#1: The pointer of L now points at the second item in L chain. Thus, we can access and square the 'first' in the second item
             * Loop#2: The pointer of L now points at the third item in L chain. Thus, we can access and square the 'first' in the third item
             */
            ptr.rest = new IntList(L.first * L.first, null);

            //6. Move the pointer L to the next item in L chain
            L = L.rest;

            //7. Move the ptr to the next item in res chain
            ptr = ptr.rest;
        }

        return res; //return the holder of the res chain (Now we have a complete 3-item chain as an identical to L on the heap, and the holder is res)
    }

    /** 3
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        /* Goal: 1. Static method
              2. It returns an IntList thing
              3. Using recursion - calling the same method again and again. Using the new keyword */

        //1. The base - while L is not null
        if (L == null) {
            return L;
        }
        IntList idenL = new IntList(L.first * L.first, squareListRecursive(L.rest)); //move down to the next item
        return idenL; //each idenL will be the 'rest' of item in the chainppp
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /** Destructive, recursive */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A.rest == null) {
            A.rest = B;
            return A; //run this in java visualizer
        }
        dcatenate(A.rest, B);
        return A;
    }

    /** Non-destructive, recursive */
    public static IntList catenate(IntList A, IntList B) {
        if (A.rest == null) {
            A.rest = B;
            return A;
        }
        return new IntList(A.first, catenate(A.rest, B));

    }





    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

