package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Answer(
                rs.getInt("answerid"),
                rs.getInt("questionid"),
                rs.getInt("userid"),
                rs.getString("text"),
                rs.getTimestamp("creationdate"));
    }
}