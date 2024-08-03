package mungMo.member.domain.member.entity.function;

import mungMo.member.domain.member.entity.MemberEntity;

public class DIMannerTemperature {
    public MannerTemperatureFunction mtfFactory(MemberEntity member) {
        return new MannerTemperatureFunctionPolicyV1(member);
    }
}
