package com.game.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author：xuxin
 * @Date: 2019/7/9 18:14
 */
public class ScannerUtil {
    private static final Pattern pattern = Pattern.compile("[0-9]*");

    public static boolean isNumeric(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
