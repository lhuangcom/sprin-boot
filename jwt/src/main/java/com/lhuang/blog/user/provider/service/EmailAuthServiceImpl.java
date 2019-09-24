package com.lhuang.blog.user.provider.service;

import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.EmailNoAuth;
import com.lhuang.blog.user.api.service.EmailAuthService;
import com.lhuang.blog.user.api.service.EmailService;
import com.lhuang.blog.user.api.utils.CharSequenceUtil;
import com.lhuang.blog.user.api.utils.JWTUtils;
import com.lhuang.blog.user.provider.dao.EmailAuthDao;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailAuthServiceImpl implements EmailAuthService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private EmailAuthDao emailAuthDao;

    @Value("${email.valid.href}")
    private String validHref;

    @Override
    public Integer validLoginAuth(String userId){
          //每次登陆验证先进行邮件验证
        //进行判空处理
         //从未授权表中没有查出数据说明没有通过验证
        if (CharSequenceUtil.isEmpty(userId)){
            throw new BaseException("userId为空");
        }
        return emailAuthDao.getRecordByUserId(userId);
    }

    @Override
    public String processEmailAuth(String token) {
        if (CharSequenceUtil.isEmpty(token)){
            throw new BaseException("token为空");
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        String message = "";
        if (claims == null || jwtUtils.isExpire(claims.getExpiration())){
            message = "失效，请重新登陆验证";
            return  message;
        }

        String userId = claims.getSubject();
        Integer count = emailAuthDao.deleteRecordByUserId(userId);
        //redis存储验证邮件处理
        message =  count > 0 ? "处理邮箱验证成功" : "已验证过，请勿重复发送验证邮件";
        return message;
    }

    @Override
    public Integer addRecord(EmailNoAuth emailNoAuth) {
        return emailAuthDao.insertRecord(emailNoAuth);
    }

    @Override
    public String sendAntuEmail(String userId) {
        String token = jwtUtils.generateToken(userId);

        String content = validHref+token;

        emailService.sendSimpleMail("2539203552@qq.com","这是一封鉴权的邮件",content);

        return "成功发送";
    }
}
