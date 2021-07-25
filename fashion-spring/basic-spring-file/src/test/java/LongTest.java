

/**
 * @Author: yangyuguang
 * @Date: 2021/7/21 10:26
 */
public class LongTest {

    public static void main(String[] args) {
        print();
    }

    public static String print(){
        try{
            int i = 1 / 0;
            return "aa";
        }catch (Exception e){
            e.printStackTrace();
            return "a";
        }finally {
            System.out.println("执行finally");
        }

    }
}
