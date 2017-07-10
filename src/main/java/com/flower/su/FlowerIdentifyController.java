package com.flower.su;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.dao.FlowerDao;
import com.flower.dao.MomentDao;
import com.flower.dao.impl.FlowerDaoImpl;
import com.flower.dao.impl.MomentDaoImpl;
import com.flower.models.Flower;
import com.flower.models.Moments;
import com.flower.util.Base64Coder;
import com.flower.util.FlowerUtil;
@Controller
public class FlowerIdentifyController {
	@RequestMapping(value = "/flowerIdentify", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody  Flower getLatestMoments(Locale locale, Model model,String file) {		
		
		try {
			if(file!=null)
			{
				byte[] b= Base64Coder.decodeLines(file);
				String filepath="/home/zhangtong/upImage";
				File imgFile=new File(filepath);
				if(!imgFile.exists())
					imgFile.mkdirs();
				FileOutputStream fos=new FileOutputStream(imgFile.getPath()+"/TestUpImage.jpg");
				//System.out.println(file.getPath());
				fos.write(b);
				fos.flush();
				fos.close();		
			}
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String flower_id=FlowerUtil.Readsh();//"1";//
		System.out.println("智能识花的结果是：");
		System.out.println("序号："+flower_id);
		FlowerDao flowerDao=new FlowerDaoImpl();
		Flower flower=flowerDao.matchFlower(flower_id);	
		return flower;		
	}	
	
}
