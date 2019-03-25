package ro.utcn.sp1tz3.Assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.exception.TagNotFoundException;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> listTags(){
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Tag addTag(String title){
        Tag tag = new Tag(title);
        tag.setQuestions(null);
        return repositoryFactory.createTagRepository().save(tag);
    }

    @Transactional
    public void removeTag(int id){
        TagRepository repo = repositoryFactory.createTagRepository();
        Tag tag = repo.findById(id).orElseThrow(TagNotFoundException::new);
        repo.remove(tag);
    }
}
