public class ReplaceAllTest {
    public static void main(String[] args) {
        String str = "{\"name\": field, \"name\": field, departments: {\"name\": field}}";
        str = str.replaceAll(": \\{\"", ".");
        System.out.println(str);
//        String[] stings = str.split(",");
//        for(int i = 0; i < stings.length; i++){
//            String[] strs = stings[i].split(":");
//            System.out.println(strs[0].trim());
//            System.out.println(strs[1].trim());
//        }
    }
}
