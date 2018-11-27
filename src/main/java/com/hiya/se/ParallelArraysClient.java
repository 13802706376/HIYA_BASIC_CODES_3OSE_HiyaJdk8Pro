package com.hiya.se;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelArraysClient
{
    public static void main(String[] args)
    {
        long[] arrayOfLong = new long [ 20000 ];        
        System.out.println(arrayOfLong);
        
        Arrays.parallelSetAll( arrayOfLong,  index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(    i -> System.out.print( i + " " ) );
        System.out.println(arrayOfLong);
 
        Arrays.parallelSort( arrayOfLong );        
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(  i -> System.out.print( i + " " ) );
        System.out.println(arrayOfLong);
    }
}
