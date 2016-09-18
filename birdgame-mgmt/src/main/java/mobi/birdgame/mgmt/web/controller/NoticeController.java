package mobi.birdgame.mgmt.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import mobi.birdgame.mgmt.persistent.domain.Notice;
import mobi.birdgame.mgmt.persistent.mapper.NoticeMapper;
import mobi.birdgame.mgmt.web.ouput.BaseJSONOutput;
import mobi.birdgame.mgmt.web.ouput.PatitionResultOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhouwei on 2016/9/7.
 */
@Controller
@RequestMapping(value = "notice")
public class NoticeController {


    @Autowired
    NoticeMapper noticeMapper;


    /**
     * 初始化，直接进入管理页面
     * @return
     */
    @RequestMapping("init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/list");
        return modelAndView;
    }

    /**
     *
     * @param currentPage
     * @param pageSize
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(Integer currentPage, Integer pageSize, Integer id,Integer type,String startDateTime, String endDateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PatitionResultOutput output = new PatitionResultOutput();
        PageHelper.startPage(currentPage, pageSize);
        Notice notice = new Notice();

        if (id != null ){
            notice.setId(id);
        }
        if(type != null){
            notice.setType(type);
        }
        if(startDateTime != null && !"".equals(startDateTime)) {
            try {
                notice.setStartTime(format.parse(startDateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(endDateTime != null && !"".equals(endDateTime)) {
            try {
                notice.setEndTime(format.parse(endDateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List list = noticeMapper.selectBySelective(notice);

        output.getPage().setCurrentPage(currentPage);
        output.getPage().setPageSize(pageSize);
        output.getPage().setTotalCount(((Long) ((Page) list).getTotal()).intValue());
        output.setList(list);
        return output.toJSONString();
    }

    /**
     *  查看消息详情
     * @param id    消息id
     * @return  消息详情
     */
    @RequestMapping(value = "detailPage")
    public ModelAndView detailPage(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/noticedetail");
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        modelAndView.addObject("notice",notice);
        return modelAndView;
    }

    /**
     *  获取编辑页面
     * @param id 消息id
     * @return  消息详情
     */
    @RequestMapping(value = "modify")
    public ModelAndView modify(Integer id ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/modify");
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        modelAndView.addObject("notice",notice);
        return modelAndView;
    }

    /**
     *  获取编辑页面
     * @param id 消息id
     * @return  消息详情
     */
    @RequestMapping(value = "update",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String update(Integer id ,Integer type ,String title ,String content ,String startTime ,
                               String endTime ,Integer intervals ,Integer status ,String remark  ) throws ParseException {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Notice notice = new Notice();
            notice.setId(id);
            notice.setType(type);
            notice.setEndTime(sdf.parse(endTime));
            notice.setStartTime(sdf.parse(startTime));
            notice.setContent(content);
            notice.setIntervals(intervals);
            notice.setRemark(remark);
            notice.setStatus(status);
            notice.setTitle(title);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("notice/list");
            int count = noticeMapper.updateByPrimaryKey(notice);
            return BaseJSONOutput.success(null).toJSONString();
        }catch (Exception e){
            return BaseJSONOutput.fail(500,e.getMessage()).toJSONString();
        }
    }

}
