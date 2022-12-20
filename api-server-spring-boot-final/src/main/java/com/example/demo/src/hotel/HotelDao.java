package com.example.demo.src.hotel;

import com.example.demo.src.hotel.model.GetHotelRes;
import com.example.demo.src.hotel.model.PatchHotelReq;
import com.example.demo.src.hotel.model.PostHotelReq;
import com.example.demo.src.motel.model.GetMotelRes;
import com.example.demo.src.motel.model.PatchMotelReq;
import com.example.demo.src.motel.model.PostMotelReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HotelDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetHotelRes> getHotels() {
        String getHotelsQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 3";
        return this.jdbcTemplate.query(getHotelsQuery,
                (rs, rownum) -> new GetHotelRes(
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

    public List<GetHotelRes> getHotelByRegion(String regionId) {
        String getHotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 3 AND REGION_ID = ?";
        String getHotelParam = regionId;
        return this.jdbcTemplate.query(getHotelByNameQuery,
                (rs, rowNum) -> new GetHotelRes(
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
                ,getHotelParam);
    }

    public GetHotelRes getHotelByName(int hotelId) {
        String getHotelByNameQuery = "SELECT * FROM ACCOMMODATION_DETAIL WHERE ACCOMMODATION_ID = 3 and ACCOMMODATION_DETAIL_ID = ?";
        int getHotelParam = hotelId;
        return this.jdbcTemplate.queryForObject(getHotelByNameQuery,
                (rs, rowNum) -> new GetHotelRes(
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
                ,getHotelParam);
    }

    public int modifyHotelName(PatchHotelReq patchHotelReq) {
        String modifyHotelNameQuery = "update ACCOMMODATION_DETAIL set ACCOMMODATION_NAME = ? " +
                "where ACCOMMODATION_ID = 3 and ACCOMMODATION_DETAIL_ID = ? and DEL_YN = ?";
        Object[] modifyHotelNameParams = new Object[]{patchHotelReq.getHotelName(), patchHotelReq.getHotelId(), patchHotelReq.getDelYn()};

        return this.jdbcTemplate.update(modifyHotelNameQuery, modifyHotelNameParams);
    }

    public int deleteHotel(PatchHotelReq patchHotelReq) {
        String modifyHotelNameQuery = "update ACCOMMODATION_DETAIL set DEL_YN = ? " +
                "where ACCOMMODATION_ID = 3 and ACCOMMODATION_DETAIL_ID = ?";
        Object[] modifyHotelNameParams = new Object[]{ patchHotelReq.getDelYn(), patchHotelReq.getHotelId()};

        return this.jdbcTemplate.update(modifyHotelNameQuery, modifyHotelNameParams);
    }
    public int createHotel(PostHotelReq postHotelReq) {
        String createHotelQuery = "insert into ACCOMMODATION_DETAIL (REGION_ID, ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_TELNO, ACCOMMODATION_LOCATION," +
                "ACCOMMODATION_NOTIFY, ACCOMMODATION_EVENT, ACCOMMODATION_COUPON_EVENT, ACCOMMODATION_SERVICE, ACCOMMODATION_POLICY," +
                "OFFICIAL_STAR, APPLYING_COUPON_ID, ACCOMMODATION_IMAGE, CEO_NAME, BUSINESS_NAME, BUSINESS_LOCATION, BUSINESS_EMAIL," +
                "BUSINESS_TELNO, BUSINESS_REGISTRATION_NO, DEL_YN, CREATE_DT) values (?, 3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, 'N', now())";
        Object[] createHotelParams = new Object[]{postHotelReq.getRegionId()
                , postHotelReq.getName()
                , postHotelReq.getTel()
                , postHotelReq.getLocation()
                , postHotelReq.getNotify()
                , postHotelReq.getEvent()
                , postHotelReq.getService()
                , postHotelReq.getPolicy()
                , postHotelReq.getPolicy()
                , postHotelReq.getStar()
                , postHotelReq.getApplyingCoupon()
                , postHotelReq.getImage()
                , postHotelReq.getCeoName()
                , postHotelReq.getBusinessName()
                , postHotelReq.getBusinessLocation()
                , postHotelReq.getBusinessEmail()
                , postHotelReq.getBusinessTelno()
                , postHotelReq.getBusinessRegistrationNo()
        };
        this.jdbcTemplate.update(createHotelQuery, createHotelParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

}
