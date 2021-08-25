package toy.pro.shop.web.member.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test1(){
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println(member.getName());
        }
    }
}