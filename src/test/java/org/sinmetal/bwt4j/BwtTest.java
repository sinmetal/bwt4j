package org.sinmetal.bwt4j;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sinmetal on 2017/09/14.
 */
public class BwtTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BwtTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(BwtTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testCreateBlockText() {
        final String TEXT = "abracadabra";
        char[][] bwt = Bwt.createBlockText(TEXT);

        assertEquals(bwt.length, TEXT.length() + 1);
        assertEquals(String.valueOf(bwt[0]), "abracadabra$");
        assertEquals(String.valueOf(bwt[1]), "bracadabra$a");
        assertEquals(String.valueOf(bwt[2]), "racadabra$ab");
        assertEquals(String.valueOf(bwt[3]), "acadabra$abr");
        assertEquals(String.valueOf(bwt[4]), "cadabra$abra");
        assertEquals(String.valueOf(bwt[5]), "adabra$abrac");
        assertEquals(String.valueOf(bwt[6]), "dabra$abraca");
        assertEquals(String.valueOf(bwt[7]), "abra$abracad");
        assertEquals(String.valueOf(bwt[8]), "bra$abracada");
        assertEquals(String.valueOf(bwt[9]), "ra$abracadab");
        assertEquals(String.valueOf(bwt[10]), "a$abracadabr");
        assertEquals(String.valueOf(bwt[11]), "$abracadabra");
    }

    public void testCreateBlockTextJapanese() {
        final String TEXT = "こんにちは世界";
        char[][] bwt = Bwt.createBlockText(TEXT);

        assertEquals(bwt.length, TEXT.length() + 1);
        assertEquals(String.valueOf(bwt[0]), "こんにちは世界$");
        assertEquals(String.valueOf(bwt[1]), "んにちは世界$こ");
        assertEquals(String.valueOf(bwt[2]), "にちは世界$こん");
        assertEquals(String.valueOf(bwt[3]), "ちは世界$こんに");
        assertEquals(String.valueOf(bwt[4]), "は世界$こんにち");
        assertEquals(String.valueOf(bwt[5]), "世界$こんにちは");
        assertEquals(String.valueOf(bwt[6]), "界$こんにちは世");
        assertEquals(String.valueOf(bwt[7]), "$こんにちは世界");
    }

    public void testSort() {
        List<String> list = new ArrayList<>();
        list.add("a$");
        list.add("$a");
        Collections.sort(list);

        assertEquals(list.get(0), "$a");
        assertEquals(list.get(1), "a$");
    }

    public void testSortBlockText() {
        final String TEXT = "abracadabra";
        char[][] bwt = Bwt.createBlockText(TEXT);

        char [][]sortedText = Bwt.sortBlockText(bwt);

        assertEquals(String.valueOf(sortedText[0]), "$abracadabra");
        assertEquals(String.valueOf(sortedText[1]), "a$abracadabr");
        assertEquals(String.valueOf(sortedText[2]), "abra$abracad");
        assertEquals(String.valueOf(sortedText[3]), "abracadabra$");
        assertEquals(String.valueOf(sortedText[4]), "acadabra$abr");
        assertEquals(String.valueOf(sortedText[5]), "adabra$abrac");
        assertEquals(String.valueOf(sortedText[6]), "bra$abracada");
        assertEquals(String.valueOf(sortedText[7]), "bracadabra$a");
        assertEquals(String.valueOf(sortedText[8]), "cadabra$abra");
        assertEquals(String.valueOf(sortedText[9]), "dabra$abraca");
        assertEquals(String.valueOf(sortedText[10]), "ra$abracadab");
        assertEquals(String.valueOf(sortedText[11]), "racadabra$ab");
    }

    public void testSortBlockTextJapanese() {
        final String TEXT = "こんにちは世界";
        char[][] bwt = Bwt.createBlockText(TEXT);

        char [][]sortedText = Bwt.sortBlockText(bwt);

        assertEquals(String.valueOf(sortedText[0]), "$こんにちは世界");
        assertEquals(String.valueOf(sortedText[1]), "こんにちは世界$");
        assertEquals(String.valueOf(sortedText[2]), "ちは世界$こんに");
        assertEquals(String.valueOf(sortedText[3]), "にちは世界$こん");
        assertEquals(String.valueOf(sortedText[4]), "は世界$こんにち");
        assertEquals(String.valueOf(sortedText[5]), "んにちは世界$こ");
        assertEquals(String.valueOf(sortedText[6]), "世界$こんにちは");
        assertEquals(String.valueOf(sortedText[7]), "界$こんにちは世");
    }
}
