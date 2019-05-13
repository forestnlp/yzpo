package com.yzpo.crawler.baidutieba.page;

import us.codecraft.webmagic.Spider;

public class BaiduTiebaPageSpider {


    public static Spider getInstance(int po_id,String url) {
        return Spider.create(new BaiduTieBaPageProcessor())
                .addPipeline(new BaiduTiebaPagePipeline(po_id))
                .addUrl("https://tieba.baidu.com"+url)
                .thread(Runtime.getRuntime().availableProcessors());
    }

}