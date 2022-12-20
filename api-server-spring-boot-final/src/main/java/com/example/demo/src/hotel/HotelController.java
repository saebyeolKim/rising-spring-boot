package com.example.demo.src.hotel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.hotel.model.*;
import com.example.demo.src.motel.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final HotelProvider hotelProvider;

    @Autowired
    private final HotelService hotelService;

    public HotelController(HotelProvider hotelProvider, HotelService hotelService) {
        this.hotelProvider = hotelProvider;
        this.hotelService = hotelService;
    }


    /**
     * 호텔 조회 API
     * [GET] /hotels
     * 호텔 지역 조회 API
     * [GET] /hotels? regionId=
     * @return BaseResponse<List<GetHotelRes>>
     */
    @ResponseBody
    @GetMapping("")
    private BaseResponse<List<GetHotelRes>> getMotels(@RequestParam(required = false) String regionId) {
        try {
            if (regionId == null) {
                List<GetHotelRes> getHotelRes = hotelProvider.getHotels();
                return new BaseResponse<>(getHotelRes);
            }
            List<GetHotelRes> getHotelRes = hotelProvider.getHotelByRegion(regionId);
            return new BaseResponse<>(getHotelRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 이름으로 호텔 조회 API
     * [GET] /hotels/:hotelName
     * @return BaseResponse<GethotelRes>
     */
    @ResponseBody
    @GetMapping("/{hotelId}")
    public BaseResponse<GetHotelRes> getHotelByName(@PathVariable("hotelId")int hotelId) {
        try {
            GetHotelRes getHotelRes = hotelProvider.getHotelByName(hotelId);
            return new BaseResponse<>(getHotelRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * ID로 호텔 이름 수정
     * [PATCH] /hotels/:hotelId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{hotelId}")
    public BaseResponse<String> patchHotelByMotelId(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        try {
            if (hotel.getHotelName() == null || hotel.getHotelName() == "") {
                throw new BaseException(PATCH_EMPTY_NAME);
            }
            PatchHotelReq patchHotelReq = new PatchHotelReq(hotelId,hotel.getHotelName(), hotel.getDelYn());
            hotelService.modifyHotelName(patchHotelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * ID로 호텔 삭제
     * [PATCH] /hotels/closing/:hotelId
     * @return BaseResponse<String>
     */
    @PatchMapping("/closing/{hotelId}")
    public BaseResponse<String> deleteHotel(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        try {
            PatchHotelReq patchHotelReq = new PatchHotelReq(hotelId, hotel.getHotelName(), hotel.getDelYn());
            hotelService.deleteHotel(patchHotelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 새로운 호텔 생성
     * [POST] /hotels
     * @return BaseResponse<PostHotelRes>
     */
    @PostMapping("")
    public BaseResponse<PostHotelRes> createHotel(@RequestBody PostHotelReq postHotelReq) {
        if (postHotelReq.getRegionId() == 0) {
            return new BaseResponse<>(POST_EMPTY_REGION);
        }else if (postHotelReq.getName() == null) {
            return new BaseResponse<>(POST_EMPTY_NAME);
        }else if (postHotelReq.getTel() == null) {
            return new BaseResponse<>(POST_EMPTY_TEL);
        }else if (postHotelReq.getLocation() == null) {
            return new BaseResponse<>(POST_EMPTY_LOCATION);
        }else if (postHotelReq.getImage() == null) {
            return new BaseResponse<>(POST_EMPTY_IMAGE);
        }else if (postHotelReq.getCeoName() == null) {
            return new BaseResponse<>(POST_EMPTY_CEO_NAME);
        }else if (postHotelReq.getBusinessName() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_NAME);
        }else if (postHotelReq.getBusinessLocation() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_LOCATION);
        }else if (postHotelReq.getBusinessEmail() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }else if (postHotelReq.getBusinessTelno() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_TEL);
        }else if (postHotelReq.getBusinessRegistrationNo() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_REGISTRATION);
        }

        try {
            PostHotelRes postHotelRes = hotelService.createHotel(postHotelReq);
            return new BaseResponse<>(postHotelRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
