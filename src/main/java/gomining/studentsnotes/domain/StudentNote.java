package gomining.studentsnotes.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class StudentNote {
    @Id
    private String id;
    private String student;
    private String subject;
    private double note;
    private boolean approved;
}
