package ro.utcn.sp1tz3.Assignment1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sp1tz3.Assignment1.repository.*;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
    private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private final InMemoryQuestionRepository questionRepository = new InMemoryQuestionRepository();
    private final InMemoryAnswerRepository answerRepository = new InMemoryAnswerRepository();
    private final InMemoryTagRepository tagRepository = new InMemoryTagRepository();

    @Override
    public UserRepository createUserRepository(){ return userRepository;}

    @Override
    public QuestionRepository createQuestionRepository(){ return questionRepository;}

    @Override
    public AnswerRepository createAnswerRepository(){ return answerRepository;}

    @Override
    public TagRepository createTagRepository(){ return tagRepository;}
}
