package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;


@SpringBootTest  // spring boot를 띄운 상태에서 테스트하기 위해 필요
@Transactional  // test 롤백
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(value = false)
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("kim2");

        // when
        Long savedId = memberService.join(member);
        em.flush();
        // then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void 중복_회원_가입() {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);

        // then
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}