import java.util.Arrays;

public class SplitTest {
    public static void main(String[] args) {
        String str = "aaaaa | bbbbb| cccccc |ddddd|fffff";
        String[] strings = str.split("\\|");
        System.out.println(strings.length);
        for(int i = 0; i < strings.length; i++){
            System.out.println(strings[i]);
        }
    }
}
