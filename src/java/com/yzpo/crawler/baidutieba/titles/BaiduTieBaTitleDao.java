package com.yzpo.crawler.baidutieba.titles;

import com.yzpo.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaiduTieBaTitleDao {

    private static String spider_code="0001";
    private static String source_code="0001";
    private static String source_cd_code="02";

    public static void save(List<BaiduTieBaTitle> list) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ConnectionManager.getConnection();

            pstmt = conn.prepareStatement("insert into YZPO_PUBLICOPINION(po_id,po_url,title,counts_reply,reply_recent_time,publisher_id,spider_code,source_code,source_cd_code) values (s_po.nextval,?,?,?,?,?,?,?,?)");
            for(BaiduTieBaTitle e :list) {

                pstmt.setString(1,e.getUrl());
                pstmt.setString(2,e.getTitle());
                pstmt.setInt(3,e.getComments_num());
                pstmt.setString(4,e.getLast_visit_date());
                pstmt.setString(5,e.getAuthor());
                pstmt.setString(6,spider_code);
                pstmt.setString(7,source_code);
                pstmt.setString(8,source_cd_code);

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
