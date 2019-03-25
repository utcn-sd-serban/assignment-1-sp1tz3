package ro.utcn.sp1tz3.Assignment1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.repository.AnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateAnswerRepository implements AnswerRepository {
    private final EntityManager entityManager;

    @Override
    public List<Answer> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
        query.select(query.from(Answer.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Answer save(Answer answer) {
        if (answer.getAnswerid() == null) {
            entityManager.persist(answer);
            return answer;
        } else {
            return entityManager.merge(answer);
        }
    }

    @Override
    public void remove(Answer answer) {
        entityManager.remove(answer);
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(entityManager.find(Answer.class, id));
    }
}
