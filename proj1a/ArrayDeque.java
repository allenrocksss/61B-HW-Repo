public class ArrayDeque<E> {

    /** The array */
    private E[] items;
    /** The index position that will hold the first item */
    private int nextFirst;
    /** The index position that will hold the last item */
    private int nextLast;

    /** The size (Note: size couldn't be double, just because 0.25 is a double: (size/length<0.25)) */
    private int size;

    /** Constructor#1: Make an empty array */
    public ArrayDeque() {
        items = (E[]) new Object[6];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /** Constructor#2: Make an element-alive array */
    public ArrayDeque(E element) {
        items = (E[]) new Object[6];
        items[3] = element;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }

    /** Constructor#3: Make a DEEP copy of an existed array deque */
    public ArrayDeque(ArrayDeque<E> AnArrayDeque) {
        // Also, I can use arraycopy, but I use a for loop here :)
        //1. Initialize the items[] and get the size from other array - AnArrayDeque
        items = (E[]) new Object[AnArrayDeque.size];
        System.arraycopy(AnArrayDeque.items, 0, items, 0, AnArrayDeque.items.length);
        nextFirst = AnArrayDeque.nextFirst;
        nextLast = AnArrayDeque.nextLast;
    }

    /** New added item will be the last item */
    public void addLast(E element) {
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
                * them, (not reaches), simply give the element to the nextLast, and update size
         */
    }

    /** New added item will be the first item */
    public void addFirst(E element) {
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
    public E removeLast() {
        //1. See if the array is empty
        if (size == 0) {
            return null;
        }
        //2. See if (size / items.length < 0.25) "if it is under the usage ratio, shrink the array!"
        if (size / items.length < 0.25) {
            shrinkArray(size / 2);
        }
        size -= 1;
        //3. See if nextLast is at index 0 (the Tail is the last item of the array)
        if (nextLast == 0) {
            E holder = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            return holder;
        }
        //. Give holder the Tail. The index of last item is one step before the nextLast
        E holder = items[nextLast - 1];
        /*3. Cut off the relation between items[nextLast] and the value that was at nextLast.
             Now, only the local variable holder holds the old value. */
        items[nextLast - 1] = null;
        //. Move the nextLast one step back
        nextLast -= 1;
        return holder;
    }

    /** Remove and return the first item */
    public E removeFirst() {
        //1. See if the array is empty
        if (size == 0) {
            return null;
        }
        //2. See if (size / items.length < 0.25) "if it is under the usage ratio, shrink the array!"
        if (size / items.length < 0.25) {
            shrinkArray(size / 2);
        }
        size -= 1;
        //3. See if nextFirst is at index items.length (the Head is the first item in the array)
        if (nextFirst == items.length - 1) {
            E holder = items[0];
            items[0] = null;
            nextFirst = 0;
            return holder; //this condition stops right here!
        }
        //1. Give the holder the first item
        E holder = items[nextFirst + 1];
        /*2. Cut off the relation between items[nextFirst] and the value that was at nextFirst.
             Now, only holder holds the old value */
        items[nextFirst + 1] = null;
        //3. Move the nextFirst one step forward
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

    /** Return the item on the given index */
    public E get(int index) {
        return items[index];
    }

    /** Print each item from first to last */
    public void printDeque() {
        //1. Tools
        int head = nextFirst + 1;
        int headToEnd = items.length - head;

        if (headToEnd < size) { //If the array is NOT contiguous
            /** Walk from head to the end */
            for (int i = head; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            /** Walk from the start (0) to the tail */
            for (int i = 0; i < nextLast; i++) {
                System.out.println(items[i] + " ");
            }
        } else { //else, the array is contiguous
            for (int i = head; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public void extendArray(int doubleSize) {
        //1. Make a new array that has double size of old array
        E[] newItems = (E[]) new Object[doubleSize];
        //2. Identify the head (No need to identify the tail)
        int head = nextFirst + 1;
        //3. Specify how many steps we need to walk from the 'head' to the end
        int headToEnd = items.length - head;
        //4. Copy the first part - from the head to the end (headToEnd is how many steps to walk)
        System.arraycopy(items, head, newItems, head, headToEnd);
       /* 3. Copy the second part - from the start to the tail (head is how many steps to walk)
             When items[] is full, if head is at the index 0, the first arraycopy has finished the job
             , and the second arraycopy will not even move forward since the steps is 'head', which is 0
         */
        System.arraycopy(items, 0, newItems, items.length, head);
      /* 4. Reassign new nextLast - no need to do so on nextFirst since we use the same 'head' in
            items[] and newItems[], and 'nextFirst' is not changed at all. ONLY nextLast will be changed.
       */
        nextLast = head + items.length;
        //5. Abandon the old short array and use the new longer array
        items = newItems;
    }

    public void shrinkArray(int halfSize) {
        //1. Create a new array with half size
        E[] newItems = (E[]) new Object[halfSize];
        //2. See if every element in old array is contiguous
        int head = nextFirst + 1;
        int headToTheEnd = items.length - head;
        if (headToTheEnd < size) { //The elements are not contiguous, some elements are at the beginning part of items[]!
            System.arraycopy(items, head, newItems, 1, headToTheEnd);
            System.arraycopy(items, 0, newItems, 1 + headToTheEnd, size - headToTheEnd);
        } else { //headToTheEnd >= size, which means ALL elements are contiguous!
            System.arraycopy(items, head, newItems, 1, size);
        }
        //3. Re-assign the nextFirst and nextLast
        nextFirst = 0;
        nextLast = 1 + size;
        //4. Abandon the old long array and use the new shorter array
        items = newItems;
    }

}