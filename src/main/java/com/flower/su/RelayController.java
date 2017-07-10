package com.flower.su;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.dao.UmbrellaDao;
import com.flower.dao.UserBarHisDao;
import com.flower.dao.UserDao;
import com.flower.dao.impl.UmbrellaDaoImpl;
import com.flower.dao.impl.UserBarHisDaoImpl;
import com.flower.dao.impl.UserDaoImpl;
import com.flower.models.NetResult;
import com.flower.models.Umbrella;
import com.flower.models.User;
import com.flower.models.UserBarHistory;
import com.flower.socket.SocketStart;
import com.flower.socket.TcpServerFoward;
import com.flower.util.BitUtils;

@Controller
public class RelayController {
	@RequestMapping(value="/openOrCloseRelay",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult oacRelay(Locale locale, Model model, String devUuid, String umId, String operate,String admin,
			HttpSession session
	) {
//		System.out.println("devUuid"+devUuid+"umId"+umId+"operate"+operate);
			NetResult r = new NetResult();		
			Map<String, Socket> sockets = SocketStart.getSocketClients();// 获取当前Socket列表
			Socket socket = sockets.get(devUuid);
			byte[] uuidByte=TcpServerFoward.stringToByte(devUuid);
			byte[] umOperate=new byte[22];
			umOperate [0]=0x01;
			umOperate [1]=0x01;
			umOperate [2]=0x03;
			for(int i=0;i<uuidByte.length;i++){
				umOperate [i+3]=uuidByte[i];	
			}
			umOperate [19]=0x16;
			umOperate [20]=(byte) Integer.parseInt(umId); 		
			if(operate.equals("open")){			
				umOperate [21]=0x00;			
			}else if(operate.equals("close")){
				umOperate [21]=0x01;
			}	
			UmbrellaDao dao = new UmbrellaDaoImpl();
			Umbrella umBefore = dao.findDeviceByUuid(devUuid);
			byte[] staBefore=umBefore.getUmbrellaSta();
			try{
				send(socket, umOperate);
			}catch(Exception e){
				r.setStatus(0);
				r.setContent("该设备未在线");
			//	System.out.println("伞架未在线");
				return r;
			}				
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
		
			Umbrella umAfter = dao.findDeviceByUuid(devUuid); 
			byte[] staAfter=umAfter.getUmbrellaSta();
			int umIndex = 0;
			int umIdDeal=Integer.parseInt(umId);
			if(Integer.parseInt(umId) < 9){
				umIdDeal-=1;
				umIndex=0;
			}
		
			else if(8 < Integer.parseInt(umId) && Integer.parseInt(umId) < 12){
				umIdDeal-=9;
				umIndex=1;
			}			
			System.out.println("umIndex"+umIndex);
			UserDao userDao = new UserDaoImpl();
	
			User user = userDao.findByName(admin); // 寻找当前开伞用户

			
			
			
			if (operate.equals("open")){
	
				
					r.setStatus(0);
					r.setContent("开启成功");											
				
				
			}else if(operate.equals("close")){
				
					r.setContent("关闭失败");				
																	
			}			
			userDao.addUser(user);
			return r;
		
	
		
	}
	public static void send(Socket socket,byte [] operate) throws Exception{
		PrintWriter	pOut=null;
		OutputStream outPutStream=null;			
		outPutStream=socket.getOutputStream();	
		outPutStream.write(operate);
			//outPutStream.close();
			//out.close();
			//clientSocket.close();		
	}
	
}
