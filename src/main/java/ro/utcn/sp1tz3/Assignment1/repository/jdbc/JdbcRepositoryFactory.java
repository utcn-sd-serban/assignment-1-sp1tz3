package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sp1tz3.Assignment1.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {
    private final JdbcTemplate userTemplate;
    private final JdbcTemplate questionTemplate;
    private final JdbcTemplate answerTemplate;
    private final JdbcTemplate tagTemplate;

    @Override
    public UserRepository createUserRepository(){ return new JdbcUserRepository(userTemplate);}

    @Override
    public QuestionRepository createQuestionRepository(){ return new JdbcQuestionRepository(questionTemplate);}

    @Override
    public AnswerRepository createAnswerRepository(){ return new JdbcAnswerRepository(answerTemplate);}

    @Override
    public TagRepository createTagRepository(){ return new JdbcTagRepository(tagTemplate);}
}
