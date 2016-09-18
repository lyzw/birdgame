package mobi.birdgame.mgmt.web.util;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by zhouwei on 2016/9/14.
 */
public class QuartzTest {

    public static void main(String[] args) throws SchedulerException {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:common/spring/applicationContext.xml");
        Scheduler scheduler = (Scheduler)context.getBean("scheduler");
        scheduler.start();
    }
}
