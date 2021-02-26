package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    //회원을 저장하기 위한 변수 store와 회원ID를 관리하기 위한 변수 sequence//
    //강의에서는 동시성을 고려하지 않아 각각 HashMap, Long으로 구현했지만 우선은 ConcurrentHashMap과 AtomicLong 사용
    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(1); //sequence의 시작 값은 1로 설정

    @Override
    public Member save(Member member) {
        member.setId(sequence.getAndIncrement());   //회원ID를 정한다. sequence의 현재 값을 반환 후 sequence는 1 증가.
        store.put(member.getId(), member);  //회원을 Map에 추가
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //자바8에서 지원하는 람다식을 사용

                //store을 탐색하면서
        return store.values().stream()
                //각 member의 이름이 parameter로 넘어온 name과 동일한지 필터링을 거쳐
                .filter(member -> member.getName().equals(name))
                //member가 존재한다면 return. 끝까지 존재하지 않는다면 null을 return.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }
}
