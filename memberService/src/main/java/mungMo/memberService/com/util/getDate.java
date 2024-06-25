package mungMo.memberService.com.util;

import java.text.SimpleDateFormat;

public class getDate {
    public static String getCurrentTime(String timeFormat){
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }
}
