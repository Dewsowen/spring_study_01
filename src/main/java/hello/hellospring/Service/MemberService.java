package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //@Service 및 @Controller , @Reposiroty안에 @Component가 들어가 있음, 이 방법을 컴포넌트 스캔이라고 부름.
public class MemberService {

    private final MemberRepository memberRepository;

    //Consturctor 단축키 = alt + insert
    //DI : Dependency Injection 의존성 주입, 회원서비스 테스트 17:21초
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public long join(Member member){
        //같은 이름이 있는 중복 회원 X
        /* 아래 더 깔끔한 버젼
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMermbers(){
        return  memberRepository.findAll();
    }

    public Optional<Member> findeOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
