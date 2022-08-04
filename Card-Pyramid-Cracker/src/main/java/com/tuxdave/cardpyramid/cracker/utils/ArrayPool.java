package com.tuxdave.cardpyramid.cracker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPool {
    private List<int[]> pool;

    public ArrayPool() {
        pool = new ArrayList<>();
    }

    public int[] get(int[] values){
        for(int[] value : pool){
            if(Arrays.equals(value, values)){
                return value;
            }
        }
        pool.add(values);
        return values;
    }
}
