package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository{

    //회원을 저장하기 위한 변수 store와 회원ID를 관리하기 위한 sequence//
    //강의에서는 동시성을 고려하지 않아 각각 HashMap, Long으로 구현했지만 우선은 ConcurrentHashMap과 AtomicLong 사용
    private static ConcurrentHashMap<AtomicLong, Member> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    @Override
    public Member save(Member member) {
        member.setId(sequence);             //회원ID를 정한다.
        store.put(member.getId(), member);  //회원을 Map에 추가
        return member;
    }

    @Override
    public Optional<Member> findById(AtomicLong id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //자바8에서 지원하는 람다식을 사용
        return store.values().stream()  //store을 탐색하면서
                .filter(member -> member.getName().equals(name))    //각 member의 이름이 parameter로 넘어온 name과 동일한지 필터링을 거쳐
                .findAny(); //member가 있다면 return해준다. 끝까지 돌았는데 없다면 null을 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
