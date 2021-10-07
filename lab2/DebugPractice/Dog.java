public class Dog {

    int size;
    String color;

    public static void main(String[] args) {
        makeDog1(new Dog());
    }

    public static Dog makeDog1(Dog d) {
        d.size = 3;
        d.color = "red";
        makeDog2(new Dog());
        return d;
    }

    public static Dog makeDog2(Dog d) {
        d.size = 3;
        d.color = "red";
        makeDog3(new Dog());
        return d;
    }

    public static Dog makeDog3(Dog d) {
        d.size = 3;
        d.color = "red";
        makeDog4(new Dog());
        return d;
    }

    public static Dog makeDog4(Dog d) {
        return null;
    }


}
