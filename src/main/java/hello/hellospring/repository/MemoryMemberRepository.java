package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository  implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store.id 가 널이여도 반환됨
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member 가 넘어온 member.name의 파라미터와 같은지, 
                .findAny(); /* 랃다식 쓰임 , 찾을시 바로 반환 */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); /* store의 값들(member)을 불러오기 */
    }

    public void clearStore(){
        store.clear(); // store의 내용을 지움
    }
}
