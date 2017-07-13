package com.flower.su;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.flower.dao.UserDao;
import com.flower.dao.impl.UserDaoImpl;
import com.flower.models.NetResult;
import com.flower.models.User;
import com.flower.models.UserVerifyCode;
import com.flower.util.Md5_1;
import com.flower.util.SmsDemo;
import com.flower.util.SmsServer;


/**
 * @author Yujie
 *
 */
@Controller
public class UserAjax {

	@RequestMapping(value = "/register-mobile", method = {RequestMethod.POST, RequestMethod.GET})
	public  @ResponseBody NetResult register(Locale locale, Model model,			
			@RequestParam(value="userName",required=true)  String userName,		
			@RequestParam(value="password",required=true)  String password,
			
			@RequestParam(value="smsCode",required=true)   String smsCode
			) {
		NetResult r=new NetResult();
		UserDaoImpl dao=new UserDaoImpl();	   
		if(dao.findByName(userName)==null){
			String smsCodeLocal=dao.getUserRegCode(userName);//获取数据库验证码
			if(smsCodeLocal.equals(smsCode)){
				User user=new User();
				user.setUserName(userName);
				user.setPassword(Md5_1.GetMD5Code(password));
				user.setBorrowSta(false);
				user.setTime(new Date());
				user.setUserAuth(true);
			
				dao.addUser(user);
				r.setStatus(1);
				r.setContent("注册成功");	
			}else{
				r.setStatus(0);
				r.setContent("验证码错误");	
			}
			
			
		}else{			
			r.setStatus(0);
			r.setContent("用户名重复，注册失败");
		}										
		return  r;
	}
	
	@RequestMapping(value = "/login-mobile", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult login(Locale locale, Model model,
			@RequestParam(value="userName",required=true)  String userName,	
			@RequestParam(value="password",required=true)  String password,			
			HttpSession session,
			HttpServletResponse response) {
			
		NetResult r=new NetResult();
		UserDao dao=new UserDaoImpl();	
		if(dao.findByName(userName)!=null){	
			if(dao.findByName(userName).getPassword().equals(Md5_1.GetMD5Code(password))){
				r.setStatus(1);
				r.setContent("登录成功");	
			}else{
				r.setStatus(0);
				r.setContent("密码错误");	
			}
												
		}else{
			r.setStatus(0);
			r.setContent("无此用户");
		}		
	   return r;	
	}
	
	@RequestMapping(value = "/register-verifycode", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody NetResult registerVerfyCode(Locale locale, Model model,			
			@RequestParam(value="userName",required=true)  String userName
			) throws ClientException, InterruptedException {
		NetResult r=new NetResult();
		UserDaoImpl dao=new UserDaoImpl();	   
		if(dao.findByName(userName)==null){
			UserVerifyCode userVerifyCode=new UserVerifyCode();
		
			String smsCode="";//生成4位验证码
			for (int i = 0; i < 4; i++) {
				smsCode += new Random().nextInt(10) + "";
			}
			userVerifyCode.setUserName(userName);
			userVerifyCode.setVerfyCode(smsCode);
			dao.addUserRegCode(userVerifyCode);
			SmsDemo.sendSmsMsg(userName,smsCode);			
			r.setStatus(1);
			r.setContent("下发验证码成功");
			
		}else{			
			r.setStatus(0);
			r.setContent("下发验证码失败，该手机号已注册，请登录");
		}										
		return  r;
	}
	
}
