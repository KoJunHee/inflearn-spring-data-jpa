package me.jko.inflearnspringdatajpa.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository<Post>{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("Custom findMyPost");
        return entityManager.createQuery("SELECT p FROM Post as p", Post.class)
                .getResultList();
    }

    @Override
    public void delete(Post entity) {
        System.out.println("Custom delete");
        entityManager.remove(entity);

    }
}
