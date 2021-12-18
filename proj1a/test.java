public class test {
    public static void main(String[] args) {

        int items[] = {2, 3, 4, 5, 6, 7};
        int newItems[] = new int[3];
        int size = 1;
        System.arraycopy(items, 4, newItems, 1, size);

    }
}
