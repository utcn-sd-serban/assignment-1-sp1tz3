package ro.utcn.sp1tz3.Assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.exception.QuestionNotFoundException;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Question> listQuestions(){
        return repositoryFactory.createQuestionRepository().findAll();
    }

    @Transactional
    public Question addQuestion(int userid, String title, String text, Timestamp creationdate, List<Tag> tags){
        return repositoryFactory.createQuestionRepository().save(new Question(userid, title, text, creationdate, tags));
    }

    @Transactional
    public void removeQuestion(int id){
        QuestionRepository repo = repositoryFactory.createQuestionRepository();
        Question question = repo.findById(id).orElseThrow(QuestionNotFoundException::new);
        repo.remove(question);
    }

    public Question get(int id){
        return repositoryFactory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }
}
