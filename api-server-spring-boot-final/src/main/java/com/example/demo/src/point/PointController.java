package com.example.demo.src.point;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.point.model.GetPointRes;
import com.example.demo.src.point.model.PatchPointReq;
import com.example.demo.src.point.model.PostPointReq;
import com.example.demo.src.point.model.PostPointRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/points")
public class PointController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PointProvider pointProvider;
    @Autowired
    private final PointService pointService;
    @Autowired
    private final JwtService jwtService;

    public PointController(PointProvider pointProvider, PointService pointService, JwtService jwtService) {
        this.pointProvider = pointProvider;
        this.pointService = pointService;
        this.jwtService = jwtService;
    }


    /**
     * 포인트 조회 API
     * [GET] /points
     * @return BaseResponse<List<GetPointRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetPointRes>> getPoints(@PathVariable("userIdx") int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetPointRes> getPointRes = pointProvider.getPoints(userIdx);
            return new BaseResponse<>(getPointRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 포인트 적립 API
     * [POST] /points/:userIdx
     * @return BaseResponse<PostPointRes>
     */
    @ResponseBody
    @PostMapping("/{userIdx}")
    public BaseResponse<PostPointRes> createPoint(@PathVariable("userIdx") int userIdx, @RequestBody PostPointReq postPointReq) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (postPointReq.getPointCount() < 1) {
                return new BaseResponse<>(REQUEST_ERROR);
            }
            if (postPointReq.getPointName() == null) {
                return new BaseResponse<>(POST_EMPTY_NAME);
            }
            if (postPointReq.getStartDt() == null) {
                return new BaseResponse<>(EMPTY_START_DT);
            }
            if (postPointReq.getEndDt() == null) {
                return new BaseResponse<>(EMPTY_END_DT);
            }

            postPointReq.setUserIdx(userIdx);
            PostPointRes postPointRes = pointService.createPoint(postPointReq);
            return new BaseResponse<>(postPointRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 포인트 사용 API
     * [Patch] /points/:userIdx/:pointId
     * @return BaseResponse<PatchPointRes>
     */
    @ResponseBody
    @PatchMapping("/{userIdx}/{pointId}")
    public BaseResponse<String> createPoint(@PathVariable("userIdx") int userIdx, @PathVariable("pointId") int pointId) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PatchPointReq patchPointReq = new PatchPointReq(userIdx, pointId);
            pointService.modifyUseStatus(patchPointReq);
            String result = "";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
