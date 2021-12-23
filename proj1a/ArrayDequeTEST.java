public class ArrayDequeTEST {

    public static void main(String[] args) {

        ArrayDeque<Integer> arrayDequeOne = new ArrayDeque<>();

        /** Test 1: addLast() -> get()
         * Expected print-outs: 0, 0, 0, 0, 0, 0
         * AddLast six zeros to arrayDeque and print every element from this deque
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addLast(0);
        }
        for (int i = 0; i < 6; i++) {
            System.out.print(arrayDequeOne.get(i) + " ");
        } */

        /** Test 2: addFirst() -> get()
         * Expected print-outs: 0, 0, 0, 0, 0, 0
         * AddFirst six zeros to arrayDeque and print every element from this deque
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(0);
        }
        for (int i = 0; i < 6; i++) {
            System.out.print(arrayDequeOne.get(i) + " ");
        } */

        /** Test 3: addLast() -> removeLast() -> get()
         * Expected print-outs: null, null, null
         * AddLast six elements - {0,  2,  4,  6,  8, 10}
         *                        {4,  6,  8, 10,  0,  2}
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addLast(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.removeLast();
        }
        //We are in the new items[3] now, and It is empty
        for (int i = 0; i < 3; i++) {
            System.out.print(arrayDequeOne.get(i) + " ");
        } */

        /** Test 4: addFirst() + extendArray()
         * Steps below are based on items[6]
         * 1. Execute addFirst() 6 times
         * 2. Print every element
         * 3. Execute addFirst() more than 6 times - 7 times
         *    The extendArray() will be triggered at 7th time
         * 4. Execute extendArray(), and we have items[12]
         * 5. Execute removeFirst()
         * -> extendArray() -> removeFirst() -> get()
         * Expected print-outs:
         * addFirst() - adding six elements - 0,  2,  4,  6,  8, 10
         *  items[6] looks like: {4,  6,  8,  10,  0,  2}
         * addFist() - adding one more element - 12
         *      items[6] looks like: {      6,       4,     2,     0,     10,     8}
         *  newItems[12] looks like: {   null,    null,  null,    12, 10, 8, 6, 4, 2, 0}
         *
         *
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            arrayDequeOne.addFirst(i * 2);
        } */

        /** Test 5: removeLast()
         * Following to the Test 4, we have an arrayDeque of 48 elements

        for (int i = 0; i < 48; i++) {
            arrayDequeOne.removeLast();
        }*/

        /** Test 6: removeFirst()
         * Following to the Test 4, we have an arrayDeque of 48 elements

        for (int i = 0; i < 48; i++) {
            arrayDequeOne.removeFirst();
        }*/

        /** Test 7: printDeque()
         * Following to the Test 4, we have an arrayDeque of 48 elements

        arrayDequeOne.printDeque();*/

        arrayDequeOne.addLast(0);
        System.out.println(arrayDequeOne.removeFirst());

        arrayDequeOne.addFirst(2);
        arrayDequeOne.addFirst(3);
        arrayDequeOne.addLast(4);
        arrayDequeOne.addLast(5);
        System.out.println(arrayDequeOne.removeLast());
        System.out.println(arrayDequeOne.removeFirst());
        arrayDequeOne.addFirst(8);
        arrayDequeOne.addFirst(9);









    }//Close main()
}//Close class
