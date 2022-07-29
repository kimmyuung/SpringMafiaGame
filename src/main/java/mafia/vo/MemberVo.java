package mafia.vo;

import lombok.*;

@Getter
@NoArgsConstructor
@Data
@ToString
public class MemberVo {
    private int MemberNo;
    private String Memberid;
    private String MemberPassWord;
    private String MemberName;
    private String MemberPhone;
    private String MemberEmail;
    private Role role;

}
