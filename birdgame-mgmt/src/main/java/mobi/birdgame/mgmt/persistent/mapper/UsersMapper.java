package mobi.birdgame.mgmt.persistent.mapper;

import mobi.birdgame.mgmt.persistent.domain.Users;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List selectBySelective(Users record);
}