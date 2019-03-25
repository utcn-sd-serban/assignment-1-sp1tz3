package ro.utcn.sp1tz3.Assignment1.repository;


public interface RepositoryFactory {
    UserRepository createUserRepository();
    QuestionRepository createQuestionRepository();
    AnswerRepository createAnswerRepository();
    TagRepository createTagRepository();
}
