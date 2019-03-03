package me.jko.inflearnspringdatajpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager; //JPA의 가장 핵심적인 API

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Post post = new Post();
        post.setTitle("spring data jpa post");

        Comment comment = new Comment();
        comment.setComment("view quickly01");
        post.addComment(comment);

        Comment comment01 = new Comment();
        comment01.setComment("view quickly02");
        post.addComment(comment01);

        //Hibernate 이용
//        Session session = entityManager.unwrap(Session.class);
//        session.save(post);

        //cascade delete
        Session session = entityManager.unwrap(Session.class);
        Post post01 = session.get(Post.class, 1l);
        session.delete(post01);

    }
}
