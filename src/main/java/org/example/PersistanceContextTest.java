package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistanceContextTest {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

//            Post post = new Post();
//            post.setId(21L);
//            post.setTitle("제목21");
//            post.setBody("내용21");
//
//            System.out.println("BEFORE");
//            em.persist(post);
//            System.out.println("AFTER");

            Post post1 = em.find(Post.class, 21L); // select query 실행되지 않음. persist한 객체를 1차 캐시에 저장했기 때문에
            System.out.println(post1.getId());
            System.out.println(post1.getTitle());
            System.out.println(post1.getBody());

            Post post2 = em.find(Post.class, 21L); // select query 실행되지 않음. 1차 캐시에 저장된 객체를 반환

            System.out.println(post1 == post2); // true
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
