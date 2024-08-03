package mungMo.member.domain.member.entity.function;

import mungMo.member.domain.member.entity.MemberEntity;
import org.springframework.util.ObjectUtils;

public class MannerTemperatureFunctionPolicyV1 implements MannerTemperatureFunction{
    private final MemberEntity member;

    public MannerTemperatureFunctionPolicyV1(MemberEntity member) {
        this.member = member;
    }

    @Override
    public void comeFromReport(String problem) {
        switch (problem) {
            case "gender" -> member.fromPolicy(member.getMannerTemperature() -4);
            case "dog" -> member.fromPolicy(member.getMannerTemperature() -2);
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public void comeFromReview(int point) {
        if(point < 1 || point > 5 || ObjectUtils.isEmpty(point)) throw new IllegalArgumentException();
        member.fromPolicy(member.getMannerTemperature() +Math.round((float) point / 2));
    }

    @Override
    public void comeFromNoShow() {
        member.fromPolicy(member.getMannerTemperature() -5);
    }
}
