package com.flower.su;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.dao.IotDao;
import com.flower.dao.UmbrellaDao;
import com.flower.dao.UserDao;
import com.flower.dao.impl.IotDaoImpl;
import com.flower.dao.impl.UmbrellaDaoImpl;
import com.flower.dao.impl.UserDaoImpl;
import com.flower.models.IotDevice;
import com.flower.models.IotSubDevice;
import com.flower.models.NetResult;
import com.flower.models.Umbrella;
import com.flower.models.User;
import com.flower.socket.SocketStart;
import com.flower.socket.TcpServerFoward;
import com.flower.util.BitUtils;
import com.flower.util.Md5_1;
@Controller
public class IotDeviceController {
	
	@RequestMapping(value = "/iot-add", method ={ RequestMethod.GET,RequestMethod.POST})
	public String deviceAdd(Locale locale, Model model,String name,String serial,String userName,HttpSession session) {
		
		if (serial != null) {
			IotDao dao=new IotDaoImpl();
			String devUuid = Md5_1.GetMD5Code(serial+(String)session.getAttribute("admin"));//获取UUID

			if (dao.findIotByUuid((String)session.getAttribute("admin"), devUuid) == null) {			
				IotDevice iot=new IotDevice();
				iot.setName(name);
				iot.setDevice_uuid(Md5_1.GetMD5Code(serial+(String)session.getAttribute("admin")));
				iot.setStatus(false);
				iot.setOwner((String) session.getAttribute("admin"));				
				iot.setTime(new Date());
				iot.setSerial(serial);			
				dao.addDevice(iot);
				List<IotDevice>  iotDevices=dao.findAllIotDevice((String)session.getAttribute("admin"));
				System.out.println("deviceNum"+iotDevices.size());
				session.setAttribute("deviceNum", iotDevices.size());
				
				model.addAttribute("addInfo", "添加物联网设备成功");
			
				return "firstPage";
			}else{
				model.addAttribute("addInfo", "设备编号重复请重新输入");
			}			
		
		}
		return "iot-add";
	
	}
	@RequestMapping(value = "/iot-add-mobile", method ={ RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult deviceAddMobile(Locale locale, Model model,String owner,String serial,String name,HttpSession session) {
		NetResult r=new NetResult();
		if (serial != null) {
			IotDao dao=new IotDaoImpl();
			String devUuid = Md5_1.GetMD5Code(serial+owner);//获取UUID
			if (dao.findIotByUuid(owner, devUuid) == null) {			
				IotDevice iot=new IotDevice();
				iot.setName(name);
				iot.setDevice_uuid(Md5_1.GetMD5Code(serial)+owner);
				iot.setStatus(false);
				iot.setOwner(owner);				
				iot.setTime(new Date());
				iot.setSerial(serial);			
				dao.addDevice(iot);						
					
				r.setStatus(1);
				r.setContent("添加设备成功");
			}else{
				r.setStatus(0);
				r.setContent("设备编号重复，添加设备失败");
			}			
		 return r;
		}
		r.setStatus(0);
		r.setContent("设备编号为空");
		return r;	
	}
	@RequestMapping(value = "/iot-list", method ={ RequestMethod.GET,RequestMethod.POST})	
	public String deviceList(Locale locale, Model model,HttpSession session) {				
		IotDao dao=new IotDaoImpl();
		List<IotDevice>  iotDevices=dao.findAllIotDevice((String)session.getAttribute("admin"));
        if(iotDevices!=null){
        	   model.addAttribute("deviceNum",iotDevices.size());  
        }else{
        	   model.addAttribute("deviceNum","0");
        }
		model.addAttribute("iotDevices", iotDevices);	
        
		return "iot-list";
	}
	@RequestMapping(value = "/iot-sub-list", method ={ RequestMethod.GET,RequestMethod.POST})	
	public String subDeviceList(Locale locale, Model model,HttpSession session,String serial) {						
	String devUuid=Md5_1.GetMD5Code(serial+session.getAttribute("admin"));//获取当前子设备的UUID
	model.addAttribute("devUuid", devUuid);
	IotDao dao=new IotDaoImpl();
	List<IotSubDevice> iotSubDevices=dao.findSubDevice(devUuid);
	if(iotSubDevices!=null){
		
		model.addAttribute("iotSubNum", iotSubDevices.size());
	}else{
		model.addAttribute("iotSubNum", "0");
	}
	
	model.addAttribute("iotSubDevices", iotSubDevices);
	
	return "iot-sub-list";	
	}

	@RequestMapping(value = "/iot-sub-list-ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<IotSubDevice> subDeviceListAjax(Locale locale, Model model, HttpSession session,
			String devUuid) {

		IotDao dao = new IotDaoImpl();
		List<IotSubDevice> iotSubDevices = dao.findSubDevice(devUuid);
		if (iotSubDevices != null) {

			model.addAttribute("iotSubNum", iotSubDevices.size());
		} else {
			model.addAttribute("iotSubNum", "0");
		}

		return iotSubDevices;
	}
	
	@RequestMapping(value = "/delete-iot", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody NetResult iotDelete(Locale locale, Model model, HttpSession session,
			String uuid) {
		String devUuid=Md5_1.GetMD5Code(uuid+session.getAttribute("admin"));				
		NetResult r=new NetResult();
		IotDao dao = new IotDaoImpl();
		
		try{
			dao.deleteIot(devUuid);
			session.setAttribute("deviceNum", dao.findAllIotDevice((String)session.getAttribute("admin")).size()); 
		}catch (Exception e){
			r.setStatus(0);
			return r;
		}
		
		r.setStatus(1);
		return r;

	}
	@RequestMapping(value = "/iot-sub-add", method ={ RequestMethod.GET,RequestMethod.POST})
	public String subDeviceAdd(Locale locale, Model model,HttpServletRequest reg,String iotname,String uuid,HttpSession session) {
		System.out.println("==="+uuid);
		model.addAttribute("devUuid", uuid);
		String type=reg.getParameter("type");
		System.out.println(type);
		if (type!=null) {
			IotDao dao=new IotDaoImpl();
			IotSubDevice iotsubDevice =new IotSubDevice();
			iotsubDevice.setName(iotname);
			iotsubDevice.setUuid(uuid);
			iotsubDevice.setType(Integer.parseInt(type));
			iotsubDevice.setValue(0);
			dao.addSubDevice(iotsubDevice);	
			List<IotSubDevice> iotSubDevices=dao.findSubDevice(uuid);
			System.out.println("子设备个数"+iotSubDevices.size());
			model.addAttribute("iotSubDevices", iotSubDevices);
			return "iot-sub-list";
		}else{
			//
			return "iot-sub-add";
		}
	
		
	}
	@RequestMapping(value = "/relay-operate", method ={ RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult relayOperate(Locale locale, Model model,String devUuid,String iotSubId,String iotSubOperate) {
		System.out.println("devUuid"+devUuid+"umId"+iotSubId+"operate"+iotSubOperate);
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
		umOperate [20]=(byte) Integer.parseInt("2"); 		
		if(iotSubOperate.equals("开启")){			
			umOperate [21]=0x00;			
		}else if(iotSubOperate.equals("关闭")){
			umOperate [21]=0x01;
		}	
	
		try{
			if(iotSubOperate.equals("开启"))
			send("B", socket, devUuid);
			else
			send("S", socket, devUuid);
		}catch(Exception e){
			r.setStatus(2);
			r.setContent("该设备在线");
		
			return r;
		}		
		//
		if (iotSubOperate.equals("开启")){
		
				r.setStatus(0);
				r.setContent("开启成功");						
			
		}else if(iotSubOperate.equals("关闭")){
				r.setStatus(1);
				r.setContent("关闭成功");				
							
		}			
		return r;


	}
	@RequestMapping(value = "/get-temperature", method ={ RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult getTemperature(Locale locale, Model model,String devUuid,String iotSubId,String iotSubOperate) {
		System.out.println("devUuid"+devUuid+"umId"+iotSubId+"operate"+iotSubOperate);
		NetResult r = new NetResult();		
		
		return r;


	}
	public void send(String msg,Socket socket,String devUuid){
		try {
			PrintWriter	pOut = new PrintWriter(socket.getOutputStream(), true); 			
			pOut.println(msg);			
			//out.close();
			//clientSocket.close();
		} catch (Exception e) {
			Map<String, Socket> sockets = SocketStart.getSocketClients();// 获取当前Socket列表
			sockets.remove(devUuid);	
			e.printStackTrace();
			System.out.println("发送失败");
		}
	}
}
