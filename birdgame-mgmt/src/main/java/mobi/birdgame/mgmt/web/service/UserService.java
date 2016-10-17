package mobi.birdgame.mgmt.web.service;

import mobi.birdgame.mgmt.persistent.domain.Users;

/**
 * {file description}
 * Created by zhouwei on 2016/10/17.
 */
public interface UserService {

    Users getUserByUserId(Integer userId);

    Users getUserByUserName(String userName);
}
