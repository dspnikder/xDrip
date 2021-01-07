package com.eveningoutpost.dexdrip.G5Model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomnessTest {



    @Test
    public void generate100AuthReuestTxMessage() {
        List<byte[]> outcomes = new ArrayList<>();
        for(int i = 0; i < 1000;i++) {
            outcomes.add(new AuthRequestTxMessage(20).getByteSequence());
        }

        //we know byte 0 is opcode so let just look at the mid range;
        int[] distribution = new int[256];
        Arrays.fill(distribution,0);
        outcomes.forEach(o -> {
            for (int i = 1; i < 21;i++) {
                distribution[0xFF&o[i]]+=1;
            }
        });
        int count = 0;
        for (int i = 0; i < distribution.length;i++) {
            if (distribution[i]>0) {
                count++;
                System.out.println(i + ": " + distribution[i]);
            }
        }
        System.out.println((256-count) + " plausible values had no value");

    }
}
