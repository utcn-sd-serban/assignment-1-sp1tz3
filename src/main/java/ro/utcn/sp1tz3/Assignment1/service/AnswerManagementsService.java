package ro.utcn.sp1tz3.Assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.exception.AnswerNotFoundException;
import ro.utcn.sp1tz3.Assignment1.repository.AnswerRepository;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerManagementsService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Answer> listAnswers(){
        return repositoryFactory.createAnswerRepository().findAll();
    }

    @Transactional
    public Answer addAnswer(int questionid, int userid, String text, Timestamp creationdate){
        return repositoryFactory.createAnswerRepository().save(new Answer(questionid,userid,text,creationdate));
    }

    @Transactional
    public void removeAnswer(int id){
        AnswerRepository repo = repositoryFactory.createAnswerRepository();
        Answer answer = repo.findById(id).orElseThrow(AnswerNotFoundException::new);
        repo.remove(answer);
    }

}
