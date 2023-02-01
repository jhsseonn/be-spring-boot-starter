package likelion.bespringboot;

import likelion.bespringboot.repository.MemberRepository;
import likelion.bespringboot.repository.MemoryMemberRepository;
import likelion.bespringboot.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
