package hello.hellospring.domain;

import java.util.concurrent.atomic.AtomicLong;

public class Member {

    private Long id;  //서버에서 회원을 구분하기 위한 변수.
    private String name;    //회원 이름

    //id의 Getter와 Setter//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
