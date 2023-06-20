package com.example;

import com.example.util.MD5util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class FunPlayPeripheralApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("啓動·試運行");
    }

    @Test
    void getCPUCore(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    @Test
    void useHashMapStudy(){
        System.out.println(MD5util.getPWD("13sudiu"));
    }
    @Test
    void testMath() {
        int[] arrys = {2,2};
        int[] rts = new int[2];
        for (int i = 0; i < arrys.length; i++) {
            if (arrys[0] > arrys[1]) {
                if((i+1)>arrys[i]){
                    rts[0]=arrys[i];
                    rts[1]=(arrys[i]-1);
                }
            }else if(arrys[0]==arrys[1]&&arrys.length==2){
                if(arrys[0]==1){
                    rts[0]=1;
                    rts[1]=2;
                }else {
                    rts[0]=arrys[0];
                    rts[1]=arrys[0]-1;
                }
            }else {
                if((i+1)>arrys[i]){
                    rts[0]=arrys[i];
                    rts[1]=(i+1);
                }
            }
        }
        System.out.println(rts[0]+","+rts[1]);
    }
    @Test
    public void ud(){
        System.out.println(new Date().getTime());
    }
}
