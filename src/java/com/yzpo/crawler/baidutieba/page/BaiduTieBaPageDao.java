package com.yzpo.crawler.baidutieba.page;

import com.yzpo.jdbc.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaiduTieBaPageDao {
    private static Logger logger = LoggerFactory.getLogger(BaiduTieBaPageDao.class);

    public static Map<Integer, String> getBaiduTieBaPages() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement("select  * from yzpo_publicopinion where source_code='0001' and pagedownloaded=0 and counts_reply<=50 and rownum<2 order by po_id");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                BaiduTieBaPage page = new BaiduTieBaPage();
                map.put(rs.getInt("po_id"),rs.getString("po_url"));
            }
            rs.close();

            for(Integer po_id:map.keySet())
                pstmt.executeUpdate("update yzpo_publicopinion set pagedownloaded=1 where po_id="+po_id);
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
        return map;
    }

    public static void save(int po_id,List<BaiduTieBaPage> list) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement("insert into yzpo_po_reply(po_id,comments_id,publisher_id,content,floor,publisher_time,publisher_src) values (?,s_po_reply.nextval,?,?,?,?,?)");
            for(BaiduTieBaPage e :list) {
                pstmt.setInt(1,po_id);
                pstmt.setString(2,e.getAuthor());

                if(e.getContent().length()>500)
                    pstmt.setString(3,e.getContent().substring(0,500));
                else
                    pstmt.setString(3,e.getContent());

                pstmt.setString(4,e.getFloor());
                pstmt.setString(5,e.getPublisher_time());
                pstmt.setString(6,e.getPublisher_src());
                pstmt.executeUpdate();

            }

            for(BaiduTieBaPage e :list){
                logger.info("listmsg {}",e.toString());
            }

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
