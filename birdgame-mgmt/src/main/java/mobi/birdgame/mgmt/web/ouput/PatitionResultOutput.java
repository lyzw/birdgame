package mobi.birdgame.mgmt.web.ouput;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * 分页结果输出
 * Created by zhouwei on 2016/9/1.
 */
public class PatitionResultOutput {

    private static Logger logger = LogManager.getLogger(PatitionResultOutput.class);

    private PageInfo page;
    private List list;

    public PatitionResultOutput(){
        page = new PageInfo();
        list = new ArrayList();
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String toJSONString(){
        logger.debug("the page info is [{}]",JSON.toJSONString(this));
        return JSON.toJSONString(this);
    }
}
