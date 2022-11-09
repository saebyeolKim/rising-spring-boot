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
        String getMotelsQuery = "SELECT * FROM ACCOMMODATION_DETAIL";
        return this.jdbcTemplate.query(getMotelsQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("regionId"),
                        rs.getInt("accommodationId"),
                        rs.getInt("motelId"),
                        rs.getString("motelName"),
                        rs.getString("motelTelno"),
                        rs.getString("motelLocation"),
                        rs.getString("motelNotify"),
                        rs.getString("motelEvent"),
                        rs.getString("motelCouponEvent"),
                        rs.getString("motelService"),
                        rs.getString("motelPolicy"),
                        rs.getString("motelStar"),
                        rs.getString("motelApplyingCoupon"),
                        rs.getString("motelImage"),
                        rs.getString("motelCeoName"),
                        rs.getString("motelBusinessName"),
                        rs.getString("motelBusinessEmail"),
                        rs.getString("motelBusinessTelno"),
                        rs.getString("motelBusinessRegistrationNo"),
                        rs.getString("delYn"),
                        rs.getString("createDt"),
                        rs.getString("updateDt"))
                );
    }

    public GetMotelRes getMotelByName(String motelName) {
        String getMotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_NAME = ?";
        String getMotelParam = motelName;
        return this.jdbcTemplate.queryForObject(getMotelByNameQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("regionId"),
                        rs.getInt("accommodationId"),
                        rs.getInt("motelId"),
                        rs.getString("motelName"),
                        rs.getString("motelTelno"),
                        rs.getString("motelLocation"),
                        rs.getString("motelNotify"),
                        rs.getString("motelEvent"),
                        rs.getString("motelCouponEvent"),
                        rs.getString("motelService"),
                        rs.getString("motelPolicy"),
                        rs.getString("motelStar"),
                        rs.getString("motelApplyingCoupon"),
                        rs.getString("motelImage"),
                        rs.getString("motelCeoName"),
                        rs.getString("motelBusinessName"),
                        rs.getString("motelBusinessEmail"),
                        rs.getString("motelBusinessTelno"),
                        rs.getString("motelBusinessRegistrationNo"),
                        rs.getString("delYn"),
                        rs.getString("createDt"),
                        rs.getString("updateDt"))
                ,getMotelParam);
    }

    public List<GetMotelRes> getMotelByRegion(int regionId) {
        String getMotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE REGION_ID = ?";
        int getMotelParam = regionId;
        return this.jdbcTemplate.query(getMotelByNameQuery,
                (rs, rowNum) -> new GetMotelRes(
                        rs.getInt("regionId"),
                        rs.getInt("accommodationId"),
                        rs.getInt("motelId"),
                        rs.getString("motelName"),
                        rs.getString("motelTelno"),
                        rs.getString("motelLocation"),
                        rs.getString("motelNotify"),
                        rs.getString("motelEvent"),
                        rs.getString("motelCouponEvent"),
                        rs.getString("motelService"),
                        rs.getString("motelPolicy"),
                        rs.getString("motelStar"),
                        rs.getString("motelApplyingCoupon"),
                        rs.getString("motelImage"),
                        rs.getString("motelCeoName"),
                        rs.getString("motelBusinessName"),
                        rs.getString("motelBusinessEmail"),
                        rs.getString("motelBusinessTelno"),
                        rs.getString("motelBusinessRegistrationNo"),
                        rs.getString("delYn"),
                        rs.getString("createDt"),
                        rs.getString("updateDt"))
                ,getMotelParam);
    }

    public int modifyMotelName(PatchMotelReq patchMotelReq) {
        String modifyMotelNameQuery = "update ACCOMMODATION_DETAIL set ACCOMMODATION_NAME = ? " +
                "where ACCOMMODATION_DETAIL_ID = ? and DEL_YN = ?";
        Object[] modifyUserNameParams = new Object[]{patchMotelReq.getMotelName(), patchMotelReq.getMotelId(), patchMotelReq.getDelYn()};

        return this.jdbcTemplate.update(modifyMotelNameQuery, modifyUserNameParams);
    }

    public int deleteMotel(PatchMotelReq patchMotelReq) {
        String modifyMotelNameQuery = "update ACCOMMODATION_DETAIL set DEL_YN = ? " +
                "where ACCOMMODATION_DETAIL_ID = ? and ACCOMMODATION_NAME = ? ";
        Object[] modifyUserNameParams = new Object[]{ patchMotelReq.getDelYn(), patchMotelReq.getMotelId(), patchMotelReq.getMotelName()};

        return this.jdbcTemplate.update(modifyMotelNameQuery, modifyUserNameParams);
    }

    public int createMotel(PostMotelReq postMotelReq) {
        String createMotelQuery = "insert into ACCOMMODATION_DETAIL (REGION_ID, ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_TELNO, ACCOMMODATION_LOCATION," +
                "ACCOMMODATION_NOTIFY, ACCOMMODATION_EVENT, ACCOMMODATION_COUPON_EVENT, ACCOMMODATION_SERVICE, ACCOMMODATION_POLICY," +
                "OFFICIAL_STAR, APPLYING_COUPON_ID, ACCOMMODATION_IMAGE, CEO_NAME, BUSINESS_NAME, BUSINESS_LOCATION, BUSINESS_EMAIL," +
                "BUSINESS_TELNO, BUSINESS_REGISTRATION_NO, DEL_YN, CREATE_DT) valuse (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, now())";
        Object[] createUserParams = new Object[]{postMotelReq.getRegionId()
                , postMotelReq.getAccommodationId()
                , postMotelReq.getMotelName()
                , postMotelReq.getMotelTelno()
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
                , postMotelReq.getDelYn()
                ,};
        this.jdbcTemplate.update(createMotelQuery, createUserParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }
}
