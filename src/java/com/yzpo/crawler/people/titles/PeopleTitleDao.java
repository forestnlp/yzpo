package com.yzpo.crawler.people.titles;


import com.yzpo.crawler.baidutieba.titles.BaiduTieBaTitle;
import com.yzpo.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PeopleTitleDao {

    private static String spider_code="0003";
    private static String source_code="0006";
    private static String source_cd_code="01";

    public static void save(List<PeopleTitle> list) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ConnectionManager.getConnection();

            pstmt = conn.prepareStatement("insert into YZPO_PUBLICOPINION(po_id,po_url,title,publisher_time,content,spider_code,source_code,source_cd_code) values (s_po.nextval,?,?,?,?)");
            for(PeopleTitle e :list) {

                pstmt.setString(1,e.getUrl());
                pstmt.setString(2,e.getTitle());
                pstmt.setString(3,e.getPublisher_time());
                pstmt.setString(4,e.getContent());

                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

