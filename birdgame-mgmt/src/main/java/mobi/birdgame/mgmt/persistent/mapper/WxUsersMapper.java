package mobi.birdgame.mgmt.persistent.mapper;

import mobi.birdgame.mgmt.persistent.domain.WxUsers;

import java.util.List;

public interface WxUsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(WxUsers record);

    int insertSelective(WxUsers record);

    WxUsers selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(WxUsers record);

    int updateByPrimaryKey(WxUsers record);

    List selectAll();

    /**
     * 根据给定的用户信息查询用户
     * @param record 用户信息
     * @return  符合给定信息的用户列表
     */
    List selectBySelective(WxUsers record);
}