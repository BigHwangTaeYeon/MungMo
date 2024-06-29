package mungMo.memberService.com.util;

import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.ValidationException;

public class Validation {

    public static void nickname(String name) throws ValidationException {
        if(!(name).equals(nicknameConfirm(name))) {
            throw new ValidationException(ResponseMessage.valueOfCode("Validation").getMessage());
        }
    }

    public static String nicknameConfirm(String name){
        String nickName = name.replaceAll("[^ㄱ-ㅎ|가-힣]", "");
        if(nickName.isEmpty()) {
            return "utteok" + System.currentTimeMillis();
        }
        else {
            return nickName;
        }
    }
}
