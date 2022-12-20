package com.example.demo.src.motel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.motel.model.GetMotelRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotelProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MotelDao motelDao;

    @Autowired
    public MotelProvider(MotelDao motelDao) {
        this.motelDao = motelDao;
    }

    public List<GetMotelRes> getMotels() throws BaseException {
        try {
            List<GetMotelRes> getMotels = motelDao.getMotels();
            return getMotels;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetMotelRes getMotelByName(int motelId) throws BaseException {
        try {
            GetMotelRes getMotelByName = motelDao.getMotelByName(motelId);
            return getMotelByName;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetMotelRes> getMotelByRegion(String regionId) throws BaseException {
        try {
            List<GetMotelRes> getMotelByRegion = motelDao.getMotelByRegion(regionId);
            return getMotelByRegion;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
