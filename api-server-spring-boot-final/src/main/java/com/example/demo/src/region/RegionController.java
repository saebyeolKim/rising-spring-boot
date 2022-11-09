package com.example.demo.src.region;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.region.model.GetRegionRes;
import com.example.demo.src.region.model.PatchRegionReq;
import com.example.demo.src.region.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final RegionProvider regionProvider;

    @Autowired
    private final RegionService regionService;

    @Autowired

    public RegionController(RegionProvider regionProvider, RegionService regionService){
        this.regionProvider = regionProvider;
        this.regionService = regionService;
    }

    /**
     * 지역 조회 API
     * [GET] /regions
     * @return BaseResponse<List<GetRegionRes>>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetRegionRes>> getRegions() {
        try {
            List<GetRegionRes> getRegionRes = regionProvider.getRegions();
            return new BaseResponse<>(getRegionRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 지역 이름 수정 API
     * [PATCH] /regions/:regionId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{regionId}")
    public BaseResponse<String> patchRegions(@PathVariable("regionId") int regionId, @RequestBody Region region) {
        try {
            PatchRegionReq patchRegionReq = new PatchRegionReq(regionId, region.getRegionName());
            regionService.modifyRegionName(patchRegionReq);
            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
