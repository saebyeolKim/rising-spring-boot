package com.example.demo.src.heart;

import com.example.demo.src.heart.model.DeleteHeartReq;
import com.example.demo.src.heart.model.GetHeartsRes;
import com.example.demo.src.heart.model.PostHeartReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HeartDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetHeartsRes> getHearts(){
        String getHeartsQuery = "SELECT CONCAT(DATE_FORMAT(HT.HEART_START_DT, '%m.%d'), '~', DATE_FORMAT(HT.HEART_END_DT, '%m.%d')) AS startDt" +
                "       , CONCAT(TIMESTAMPDIFF(DAY, HT.HEART_START_DT, HT.HEART_END_DT), '박') AS endDt" +
                "       , CONCAT('성인', HT.HEART_ADULTS) AS adults" +
                "       , CONCAT('아동', IFNULL(HT.HEART_CHILDREN, 0)) AS children" +
                "       , AD.ACCOMMODATION_NAME AS name" +
                "       , AD.ACCOMMODATION_LOCATION AS location" +
                "       , AD.OFFICIAL_STAR AS star" +
                "       , AD.ACCOMMODATION_IMAGE AS image" +
                "    FROM ACCOMMODATION_DETAIL AD" +
                "    INNER JOIN HEART HT" +
                "      ON HT.ACCOMMODATION_DETAIL_ID = AD.ACCOMMODATION_DETAIL_ID" +
                "        WHERE AD.REGION_ID IN (1, 2, 3, 4)" +
                "      ORDER BY HT.CREATE_DT DESC";
        return this.jdbcTemplate.query(getHeartsQuery,
                (rs, rowNum) -> new GetHeartsRes(
                        rs.getString("userId"),
                        rs.getString("startDt"),
                        rs.getString("endDt"),
                        rs.getString("adults"),
                        rs.getString("children"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("star"),
                        rs.getString("image")
                ));
    }
    public List<GetHeartsRes> getHeartsByAccommodation(int accommodationId){
        String getHeartsByAccommodationQuery = "SELECT CONCAT(DATE_FORMAT(HT.HEART_START_DT, '%m.%d'), '~', DATE_FORMAT(HT.HEART_END_DT, '%m.%d')) AS startDt" +
                "       , CONCAT(TIMESTAMPDIFF(DAY, HT.HEART_START_DT, HT.HEART_END_DT), '박') AS endDt" +
                "       , CONCAT('성인', HT.HEART_ADULTS) AS adults" +
                "       , CONCAT('아동', IFNULL(HT.HEART_CHILDREN, 0)) AS children" +
                "       , AD.ACCOMMODATION_NAME AS name" +
                "       , AD.ACCOMMODATION_LOCATION AS location" +
                "       , AD.OFFICIAL_STAR AS star" +
                "       , AD.ACCOMMODATION_IMAGE AS image" +
                "    FROM ACCOMMODATION_DETAIL AD" +
                "    INNER JOIN HEART HT" +
                "      ON HT.ACCOMMODATION_DETAIL_ID = AD.ACCOMMODATION_DETAIL_ID" +
                "        WHERE AD.REGION_ID IN (1, 2, 3, 4)" +
                "          and ACCOMMODATION_DETAIL_ID = ?" +
                "      ORDER BY HT.CREATE_DT DESC";
        int getHeartsByAccommodationParams = accommodationId;
        return this.jdbcTemplate.query(getHeartsByAccommodationQuery,
                (rs, rowNum) -> new GetHeartsRes(
                        rs.getString("userId"),
                        rs.getString("startDt"),
                        rs.getString("endDt"),
                        rs.getString("adults"),
                        rs.getString("children"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("star"),
                        rs.getString("image")
                ), getHeartsByAccommodationParams);
    }
    public int createHeart(PostHeartReq postHeartReq) {
        String createHeartQuery = "insert into HEART (USER_ID, ACCOMMODATION_DETAIL_ID, HEART_START_DT, HEART_END_DT, HEART_ADULTS," +
                "HEART_CHILDREN, DEL_YN, CREATE_DT)" +
                "values ((SELECT USER_ID FROM USER WHERE USER_IDX = ?), ?, IFNULL(?, NOW()), IFNULL(?, DATE_ADD(NOW(), INTERVAL 1 DAY)), IFNULL(?, 2), IFNULL(?, 0), 'N', now())";
        Object[] createHeartParams = new Object[]{postHeartReq.getUserIdx()
                , postHeartReq.getAccommodationId()
                , postHeartReq.getStartDt()
                , postHeartReq.getEndDt()
                , postHeartReq.getAdults()
                , postHeartReq.getChildren()
        };
        this.jdbcTemplate.update(createHeartQuery, createHeartParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
    public int checkAccommodation(int userId,int accommodationId){
        String checkAccommodationQuery = "select exists(select ACCOMMODATION_DETAIL_ID from HEART where USER_ID = (SELECT USER_ID FROM USER WHERE USER_IDX = ?) and ACCOMMODATION_DETAIL_ID = ?)";
        Object[] checkAccommodationParams = new Object[]{userId, accommodationId};
        return this.jdbcTemplate.queryForObject(checkAccommodationQuery,
                int.class,
                checkAccommodationParams);

    }
    public int deleteHeart(DeleteHeartReq deleteHeartReq) {
        String createHeartQuery = "delete from HEART " +
                "where USER_ID = (SELECT USER_ID FROM USER WHERE USER_IDX = ?) " +
                "AND ACCOMMODATION_DETAIL_ID = ?";
        Object[] createHeartParams = new Object[]{deleteHeartReq.getUserIdx()
                , deleteHeartReq.getAccommodationId()
        };
        this.jdbcTemplate.update(createHeartQuery, createHeartParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
}
