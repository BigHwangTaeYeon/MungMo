package mungMo.member.domain.member.entity.function;

public interface MannerTemperatureFunction {
    void comeFromReport(String problem);
    void comeFromReview(int point);
    void comeFromNoShow();
}
