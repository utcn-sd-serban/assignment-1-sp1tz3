package ro.utcn.sp1tz3.Assignment1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateTagRepository implements TagRepository {
    private final EntityManager entityManager;


    @Override
    public List<Tag> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        query.select(query.from(Tag.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Tag save(Tag tag) {
        if(tag.getTagid() == null){
            entityManager.persist(tag);
            return tag;
        } else {
            return entityManager.merge(tag);
        }
    }

    @Override
    public void remove(Tag tag) {
        entityManager.remove(tag);
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }
}
