package com.example.demo.src.point;

import com.example.demo.config.BaseException;
import com.example.demo.src.point.model.GetPointRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class PointProvider {
    private final UserDao userDao;
    private final PointDao pointDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PointProvider(UserDao userDao, PointDao pointDao, JwtService jwtService) {
        this.userDao = userDao;
        this.pointDao = pointDao;
        this.jwtService = jwtService;
    }


    public List<GetPointRes> getPoints(int userIdx) throws BaseException {
        try{
            List<GetPointRes> getPointRes = pointDao.getPoints(userIdx);
            return getPointRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
