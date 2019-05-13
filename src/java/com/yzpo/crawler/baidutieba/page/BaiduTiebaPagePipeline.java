package com.yzpo.crawler.baidutieba.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class BaiduTiebaPagePipeline implements Pipeline {

    private int po_id;

    public BaiduTiebaPagePipeline(int po_id) {
        this.po_id = po_id;
    }

    private Logger logger = LoggerFactory.getLogger(BaiduTiebaPagePipeline.class);
    //存储信息到数据库
    public void process(ResultItems resultItems, Task task) {
        //取出列表信息
        List<BaiduTieBaPage> list = resultItems.get("list");
        //利用DAO对实体进行处理
        BaiduTieBaPageDao.save(po_id,list);
        //存入MongoDB
        //BaiduTieBaPageMongoDao.save(po_id,list);
    }
}
