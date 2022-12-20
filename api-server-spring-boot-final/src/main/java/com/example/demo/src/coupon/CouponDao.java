package com.example.demo.src.coupon;

import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.src.coupon.model.PatchCouponReq;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.src.coupon.model.PostCouponRes;
import com.example.demo.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CouponDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCouponRes> getCoupons() {
        String getCouponsQuery = "SELECT COUPON_NAME, COUPON_START_DT, COUPON_END_DT, DISCOUNT_PRICE, PRODUCT_URL, " +
                "LIMIT_PRICE, USE_START_DT, USE_END_DT, USE_PRICE, USE_COMMENT FROM COUPON";
        return this.jdbcTemplate.query(getCouponsQuery,
                (rs, rowNum) -> new GetCouponRes(
                        rs.getString("COUPON_NAME"),
                        rs.getString("COUPON_START_DT"),
                        rs.getString("COUPON_END_DT"),
                        rs.getInt("DISCOUNT_PRICE"),
                        rs.getString("PRODUCT_URL"),
                        rs.getInt("LIMIT_PRICE"),
                        rs.getString("USE_START_DT"),
                        rs.getString("USE_END_DT"),
                        rs.getInt("USE_PRICE"),
                        rs.getString("USE_COMMENT")
                ));
    }

    public int createCoupon(PostCouponReq postCouponReq)  {
        String createCouponQuery = "INSERT INTO COUPON (COUPON_TYPE, COUPON_NAME, COUPON_START_DT, COUPON_END_DT, DISCOUNT_PRICE, PRODUCT_URL,\n" +
                "PUBLISH_COUNT, CREATE_DT, LIMIT_PRICE, USE_START_DT, USE_END_DT, USE_PRICE, USE_COMMENT)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?)";
        Object[] createCouponParams = new Object[]{postCouponReq.getCouponType(), postCouponReq.getCouponName(), postCouponReq.getStartDt(),
                postCouponReq.getEndDt(), postCouponReq.getDiscountPrice(), postCouponReq.getProductUrl(), postCouponReq.getPublishCount(),
                postCouponReq.getLimitPrice(), postCouponReq.getUseStartDt(), postCouponReq.getUseEndDt(), postCouponReq.getUsePrice(), postCouponReq.getUseComment()};
        this.jdbcTemplate.update(createCouponQuery, createCouponParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyCouponStatus(PatchCouponReq patchCouponReq){
        String modifyCouponStatusQuery = "update USER_COUPON set USE_YN = ? where USER_ID = (select USER_ID FROM USER WHERE USER_IDX = ?) " +
                "and COUPON_ID = ? ";
        Object[] modifyCouponStatusParams = new Object[]{patchCouponReq.getUseYn(), patchCouponReq.getUserIdx(), patchCouponReq.getCouponId()};

        return this.jdbcTemplate.update(modifyCouponStatusQuery,modifyCouponStatusParams);
    }
}
