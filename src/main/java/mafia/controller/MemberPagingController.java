package mafia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/member")
@Controller
public class MemberPagingController {
    @GetMapping("/member/login")
    public String memberLogin() {return "member/login";}

    @GetMapping("/member/signup")
    public String memberSignup() {return "member/signup";}

    @GetMapping("/member/agreement")
    public String memberAgreement() {return "member/agreement";}

    @GetMapping("/member/signupSuccess")
    public String memberSignupSuccess() {return "member/signupSuccess";}

    @GetMapping("/member/findid")
    public String findId() {return "member/findid";}

    @GetMapping("/member/findIdSuccess/{findId}/{findName}")
    public String findIdSuccess(@PathVariable("findId") String findId,
                                @PathVariable("findName") String findName,
                                HttpServletRequest request)
    {
        request.getSession().setAttribute("id", findId);
        return "member/find";
    }
    @GetMapping("/member/findPassword")
    public String findPassword() {return "member/findPassword";}

    @GetMapping("/member/findPasswordSuccess/{id}")
    public String findPasswordSuccess(@PathVariable("id") String id,
                                      HttpServletRequest request){
        request.getSession().setAttribute("id", id);
        return "member/findPasswordSuccess";
    }



}
