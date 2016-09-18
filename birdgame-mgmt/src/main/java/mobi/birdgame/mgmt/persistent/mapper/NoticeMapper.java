package mobi.birdgame.mgmt.persistent.mapper;

import mobi.birdgame.mgmt.persistent.domain.Notice;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List selectBySelective(Notice record);
}