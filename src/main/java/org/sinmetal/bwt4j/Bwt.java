package org.sinmetal.bwt4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sinmetal on 2017/09/14.
 */
public class Bwt {

    public static char[] create(String text) {
        return text.toCharArray();
    }

    static char[][] sortBlockText(char[][] blockText) {
        List<String> texts = new ArrayList<>();
        for (char[] text : blockText) {
            String value = String.valueOf(text);
            texts.add(value);
        }
        Collections.sort(texts);

        char[][] results = new char[blockText.length][blockText.length];
        for (int i = 0; i < texts.size(); i++) {
            results[i] = texts.get(i).toCharArray();
        }
        return results;
    }

    static char[][] createBlockText(String text) {
        String value = text.concat("$");
        char[] chars = value.toCharArray();

        char[][] bwt = new char[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                int index = j + i;
                if (index >= chars.length) {
                    index -= chars.length;
                }
                bwt[i][j] = chars[index];
            }
        }

        return bwt;
    }
}
