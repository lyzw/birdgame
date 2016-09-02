package mobi.birdgame.web.ouput;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分页结果输出
 * Created by zhouwei on 2016/9/1.
 */
public class PatitionResultOutput {

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
}
