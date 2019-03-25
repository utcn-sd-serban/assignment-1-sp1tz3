package ro.utcn.sp1tz3.Assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerid;
    private Integer questionid;
    private Integer userid;
    private String text;
    private Timestamp creationdate;

    public Answer(Integer questionid, Integer userid, String text, Timestamp creationdate){
        this.questionid = questionid;
        this.userid = userid;
        this.text = text;
        this.creationdate = creationdate;
    }
}
