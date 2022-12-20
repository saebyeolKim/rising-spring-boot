package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.coupon.model.GetCouponRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CouponDao couponDao;

    public CouponProvider(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public List<GetCouponRes> getCoupon() throws BaseException {
        try {
            List<GetCouponRes> getCoupons = couponDao.getCoupons();
            return getCoupons;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
