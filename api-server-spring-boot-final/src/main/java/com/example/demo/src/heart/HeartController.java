package com.example.demo.src.heart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.heart.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/hearts")
public class HeartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final HeartProvider heartProvider;
    @Autowired
    private final HeartService heartService;
    @Autowired
    private final JwtService jwtService;

    public HeartController(HeartProvider heartProvider, HeartService heartService, JwtService jwtService) {
        this.heartProvider = heartProvider;
        this.heartService = heartService;
        this.jwtService = jwtService;
    }

    /**
     * 찜 목록 조회 API
     * [GET] /hearts
     * @return BaseResponse<List<GetHeartsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<List<GetHeartsRes>> getHearts(@PathVariable("userIdx") int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetHeartsRes> getHeartsRes = heartProvider.getHearts();
            return new BaseResponse<>(getHeartsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 찜 등록 API
     * [POST] /hearts/:userIdx
     * @return BaseResponse<PostHeartRes>
     */
    @ResponseBody
    @PostMapping("/{userIdx}")
    public BaseResponse<PostHeartRes> createHeart(@PathVariable("userIdx") int userIdx, @RequestBody PostHeartReq postHeartReq) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (postHeartReq.getAccommodationId() == 0) {
                return new BaseResponse<>(POST_EMPTY_ACCOMMODATION);
            }

            postHeartReq.setUserIdx(userIdx);
            PostHeartRes postHeartRes = heartService.createHeart(postHeartReq);
            return new BaseResponse<>(postHeartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 찜 삭제 API
     * [DELETE] /hearts/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/{userIdx}")
    public BaseResponse<String> deleteHeart(@PathVariable("userIdx") int userIdx, @RequestBody DeleteHeartReq deleteHeartReq) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (deleteHeartReq.getAccommodationId() == 0) {
                return new BaseResponse<>(POST_EMPTY_ACCOMMODATION);
            }
            //같다면 찜 등록
            deleteHeartReq.setUserIdx(userIdx);
            heartService.deleteHeart(deleteHeartReq);
            String result = "";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
