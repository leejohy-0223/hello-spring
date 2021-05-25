package hello.hellospring.repositiry;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    //sequence 는 key를 생성해주는 것임(0, 1, 2 ...)
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 없으면 null, optional로 감싸야 함
//        return store.get(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //stream 공부 필요...
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 자바에서 실무할 때 List로 많이 쓰므로, 이걸로 반환.
        // hashmap의 member를 values로 쭉 반환해서 ArrayList로 만듬
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
