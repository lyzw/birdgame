package mobi.birdgame.mgmt.quartz;

import com.alibaba.fastjson.JSON;
import mobi.birdgame.mgmt.constants.MgmtConstants;
import mobi.birdgame.mgmt.persistent.domain.Notice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

/**
 * Created by zhouwei on 2016/9/13.
 */
public class QuartzUtil {

    private static Logger logger = LogManager.getLogger(QuartzUtil.class);

    /**
     * 新增任务调度
     *
     * @param scheduler 调度器
     * @param jobName   任务名称
     * @param notice    通知信息
     * @throws SchedulerException 调度异常
     */
    public static void broadNewTask(Scheduler scheduler, String jobName, Notice notice) throws SchedulerException {
        logger.debug("发布新的定时任务");
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, MgmtConstants.QUARTZ_NOTICE_GROUP);

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            SimpleTrigger trigger = (SimpleTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                logger.debug("开始发布新的定时任务");

                JobDetail jobDetail = JobBuilder.newJob(NoticeTask.class)
                        .withIdentity(jobName, MgmtConstants.QUARTZ_NOTICE_GROUP).build();
                jobDetail.getJobDataMap().put("data", notice);
                SimpleScheduleBuilder scheduleBuilder = getScheduleBuilderByNotice(notice);

                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, MgmtConstants.QUARTZ_NOTICE_GROUP).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                refreshExistsTask(scheduler, jobName, notice);
            }
            logger.debug("新的定时任务发布成功");

        } catch (SchedulerException e) {
            logger.error(e);
            throw e;
        }
    }


    /**
     * 刷新任务的信息
     *
     * @param scheduler 调度器
     * @param jobName   任务名称
     * @param notice    通知信息
     * @throws SchedulerException 调度异常
     */
    public static void refreshExistsTask(Scheduler scheduler, String jobName, Notice notice) throws SchedulerException {
        try {
            logger.debug("获取并刷新任务信息");

            //获取并刷新任务信息
            JobKey jobKey = JobKey.jobKey(jobName, MgmtConstants.QUARTZ_NOTICE_GROUP);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            //重新开始调度
            broadNewTask(scheduler, jobName, notice);
            logger.debug("获取并刷新任务信息成功");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static SimpleScheduleBuilder getScheduleBuilderByNotice(Notice notice) {
        logger.debug("info ：{}", JSON.toJSONString(notice));
        long endTime = notice.getEndTime().getTime();
        long startTime = notice.getStartTime().getTime();
        long interval = notice.getIntervals();
        int count = ((Long) ((notice.getEndTime().getTime() - notice.getStartTime().getTime()) / notice.getIntervals())).intValue();
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(notice.getIntervals()).withRepeatCount(count);
        return scheduleBuilder;
    }
}
