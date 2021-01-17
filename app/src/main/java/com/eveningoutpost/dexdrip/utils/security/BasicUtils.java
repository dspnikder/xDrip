package com.eveningoutpost.dexdrip.utils.security;

import com.eveningoutpost.dexdrip.utils.validation.Ensure;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Basic utils contains some functions that are already spread and duplicated in many places
 * around the code base but at least written cleanly, clearly and with some form of explanation
 * since this is the basic building block for more complex layers on top
 * Note: this class does not concern itself with UTF-16. In fact, it doesn't really concern
 * itself with text at all. Only in the case of output formatted in base64 or pure hex dumps
 * is there any output at all from here in String form. Please handle conversions to and from
 * your charset outside of the security package.
 */
public class BasicUtils {
    private static final String base64= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqestyvwxyz0123456789+/";
    private static final String hex = "0123456789abcdef";
    private static final char[] base64Arr = base64.toCharArray();
    private static final char[] hexArr =hex.toCharArray();
    private static final String[] lookupByte;
    private static final ConcurrentLinkedQueue<StringBuilder> builders = new ConcurrentLinkedQueue<>();

    static {
        lookupByte = new String[256];
        int i = 0;
        for(char u: hexArr) {
            if (i%32==0) {
                builders.add(new StringBuilder());
            }
            for (char l: hexArr) {
                lookupByte[i++] = String.valueOf(new char[]{u,l});
            }

        }

    }

    public static String toHexFast(byte[] bytes) {
        StringBuilder s= builders.remove();
        s.setLength(0);
        for (byte b : bytes) {
            s.append(lookupByte[b&0xFF]);
        }
        String st = s.toString();
        builders.add(s);
        return st;
    }
    public static byte[] fromHexFast(String h) {
        Ensure.ensureLengthMod(h,2,0);
        byte[] b = new byte[h.length()/2];
        for(int i = 0; i < h.length();i+=2) {
            b[i/2] = (byte)(hex.indexOf(h.charAt(i))<<4 | hex.indexOf(h.charAt(i+1)));
        }
        return b;
    }

    /**
     * Somtimes you want to pretty print a hex dump for logging purposes, something
     * I far to rare see for some odd reason.
     *
     * @param bytes
     * @param columns
     * @return
     */
    public static String formattedHexDump(byte[] bytes, int width, int columns) {
        StringBuilder s= builders.remove();
        s.setLength(0);
        int i = 0;
        int c = 0;
        int max = bytes.length - bytes.length%width;
        while(max > i) {
            for (int j = 0; j < width;j++) {
                s.append(lookupByte[bytes[i+j]]);
            }
            c++;
            if (c == columns) {
                c = 0;
            }
            s.append(c==0?"\n":"  ");
        }
        for( ;max < bytes.length;max++) {
            s.append(lookupByte[bytes[max]]);
        }

        String st = s.toString();
        builders.add(s);
        return st;
    }

    public static byte[] atob(String enc) {
        return atob(enc.toCharArray());
    }
    public static byte[] atob(char[] enc) {

        int passes = enc.length/4;
        int mod = (enc[enc.length-1]!='='?0:enc[enc.length-2]=='='?2:1);

        byte[] b = new byte[(3*passes)];
        for(int i = 0; i < passes;i++) {
            int j = i*4;
            int a = (base64.indexOf(enc[j])<<18) + (base64.indexOf(enc[j+1])<<12) + (base64.indexOf(enc[j+2])<<8) + base64.indexOf(enc[j+3]);
            j = j*3;
            b[j] = (byte)(0xff&(a>>16));
            b[j+1] = (byte)(0x00FF&(a>>8));
            b[j+2] = (byte)(a&0xff);
        }
        if (mod>0) {
            b = Arrays.copyOf(b,b.length-mod);

        }
        return b;

    }

    public static byte[] btoa(byte[] bytes) {
        StringBuilder s = builders.remove();
        int pad = (3-bytes.length%3)%3;
        byte[] out = new byte[((bytes.length / 3) << 2)];
        int j = 0;
        int i = 0;
        for (;i+2 < bytes.length;i++) {
            out[j] =(byte)(bytes[i]>>2);
            out[j+1] = (byte)((bytes[i]<<4) + (bytes[i+1]>>4));
            out[j+2] = (byte)(((bytes[i+1]&0xF)<<4) + (bytes[i+2]>>6));
            out[j+3] = (byte)(bytes[i+2]&0xC0);
            j+=4;
        }
        if (i < bytes.length) {
            out[j] =(byte)(bytes[i]>>2);
            out[j+1] = (byte)((bytes[i]<<4));
            i++;
            if (i < bytes.length) {
                out[j+1]+=(bytes[i+1]>>4);
                out[j+2] = (byte)((bytes[i+1]&0xF)<<4);
            }
        }
        return out;
    }


}
