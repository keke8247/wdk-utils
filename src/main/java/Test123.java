import java.util.Hashtable;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/1/25 17:59
 * @Since version 1.0.0
 */
public class Test123 {
    public static void main(String[] args) {
        Hashtable<String,Object> test = new Hashtable<>();


        test.put("test",12);

        System.out.println(test.get("test"));

        System.out.println(test.keys().nextElement());
    }

}
