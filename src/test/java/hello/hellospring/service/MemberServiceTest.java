package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    private MemoryMemberRepository repository;
    private MemberService memberService;

    //각 테스트를 수행하기 전에 다음을 먼저 수행한다.
    @BeforeEach
    public void beforeEach(){
        //repository를 선언하고 memberService가 repository를 사용하도록 한다.
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clear();
    }

    @Test
    public void join(){
        //given: 회원 객체를 생성한다.
        Member member = new Member();
        member.setName("test");

        //when: join을 통해 회원 가입을 수행한다.
        memberService.join(member);

        //then: 리포지토리에 제대로 저장되었는지 확인한다.
        Member result = repository.findByName(member.getName()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void 중복회원예외() {
        //given: "test" 회원이 가입되어있는 상황
        Member member1 = new Member();
        member1.setName("test");
        memberService.join(member1);

        //when: 동일한 이름을 가진 회원이 가입하려고 한다.
        Member member2 = new Member();
        member2.setName("test");

        //then: 가입이 진행되면 안된다.
        try{
            memberService.join(member2);
            //member2가 가입이 된다면 테스트가 실패한 것이다.
            fail("예외가 발생해야 한다.");
        }catch (IllegalStateException e){
            //member2가 가입되지 않은 상황
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        //혹은 다음과 같이 작성할 수도 있다.
        //() -> memberService.join(member2) : 이런 로직을 실행시킬 때
        //IllegalStateException.class : 이 예외가 생겨야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}