package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.coupon.model.PatchCouponReq;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.src.coupon.model.PostCouponRes;
import com.example.demo.src.user.model.PatchUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CouponService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CouponDao couponDao;

    public CouponService(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public PostCouponRes createCoupon(PostCouponReq postCouponReq) throws BaseException {
        try {
            int couponId = couponDao.createCoupon(postCouponReq);
            return new PostCouponRes(couponId, postCouponReq.getCouponName());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyCouponStatus(PatchCouponReq patchCouponReq) throws BaseException {
        try{
            int result = couponDao.modifyCouponStatus(patchCouponReq);
            if(result == 0){
                throw new BaseException(COUPON_USE_ERROR);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
