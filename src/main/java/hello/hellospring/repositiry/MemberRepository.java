package hello.hellospring.repositiry;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 저장소에 저장
    Member save(Member member);
    // null이 반환 될 경우, 요즘은 optional로 감싸서 반환하는 거를 선호함(java 8 기능)
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    //저장된 모든 리스트 반환
    List<Member> findAll();
}
