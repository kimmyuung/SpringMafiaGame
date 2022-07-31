package mafia.service;

import mafia.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

@Service
public class MemberService implements UserDetailsService, OAuth2UserService {


    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public boolean idcheck(String mid) {
        String id = memberMapper.idcheck(mid);
        if(id == null) {return true;}
        else {return false;}
    }

    public boolean emailchek(String memail) {
        String email = memberMapper.emailchek(memail);
        if(email == null) {return true;}
        else {return false;}
    }

}
