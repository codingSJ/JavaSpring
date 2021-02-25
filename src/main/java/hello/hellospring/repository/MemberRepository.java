package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface MemberRepository {
    //레포지토리 기능//
    Member save(Member member);                 //회원 저장

    Optional<Member> findById(AtomicLong id);   //회원ID로 회원 검색

    Optional<Member> findByName(String name);   //회원 이름으로 회원 검색

    List<Member> findAll();                     //모든 회원 검색
}
