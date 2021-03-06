package ORM.repository.Impl;

import ORM.models.BlogPost;
import ORM.repository.BlogRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BlogRepositoryImpl implements BlogRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public BlogPost findById(Integer id) {
        TypedQuery<BlogPost> typedQuery = entityManager.createQuery(
                "select s from BlogPost s where s.id = :id", BlogPost.class);
        typedQuery.setParameter("id",id);
        return typedQuery.getSingleResult();
    }

    @Override
    public String save(BlogPost postBlog) {
        entityManager.persist(postBlog);
        return "Done!";
    }

    @Override
    public List<BlogPost> findAll() {
        TypedQuery<BlogPost> typedQuery = entityManager.createQuery(
                "select s from BlogPost as s", BlogPost.class);
        return typedQuery.getResultList();
    }

    @Override
    public void edit(BlogPost postBlog) {
        entityManager.merge(postBlog);
    }

    @Override
    public void delete(BlogPost postBlog) {
        entityManager.remove(postBlog);
    }
}
