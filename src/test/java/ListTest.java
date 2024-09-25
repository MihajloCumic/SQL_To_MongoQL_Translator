import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();

        nums.add(1);
        nums.add(2);
        System.out.println(nums.toString());

         nums.set(0, nums.get(0) + 3);

        System.out.println(nums.toString());
    }
}
