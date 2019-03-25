package ro.utcn.sp1tz3.Assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"questionid", "userid", "title", "text", "creationdate"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionid;
    private Integer userid;
    private String title;
    private String text;
    private Timestamp creationdate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "question_tag",
    joinColumns = {@JoinColumn(name = "questionid")},
    inverseJoinColumns = {@JoinColumn(name = "tagid")})
    private List<Tag> tags;

    public Question(Integer userid, String title, String text, Timestamp creationdate, List<Tag> tags){
        this.userid = userid;
        this.title = title;
        this.text = text;
        this.creationdate = creationdate;
        this.tags = tags;
    }
}
