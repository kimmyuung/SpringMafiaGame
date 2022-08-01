package mafia.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import mafia.dto.MemberDto;
import mafia.mapper.MemberMapper;
import mafia.vo.MemberVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

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
        return false;
    }

    public boolean emailchek(String memail) {
        String email = memberMapper.emailchek(memail);
        if(email == null) {return true;}
        return false;
    }

    public boolean signup(MemberDto memberDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        MemberVo memberVo = new MemberVo(
          memberDto.getMemberNo(), memberDto.getMemberid(),
          memberDto.getMemberPassWord(),
          memberDto.getMemberName(), memberDto.getMemberPhone(),
          memberDto.getMemberEmail(), memberDto.getRole()
        );
        boolean result = memberMapper.signup(memberVo);
        if(result) return true;
        return false;
    }

    public Map<String,Object> findId(String memberName, String memberphone) {
        Map<String, Object> map = memberMapper.findId(memberName, memberphone);
        if(map !=null) {return map;}
        return null;
    }

    public JSONObject  findIdSuccess(String findIdName){
        JSONObject jo = new JSONObject();
        String findId = findIdName.split(",")[0];
        String findName = findIdName.split(",")[1];
        jo.put("findId", findId);
        jo.put("findName",findName);
        return jo;
    }
    public boolean findPassword(String mid, String memberName) {
        String find = memberMapper.findPassword(mid, memberName);
        if(find ==null) {return false;}
        return true;
    }

    public String sendemail(String mid){
        String email = memberMapper.findEmail(mid);
        if(email == null) return null;
        try{
            Random random = new Random();
            String randomNumber=Integer.toString(random.nextInt(100001+999999));
            StringBuilder html=new StringBuilder();
            StringBuilder authKey= new StringBuilder();
            html.append("<html><body><h3>TransportationDataSystem</h3>");
            authKey.append(randomNumber);
            html.append("<h3>주의**개인정보 노출위험이 있습니다. <h3><br><br><br>");
            html.append("<h3>회원님의 인증번호는 <h3><br>");
            html.append("<h1>"+authKey+"</h1><br>");
            html.append("<h3>입니다. <h3><br>");
            html.append("</body></html>");
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(message,true,"UTF-8");
            mimeMessageHelper.setFrom("kimmyungho1003@naver.com","Mafia");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("비밀번호 인증 메일");
            mimeMessageHelper.setText(html.toString() ,true);
            javaMailSender.send(message);

            return randomNumber;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    public boolean passwordUpdate(String id,String password){
        BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
        System.out.println(id + " service");
        //입력받은 값 암호화
        String passwordEncrypt =encoder.encode(password);
        return  memberMapper.passwordUpdate(id,passwordEncrypt);
    }
}
