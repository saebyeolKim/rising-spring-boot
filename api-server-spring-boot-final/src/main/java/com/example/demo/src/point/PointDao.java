package com.example.demo.src.point;

import com.example.demo.src.heart.model.PostHeartReq;
import com.example.demo.src.point.model.GetPointRes;
import com.example.demo.src.point.model.PatchPointReq;
import com.example.demo.src.point.model.PostPointReq;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PointDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetPointRes> getPoints(int userIdx){
        String getPointsQuery = "SELECT POINT_NAME" +
                "         , POINT_COUNT" +
                "         , (SELECT POINT_COUNT FROM POINT WHERE POINT_START_DT <= NOW() AND POINT_END_DT >= NOW() AND USE_YN = 'N') AS possiblePoints" +
                "         , (SELECT POINT_COUNT FROM POINT WHERE TIMESTAMPDIFF(DAY, NOW(), POINT_END_DT) <= 15 AND TIMESTAMPDIFF(DAY, NOW(), POINT_END_DT) > 0 AND USE_YN = 'N') AS disappearPoints" +
                "     FROM POINT" +
                "     WHERE USER_ID = (select USER_ID from USER where USER_IDX = ?)\n" +
                "     ;";
        int getPointsParam = userIdx;
        return this.jdbcTemplate.query(getPointsQuery,
                (rs,rowNum) -> new GetPointRes(
                        rs.getString("POINT_NAME"),
                        rs.getInt("POINT_COUNT"),
                        rs.getInt("possiblePoints"),
                        rs.getInt("disappearPoints")),
                        getPointsParam
        );
    }
    public int createPoint(PostPointReq postPointReq) {
        String createPointQuery = "insert into POINT (USER_ID, POINT_COUNT, POINT_NAME, POINT_START_DT, POINT_END_DT, USE_YN, CREATE_DT)" +
                "values ((select USER_ID from USER where USER_IDX = ?), ?, ?, ?, ?, 'N', now());";
        Object[] createPointParams = new Object[]{postPointReq.getUserIdx()
                , postPointReq.getPointCount()
                , postPointReq.getPointName()
                , postPointReq.getStartDt()
                , postPointReq.getEndDt()
        };
        this.jdbcTemplate.update(createPointQuery, createPointParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
    public int modifyUseStatus(PatchPointReq patchPointReq){
        String modifyUseStatuseQuery = "update POINT set USE_YN = 'Y' where USER_ID = (select USER_ID from USER where USER_IDX = ?) " +
                "and POINT_ID = ?";
        Object[] modifyUseStatusParams = new Object[]{patchPointReq.getUserIdx(), patchPointReq.getPointId()};

        return this.jdbcTemplate.update(modifyUseStatuseQuery,modifyUseStatusParams);
    }
}
