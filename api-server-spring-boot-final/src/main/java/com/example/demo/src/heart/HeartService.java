package com.example.demo.src.heart;

import com.example.demo.config.BaseException;
import com.example.demo.src.heart.model.DeleteHeartReq;
import com.example.demo.src.heart.model.DeleteHeartRes;
import com.example.demo.src.heart.model.PostHeartReq;
import com.example.demo.src.heart.model.PostHeartRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class HeartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final HeartDao heartDao;
    private final UserProvider userProvider;
    private final HeartProvider heartProvider;
    private final JwtService jwtService;

    public HeartService(UserDao userDao, HeartDao heartDao, UserProvider userProvider, HeartProvider heartProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.heartDao = heartDao;
        this.userProvider = userProvider;
        this.heartProvider = heartProvider;
        this.jwtService = jwtService;
    }

    public PostHeartRes createHeart(PostHeartReq postHeartReq) throws BaseException {
       try {
           //중복되면 찜 삭제
           if(heartProvider.checkAccommodation(postHeartReq.getUserIdx(), postHeartReq.getAccommodationId()) == 1) {
               DeleteHeartReq deleteHeartReq = new DeleteHeartReq(postHeartReq.getUserIdx(), postHeartReq.getAccommodationId());
               deleteHeart(deleteHeartReq);
               int heartId = 0;
               return new PostHeartRes(heartId);
           }
           int heartId = heartDao.createHeart(postHeartReq);
           return new PostHeartRes(heartId);
       } catch (Exception exception) {
           throw new BaseException(DATABASE_ERROR);
       }
    }
    public DeleteHeartRes deleteHeart(DeleteHeartReq deleteHeartReq) throws BaseException {
        try {
            int heartId = heartDao.deleteHeart(deleteHeartReq);
            return new DeleteHeartRes(heartId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
