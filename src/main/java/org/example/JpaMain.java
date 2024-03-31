package org.example;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            //code
//            Post article = new Post();
//            article.setId(3L);
//            article.setTitle("제목3");
//            article.setBody("내용3");
//
//            em.persist(article);

//            Post post = em.find(Post.class, 3L);
//            System.out.println(post.getId());
//            System.out.println(post.getTitle());
//            System.out.println(post.getBody());

//            Post post = em.find(Post.class, 3L);
//            post.setBody("내용변경");

            Post removeTarget = em.find(Post.class, 3L);
            em.remove(removeTarget);

            List<Post> postList = em.createQuery("select p from Post as p", Post.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Post post : postList) {
                System.out.println(post.getId());
                System.out.println(post.getTitle());
                System.out.println(post.getBody());
            }

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }
}
