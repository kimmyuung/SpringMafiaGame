package mafia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberPagingController {
    @GetMapping("/member/login")
    public String memberLogin() {return "member/login";}

    @GetMapping("/member/signup")
    public String memberSignup() {return "member/signup";}

    @GetMapping("/member/agreement")
    public String memberAgreement() {return "member/agreement";}


}
