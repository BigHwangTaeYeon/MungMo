package mungMo.member.response.publiccode.entity;

public enum PublicType {
    MBTP,DGTP,RPRT;

    public static String str(PublicType type) {
        return type.toString();
    }
}
