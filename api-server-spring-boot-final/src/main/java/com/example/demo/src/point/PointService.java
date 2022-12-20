package com.example.demo.src.point;

import com.example.demo.config.BaseException;
import com.example.demo.src.point.model.PatchPointReq;
import com.example.demo.src.point.model.PostPointReq;
import com.example.demo.src.point.model.PostPointRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class PointService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final PointDao pointDao;
    private final UserProvider userProvider;
    private final PointProvider pointProvider;
    private final JwtService jwtService;

    public PointService(UserDao userDao, PointDao pointDao, UserProvider userProvider, PointProvider pointProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.pointDao = pointDao;
        this.userProvider = userProvider;
        this.pointProvider = pointProvider;
        this.jwtService = jwtService;
    }
    public PostPointRes createPoint(PostPointReq postPointReq) throws BaseException {
        try {
            int pointId = pointDao.createPoint(postPointReq);
            return new PostPointRes(pointId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void modifyUseStatus(PatchPointReq patchPointReq) throws BaseException {
        try {
            int result = pointDao.modifyUseStatus(patchPointReq);
            if(result == 0){
                throw new BaseException(POINT_USE_ERROR);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
