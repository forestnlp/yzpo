package com.yzpo.crawler.baidutieba.titles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


import java.util.List;

public class BaiduTiebaTitlePipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(BaiduTiebaTitlePipeline.class);
    //存储信息到数据库
    public void process(ResultItems resultItems, Task task) {
        //取出列表信息
        List<BaiduTieBaTitle> list = resultItems.get("list");
        //利用DAO对实体进行处理
        BaiduTieBaTitleDao.save(list);

    }
}
