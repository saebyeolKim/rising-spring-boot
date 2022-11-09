package com.example.demo.src.notice;

import com.example.demo.src.notice.model.GetNoticeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class NoticeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetNoticeRes> getNotices() {
        String getNoticesQuery = "SELECT NOTIFY_TITLE, DATE_FORMAT(IFNULL(UPDATE_DT, CREATE_DT), '%Y-%m-%d')" +
                "FROM NOTIFY ORDER BY IFNULL(UPDATE_DT, CREATE_DT)";
        return this.jdbcTemplate.query(getNoticesQuery,
                (rs, rowNum) -> new GetNoticeRes(
                        rs.getString("notifyTitle"),
                        rs.getString("createDt"),
                        rs.getString("updateDt")
                ));
    }

    public GetNoticeRes getNotice(int noticeId) {
        String getNoticeQuery = "SELECT NOTIFY_TITLE, DATE_FORMAT(IFNULL(UPDATE_DT, CREATE_DT), '%Y-%m-%d')" +
                "FROM NOTIFY ORDER BY IFNULL(UPDATE_DT, CREATE_DT) WHERE NOTIFY_ID = ? ";
        int getNotifyParam = noticeId;
        return this.jdbcTemplate.queryForObject(getNoticeQuery,
                (rs, rowNum) -> new GetNoticeRes(
                        rs.getString("notifyTitle"),
                        rs.getString("createDt"),
                        rs.getString("updateDt")
                ), getNotifyParam);
    }
}
