package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(
                rs.getInt("tagid"),
                rs.getString("title"),
                new ArrayList<Question>()
                );
    }
}