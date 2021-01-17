package com.eveningoutpost.dexdrip.services.ob1.ble;

import com.eveningoutpost.dexdrip.G5Model.FastCRC16;
import com.eveningoutpost.dexdrip.utils.security.BasicUtils;

import org.junit.Test;

public class PreppedRequestsTest {

    @Test
    public void prepareLookup() {
        byte[] l = new byte[3];
        for (int i = 0; i < 256;i++) {
            l[0] = (byte)i;
            System.arraycopy(FastCRC16.calculate(l[0]),0,l,1,2);

            System.out.println("0x" + BasicUtils.toHexFast(l));
        }

    }
}
