package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//굳이 public으로 구현할 필요가 없다.
/*public*/ class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //각 테스트가 끝난 뒤에 수행해주는 annotation
    public void afterEach(){
        repository.clear();
    }

    //각 기능들을 테스트해준다//
    @Test
    public void save() {
        //given: Member객체를 생성한다.
        Member member = new Member();
        member.setName("testMember");

        //when: 생성한 member를 리포지토리에 저장한다.
        repository.save(member);
        System.out.println("save()");
        List<Member> all = repository.findAll();
        for(Member mem:all) {
            System.out.println("mem.getId() = " + mem.getId());
            System.out.println("mem.getName() = " + mem.getName());
        }


        //then: 리포지토리에서 찾은 result와 위에서 생성한 member가 동일한 객체여야 한다.
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        //given: Member 객체를 2개 생성 후 저장한다.
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        System.out.println("fbName()");
        List<Member> all = repository.findAll();
        for(Member mem:all) {
            System.out.println("mem.getId() = " + mem.getId());
            System.out.println("mem.getName() = " + mem.getName());
        }

        //when: 저장된 객체 중에서 이름을 통해 검색한다.
        Member result1 = repository.findByName("test1").get();

        //then: 리포지토리에서 찾은 result1와 위에서 생성한 member1가 동일한 객체여야 한다.
        assertThat(result1).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        //given: Member 객체를 2개 생성 후 저장한다.
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        //when: 리포지토리에서 저장된 회원을 List로 갖고온다.
        System.out.println("fAll");
        List<Member> result = repository.findAll();
        for(Member mem:result) {
            System.out.println("mem.getId() = " + mem.getId());
            System.out.println("mem.getName() = " + mem.getName());
        }

        //then: 리포지토리에서 찾은 result의 size는 2인지 확인한다.
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        //given: Member객체를 생성한다.
        Member member = new Member();
        member.setName("test");
        repository.save(member);

        //when: 리포지토리에서 ID를 통해 객체를 가져온다.
        Member result = repository.findById(member.getId()).get();

        System.out.println("fbId()");
        List<Member> all = repository.findAll();
        for(Member mem:all) {
            System.out.println("mem.getId() = " + mem.getId());
            System.out.println("mem.getName() = " + mem.getName());
        }

        //then: 리포지토리에서 찾은 result와 위에서 생성한 member가 동일한 객체여야 한다.
        assertThat(member.getId()).isEqualTo(result.getId());
    }
}
