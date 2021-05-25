package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositiry.MemberRepository;
import hello.hellospring.repositiry.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    //여기에서 사용되는 memberRepository를 memberService의 Repository와 동일하게.
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given, 주어졌을 때
        Member member = new Member();
        member.setName("spring111");

        //when, 언제?
        Long saveId = memberService.join(member);

        //then, 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //test는 예외 flow가 중요함
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 있는 회원입니다.");

    }
}