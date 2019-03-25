package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sp1tz3.Assignment1.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new User(rs.getInt("userid"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("usertype"),
                rs.getInt("score"));
    }
}
