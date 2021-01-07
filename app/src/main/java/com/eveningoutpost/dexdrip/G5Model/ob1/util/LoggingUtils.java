package com.eveningoutpost.dexdrip.G5Model.ob1.util;



/**
 * Typically useful and typically useful to have available to reduce unnecessary imports,
 * especially when you start dealing with more complex forms of data that you may want to
 * log in various ways.
 * Packet logging is a typical example of how helpful it can be to have a few utility functions
 * on your side.
 */

public class LoggingUtils {

    private static final String[] HEX_BASE = "0123456789ABCDEF".split("");
    private static String[] BYTE_LOOKUP = generateByteLookup();
    private static short[] STRING_LOOKUP = generateStringLookup();
    private static final java.nio.ByteBuffer b = java.nio.ByteBuffer.allocate(5000);
    private static final String[] B64_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".split("");
    private static final char B64_PADDING = '=';

    /**
     * Funny thing that Base64 turned up so late right?
     * @param data
     * @return
     */
    public static byte[] binaryToBase64(byte[] data){
        return splitChunks(chunkAsInt(data,3),createBitMaskSet(0,
                6,4),6);
    }
    public static String binaryToBase64String(byte[] data) {
        int pad = data.length%3;
        StringBuilder out = new StringBuilder();
        for(byte b: binaryToBase64(data)) {
            out.append(B64_CHARSET[b]);
        }
        switch(pad) {
            case 2:
                out.setCharAt(out.length()-2, B64_PADDING);
            case 1:
                out.setCharAt(out.length()-1, B64_PADDING);
        }
        return out.toString();
    }
    private static byte[] splitChunks(int[] chunks,long[] masks, int bits) {

        byte[] parts = new byte[chunks.length*masks.length];
        int partIndex = 0;
        for (int i = 0; i < chunks.length;i++) {

            for (int j = masks.length-1; j >=0;j--) {
                parts[partIndex++] = (byte)((chunks[i]&(int)masks[j])>>(j*bits));
            }
        }
        return parts;
    }

    public static long createBitMask(int bits) {
        if (bits == 0) {
            return 0L;
        }
        long mask = 1;
        for (int i = 1; i < bits;i++) {
            mask = (mask << 1) + 1;
        }
        return mask;
    }
    public static long[] createBitMaskSet(int startBit, int bitsPerMask, int numberOfMasks) {
        if (startBit < 0 || bitsPerMask <= 0 || numberOfMasks < 1) {
            throw new IllegalArgumentException("Bad value: startBit " + startBit + " bitsPerMask: " + bitsPerMask + " numberofMasks: " + numberOfMasks);
        }
        if (startBit + bitsPerMask*numberOfMasks >= 62) {
            throw new IllegalArgumentException("Too many bits there may friend");
        }
        long[] masks = new long[numberOfMasks];
        masks[0] = createBitMask(bitsPerMask) << startBit;

        for (int i = 1; i < masks.length;i++) {
            masks[i] = masks[i-1]<<bitsPerMask;
        }
        return masks;
    }

    /**
     * This could easily be done with bytes but this makes it, at least with a
     * stream capable api a lot smoother
     * @param data
     * @param bytesPerChunk
     * @return
     */
    private static int[] chunkAsInt(byte[] data, int bytesPerChunk) {
        if (bytesPerChunk <2 || bytesPerChunk > 4) {
            throw new IllegalArgumentException("A chunk has to be between 2 and 4 bytes, you suggested " + bytesPerChunk);
        }
        if (data.length%bytesPerChunk > 0) {
            //pad with 0
            byte[] padded = new byte[data.length + (bytesPerChunk - (data.length%bytesPerChunk))];
            System.arraycopy(data,0,padded,0,data.length);
            for(int i = data.length;i< padded.length;i++) {
                padded[i] = 0;
            }
            data = padded;
        }
        int[] chunks = new int[(data.length/bytesPerChunk)];
        for(int i = 0; i < chunks.length;i++) {
            int dB = i*bytesPerChunk;
            for(int j = bytesPerChunk;j>0;j--) {
                chunks[i] += data[dB++]<<(8*(j-1));  //4 would become 24,16,8 0, 3 16 8 0
            }

        }
        //give me a stream api pliss
        return chunks;
    }

    /**
     * for simple pretty printing or logging in human readable format
     * consider using space = true and 16 per row
     */
    public static String byteAsHexString(byte b) {
        return bytesAsHexString(new byte[]{b});
    }
    public static String bytesAsHexString(byte[] convert) {
        return bytesAsHexString(convert,false,Integer.MAX_VALUE);
    }
    public static String bytesAsHexString(byte[] convert, boolean space) {
        return bytesAsHexString(convert, space, Integer.MAX_VALUE);
    }
    public static String bytesAsHexString(byte[] convert, boolean space, int perRow) {
        StringBuilder stringBuilder = new StringBuilder();
        String sp = space?" ":"";
        int count = 0;
        for(byte b: convert) {
            stringBuilder.append(BYTE_LOOKUP[b]).append(sp);
            count++;
            if (count == perRow) {
                count = 0;
                stringBuilder.setCharAt(stringBuilder.length()-1, '\n');
            }
        }
        return stringBuilder.toString().trim();
    }

    /**
     * Here you may wonder why this returns bytes. Please
     * refer to cryptpals challenge.
     * @param convert
     * @return
     */
    public static byte[] bytesAsHexBytes(byte[] convert) {
        if (convert.length > 2500) {
            int rounds = convert.length/2500 + (convert.length%2500 > 0?1:0);
            byte[] out = new byte[convert.length*2];
            for (int i = 0; i < convert.length;i+=2500) {
                byte[] chunk = new byte[Math.min(2500, convert.length-(i*2500))];
                System.arraycopy(convert,i*2500,chunk,0, chunk.length);
                System.arraycopy(bytesAsHexBytes(chunk),0,out,i*2500,chunk.length*2);
            }
            return out;
        } else {
            b.clear().rewind();
            for (byte c : convert) {
                b.putShort(STRING_LOOKUP[c]);
            }
            byte[] out = new byte[b.position()];
            b.position(0);
            b.get(out);
            return out;
        }
    }

    /**
     * Internals startup
     * @return
     */
    private static String[] generateByteLookup() {
        String[] look = new String[256];
        for(int i = 0; i < look.length;i++) {
            look[i] = HEX_BASE[i/16] + HEX_BASE[i%16];
        }
        return look;
    }
    private static short[] generateStringLookup() {
        short[] look = new short[BYTE_LOOKUP.length];
        int j = 0;
        for (int i = 0;i<BYTE_LOOKUP.length;i++) {
            look[i] = (short)(BYTE_LOOKUP[i].codePointAt(0)<<8 | BYTE_LOOKUP[i].codePointAt(1));

        }
        return look;
    }
}
