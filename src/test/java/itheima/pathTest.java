package itheima;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class pathTest<T> {
    private String s="D:\\瑞吉外卖\\reggie";

    @Test
    void test(){
        File file = new File("D:\\瑞吉外卖\\reggie\\test1");
        File file1 = new File("D:\\瑞吉外卖\\reggie\\test2\\");
        file.mkdirs();
        file1.mkdirs();

    }


    @Test
    public void test2(){
        int[] nums={-1,2,3,-1,5};
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=nums[i]*nums[i];

        }
        Arrays.sort(nums);
        for (int num : nums){
            System.out.println(num);
        }
        System.out.println(nums);



    }
    @Test
    public void rotate() {
        int[] nums={1,2,3,4};
        int k=2;
        int flag=0;
        int modk=k%nums.length;
        int count=0;
        int j=flag;
        int tep;
        for(int i=0;i<nums.length;i++){
            while (count<nums.length){
                tep=nums[(j+modk)%nums.length];
                nums[(j+modk)%nums.length]=nums[flag];
                nums[flag]=tep;
                count++;
                j=(j+modk)%nums.length;
                if(flag==(j+modk)%nums.length){
                    flag++;
                    j=flag;
                    count++;
                }

            }
        }


        for(int num: nums){
            System.out.println(num);
        }

    }
    public int gcd(int x, int y) {




        
        return y > 0 ? gcd(y, x % y) : x;
    }
    @Test
    void test5(){
        String s="abc"+"def";
        String s2=new String(s);

        char[] c=new char[s.length()];
        char[] chars = s.toCharArray();
        for (int i=0;i<s.length();i++){
            c[s.length()-i-1]=chars[i];
        }
        String s1 = new String(c);
        System.out.println(s1);

//        if (s.equals(s2)){
//            System.out.println(s);
//            System.out.println("1111");
//        }
//        if (s==s2){
//            System.out.println("=======");
//        }
    }
    @Test
    void test6(){
        double i=1D;
        for (int j=1;j<51;j++){
            i=i*j;
            System.out.println(i);
        }
        System.out.println(i);
    }


    




}
