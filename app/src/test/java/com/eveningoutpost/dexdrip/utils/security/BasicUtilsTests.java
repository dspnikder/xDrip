package com.eveningoutpost.dexdrip.utils.security;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class BasicUtilsTests {

    public static String original = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static byte[] bytes;

    @BeforeClass
    public static void init() {
        for (int i = 0; i< 10;i++) {
            original +=original;
        }
        bytes = original.getBytes(StandardCharsets.US_ASCII);
    }


    @Test
    public void testFastHexShort() {
        //just 04 08 56 fa
        byte[] bytes= new byte[]{0x04,0x08,0x56, (byte) 0xfa};
        String result = BasicUtils.toHexFast(bytes);
        Assert.assertEquals("040856fa",result);
    }

    @Test
    public void testFastHexLonger() {

        long t = System.nanoTime();
        String result = BasicUtils.toHexFast(bytes);
        byte[] resBytes = new byte[result.length()/2];
        for (int i = 0; i < result.length();i+=2) {
            resBytes[i/2] = (byte)Short.parseShort(result.substring(i, i+2), 16);
        }
        String post = new String(resBytes, StandardCharsets.US_ASCII);
        Assert.assertEquals(original,post);
        System.out.println(System.nanoTime()-t);
    }
    @Test
    public void testToAndFromFastHexLonger() {
        long t = System.nanoTime();
        Assert.assertEquals(original,new String(BasicUtils.fromHexFast(BasicUtils.toHexFast(bytes)),StandardCharsets.US_ASCII));
        System.out.println(System.nanoTime()-t);
    }


}
