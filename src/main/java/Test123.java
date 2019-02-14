/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/1/25 17:59
 * @Since version 1.0.0
 */
public class Test123 {



    private  static void testM(int num){
        if(num > 6){
            return;
        }

        System.out.println(num);

        num++ ;

        testM(num);
    }


    public static void main(String[] args) {
        int num = 1;

        testM(num);
    }


}
