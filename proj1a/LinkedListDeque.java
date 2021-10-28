public class LinkedListDeque<T> {
    /** Inner class */
    public class StuffNode {
        StuffNode prevOne; //The last item in the queue
        T theItem;
        StuffNode nextOne; //The first item in the queue
        public StuffNode(StuffNode prev, T item, StuffNode next) {
            prevOne = prev;
            theItem = item;
            nextOne = next;
        }
    }

    /** Instance#1: A circular sentinel (sentinel is the holder of circular chain) */
    StuffNode sentinel;

    /** Instance#2: A size recorder */
    int size;

    /** Constructor#1: Make an empty deque */
    public LinkedListDeque() {
        /**
         * prev is the last item in the deque
         * next is the first item in the deque
         * */
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }

    /** Constructor#2: Make an item-live deque */
    public LinkedListDeque (T item) {
        size += 1;
        sentinel = new StuffNode(null, null, null);

        //The code below is same as the one in addLast()
        sentinel.prevOne.nextOne = new StuffNode(sentinel.prevOne, item, sentinel);
        sentinel.prevOne = sentinel.prevOne.nextOne;
    }

    /** Constructor#3: Make a DEEP COPY of other instance of LinkedListDeque
     * ⚠️The key point is to downgrade the reference variables to primitive variables so
     * I can copy the value without affecting the other instance */
    public LinkedListDeque(LinkedListDeque other) {
        //1. create an empty deque
        sentinel = new StuffNode(null, null, null);
        size = 0;
        //2. Loop through every StuffNode in the other. Then retrieve item from each StuffNode.
        //---> Since each item is primitive variable, I can simply copy the value without affecting the other instance
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i)); //The item get() returns is primitive value, so don't worry about overwriting the original values.
        }
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
        if (size == 0) {
            return true;
        }
        return false;
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
        size -= 1;
        sentinel.nextOne = sentinel.nextOne.nextOne;
        sentinel.nextOne.prevOne = sentinel;
        return sentinel.nextOne.theItem;
        //My understanding of this is: remove the first item, then return the new first item
    }

    /** Remove the last item in the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        //Cut off two '你中有我' links
        sentinel.prevOne = sentinel.prevOne.prevOne;
        sentinel.prevOne.nextOne = sentinel;
        return sentinel.prevOne.theItem;
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
    public T getRecur(int index) {
        if (size <= index) {
            return null;
        }
        return getRecur(sentinel.nextOne, index);
                //sentinel.nextOne.getRecurHelper(index);
    }

    /** Helper method
     * It is private
     * It is a static method */
    public T getRecur(StuffNode x, int i) {
        if (i == 0) {
            return x.theItem;
        }
        return getRecur(x.nextOne, i - 1);
    }

//    Note:
//    After finish this method, change the third constructor (use the casting version generics)
//    Then, put the project into the autograder to see if it passes
//    Next, see the github answers to improve my code

//    public static void main(String[] args) {
//        //1. Create two different-type LLDeque below:
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addLast(0);
//        L.addLast(1);
//        L.addLast(2);
//        L.addLast(3);
//        L.addLast(4);
//        System.out.println(L.getRecur(0));
//        System.out.println(L.getRecur(60));
//
//
////        LinkedListDeque<Integer> identicalOfL = new LinkedListDeque<>(L);
////        System.out.println(L.get(0));
////
////        //2. Implement addFirst(T item) AND addLast(T item)
////        L.addLast(5);
////        L.addFirst(3);
////        L.addFirst(1);
////
////        //3. Implement printDeque()
////        L.printDeque();
////
////        //4. Implement size()
////        System.out.println("Now, the deque size is: " + L.size());
////
////        //5. Implement removeFirst() AND removeLast()
////        L.removeFirst(); //remove 1
////        L.removeLast();  //remove 5
////
////        //6. Implement get(int index)
////        System.out.println("The item at index 0 is: " + L.get(0)); // 3
//
//    }

}
