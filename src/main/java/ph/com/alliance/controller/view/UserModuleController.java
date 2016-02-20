
package ph.com.alliance.controller.view;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.com.alliance.entity.UserModule;
import ph.com.alliance.service.UserModuleService;

/**
 * 
 * @author cutamora
 * 
 *
 */

@Controller
@RequestMapping("/usermodule")
public class UserModuleController {
	
	@Autowired
	private UserModuleService userModuleService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		String searchName = request.getParameter("searchName");
		List <UserModule> list = userModuleService.getUserModuleList(searchName);
		map.addAttribute("userList",list);
		return "usermodule/list";
	}
	
	@RequestMapping("/")
	public String loadIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "usermodule/index";
	}
	
	@RequestMapping(value="/createuser", method=RequestMethod.GET)
	public String loadCreateUser(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		List <UserModule> list = userModuleService.getUserListFetchFirstFive();
		map.addAttribute("userList",list);
		map.addAttribute("function","add");
		return "usermodule/createuser";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		String firstName = request.getParameter("firstName");
		String familyName = request.getParameter("familyName");
		String email = request.getParameter("email");
		String midinit = request.getParameter("midinit");
		String address = request.getParameter("address");
		String birthdate = request.getParameter("birthdate");
		String tinNo = request.getParameter("tinNo");
		String adminFlag = request.getParameter("adminFlag");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm:ss");
		Date currentdate = new Date();
		String createdDate = dateFormat.format(currentdate)+"";
		
		
		UserModule user = new UserModule();
		user.setFirstName(firstName);
		user.setFamilyName(familyName);
		user.setMidinit(midinit);
		user.setAddress(address);
		user.setEmail(email);
		user.setDeletedFlag("0");
		user.setPassword("12345");
		user.setCreatedDate(createdDate);
		user.setBirthdate(birthdate);
		user.setTinNo(tinNo);
		user.setAdminFlag(adminFlag);
		user.setModifiedDate(createdDate);
		user.setCreatedBy("1");
		userModuleService.insert(user);
		
		return list(request,response,map);
	}
	
	@RequestMapping(value="/edituser", method=RequestMethod.GET)
	public String loadEditUser(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		List <UserModule> list = userModuleService.getUserListFetchFirstFive();
		map.addAttribute("userList",list);
		map.addAttribute("function","edit");
		//get data from DB
		String userid = request.getParameter("userid");
		UserModule user = userModuleService.getUserById(userid);
		
		map.addAttribute("user", user);
		return "usermodule/createuser";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editUser(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		String userid = request.getParameter("userid");
		String firstName = request.getParameter("firstName");
		String familyName = request.getParameter("familyName");
		String email = request.getParameter("email");
		String midinit = request.getParameter("midinit");
//		String address = request.getParameter("address");
//		String birthdate = request.getParameter("birthdate");
//		String tinNo = request.getParameter("tinNo");
//		String adminFlag = request.getParameter("adminFlag");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh/mm:ss");
		Date currentdate = new Date();
		String createdDate = dateFormat.format(currentdate)+"";
		
		UserModule user = userModuleService.getUserById(userid);
		user.setFirstName(firstName);
		user.setFamilyName(familyName);
		user.setMidinit(midinit);
//		user.setAddress(address);
		user.setEmail(email);
		user.setDeletedFlag("0");
//		user.setCreatedDate(createdDate);
//		user.setBirthdate(birthdate);
//		user.setTinNo(tinNo);
//		user.setAdminFlag(adminFlag);
//		user.setCreatedDate(createdDate);
//		user.setCreatedBy("1");
		
		userModuleService.update(user);
		
		return list(request,response,map);
	}
	
	public String delete(){
		return null;
	}
}
