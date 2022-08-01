package mafia.controller;

import mafia.dto.MemberDto;
import mafia.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/idcheck")
    public boolean idcheck(@RequestParam("mid") String mid) {
        return memberService.idcheck(mid);
    }
    @GetMapping("/emailcheck")
    public boolean emailcheck(@RequestParam("MemberEmail") String email) {
        return memberService.emailchek(email);
    }

    @GetMapping("/signup")
    public boolean signup(MemberDto memberDto) {
        return memberService.signup(memberDto);
    }

    @GetMapping("/login/{loginFalse}")
    @ResponseBody
    public String loginFalse(@PathVariable("loginFalse") String loginFalse){
        return loginFalse;
    }

    @PostMapping("/findId")
    @ResponseBody
    public Map<String, Object> findId(@RequestParam("memberName") String memberName
    , @RequestParam("memberEmail") String memberEmail){
        return memberService.findId(memberName, memberEmail);
    }

    @PostMapping("/findIdSuccess")
    public void findIdSuccess(HttpServletRequest request, HttpServletResponse response) {
        String findIdName = (String) request.getSession().getAttribute("findIdName");
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(memberService.findIdSuccess(findIdName));
        }catch(Exception e){e.printStackTrace();}
    }

    @PostMapping("/findPassword")
    public boolean findPassword(@RequestParam("memberId") String memberId, @RequestParam("memberName") String memberName) {
        return memberService.findPassword(memberId, memberName);
    }

    @PutMapping("/passwordUpdate")
    public boolean passwordUpdate(@RequestParam("MemberPassword") String password,
                                  HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute("id");
        return memberService.passwordUpdate(id, password);
    }


}
