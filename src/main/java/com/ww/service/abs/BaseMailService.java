package com.ww.service.abs;

import com.alibaba.fastjson.JSONObject;
import com.ww.mail.feign.help.FeignServiceFactory;
import com.ww.mail.feign.service.MailApiFeign;
import com.ww.mail.model.dto.req.SimpleMailMessageDTO;
import com.ww.model.vo.CommonResVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：黑洞里的光
 * @date ：Created in 2021/8/29 17:18
 * @description：邮件通知服务
 */
@Slf4j
public abstract class BaseMailService {

    private static MailApiFeign service = FeignServiceFactory.createServiceWithJsonCodec(MailApiFeign.class, System.getProperty("mail.url"));

    public CommonResVO send(Object... params) {
        SimpleMailMessageDTO simpleMailMessageDTO = assembleMailMessage(params);
        log.info("发送邮件");
        CommonResVO commonResVO = service.sendMail(simpleMailMessageDTO);
        log.info("发送邮件响应：{}", JSONObject.toJSONString(commonResVO));
        return commonResVO;
    }

    protected abstract SimpleMailMessageDTO assembleMailMessage(Object... params);
}
