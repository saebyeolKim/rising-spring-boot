package com.example.demo.src.hotel;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.hotel.model.GetHotelRes;
import com.example.demo.src.motel.model.GetMotelRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HotelDao hotelDao;
    @Autowired
    public HotelProvider(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public List<GetHotelRes> getHotels() throws BaseException {
        try {
            List<GetHotelRes> getHotels = hotelDao.getHotels();
            return getHotels;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetHotelRes> getHotelByRegion(String regionId) throws BaseException {
        try {
            List<GetHotelRes> getHotelByRegion = hotelDao.getHotelByRegion(regionId);
            return getHotelByRegion;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetHotelRes getHotelByName(int hotelId) throws BaseException {
        try {
            GetHotelRes getHotelByName = hotelDao.getHotelByName(hotelId);
            return getHotelByName;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
