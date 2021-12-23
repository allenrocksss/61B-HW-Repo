public class LinkedListDeque<T> {

    /** Inner class */
    private class StuffNode {
        StuffNode prevOne; //The last item in the queue
        T theItem;
        StuffNode nextOne; //The first item in the queue
        private StuffNode(StuffNode prev, T item, StuffNode next) {
            prevOne = prev;
            theItem = item;
            nextOne = next;
        }
    }

    /** Instance#1: A circular sentinel (sentinel is the holder of circular chain) */
    private StuffNode sentinel;

    /** Instance#2: A size recorder */
    private int size;

    /** Constructor#1: Make an empty deque */
    public LinkedListDeque() {
        /** A circular sentinel chain
         * prevOne is the last item in the deque
         * nextOne is the first item in the deque
         * */
        sentinel = new StuffNode(null, null, null);
        sentinel.prevOne = sentinel;
        sentinel.nextOne = sentinel;
        size = 0;
    }

    /** New added item will be the first item in the deque */
    public void addFirst(T item) {
        /**If the deque is empty, the new added item's prevOne will be sentinel, its nextOne will be sentinel too
         * ⚠️ Make sure that "我们中有你们，你们中有我们 ⚠️*/
        if (size == 0) {
            size += 1;
            sentinel.nextOne = new StuffNode(sentinel, item, sentinel);
            sentinel.prevOne = sentinel.nextOne;
            return;
        }
        /**If the deque has item/items, we just go head to insert the new item before it/them */
        size += 1;
        sentinel.nextOne.prevOne = new StuffNode(sentinel, item, sentinel.nextOne);
        sentinel.nextOne = sentinel.nextOne.prevOne;
    }

    /** New added item will be the last item in the deque */
    public void addLast(T item) {
        if (size == 0) {
            size += 1;
            sentinel.nextOne = new StuffNode(sentinel, item, sentinel);
            sentinel.prevOne = sentinel.nextOne;
            return;
        }
        size += 1;
        sentinel.prevOne.nextOne = new StuffNode(sentinel.prevOne, item, sentinel);
        sentinel.prevOne = sentinel.prevOne.nextOne;
    }

    /** Return true if the deque is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last */
    public void printDeque() {
        StuffNode pptr = sentinel;
        while (pptr.nextOne != sentinel) {
            pptr = pptr.nextOne;
            System.out.print(pptr.theItem + " ");
        }
        System.out.println(" ");
    }

    /** Remove the first item in the deque*/
    public T removeFirst() { //size -= 1;
        if (size == 0) {
            return null;
        }
        T item = sentinel.nextOne.theItem;
        size -= 1;
        sentinel.nextOne = sentinel.nextOne.nextOne;
        sentinel.nextOne.prevOne = sentinel;
        return item;
        //My understanding of this is: remove the first item, then return the new first item
        //Correct understanding: remove the first item, then return the item has just been removed
    }

    /** Remove the last item in the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prevOne.theItem;
        size -= 1;
        //Cut off two '你中有我' links
        sentinel.prevOne = sentinel.prevOne.prevOne;
        sentinel.prevOne.nextOne = sentinel;
        return item;
    }

    /** Gets the item at the given index (by iteration)
     * Please use this method when the Deque is not empty and
     * make sure the index must be less than the size */
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        StuffNode iptr = sentinel.nextOne;
        int walkSteps = index;
        while (walkSteps != 0) {
            iptr = iptr.nextOne;
            walkSteps -= 1;
        }
        return iptr.theItem;
    }

    /** Gets the item at the given index (by recursion)
     * Please use this method when the Deque is not empty and
     * make sure the index must be less than the size
     * Since the deque is a circular queue, if there is no condition, it will go to the first
     * element again, and I never know which element I am looking for */
    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return getRecursive(sentinel.nextOne, index);
                //sentinel.nextOne.getRecurHelper(index);
    }

    /** Helper method
     * It is private
     * It is a static method */
    private T getRecursive(StuffNode x, int i) {
        if (i == 0) {
            return x.theItem;
        }
        return getRecursive(x.nextOne, i - 1);
    }

}
