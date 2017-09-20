package com.liliuhuan.com.mylibrary.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @Author lc
 */
public class StringUtil {
	/**
	 * 
	* @Method: reverse 
	* @Description:反转字符串  
	* @param input
	* @return
	 */
	public static String reverse(String input){
		StringBuffer sb = new StringBuffer();
		for(int i=input.length()-1;i>=0;i--){
			sb.append(input.charAt(i));
		}
		return sb.toString();
	}
	
	
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * 
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String bytes2Base64(byte[] buff){
        return Base64.encodeToString(buff, Base64.DEFAULT);
    }


    public static boolean isIDCard(String card){//大陆 港澳台身份证
       // Pattern pattern = Pattern.compile("(\\d{14}[0-9xX])|(\\d{17}[0-9xX])");///^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/
        Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        Pattern taiwan = Pattern.compile("^[A-Z][0-9]{9}$");
        Pattern xianggang = Pattern.compile("^[A-Z][0-9]{6}\\([0-9A]\\)$");
        Pattern aomen = Pattern.compile("^[157][0-9]{6}\\([0-9]\\)$");

        Matcher matcher = pattern.matcher(card);
        Matcher matcher1 = taiwan.matcher(card);
        Matcher matcher2 = xianggang.matcher(card);
        Matcher matcher3 = aomen.matcher(card);
        return (matcher.matches() || matcher1.matches()||matcher2.matches()||matcher3.matches());
    }

    public static boolean isNewIDCard(String card){//大陆 港澳台身份证
        Pattern pattern = Pattern.compile("^[A-Za-z0-9()]{7,18}$");///^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/
        Matcher matcher = pattern.matcher(card);
        return (matcher.matches());
    }

    /**
     * 去除html字符串中的转义。
     *
     * @return
     */
    public static String unescapeHtmlString( String html ) {

        String strEscape = html.replace("&lt;", "<");
        strEscape = strEscape.replace("&gt;", ">");
        strEscape = strEscape.replace("&amp;", "&");
        strEscape = strEscape.replace("&quot;", "\"");
        strEscape = strEscape.replace("&reg;", "®");
        strEscape = strEscape.replace("&copy;", "©");
        strEscape = strEscape.replace("&trade;", "™");

        return strEscape;
    }
    /**
     * 去除html字符串中的转义。
     *
     * @return
     */
    public static String unescapeHtmlString01( String html ) {

        String strEscape = html.replace("&lt;", "");
        strEscape = strEscape.replace("&gt;", "");
        strEscape = strEscape.replace("&amp;", "");
        strEscape = strEscape.replace("&quot;", "");
        strEscape = strEscape.replace("&reg;", "");
        strEscape = strEscape.replace("&copy;", "");
        strEscape = strEscape.replace("&trade;", "");
        strEscape = strEscape.replace("<p>","");
        strEscape = strEscape.replace("</p>","");
        strEscape = strEscape.replace("/", "");
        strEscape = strEscape.replace("br","");
        return strEscape;
    }


    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    ///字母 数字 ——
    public static boolean isStringFormatCorrect(String str) {
        String strPattern = "[a-zA-Z_0-9]+";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
