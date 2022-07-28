package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
<<<<<<< HEAD
     * 전체 회원 조회
=======
     * 회원 전체 조회
>>>>>>> d407a93943c70b90ceebf985e24b6bd63f4c8a91
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
<<<<<<< HEAD
     * 한 회원만 조회
=======
     * 회원 한명 조회
>>>>>>> d407a93943c70b90ceebf985e24b6bd63f4c8a91
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }







}
