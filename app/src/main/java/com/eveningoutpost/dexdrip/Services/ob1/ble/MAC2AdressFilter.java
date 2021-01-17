package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.eveningoutpost.dexdrip.utils.validation.Ensure;

import java.util.regex.Pattern;

public class MAC2AdressFilter extends AbstractScanFilter<String> {

    public static final int HYPHEN = 1;
    public static final int COLON = 2;
    public static final int DOT = 4;
    private String accepatable = " -: .";

    private String separators = ":";

    public MAC2AdressFilter() {}
    public MAC2AdressFilter(int... accepted) {
        if (accepted != null && accepted.length > 0) {
            separators = "";
        }
        for(int a: accepted) {
            separators+=accepatable.charAt(a);
        }
    }

    @Override
    protected boolean match(String value) {
        Ensure.notNull(value);
        if (value.length() != 17) {
            return false;
        }
        char[] seps = separators.toCharArray();
        for(char s: seps) {
            if (value.indexOf(s) == 2) {
                char[] allChars = value.toCharArray();
                char[] onlyDigits = {allChars[0], allChars[1] ,allChars[3], allChars[4],allChars[6],
                        allChars[7],allChars[9],allChars[10],allChars[12], allChars[13],allChars[15],allChars[16]};
                String hex = "1234567890ABCDEFabcdef";
                for (char d : onlyDigits) {
                    if (hex.indexOf(d)<0) {
                        return false;
                    }
                }

                return (allChars[5] == allChars[8] && allChars[5] == allChars[11] && allChars[14] == s && s == allChars[5]);
            }
        }

        return false;
    }
}
