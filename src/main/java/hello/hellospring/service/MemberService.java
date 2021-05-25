package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositiry.MemberRepository;
import hello.hellospring.repositiry.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//비즈니스 서비스 로직!
public class MemberService {

    private final MemberRepository memberRepository;

    //외부에서 넣어주도록 바꾼다. : dependancy Injection(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public long join(Member member) {


        validateDuplicateMember(member);//중복회원 검출
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 있는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {

        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
