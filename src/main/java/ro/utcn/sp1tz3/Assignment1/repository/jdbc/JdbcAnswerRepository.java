package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.repository.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcAnswerRepository implements AnswerRepository {
    private final JdbcTemplate template;

    @Override
    public List<Answer> findAll() {
        return template.query("SELECT * FROM answer", new AnswerMapper());
    }

    @Override
    public Answer save(Answer answer) {
        if(answer.getAnswerid() == null){
            answer.setAnswerid(insert(answer));
        }
        else update(answer);
        return answer;
    }

    @Override
    public void remove(Answer answer) {
        template.update("DELETE FROM answer WHERE answerid = ?", answer.getAnswerid());
    }

    @Override
    public Optional<Answer> findById(int id) {
        List<Answer> answers = template.query("SELECT * FROM answer WHERE answerid = ?", new AnswerMapper(), id);
        return answers.isEmpty() ? Optional.empty() : Optional.of(answers.get(0));
    }

    private int insert(Answer answer){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("answer");
        insert.usingGeneratedKeyColumns("answerid");
        Map<String, Object> map = new HashMap<>();
        map.put("questionid", answer.getQuestionid());
        map.put("userid", answer.getUserid());
        map.put("text", answer.getText());
        map.put("creationdate", answer.getCreationdate());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Answer answer){
        template.update("UPDATE answer SET questionid = ?, userid = ?, text = ?, creationdate = ? WHERE answerid = ?",
                answer.getQuestionid(), answer.getUserid(), answer.getText(), answer.getCreationdate(), answer.getAnswerid());
    }
}