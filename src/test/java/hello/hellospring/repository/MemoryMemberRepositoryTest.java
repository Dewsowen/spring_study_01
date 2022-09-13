package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트가 실행되고 끝날때마다 clearStore()로 인해 저장되었었던 데이터가 지워짐 (중요!)
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result);
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result); //Assertions alt + enter 시 import하여 간편하게 사용가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+f6 동일 변수이름 변경
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //ctrl + alt + V , 맞는 임시 변수 생성 ( Introduce variavle)
        assertThat(result.size()).isEqualTo(2);
    }
}

// 테스트 순서는 보장이 되지않기에, 모든 메소드별로 따로 돌아가도록 설정해주어야함.
// 테스트가 끝나면, 다음 순서를 위해 저장되었던 데이터를 지워주어야함.
