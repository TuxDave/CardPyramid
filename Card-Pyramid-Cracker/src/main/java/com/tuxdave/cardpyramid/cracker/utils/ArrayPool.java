package com.tuxdave.cardpyramid.cracker.utils;

import com.tuxdave.cardpyramid.cracker.tree.Node;
import kotlin.jvm.Synchronized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayPool {
    public HashMap<int[], Node> pool;

    public ArrayPool() {
        pool = new HashMap<int[], Node>();
    }

    public synchronized int[] get(int[] values){
        for(int[] value : pool.keySet()){
            if(Arrays.equals(value, values)){
                return value;
            }
        }
        pool.put(values, null);
        return values;
    }

    public synchronized Node getMemoized(int[] key){
        return pool.get(key);
    }

    public void set(int[] key, Node value){

        pool.put(key, value);
    }
}
