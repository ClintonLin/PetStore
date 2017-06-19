package com.ruanko.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.ruanko.entity.User;
import com.ruanko.service.UserService;
import com.ruanko.uitl.PageModel;

@Controller("userAction")
@Scope("prototype")
public class UserAction{
	@Resource 
	private UserService userService;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private String pagePath=null; //指定跳转路径
	private User user;//收集页面用户对象的数据
	

	/**
	 * 用户登录
	 * @return
	 */
	public String userLogin(){
		System.out.println(user.getUserName()+" "+user.getPassWord());
		List list = this.userService.userLogin(user.getUserName(), user.getPassWord());
		if(list.size()<=0){
			ActionContext.getContext().put("message", "您的用户名或密码错误。");
			this.pagePath="/index.jsp";
			return "pagePath";
		}else{
			this.pagePath="/succ.jsp";
			return "pagePath";
		}
	}	
	/**
	 * 查询用户
	 * @return
	 */
	public String findUser(){
		int offset=0;//起始行数
		int line=2;//每页显示的行数
		if(request.getParameter("offset")!=null){
			offset= Integer.parseInt(request.getParameter("offset"));
		}
		PageModel model=this.userService.findUser(offset, line);
		ActionContext.getContext().put("model", model);
		this.pagePath="/user.jsp";
		return "pagePath";
		
	}
	/**
	 * 跳转到添加用户的页面
	 * @return
	 */
	public String gotoAddUserPage(){
		this.pagePath="/add_user.jsp";
		return"pagePath";
	}
	/**
	 * 添加用户（保存用户数据）
	 * @return
	 */
	public String addUser(){
		
		this.userService.addUser(user);
		return this.findUser();
//		this.pagePath="userAction!findUser";
//		return "addUser";
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String gotoEditPage(){
		//获取页面传递过来的ID
		int id=Integer.parseInt(request.getParameter("id").toString());
		//根据Id查询用户信息
		User user=this.userService.getUserByID(id);
		request.setAttribute("user",user);
		this.pagePath="/edit_user.jsp";
		return "pagePath";
		
	}
	/**
	 * 编辑用户信息
	 * @return
	 */
	public String editUser(){
		this.userService.updateUser(user);
		return this.findUser();
	}
	
	/**
	 * 删除用户信息
	 * @return
	 */
	public String deleteUser(){
		int id=Integer.parseInt(request.getParameter("id").toString());
		this.userService.deleteUser(id);
		return this.findUser();
	}
	
	/*************************************************************/
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
