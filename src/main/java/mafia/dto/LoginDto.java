package mafia.dto;

import lombok.*;
import mafia.vo.MemberVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class LoginDto implements UserDetails {  //UserDetails 인터페이스 ( 커스텀  세션 )

    private String memberId;    //유저 아이디
    private String password;     //유저 비밀번호
    private String memberName;  //유저 이름
    private String memberemail; // 유저 이메일

    private String memberphone; // 유저 폰

    private Set<GrantedAuthority> authorities;  //권한



    public LoginDto(MemberVo memberVo, Collection<? extends GrantedAuthority> authorities) {

        this.memberId=memberVo.getMemberid();
        this.password=memberVo.getMemberPassWord();
        this.memberName=memberVo.getMemberName();
        this.memberemail=memberVo.getMemberEmail();
        this.memberphone=memberVo.getMemberPhone();
        this.authorities= Collections.unmodifiableSet(new LinkedHashSet<>(authorities));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}