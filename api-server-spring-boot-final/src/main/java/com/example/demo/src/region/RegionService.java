package com.example.demo.src.region;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.region.model.PatchRegionReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_REGIONNAME;

@Service
public class RegionService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RegionDao regionDao;

    @Autowired
    public RegionService(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    public void modifyRegionName(PatchRegionReq patchRegionReq) throws BaseException {
        try {
            int result = regionDao.patchRegionReqs(patchRegionReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_REGIONNAME);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
