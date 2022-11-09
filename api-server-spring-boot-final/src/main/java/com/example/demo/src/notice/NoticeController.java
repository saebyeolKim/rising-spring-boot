package com.example.demo.src.notice;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.notice.model.GetNoticeRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notices")
public class NoticeController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final NoticeProvider noticeProvider;

    @Autowired
    private final NoticeDao noticeDao;

    public NoticeController(NoticeProvider noticeProvider, NoticeDao noticeDao) {
        this.noticeProvider = noticeProvider;
        this.noticeDao = noticeDao;
    }

    /**
     * 공지사항 조회 API
     * [GET] /notices
     * @return BaseResponde<List<GetNoticeRes>>
     */
    @GetMapping("")
    public BaseResponse<List<GetNoticeRes>> getNoticeRes() {
        try {
            List<GetNoticeRes> getNoticeRes = noticeProvider.getNotices();
            return new BaseResponse<>(getNoticeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 공지사항 1개 조회
     * [GET] /notices/:noticeId
     * @return BaseResponde<List<GetNoticeRes>>
     */
    @GetMapping("/{noticeId}")
    public BaseResponse<GetNoticeRes> getNotice(@PathVariable("noticeId") int noticeId) {
        try {
            GetNoticeRes getNoticeRes = noticeProvider.getNotice(noticeId);
            return new BaseResponse<>(getNoticeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
