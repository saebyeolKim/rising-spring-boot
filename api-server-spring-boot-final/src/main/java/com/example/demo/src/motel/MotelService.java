package com.example.demo.src.motel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.motel.model.PatchMotelReq;
import com.example.demo.src.motel.model.PostMotelReq;
import com.example.demo.src.motel.model.PostMotelRes;
import com.example.demo.src.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class MotelService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MotelDao motelDao;

    @Autowired
    public MotelService(MotelDao motelDao) {
        this.motelDao = motelDao;
    }

    public void modifyMotelName(PatchMotelReq patchMotelReq) throws BaseException {
        try {
            int result = motelDao.modifyMotelName(patchMotelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_NAME);
            }
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void deleteMotel(PatchMotelReq patchMotelReq) throws BaseException {
        try {
            int result = motelDao.deleteMotel(patchMotelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_NAME);
            }
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public PostMotelRes createMotel(PostMotelReq postMotelReq) throws BaseException{
        try {
            int motelId = motelDao.createMotel(postMotelReq);
            return new PostMotelRes(motelId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
