package com.yzpo.crawler.people.titles;

import com.yzpo.crawler.baidutieba.titles.BaiduTieBaTitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class PeopleTitleProcessor implements PageProcessor {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(PeopleTitleProcessor.class);
    //定义站点信息
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    //get方法
    public Site getSite() {
        return site;
    }
    //get与set方法

    //解析方法
    public void process(Page page) {

        //取出页面所有标题
        Selectable st = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']");

        List<BaiduTieBaTitle> list = new ArrayList<BaiduTieBaTitle>();

        for(Selectable s :st.nodes()) {
            String title = s.xpath("div[@class='threadlist_title pull_left j_th_tit ']/a/text()").get();
            String comment = s.xpath("div[@class='col2_left j_threadlist_li_left']/span/text()").get();
            String author = s.xpath("div[@class='threadlist_author pull_right']/span/span/a/text()").get();
            String last_date = s.xpath("span[@class='threadlist_reply_date pull_right j_reply_data']/text()").get();
            String url = s.xpath("div[@class='threadlist_title pull_left j_th_tit ']/a/@href").get();
            if(title==null||url==null) continue;
            if(title.equals("一个人在山东邮政公司的信访室")) {
                logger.warn("url is {}",getSite());
            }

            BaiduTieBaTitle e = new BaiduTieBaTitle();
            e.setAuthor(author);
            e.setComments_num(Integer.parseInt(comment));
            e.setLast_visit_date(last_date);
            e.setUrl(url);
            e.setTitle(title);
            list.add(e);
        }
        //传递数组，由page传递到pipeline
        page.putField("list", list);
        //取出分页框的下一页
        String nextUrl = page.getHtml().xpath("//div[@class='thread_list_bottom clearfix']/div[1]/a[@class='next pagination-item']/@href").toString().substring(18);

        //放入待调度器
        page.addTargetRequest(nextUrl);
    }

}
