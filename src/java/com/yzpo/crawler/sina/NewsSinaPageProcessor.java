package com.yzpo.crawler.sina;

import com.yzpo.crawler.baidutieba.titles.BaiduTiebaTitleProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class NewsSinaPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

    private Logger logger = LoggerFactory.getLogger(NewsSinaPageProcessor.class);

    @Override
    public void process(Page page) {
        Selectable content = page.getHtml().xpath("//*[@id=\"result\"]/div[@class='box-result clearfix']");

        List<String> titles = new ArrayList<>();
        List<String> links = new ArrayList<>();
        List<String> srcs = new ArrayList<>();

        for(Selectable node:content.nodes()) {
            titles.add(node.xpath("h2/a/text()").get());
            links.add(node.xpath("h2/a/@href").get());
            srcs.add(node.xpath("h2/span/text()").get());
        }

        page.putField("title",titles);
        page.putField("href",links);
        page.putField("src",srcs);

        Selectable nextPages = page.getHtml().xpath("//*[@id=\"_function_code_page\"]/a");

        for(Selectable nextPage:nextPages.nodes()) {
           if(nextPage.xpath("a/text()").get().contains("下一页")) {
               logger.warn("发现下一页"+nextPage.xpath("a/@href").get());
               page.addTargetRequest("https://search.sina.com.cn/"+nextPage.xpath("a/@href").get());
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new NewsSinaPageProcessor());
        spider.addUrl("http://search.sina.com.cn/?c=news&q=%D3%CA%B4%A2%D2%F8%D0%D0&range=title&time=2019&stime=&etime=&num=20&col=1_7");
        spider.addPipeline(new NewsSinaPipeline());
        spider.thread(Runtime.getRuntime().availableProcessors());
        spider.start();
    }
}
