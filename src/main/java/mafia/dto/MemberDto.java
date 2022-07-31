package mafia.dto;

import lombok.*;
import mafia.vo.Role;


@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString
public class MemberDto {
    private int MemberNo;
    private String Memberid;
    private String MemberPassWord;
    private String MemberName;
    private String MemberPhone;
    private String MemberEmail;
    private Role role;
}
