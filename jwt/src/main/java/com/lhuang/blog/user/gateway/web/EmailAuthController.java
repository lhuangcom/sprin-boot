package com.lhuang.blog.user.gateway.web;

import com.lhuang.blog.user.api.pojo.ResultBean;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.service.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/email")
@Controller
public class EmailAuthController {

    @Autowired
    private EmailAuthService emailAuthService;

   @RequestMapping("/auth/{userId}")
   @ResponseBody
   public ResultBean auth(@PathVariable("userId") String userId){

       ResultBean<User> resultBean = new ResultBean();
       Integer result = emailAuthService.validLoginAuth(userId);
       Integer resultCode = ResultBean.SUCCESS;
       if (result > 0){
           resultCode = ResultBean.FAIL;
       }
       resultBean.setResultCode(resultCode);
       return resultBean;
   }

    @RequestMapping("/validEmail/{token}")
    @ResponseBody
    public ResultBean validEmail(@PathVariable("token") String token){

        ResultBean<User> resultBean = new ResultBean();
        String message = emailAuthService.processEmailAuth(token);
        resultBean.setMessage(message);
        return resultBean;
    }


}
