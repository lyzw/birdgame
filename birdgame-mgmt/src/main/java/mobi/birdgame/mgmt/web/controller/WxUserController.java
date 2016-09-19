package mobi.birdgame.mgmt.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mobi.birdgame.common.util.StringUtils;
import mobi.birdgame.common.util.http.ExHttpException;
import mobi.birdgame.mgmt.persistent.domain.WxUsers;
import mobi.birdgame.mgmt.persistent.mapper.WxUsersMapper;
import mobi.birdgame.mgmt.web.ouput.BaseJSONOutput;
import mobi.birdgame.mgmt.web.ouput.PatitionResultOutput;
import mobi.birdgame.mgmt.web.util.AccoutServerRequestUtil;
import org.apache.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

    /**
     * 获取userId对应的微信用户详细信息
     * @param userId    微信用户id
     * @return  微信用户详情页面
     */
    @RequestMapping(value = "detailPage")
    public ModelAndView detailPage(Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxusermgmt/wxuserdetail");
        modelAndView.addObject("user",usersMapper.selectByPrimaryKey(userId));
        return modelAndView;
    }

    @RequestMapping(value = "refresh",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String refresh(Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxusermgmt/wxuserdetail");
        try{
            String res = AccoutServerRequestUtil.registerHuanxinUser(userId);
            BaseJSONOutput data = JSON.parseObject(res,BaseJSONOutput.class);
            if (data.getCode() != 200){
                return  BaseJSONOutput.fail(data.getCode(),data.getMessage()).toJSONString();
            }
            return  BaseJSONOutput.success(null).toJSONString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  BaseJSONOutput.fail().toJSONString();

        } catch (HttpException e) {
            e.printStackTrace();
            return  BaseJSONOutput.fail(null).toJSONString();

        } catch (IOException e) {
            e.printStackTrace();
            return  BaseJSONOutput.fail(null).toJSONString();

        } catch (ExHttpException e) {
            e.printStackTrace();
            return  BaseJSONOutput.fail(null).toJSONString();
        }
    }

    /**
     * 获取userId对应的微信用户房卡管理页面
     * @param userId    微信用户id
     * @return  微信用户详情页面
     */
    @RequestMapping(value = "manageCards")
    public ModelAndView manageCards(Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxusermgmt/manageCards");
        modelAndView.addObject("user",usersMapper.selectByPrimaryKey(userId));
        return modelAndView;
    }

    @RequestMapping(value = "updateCards",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateCards(Integer userId,Integer cards){
        if (userId == null || cards== null || cards<0){
            return BaseJSONOutput.fail(500,"参数异常").toJSONString();
        }
        try {
            WxUsers user = new WxUsers();
            user.setUserId(userId);
            user.setCards(cards);
            int size = usersMapper.updateByPrimaryKeySelective(user);
            return BaseJSONOutput.success(size).toJSONString();
        }catch (Exception e){
            return  BaseJSONOutput.fail(500,e.getMessage()).toJSONString();
        }

    }
}
