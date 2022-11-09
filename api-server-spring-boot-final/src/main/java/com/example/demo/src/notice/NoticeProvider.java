package com.example.demo.src.notice;

import com.example.demo.config.BaseException;
import com.example.demo.src.notice.model.GetNoticeRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class NoticeProvider {
    private final NoticeDao noticeDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public NoticeProvider(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    public List<GetNoticeRes> getNotices() throws BaseException {
        try {
            List<GetNoticeRes> getNotices = noticeDao.getNotices();
            return getNotices;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public  GetNoticeRes getNotice(int noticeId) throws BaseException {
        try {
            GetNoticeRes getNotice =noticeDao.getNotice(noticeId);
            return getNotice;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
