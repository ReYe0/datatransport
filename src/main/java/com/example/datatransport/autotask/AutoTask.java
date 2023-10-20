package com.example.datatransport.autotask;

import com.example.datatransport.transfer.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xuy
 */
@Component
@Slf4j
public class AutoTask {
    @Autowired
    Transfer transfer;
    @Value("${server.url}")
    private String url;
    @Value("${tree.id}")
    private long tree_id;

    //每隔五分钟执行一次 @Scheduled(cron="0 0/5 * * * ?")
    // 延迟0秒，每4*60分钟执行一次
    /**
     * 延迟0秒，每4*60分钟执行一次
     * @return void
     * @author xuy
     * @date 2023/10/8 16:38
     */
    @Scheduled(initialDelay = 1000, fixedRate =60 * 60 * 1000)
    public void run() {
        try {
            log.info("start");
            transfer.doPostNavigateConfig(url, tree_id, (short) 1);
            transfer.doPostCmsVib(url, tree_id, (short) 2);
            transfer.doPostEnvelopeData(url, tree_id, (short) 6);
            transfer.doPosSpeedData(url, tree_id, (short) 9);
            log.info("end");
        } catch (Exception e) {
            log.error("异常了：" + e.getMessage());
        }
    }
}
