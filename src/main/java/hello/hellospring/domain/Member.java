package hello.hellospring.domain;

import java.util.concurrent.atomic.AtomicLong;

public class Member {

    private AtomicLong id;  //서버에서 회원을 구분하기 위한 변수. 동시성을 고려해 AtomicLong으로 구현했다.
    private String name;    //회원 이름

    //id의 Getter와 Setter//
    public AtomicLong getId() {
        return id;
    }

    public void setId(AtomicLong id) {
        this.id = id;
    }

    //name의 Getter와 Setter//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
