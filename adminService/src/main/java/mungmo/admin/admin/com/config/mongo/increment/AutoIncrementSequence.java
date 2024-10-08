package mungmo.admin.admin.com.config.mongo.increment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "auto_sequence")
public class AutoIncrementSequence {
    @Id
    private String id;
    private Long seq;
}