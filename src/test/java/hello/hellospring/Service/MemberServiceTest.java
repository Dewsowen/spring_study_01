package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    //테스트는 한글로 이름을 적어도 됨.
    //테스트 코드는 실제 빌드될때 사용되지않음.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //동작하기전에 넣기
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //테스트가 실행되고 끝날때마다 clearStore()로 인해 저장되었었던 데이터가 지워짐 (중요!)
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given 무언가를 주었을때
        Member member = new Member();
        member.setName("spring");

        //when 이것을 실행했을때 , ctrl + alt + V , 맞는 임시 변수 생성 ( Introduce variavle)
        long saveId = memberService.join(member);

        //then 결과가 이것이 나와야함
        Member findMember = memberService.findeOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); //Assertions alt + enter 시 import하여 간편하게 사용가능
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail("예외가 발생해야합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
 */
        //then
    }

    @Test
    void findMermbers() {

    }

    @Test
    void findeOne() {

    }
}