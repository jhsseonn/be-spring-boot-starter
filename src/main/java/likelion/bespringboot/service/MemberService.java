package likelion.bespringboot.service;

import likelion.bespringboot.domain.Member;
import likelion.bespringboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
//        Optional<Member> result =  memberRepository.findByName(member.getName()); //cmd+opt+v : return 그냥 해줌
//        //orElseGet() : 값이 있으면 꺼내고 없으면 특정 메소드 실행
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        validateDuplicateMember(member);  //cmd+opt+M : extract Method

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }

    public List<Member> findMembers() {  //Service는 비즈니스에 의존적으로 설계함 Repository는 단순히 데이터를 넣었다 뺐다하는 역할을 하기 때문에 기계적으로 설계
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
