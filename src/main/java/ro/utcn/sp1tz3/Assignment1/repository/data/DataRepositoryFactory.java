package ro.utcn.sp1tz3.Assignment1.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sp1tz3.Assignment1.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {
    private final DataUserRepository userRepository;
    private final DataQuestionRepository questionRepository;
    private final DataAnswerRepository answerRepository;
    private final DataTagRepository tagRepository;

    @Override
    public UserRepository createUserRepository(){ return userRepository;}

    @Override
    public QuestionRepository createQuestionRepository(){ return questionRepository;}

    @Override
    public AnswerRepository createAnswerRepository(){ return answerRepository;}

    @Override
    public TagRepository createTagRepository(){ return tagRepository;}


}
