package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.eveningoutpost.dexdrip.utils.validation.Ensure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is not a really REAL class as such but mostly used as an example of how
 * you can use existing tests when refactoring and how important it is that the
 * original author takes the miniscule amount of time it takes to write the most
 * basic of tests, the ones that are involved or conmplex to understand are often
 * less useful than the straight forward and clean ones
 * This class (which is chainable) checks if a mac address follows the IEE802
 * standard or one of it's variant, MAC adresses are cmmonly used to identify
 * network devices (most commonly and tend to vary depending on the transport
 * layer and API used.
 * @Since 2021-01-14
 */
public class MACAdressFilter extends AbstractScanFilter<String> {
    public static final int HYPHEN = 1;
    public static final int COLON = 2;
    public static final int DOT = 4;
    private static Pattern macPattern = Pattern.compile("(([a-f0-9]{2,2})([:\\-\\\\.])){5,5}([a-f0-9]{2,2})",Pattern.CASE_INSENSITIVE);

    private int separators = MACAdressFilter.COLON;

    public MACAdressFilter() {}
    public MACAdressFilter(int... accepted) {
        if (accepted != null && accepted.length > 0) {
            separators = 0;
        }
        for(int a: accepted) {
            separators+=a;
        }
    }



    @Override
    protected boolean match(String value) {
        Ensure.notNullNorEmpty(value);

        Matcher m = macPattern.matcher(value);
        if (m.matches()) {
            String sep = m.group(3);
            Ensure. notNull(sep);
            if (value.split(Pattern.quote(sep)).length!=6) {
                return false;
            }
            switch (sep.charAt(0)) {
                case '-':

                    return (separators & HYPHEN) > 0;
                case '.':
                    return (separators & DOT) > 0;
                case ':':
                    return (separators & COLON) > 0;
            }
        }
        return false;
    }
}
