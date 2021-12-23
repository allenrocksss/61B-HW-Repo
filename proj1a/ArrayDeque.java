public class ArrayDeque<T> {

    /** The array */
    private T[] items;
    /** The index position that will hold the first item */
    private int nextFirst;
    /** The index position that will hold the last item */
    private int nextLast;

    /** The size (Note: size couldn't be double, just because 0.25 is a double: (size/length<0.25)) */
    private int size;

    /** Constructor#1: Make an empty array */
    public ArrayDeque() {
        items = (T[]) new Object[6];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

//    /** Constructor#2: Make an element-alive array */
//    public ArrayDeque(T element) {
//        items = (T[]) new Object[6];
//        items[3] = element;
//        size = 1;
//        nextFirst = 0;
//        nextLast = 1;
//    }
//
//    /** Constructor#3: Make a DEEP copy of an existed array deque */
//    public ArrayDeque(ArrayDeque<T> AnArrayDeque) {
//        // Also, I can use arraycopy, but I use a for loop here :)
//        //1. Initialize the items[] and get the size from other array - AnArrayDeque
//        items = (T[]) new Object[AnArrayDeque.size];
//        System.arraycopy(AnArrayDeque.items, 0, items, 0, AnArrayDeque.items.length);
//        nextFirst = AnArrayDeque.nextFirst;
//        nextLast = AnArrayDeque.nextLast;
//    }

    /** New added item will be the last item */
    public void addLast(T element) {
        if (size == items.length) {
            extendArray(size * 2);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = element;
        nextLast += 1;
        size += 1;
        /**
         * See if the array is full
         * if full, call resize()
         * then, see if addLast() reaches the rightmost end of the array
         * if it reaches, modify the nextLast to the left most of the array
         * then, (not reaches), simply give the element to the nextLast, and update size
         */
    }

    /** New added item will be the first item */
    public void addFirst(T element) {
        if (size == items.length) {
            extendArray(size * 2);
        }
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = element;
        nextFirst -= 1;
        size += 1;
        /**
         * See if the array is full
         * if full, call resize()
         * then, see if addFirst() reaches the left most end of the array
         * if it reaches, modify the nextFirst to the rightmost end of the array
         * then, simply give the element to the nextFirst
         */
    }

    /** Remove and return the last item */
    public T removeLast() {
        //1. See if the array is empty
        if (size == 0) {
            return null;
        }
        //2. See if (size / items.length < 0.25) "if it is under the usage ratio, shrink the array!"
        if ((double) size / items.length < 0.25) {
            shrinkArray(items.length / 2);
        }
        //3. When nextLast is at 0, and 'Tail' is at the (items.length-1)
        if (nextLast == 0) {//When Tail is at length-1
            T holder = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            size -= 1;
            return holder;
        }
        //4. When nextLast is not at 0
        size -= 1;
        T holder = items[nextLast - 1];
        /*3. Cut off the relation between items[nextLast] and the value that was at nextLast.
             Now, only the local variable holder holds the old value. */
        items[nextLast - 1] = null;
        nextLast -= 1;
        return holder;
    }

    /** Remove and return the first item */
    public T removeFirst() {
        //1. See if the array is empty
        if (size == 0) {
            return null;
        }
        //2. See if (size / items.length < 0.25) "if it is under the usage ratio, shrink the array!"
        if ((double) size / items.length < 0.25) {
            shrinkArray(items.length / 2);
        }
        //3. When nextFirst is at items.length - 1, and 'Head' is at 0
        if (nextFirst == items.length - 1) {
            T holder = items[0];
            items[0] = null;
            nextFirst = 0;
            size -= 1;
            return holder;
        }
        //4. When nextFirst is not at items.length - 1
        size -= 1;
        T holder = items[nextFirst + 1];
        /*2. Cut off the relation between items[nextFirst] and the value that was at nextFirst.
             Now, only holder holds the old value */
        items[nextFirst + 1] = null;
        nextFirst += 1;
        return holder;
    }

    /** Check if the array is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size of the array */
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return size;
    }

    /** Return the item on the given index
     * The index only works for the dynamic circular array (based on our
     * logics, not the physical real array! */
    public T get(int index) {
        //1. See if the index is out of bound (is more than the size)
        if (!(index < size)) { //size should be > index
           return null;
        }
        /* 2. if (Either the dynamic array is contiguous or the given index points to the rightmost part of array)
        *        just return items[hi] */
        int hi = getHead() + index;
        if (isContiguous() || hi <= items.length - 1) {
            return items[hi];
        } else {
            return items[hi - items.length];
        }
    }

    /** Print each item from first to last */
    public void printDeque() {
        //1. if the array is contiguous, one single walk is enough
        if (isContiguous()) {
            for (int i = getHead(); i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
        //2. if the array is NOT contiguous, then we need two walks
            for (int i = getHead(); i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.println(items[i] + " ");
            }
        }
    }

    /** (Helper) Get the head */
    private int getHead() {
        //1. Find the head
        int head = 0;
        if (nextFirst != items.length - 1) {
            head = nextFirst + 1; //Normally we go this way her
        }
        return head; //Special situation
    }

    /** (Helper) See if the array is contiguous or not
     * The core approach is about size and headToEnd
     * if size <= headToEnd, then the array is contiguous, vice versa */
    private boolean isContiguous() {
        //2. Calculate the headToEnd
        int headToEnd = items.length - getHead();
        //3. if true, it is contiguous
        if (size > headToEnd) {
            return false;
        } else {
            return true;
        }
    }

    private void extendArray(int doubleSize) {
        //1. Make a new array that has double size of old array
        T[] newItems = (T[]) new Object[doubleSize];
        //2. Specify how many steps we need to walk from the 'head' to the end
        int headToEnd = items.length - getHead();
        //3. Copy the first part - from the head to the end (headToEnd is how many steps to walk)
        System.arraycopy(items, getHead(), newItems, getHead(), headToEnd);
       /* 4. Copy the second part - from the start to the tail (head is how many steps to walk)
             When items[] is full, if head is at the index 0, the first arraycopy has finished the job
             , and the second arraycopy will not even move forward since the steps is 'head', which is 0
         */
        System.arraycopy(items, 0, newItems, items.length, getHead());
      /* 4. Reassign new nextLast - no need to do so on nextFirst since we use the same 'head' in
            items[] and newItems[], and 'nextFirst' is not changed at all. ONLY nextLast will be changed.
       */
        nextFirst = getHead() - 1;
        nextLast = getHead() + items.length;
        //5. Abandon the old short array and use the new longer array
        items = newItems;
    }

    private void shrinkArray(int halfSize) {
        //1. Create a new array with half size
        T[] newItems = (T[]) new Object[halfSize];
        int headToTheEnd = items.length - getHead();
        if (isContiguous()) {//ALL elements are contiguous!
            System.arraycopy(items, getHead(), newItems, 1, size);
        } else {//The elements are not contiguous, some elements are at the beginning part of items[]!
            System.arraycopy(items, getHead(), newItems, 1, headToTheEnd);
            System.arraycopy(items, 0, newItems, 1 + headToTheEnd, size - headToTheEnd);
        }
        //2. Re-assign the nextFirst and nextLast
        nextFirst = 0;
        nextLast = 1 + size;
        //3. Abandon the old long array and use the new shorter array
        items = newItems;
    }

}












