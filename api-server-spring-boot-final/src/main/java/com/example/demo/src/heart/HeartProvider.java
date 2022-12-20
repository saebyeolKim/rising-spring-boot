package com.example.demo.src.heart;

import com.example.demo.config.BaseException;
import com.example.demo.src.heart.model.GetHeartsRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class HeartProvider {
    private final HeartDao heartDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public HeartProvider(HeartDao heartDao, JwtService jwtService) {
        this.heartDao = heartDao;
        this.jwtService = jwtService;
    }

    public List<GetHeartsRes> getHearts() throws BaseException {
        try{
            List<GetHeartsRes> getHeartsRes = heartDao.getHearts();
            return getHeartsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkAccommodation(int userId, int accommodationId) throws BaseException{
        try{
            return heartDao.checkAccommodation(userId, accommodationId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
