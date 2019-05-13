package com.yzpo.crawler.sohu;

import com.yzpo.crawler.sina.NewsSinaPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class NewsSohuPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

    private Logger logger = LoggerFactory.getLogger(NewsSohuPageProcessor.class);

    @Override
    public void process(Page page) {
       List<String> ls_news =  page.getHtml().xpath("//*[@id=\"main\"]/div").links().all();
       logger.info(ls_news.toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new NewsSohuPageProcessor());
        spider.addUrl("https://news.sogou.com/news?query=%D3%CA%D5%FE&interV=kKIOkrELjbkMmLkElbkTkKIMkrELjboImLkEk74TkKILmrELjb8TkKIKmrELjbkI_-1772191188&page=1&p=40230447&dp=1");
        spider.addPipeline(new NewsSinaPipeline());
        spider.thread(Runtime.getRuntime().availableProcessors());
        spider.start();
    }
}
