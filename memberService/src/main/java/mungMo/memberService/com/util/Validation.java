package mungMo.memberService.com.util;

import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.ValidationException;
import org.springframework.util.StringUtils;

public class Validation {

    public static void Nickname1(String name) throws ValidationException {
        if(!(name).equals(Nickname(name))) {
            throw new ValidationException(ResponseMessage.valueOfCode("Validation").getMessage());
        }
    }

    public static String Nickname(String name){
        String nickName = name.replaceAll("[^ㄱ-ㅎ|가-힣]", "");
        if(nickName.isEmpty()) {
            return "utteok" + getDate.getCurrentTime("YYYYMMDDHHmmss");
        }
        else {
            return nickName;
        }
    }
}
