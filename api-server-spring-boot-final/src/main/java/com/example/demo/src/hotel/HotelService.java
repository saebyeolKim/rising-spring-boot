package com.example.demo.src.hotel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.hotel.model.PatchHotelReq;
import com.example.demo.src.hotel.model.PostHotelReq;
import com.example.demo.src.hotel.model.PostHotelRes;
import com.example.demo.src.motel.MotelDao;
import com.example.demo.src.motel.model.PatchMotelReq;
import com.example.demo.src.motel.model.PostMotelReq;
import com.example.demo.src.motel.model.PostMotelRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_NAME;

@Service
public class HotelService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HotelDao hotelDao;

    @Autowired
    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public void modifyHotelName(PatchHotelReq patchHotelReq) throws BaseException {
        try {
            int result = hotelDao.modifyHotelName(patchHotelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_NAME);
            }
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void deleteHotel(PatchHotelReq patchHotelReq) throws BaseException {
        try {
            int result = hotelDao.deleteHotel(patchHotelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_NAME);
            }
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
    public PostHotelRes createHotel(PostHotelReq postHotelReq) throws BaseException{
        try {
            int hotelId = hotelDao.createHotel(postHotelReq);
            return new PostHotelRes(hotelId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
