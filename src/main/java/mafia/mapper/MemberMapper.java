package mafia.mapper;

import mafia.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {
    String idcheck(String memberid);
    String emailchek(String email);
    boolean signup(MemberVo memberVo);
    Map<String,Object> findId(String memberName, String email);
    String findPassword(String memberId,String memberName);
    String findEmail(String memberId);
    boolean passwordUpdate(String id,String passwordEncrypt);
}
