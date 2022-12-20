package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.coupon.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;
import static com.example.demo.config.BaseResponseStatus.POST_EMPTY_NAME;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CouponProvider couponProvider;
    @Autowired
    private final CouponService couponService;
    @Autowired
    private final JwtService jwtService;

    public CouponController(CouponProvider couponProvider, CouponService couponService, JwtService jwtService) {
        this.couponProvider = couponProvider;
        this.couponService = couponService;
        this.jwtService = jwtService;
    }

    /**
     * 쿠폰 조회 API
     * [GET] /coupons
     * @return BaseResponse<List<GetHotelRes>>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetCouponRes>> getCoupons() {
        try {
            List<GetCouponRes> getCouponRes = couponProvider.getCoupon();
            return new BaseResponse<>(getCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 등록 API
     * [POST] /coupons
     * @return BaseResponse<PostCouponRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostCouponRes> createCoupon(@RequestBody PostCouponReq postCouponReq) {
        try {
            if(postCouponReq.getCouponType() == 0) {
                return new BaseResponse<>(BaseResponseStatus.POST_EMPTY_REGION);
            }
            if(postCouponReq.getCouponName() == null) {
                return new BaseResponse<>(BaseResponseStatus.POST_EMPTY_NAME);
            }
            if(postCouponReq.getStartDt() == null) {
                return new BaseResponse<>(BaseResponseStatus.EMPTY_START_DT);
            }
            if(postCouponReq.getEndDt() == null) {
                return new BaseResponse<>(BaseResponseStatus.EMPTY_END_DT);
            }
            if(postCouponReq.getDiscountPrice() == 0) {
                return new BaseResponse<>(BaseResponseStatus.POST_DISCOUNT_PRICE);
            }
            if(postCouponReq.getPublishCount() == 0) {
                return new BaseResponse<>(BaseResponseStatus.POST_PUBLISH_COUNT);
            }
            PostCouponRes postCouponRes = couponService.createCoupon(postCouponReq);
            return new BaseResponse<>(postCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 사용 API
     * [PATCH] /coupons/:userId/:couponId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userIdx}/{couponId}")
    public BaseResponse<String> modifyCouponStatus(@PathVariable("userIdx") int userIdx, @PathVariable("couponId") int couponId, @RequestBody Coupon coupon) throws BaseException {
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if (userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //같다면 유저네임 변경
            PatchCouponReq patchCouponReq = new PatchCouponReq(userIdx, couponId, coupon.getUseYn());
            couponService.modifyCouponStatus(patchCouponReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }
}
