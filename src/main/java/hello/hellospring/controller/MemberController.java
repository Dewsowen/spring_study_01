package hello.hellospring.controller;

import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //Autowired : spring 컨테이너에서 생성자를 호출할때 memberService를 찾아서 연결해줌, 여기서는 순수 자바 코드이기때문에 찾을 수 업다고 나옴. 그래서 MemberSrvice에 @Service 추가.
        this.memberService = memberService;
    }

}
