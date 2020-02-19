package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhaozhongyuan
 * 2020/2/18/5:52 下午
 */
public class GenEncodingAesKey {


    public static void main(String[] args) {
        List<Integer> asciiCode = new ArrayList<>();
        //0-9
        for(int i = 48; i <= 57 ; i++) {
            asciiCode.add(i);
        }
        //A-Z
        for(int i = 65; i <= 90 ; i++) {
            asciiCode.add(i);
        }
        //a-z
        for(int i = 97; i <= 122 ; i++) {
            asciiCode.add(i);
        }


        int size = asciiCode.size();
        for(int i = 0; i < 43; i++) {
            Random random = new Random();
            int i1 = random.nextInt(size);
            char c = (char)((int)asciiCode.get(i1));
            System.out.print(c);
        }
    }

}
