package com.yzpo.crawler.baidutieba.titles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class BaiduTiebaTitleProcessor2 implements PageProcessor {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(BaiduTiebaTitleProcessor2.class);
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
        List<String> titles = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[2]/div[1]/div[1]/a/text()").all();
        //取出页面所有评论数量
        List<String> comments = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[1]/span/text()").all();
        //取出页面所有作者信息
        List<String> authors = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list clearfix']/div[1]/div[2]/div[1]/div[2]/span[1]/span[1]/a/text()").all();
        //取出页面所有最后回复日期
        List<String> last_dates = page.getHtml().xpath("//ul[@id=\"thread_list\"]/li[@class='j_thread_list clearfix']/div/div[2]/div[2]/div[2]/span[2]/text()").all();
        //取出所有链接
        List<String> urls = page.getHtml().xpath("//ul[@id=\"thread_list\"]/li[@class='j_thread_list clearfix']/div/div[2]/div[1]/div[1]/a/@href").all();

        logger.info("this page have {} titles", titles.size());
        //存为列表
        List<BaiduTieBaTitle> list = new ArrayList<BaiduTieBaTitle>(titles.size());

        for (int i = 0; i < titles.size(); i++) {
            try {
                //新建实例
                BaiduTieBaTitle entity = new BaiduTieBaTitle();
                entity.setTitle(titles.get(i));
                entity.setAuthor(authors.get(i));
                entity.setComments_num(Integer.parseInt(comments.get(i)));
                entity.setLast_visit_date(last_dates.get(i));
                entity.setUrl(BaiduTiebaTitleSpider.getInstance().getSite().getDomain()+urls.get(i));
                //实例放入数组
                list.add(entity);

            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        //传递数组，由page传递到pipeline
        page.putField("list", list);
        //取出分页框的下一页
        String nextUrl = page.getHtml().xpath("//div[@class='thread_list_bottom clearfix']/div[1]/a[@class='next pagination-item']/@href").toString().substring(18);
        //System.out.println(page.getHtml().xpath("//div[@class='thread_list_bottom clearfix']/div[1]/a[@class='next pagination-item']/@href").toString());

        if(titles.contains("一个人在山东邮政公司的信访室")) {
            logger.warn("url is {}",nextUrl);
        }

        //放入待调度器
        page.addTargetRequest(nextUrl);
    }

}
