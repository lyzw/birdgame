package mobi.birdgame.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mobi.birdgame.common.util.StringUtils;
import mobi.birdgame.mgmt.persistent.domain.WxUsers;
import mobi.birdgame.mgmt.persistent.mapper.WxUsersMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mobi.birdgame.web.ouput.PatitionResultOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouwei on 2016/8/31.
 */
@Controller
@RequestMapping("wxuser")
public class WxUserController {
    private static Logger logger = LogManager.getLogger(WxUserController.class);
    @Autowired
    WxUsersMapper usersMapper;

    @RequestMapping(value = "init")
    public ModelAndView init() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxusermgmt/wxusermgmt");
        return modelAndView;
    }

    /**
     * 获取微信游戏用户列表
     * @param currentPage   当前页码
     * @param pageSize  每页数据量
     * @param userId    用户id
     * @param openid    微信用户openid
     * @return  游戏用户列表
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(Integer currentPage, Integer pageSize, String userId, String openid) {

        PatitionResultOutput output = new PatitionResultOutput();
        PageHelper.startPage(currentPage, pageSize);
        WxUsers users = new WxUsers();
        if (StringUtils.isNumeric(userId, 1)) {
            users.setUserId(StringUtils.parseInt(userId, 0));
        }
        if (openid != null && !"".equals(openid.trim())) {
            users.setOpenid(openid);
        }
        List list = usersMapper.selectBySelective(users);
        output.getPage().setCurrentPage(currentPage);
        output.getPage().setPageSize(pageSize);
        output.getPage().setTotalCount(((Long) ((Page) list).getTotal()).intValue());
        output.setList(list);
        return JSON.toJSONString(output);
    }

    public String registerHx(Integer userId){
        return "";
    }
}
