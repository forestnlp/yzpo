package com.yzpo.crawler.baidutieba.page;

import com.yzpo.crawler.baidutieba.titles.BaiduTiebaTitleProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class BaiduTieBaPageProcessor implements PageProcessor {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(BaiduTiebaTitleProcessor.class);

    @Override
    public void process(Page page) {

        Selectable st = page.getHtml().xpath("//[@id=\"j_p_postlist\"]/div[@class='l_post l_post_bright j_l_post clearfix  ']");
        //存为列表
        List<BaiduTieBaPage> list = new ArrayList<BaiduTieBaPage>();

        for(Selectable s:st.nodes()) {
            List<Selectable> ls_span = s.xpath("div[@class='post-tail-wrap']/span[@class='tail-info']").nodes();
            String author = s.xpath("li[@class='d_name']/a/text()").get();
            String content = s.xpath("div[@class=\"d_post_content j_d_post_content \"]/text()").get();
            String publisher_time ="",publisher_src="",floor="";
            if(ls_span.size()>=1)
                publisher_time = ls_span.get(ls_span.size()-1).xpath("span[@class='tail-info']/text()").get();
            if(ls_span.size()>=2)
                floor = ls_span.get(ls_span.size()-2).xpath("span[@class='tail-info']/text()").get();
            if(ls_span.size()>=3)
                publisher_src = ls_span.get(0).xpath("span[@class='tail-info']/allText()").get();

            BaiduTieBaPage e = new BaiduTieBaPage();
            e.setAuthor(author);
            e.setContent(content);
            e.setPublisher_time(publisher_time);
            e.setPublisher_src(publisher_src);
            e.setFloor(floor);

            list.add(e);

        }

        //传递数组，由page传递到pipeline
        page.putField("list", list);

        Selectable nextselect = page.getHtml().xpath("//*[@id=\"thread_theme_7\"]/div[1]/ul/li[1]/a");
        for(Selectable p:nextselect.nodes()) {
            if(p.xpath("a/text()").get().equals("下一页"))
            {
                page.addTargetRequests(p.xpath("a").links().all());
            }
        }

    }

    //定义站点信息
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    //get方法
    public Site getSite() {
        return site;
    }
}
