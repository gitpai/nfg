package com.flower.su;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.dao.IotDao;
import com.flower.dao.UmbrellaDao;
import com.flower.dao.impl.IotDaoImpl;
import com.flower.dao.impl.UmbrellaDaoImpl;
import com.flower.models.IotDevice;
import com.flower.models.IotSubDevice;
import com.flower.models.NetResult;
import com.flower.models.Umbrella;
import com.flower.util.Md5_1;
@Controller
public class IotDeviceController {
	
	@RequestMapping(value = "/iot-add", method ={ RequestMethod.GET,RequestMethod.POST})
	public String deviceAdd(Locale locale, Model model,String name,String serial,String userName,HttpSession session) {
		
		if (serial != null) {
			IotDao dao=new IotDaoImpl();
			String devUuid = Md5_1.GetMD5Code(serial+(String)session.getAttribute("admin"));//��ȡUUID

			if (dao.findIotByUuid((String)session.getAttribute("admin"), devUuid) == null) {			
				IotDevice iot=new IotDevice();
				iot.setName(name);
				iot.setDevice_uuid(Md5_1.GetMD5Code(serial+(String)session.getAttribute("admin")));
				iot.setStatus(false);
				iot.setOwner((String) session.getAttribute("admin"));				
				iot.setTime(new Date());
				iot.setSerial(serial);			
				dao.addDevice(iot);
			
				model.addAttribute("addInfo", "����������豸�ɹ�");
			}else{
				model.addAttribute("addInfo", "�豸����ظ�����������");
			}			
		
		}
		return "iot-add";
	
	}
	@RequestMapping(value = "/iot-add-mobile", method ={ RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NetResult deviceAddMobile(Locale locale, Model model,String owner,String serial,String name) {
		NetResult r=new NetResult();
		if (serial != null) {
			IotDao dao=new IotDaoImpl();
			String devUuid = Md5_1.GetMD5Code(serial+owner);//��ȡUUID
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
				r.setContent("����豸�ɹ�");
			}else{
				r.setStatus(0);
				r.setContent("�豸����ظ�������豸ʧ��");
			}			
		 return r;
		}
		r.setStatus(0);
		r.setContent("�豸���Ϊ��");
		return r;	
	}
	@RequestMapping(value = "/iot-list", method ={ RequestMethod.GET,RequestMethod.POST})	
	public String deviceList(Locale locale, Model model,HttpSession session) {				
		IotDao dao=new IotDaoImpl();
		List<IotDevice>  iotDevices=dao.findAllIotDevice((String)session.getAttribute("admin"));
        System.out.println(iotDevices.size());
		model.addAttribute("iotDevices", iotDevices);	
        model.addAttribute("deviceNum",iotDevices.size());     
		return "iot-list";
	}
	@RequestMapping(value = "/iot-sub-list", method ={ RequestMethod.GET,RequestMethod.POST})	
	public String subDeviceList(Locale locale, Model model,HttpSession session,String serial) {						
	String devUuid=Md5_1.GetMD5Code(serial+session.getAttribute("admin"));//��ȡ��ǰ���豸��UUID
	model.addAttribute("devUuid", devUuid);
	IotDao dao=new IotDaoImpl();
	List<IotSubDevice> iotSubDevices=dao.findSubDevice(devUuid);
	System.out.println("���豸����"+iotSubDevices.size());
	model.addAttribute("iotSubDevices", iotSubDevices);
	return "iot-sub-list";	
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
			System.out.println("���豸����"+iotSubDevices.size());
			model.addAttribute("iotSubDevices", iotSubDevices);
			return "iot-sub-list";
		}else{
			//
			return "iot-sub-add";
		}
	
		
	}
}
