package com.eveningoutpost.dexdrip.services.ob1.ble;

import com.eveningoutpost.dexdrip.Services.ob1.ble.MACAdressFilter;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests
 * com.eveningoutpost.dexdrip.Services.ob1.ble.MACAdressFilter
 * which simply controls the validity of a specific standard
 * MAC adress according to the IEE802 standard and it's commmon
 * variants.
 */
public class MACAddressFilterTests {
    private String wellFormedColon = "12:34:56:78:9A:0f";
    private String malFormedColonLength = "12:34:6:78:9A:0f";
    private String malFormedColonLength2 = "12:34:666:78:9A:0f";
    private String malFormedColonLength3 = "12:34:66:78:9A:0f4";
    private String malFormedColonLength4 = "412:34:66:78:9A:0f";
    private String malFormedColonMixed = "12:34-56:78:9A:0f";
    private String malFormedColonNonHex = "12:H4:56:78:9A:0f";

    private String wellFormedHyphen = "12-34-56-78-9A-0f";
    private String wellFormedDot = "12.34.56.78.9A.0f";

    private MACAdressFilter colonFilter = new MACAdressFilter(MACAdressFilter.COLON);
    private MACAdressFilter dotFilter = new MACAdressFilter(MACAdressFilter.DOT);
    private MACAdressFilter hyphenFilter = new MACAdressFilter(MACAdressFilter.HYPHEN);
    private MACAdressFilter hyphenDotFilter = new MACAdressFilter(MACAdressFilter.HYPHEN,MACAdressFilter.DOT);

    @Test
    public void checkWellFormedColon() {
        Assert.assertTrue(colonFilter.matches(wellFormedColon));
    }

    /**
     * Issue tested, a mac address where one hex group consists of one digit
     * and not two
     */
    @Test
    public void checkMalformedColonLength() {
        Assert.assertFalse(malFormedColonLength + " did not match the filter", colonFilter.matches(malFormedColonLength));
    }
    /**
     * Issue tested, a mac address where one hex group consists of three digits
     * and not two
     */
    @Test
    public void checkMalformedColonLength2() {
        Assert.assertFalse(malFormedColonLength2 + " did not match the filter", colonFilter.matches(malFormedColonLength2));
    }
    /**
     * Issue tested, a mac address where the last hex group consists of three digits
     * and not two
     */
    @Test
    public void checkMalformedColonLength3() {
        Assert.assertFalse(malFormedColonLength3 + " did not match the filter", colonFilter.matches(malFormedColonLength3));
    }
    /**
     * Issue tested, a mac address where the last hex group consists of three digits
     * and not two
     */
    @Test
    public void checkMalformedColonLength4() {

        Assert.assertFalse(malFormedColonLength4 + " did not match the filter", colonFilter.matches(malFormedColonLength4));
    }

    /**
     * Issue tested, a mac address with a non hex digit should not pass
     */
    @Test
    public void checkMalformedColonNonHex() {
        Assert.assertFalse(malFormedColonNonHex + " did not match the filter", colonFilter.matches(malFormedColonNonHex));
    }
    /**
     * Issue tested, a mac address with mixed separators should not pass
     */
    @Test
    public void checkMalformedColonMixedSeparators() {
        Assert.assertFalse(malFormedColonMixed + " did not match the filter", colonFilter.matches(malFormedColonMixed));
    }

    /**
     * Issue tested, null value should fail with exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNullValueFails() {
        colonFilter.matches(null);
        Assert.assertFalse(true);
    }
    /**
     * Issue tested, empty string value should fail with exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyStringFails() {
        colonFilter.matches("");
        Assert.assertFalse(true);
    }
    /**
     * Issue tested, String with whitespace only, e.g. non empty should fail with exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkWhitespaceOnlyFails() {
        colonFilter.matches("       ");
        Assert.assertFalse(true);
    }

    /**
     * CHeck wellformed Hyphen
     */
    @Test
    public void checkWellFormedHyphen() {
        Assert.assertTrue(hyphenFilter.matches(wellFormedHyphen));
    }


    /**
     * Check wellformed dot
     */
    @Test
    public void checkWellFormedDot() {
        MACAdressFilter filter = new MACAdressFilter(MACAdressFilter.DOT);
        Assert.assertTrue(dotFilter.matches(wellFormedDot));
    }

    /**
     * Check combinations
     */
    @Test
    public void checkWellFormedHyphenAndDot() {
        Assert.assertTrue(hyphenDotFilter.matches(wellFormedHyphen));
        Assert.assertTrue(hyphenDotFilter.matches(wellFormedDot));
    }
}
