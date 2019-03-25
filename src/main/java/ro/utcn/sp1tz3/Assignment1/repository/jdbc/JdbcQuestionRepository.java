package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate template;

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question", new QuestionMapper());
    }

    @Override
    public Question save(Question question) {
        if(question.getQuestionid() == null){
            question.setQuestionid(insert(question));
        }
        else {
            update(question);
        }
        return question;
    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE questionid = ?", question.getQuestionid());
    }

    @Override
    public Optional<Question> findById(int id) {
        List<Question> questions = template.query("SELECT * FROM question WHERE questionid = ?", new QuestionMapper(), id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    private int insert(Question question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.usingGeneratedKeyColumns("questionid");
        Map<String, Object> map = new HashMap<>();
        map.put("userid", question.getUserid());
        map.put("title", question.getTitle());
        map.put("text", question.getText());
        map.put("creationdate", question.getCreationdate());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Question question){
        template.update("UPDATE question SET userid = ?, title = ?, text = ?, creationdate = ? WHERE questionid = ?",
                question.getUserid(), question.getTitle(), question.getText(), question.getCreationdate(), question.getQuestionid());
    }
}
