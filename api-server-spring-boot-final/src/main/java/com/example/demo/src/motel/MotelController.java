package com.example.demo.src.motel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.motel.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/motels")
public class MotelController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MotelProvider motelProvider;

    @Autowired
    private MotelService motelService;

    public MotelController(MotelProvider motelProvider, MotelService motelService) {
        this.motelProvider = motelProvider;
        this.motelService = motelService;
    }

    /**
     * 모텔 조회 API
     * [GET] /motels
     * 모텔 지역 조회 API
     * [GET] /motels? regionId=
     * @return BaseResponse<List<GetMotelRes>>
     */
    @ResponseBody
    @GetMapping("")
    private BaseResponse<List<GetMotelRes>> getMotels(@RequestParam(required = false) String regionId) {
        try {
            if (regionId == null) {
                List<GetMotelRes> getMotelRes = motelProvider.getMotels();
                return new BaseResponse<>(getMotelRes);
            }
            List<GetMotelRes> getMotelRes = motelProvider.getMotelByRegion(regionId);
            return new BaseResponse<>(getMotelRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 이름으로 모텔 조회 API
     * [GET] /motels/:motelName
     * @return BaseResponse<GetMotelRes>
     */
    @ResponseBody
    @GetMapping("/{motelId}")
    public BaseResponse<GetMotelRes> getMotelByName(@PathVariable("motelId")int motelId) {
        try {
            GetMotelRes getMotelRes = motelProvider.getMotelByName(motelId);
            return new BaseResponse<>(getMotelRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * ID로 모텔 이름 수정
     * [PATCH] /motels/:motelId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{motelId}")
    public BaseResponse<String> patchMotelByMotelId(@PathVariable("motelId") int motelId, @RequestBody Motel motel) {
        try {
            if (motel.getMotelName() == null || motel.getMotelName() == "") {
                throw new BaseException(PATCH_EMPTY_NAME);
            }
            PatchMotelReq patchMotelReq = new PatchMotelReq(motelId,motel.getMotelName(), motel.getDelYn());
            motelService.modifyMotelName(patchMotelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * ID로 모텔 삭제
     * [PATCH] /motels/closing/:motelId
     * @return BaseResponse<String>
     */
    @PatchMapping("/closing/{motelId}")
    public BaseResponse<String> deleteMotel(@PathVariable("motelId") int motelId, @RequestBody Motel motel) {
        try {
            PatchMotelReq patchMotelReq = new PatchMotelReq(motelId,motel.getMotelName(), motel.getDelYn());
            motelService.deleteMotel(patchMotelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 새로운 모텔 생성
     * [POST] /motels
     * @return BaseResponse<PostLoginRes>
     */
    @PostMapping("")
    public BaseResponse<PostMotelRes> createMotel(@RequestBody PostMotelReq postMotelReq) {
        if (postMotelReq.getRegionId() == 0) {
            return new BaseResponse<>(POST_EMPTY_REGION);
        }else if (postMotelReq.getMotelName() == null) {
            return new BaseResponse<>(POST_EMPTY_NAME);
        }else if (postMotelReq.getMotelTel() == null) {
            return new BaseResponse<>(POST_EMPTY_TEL);
        }else if (postMotelReq.getMotelLocation() == null) {
            return new BaseResponse<>(POST_EMPTY_LOCATION);
        }else if (postMotelReq.getMotelImage() == null) {
            return new BaseResponse<>(POST_EMPTY_IMAGE);
        }else if (postMotelReq.getMotelCeoName() == null) {
            return new BaseResponse<>(POST_EMPTY_CEO_NAME);
        }else if (postMotelReq.getMotelBusinessName() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_NAME);
        }else if (postMotelReq.getMotelBusinessLocation() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_LOCATION);
        }else if (postMotelReq.getMotelBusinessEmail() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }else if (postMotelReq.getMotelBusinessTelno() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_TEL);
        }else if (postMotelReq.getMotelBusinessRegistrationNo() == null) {
            return new BaseResponse<>(POST_EMPTY_BUSINESS_REGISTRATION);
        }

        try {
            PostMotelRes postMotelRes = motelService.createMotel(postMotelReq);
            return new BaseResponse<>(postMotelRes);
        } catch (BaseException exception) {
          return new BaseResponse<>(exception.getStatus());
        }
    }

}
