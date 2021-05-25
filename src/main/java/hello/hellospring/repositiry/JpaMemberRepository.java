package hello.hellospring.repositiry;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {


    //JPA는 EntityManager로 모든게 동작을 함
    //Spring boot가 이 메니져를 자동으로 만들어 줌. 이를 injection 받으면 됨.
    private final EntityManager em;

    //주입
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //영구 저장 - 인스턴스 쿼리 만들어 집어넣고, setid까지 다 해줌.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //type, 식별자 넣으면 조회해줌
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //jpql이라는 쿼리 언어. table이 아닌, 객체를 대상으로 쿼리를 날림
        //member entity(m) 자체를 select
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
