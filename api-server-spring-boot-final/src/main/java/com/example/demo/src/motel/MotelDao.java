package com.example.demo.src.motel;

import com.example.demo.src.motel.model.GetMotelRes;
import com.example.demo.src.motel.model.PatchMotelReq;
import com.example.demo.src.motel.model.PostMotelReq;
import com.example.demo.src.motel.model.PostMotelRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MotelDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMotelRes> getMotels() {
        String getMotelsQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 2";
        return this.jdbcTemplate.query(getMotelsQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("REGION_ID"),
                        rs.getInt("ACCOMMODATION_ID"),
                        rs.getInt("ACCOMMODATION_DETAIL_ID"),
                        rs.getString("ACCOMMODATION_NAME"),
                        rs.getString("ACCOMMODATION_TELNO"),
                        rs.getString("ACCOMMODATION_LOCATION"),
                        rs.getString("ACCOMMODATION_NOTIFY"),
                        rs.getString("ACCOMMODATION_EVENT"),
                        rs.getString("ACCOMMODATION_COUPON_EVENT"),
                        rs.getString("ACCOMMODATION_SERVICE"),
                        rs.getString("ACCOMMODATION_POLICY"),
                        rs.getString("OFFICIAL_STAR"),
                        rs.getString("APPLYING_COUPON_ID"),
                        rs.getString("ACCOMMODATION_IMAGE"),
                        rs.getString("CEO_NAME"),
                        rs.getString("BUSINESS_NAME"),
                        rs.getString("BUSINESS_LOCATION"),
                        rs.getString("BUSINESS_EMAIL"),
                        rs.getString("BUSINESS_TELNO"),
                        rs.getString("BUSINESS_REGISTRATION_NO"),
                        rs.getString("DEL_YN"),
                        rs.getString("CREATE_DT"),
                        rs.getString("UPDATE_DT"))
                );
    }

    public GetMotelRes getMotelByName(int motelId) {
        String getMotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 2 and ACCOMMODATION_DETAIL_ID = ?";
        int getMotelParam = motelId;
        return this.jdbcTemplate.queryForObject(getMotelByNameQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("REGION_ID"),
                        rs.getInt("ACCOMMODATION_ID"),
                        rs.getInt("ACCOMMODATION_DETAIL_ID"),
                        rs.getString("ACCOMMODATION_NAME"),
                        rs.getString("ACCOMMODATION_TELNO"),
                        rs.getString("ACCOMMODATION_LOCATION"),
                        rs.getString("ACCOMMODATION_NOTIFY"),
                        rs.getString("ACCOMMODATION_EVENT"),
                        rs.getString("ACCOMMODATION_COUPON_EVENT"),
                        rs.getString("ACCOMMODATION_SERVICE"),
                        rs.getString("ACCOMMODATION_POLICY"),
                        rs.getString("OFFICIAL_STAR"),
                        rs.getString("APPLYING_COUPON_ID"),
                        rs.getString("ACCOMMODATION_IMAGE"),
                        rs.getString("CEO_NAME"),
                        rs.getString("BUSINESS_NAME"),
                        rs.getString("BUSINESS_LOCATION"),
                        rs.getString("BUSINESS_EMAIL"),
                        rs.getString("BUSINESS_TELNO"),
                        rs.getString("BUSINESS_REGISTRATION_NO"),
                        rs.getString("DEL_YN"),
                        rs.getString("CREATE_DT"),
                        rs.getString("UPDATE_DT"))
                ,getMotelParam);
    }

    public List<GetMotelRes> getMotelByRegion(String regionId) {
        String getMotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 2 AND REGION_ID = ?";
        String getMotelParam = regionId;
        return this.jdbcTemplate.query(getMotelByNameQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("REGION_ID"),
                        rs.getInt("ACCOMMODATION_ID"),
                        rs.getInt("ACCOMMODATION_DETAIL_ID"),
                        rs.getString("ACCOMMODATION_NAME"),
                        rs.getString("ACCOMMODATION_TELNO"),
                        rs.getString("ACCOMMODATION_LOCATION"),
                        rs.getString("ACCOMMODATION_NOTIFY"),
                        rs.getString("ACCOMMODATION_EVENT"),
                        rs.getString("ACCOMMODATION_COUPON_EVENT"),
                        rs.getString("ACCOMMODATION_SERVICE"),
                        rs.getString("ACCOMMODATION_POLICY"),
                        rs.getString("OFFICIAL_STAR"),
                        rs.getString("APPLYING_COUPON_ID"),
                        rs.getString("ACCOMMODATION_IMAGE"),
                        rs.getString("CEO_NAME"),
                        rs.getString("BUSINESS_NAME"),
                        rs.getString("BUSINESS_LOCATION"),
                        rs.getString("BUSINESS_EMAIL"),
                        rs.getString("BUSINESS_TELNO"),
                        rs.getString("BUSINESS_REGISTRATION_NO"),
                        rs.getString("DEL_YN"),
                        rs.getString("CREATE_DT"),
                        rs.getString("UPDATE_DT"))
                ,getMotelParam);
    }

    public int modifyMotelName(PatchMotelReq patchMotelReq) {
        String modifyMotelNameQuery = "update ACCOMMODATION_DETAIL set ACCOMMODATION_NAME = ? " +
                "where ACCOMMODATION_ID = 2 and ACCOMMODATION_DETAIL_ID = ? and DEL_YN = ?";
        Object[] modifyUserNameParams = new Object[]{patchMotelReq.getMotelName(), patchMotelReq.getMotelId(), patchMotelReq.getDelYn()};

        return this.jdbcTemplate.update(modifyMotelNameQuery, modifyUserNameParams);
    }

    public int deleteMotel(PatchMotelReq patchMotelReq) {
        String modifyMotelNameQuery = "update ACCOMMODATION_DETAIL set DEL_YN = ? " +
                "where ACCOMMODATION_ID = 2 and ACCOMMODATION_DETAIL_ID = ?";
        Object[] modifyUserNameParams = new Object[]{ patchMotelReq.getDelYn(), patchMotelReq.getMotelId()};

        return this.jdbcTemplate.update(modifyMotelNameQuery, modifyUserNameParams);
    }

    public int createMotel(PostMotelReq postMotelReq) {
        String createMotelQuery = "insert into ACCOMMODATION_DETAIL (REGION_ID, ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_TELNO, ACCOMMODATION_LOCATION," +
                "ACCOMMODATION_NOTIFY, ACCOMMODATION_EVENT, ACCOMMODATION_COUPON_EVENT, ACCOMMODATION_SERVICE, ACCOMMODATION_POLICY," +
                "OFFICIAL_STAR, APPLYING_COUPON_ID, ACCOMMODATION_IMAGE, CEO_NAME, BUSINESS_NAME, BUSINESS_LOCATION, BUSINESS_EMAIL," +
                "BUSINESS_TELNO, BUSINESS_REGISTRATION_NO, DEL_YN, CREATE_DT) values (?, 2, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, 'N', now())";
        Object[] createUserParams = new Object[]{postMotelReq.getRegionId()
                , postMotelReq.getMotelName()
                , postMotelReq.getMotelTel()
                , postMotelReq.getMotelLocation()
                , postMotelReq.getMotelNotify()
                , postMotelReq.getMotelEvent()
                , postMotelReq.getMotelService()
                , postMotelReq.getMotelPolicy()
                , postMotelReq.getMotelPolicy()
                , postMotelReq.getMotelStar()
                , postMotelReq.getMotelApplyingCoupon()
                , postMotelReq.getMotelImage()
                , postMotelReq.getMotelCeoName()
                , postMotelReq.getMotelBusinessName()
                , postMotelReq.getMotelBusinessLocation()
                , postMotelReq.getMotelBusinessEmail()
                , postMotelReq.getMotelBusinessTelno()
                , postMotelReq.getMotelBusinessRegistrationNo()
                };
        this.jdbcTemplate.update(createMotelQuery, createUserParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
}
