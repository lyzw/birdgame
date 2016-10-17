package mobi.birdgame.mgmt.web.service.impl;

import mobi.birdgame.mgmt.persistent.domain.Users;
import mobi.birdgame.mgmt.persistent.mapper.UsersMapper;
import mobi.birdgame.mgmt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {file description}
 * Created by zhouwei on 2016/10/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper mapper;

    public Users getUserByUserId(Integer userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    public Users getUserByUserName(String userName) {
        Users sample = new Users();
        sample.setName(userName);
        List<Users> list = mapper.selectBySelective(sample);
        if (list != null && list.size()>=1){
            return list.get(0);
        }
        return null;
    }
}
