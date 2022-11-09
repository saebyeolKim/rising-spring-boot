package com.example.demo.src.region;

import com.example.demo.config.BaseException;
import com.example.demo.src.region.model.GetRegionRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class RegionProvider {
    private final RegionDao regionDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RegionProvider(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    public List<GetRegionRes> getRegions() throws BaseException {
        try {
            List<GetRegionRes> getRegionRes = regionDao.getRegions();
            return getRegionRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
