package com.yzpo.crawler.sohu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class NewsSohuPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(NewsSohuPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("title  :"+resultItems.get("title"));
        logger.info("href  :"+resultItems.get("href"));
        logger.info("src  :"+resultItems.get("src"));
    }
}
