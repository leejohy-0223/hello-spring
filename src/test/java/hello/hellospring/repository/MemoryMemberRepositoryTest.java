package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repositiry.MemberRepository;
import hello.hellospring.repositiry.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 다른 애들이 가져다 쓸게 아니므로 굳이 public으로 할 필요 없다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test 1가지 끝날 때 마다 repository를 비워주는 메서드 추가
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    //test를 추가해서 실행 가능 함. main method 쓰는 거랑 비슷!
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //optional(findById의 반환형)에서 get으로 꺼낼 수 있음
        Member result = repository.findById(member.getId()).get();

        //글자로 보는 것보다는, 아래의 Assertions 사용
//        System.out.println("result = " + (result == member));

        //Assertions - by junit
//        Assertions.assertEquals(member, null);

        //Assertions static import 하면, 앞에 Assertion 안써도  - by assertj
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //Shift f6을 통해, 겹치는 이름 변경할 수 있음.
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
