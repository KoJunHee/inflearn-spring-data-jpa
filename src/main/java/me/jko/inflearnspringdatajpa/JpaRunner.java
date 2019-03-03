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
        Account account = new Account();
        account.setUsername("hibernate");
        account.setPassword("444");

        Study study = new Study();
        study.setName("s pring data jpa");

//        account.getStudies().add(study);
//        study.setOwner(account);

        account.addStudy(study);

        //Hibernate 이용
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
    }
}
