package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//spring container에서, @Controller 객체를 생성해서 관리하게 됨.
//이를 Spring bean이 관리된다고 함.
@Controller
public class MemberController {

    // 아래처럼 new를 이용하면, 다른 컨트롤러에서도 memberService를 사용할 수 있게 됨.
    // 하나만 생성해놓고 관리하는게 낫다.
//    private final MemberService memberService = new MemberService();

    //Spring container에 등록할 필요가 있다.
    private final MemberService memberService;

    //생성자에 Autowired 써있으면, memberService를 컨테이너에서 가져와서 연결해준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
