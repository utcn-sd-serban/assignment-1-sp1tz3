package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Question(
                rs.getInt("questionid"),
                rs.getInt("userid"),
                rs.getString("title"),
                rs.getString("text"),
                rs.getTimestamp("creationdate"),
                new ArrayList<Tag>());
    }
}