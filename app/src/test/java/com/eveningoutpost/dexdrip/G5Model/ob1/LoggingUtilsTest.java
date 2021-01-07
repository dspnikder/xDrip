package com.eveningoutpost.dexdrip.G5Model.ob1;

import com.eveningoutpost.dexdrip.G5Model.ob1.util.LoggingUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;

public class LoggingUtilsTest {

    @Test
    public void testMaskCreation() {
        byte check = 0xFF>>2;
        byte mask = (byte)LoggingUtils.createBitMask(6);
        String asString= LoggingUtils.byteAsHexString(mask);
        Assert.assertNotNull(asString);
        Assert.assertEquals(Byte.parseByte(asString,16),check);
    }

    @Test
    public void testMaskCreation2() {
        byte check = 0xFF>>1;
        byte mask = (byte)LoggingUtils.createBitMask(7);
        String asString= LoggingUtils.byteAsHexString(mask);
        Assert.assertNotNull(asString);
        Assert.assertEquals(Byte.parseByte(asString,16),check);
    }

    @Test
    public void testMultiMask() {
        long[] masks = LoggingUtils.createBitMaskSet(0,6,4);
        //0x3F 0xFC0 0x3F000 0xFC0000
        for (int i = 0; i < masks.length;i++) {
            Assert.assertEquals((int)masks[i], 0x3F<<(i*6));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStartBit()  {
        long[] masks = LoggingUtils.createBitMaskSet(-1,6,4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroLengthBitMask()  {
        long[] masks = LoggingUtils.createBitMaskSet(0,0,4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testOverflow()  {
        long[] masks = LoggingUtils.createBitMaskSet(22,5,40);
    }

    ////////////////////// Base64 tests /////////////////////////////

    @Test
    public void encodeAsBase64() throws UnsupportedEncodingException {
        String quote = "Hell is empty and all the devils are up here.";
        String correct = "SGVsbCBpcyBlbXB0eSBhbmQgYWxsIHRoZSBkZXZpbHMgYXJlIHVwIGhlcmUu";

        byte[] raw = quote.getBytes(StandardCharsets.UTF_8);
        byte[] encoded = LoggingUtils.binaryToBase64(raw);
        String encodedString = LoggingUtils.binaryToBase64String(raw);

        Assert.assertThat(correct, is(encodedString));
    }
}
