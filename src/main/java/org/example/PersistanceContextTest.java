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
//            em.persist(post);  // BEFORE, AFTER 이후에  query 출력 확인. insert query는 commit 시점에 실행됨
//            System.out.println("AFTER");

//            Post post1 = em.find(Post.class, 21L); // select query 실행되지 않음. persist한 객체를 1차 캐시에 저장했기 때문에
//            System.out.println(post1.getId());
//            System.out.println(post1.getTitle());
//            System.out.println(post1.getBody());
//
//            Post post2 = em.find(Post.class, 21L); // select query 실행되지 않음. 1차 캐시에 저장된 객체를 반환
//
//            System.out.println(post1 == post2); // true

//            Post post1 = new Post(4L, "제목4", "내용4");
//            Post post2 = new Post(5L, "제목5", "내용5");
//            em.persist(post1);
//            em.persist(post2);
//
//            System.out.println("======================"); // 쓰기 지연 sql 효과로 선 밑에서 insert query 일괄 실행됨

            System.out.println("=============");
            Post post1 = em.find(Post.class, 4L); // select query 실행 후
            System.out.println("=============");
            post1.setBody("변경내용44"); // 변경을 감지하고

            // 더티 체킹 -> 1차 캐시에 객체 읽어서 저장될 때 스냅샷 떠놓고 이후 변경사항이 있는지 체크
            // 변경 감지 되면 쓰기 지연 저장소에 update query 저장
            tx.commit(); // update query 실행
            // em.flush(); // 쓰기 지연 저장소에 있는 query 실행

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
