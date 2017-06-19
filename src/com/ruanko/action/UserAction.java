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
	private String pagePath=null; //ָ����ת·��
	private User user;//�ռ�ҳ���û����������
	

	/**
	 * �û���¼
	 * @return
	 */
	public String userLogin(){
		System.out.println(user.getUserName()+" "+user.getPassWord());
		List list = this.userService.userLogin(user.getUserName(), user.getPassWord());
		if(list.size()<=0){
			ActionContext.getContext().put("message", "�����û������������");
			this.pagePath="/index.jsp";
			return "pagePath";
		}else{
			this.pagePath="/succ.jsp";
			return "pagePath";
		}
	}	
	/**
	 * ��ѯ�û�
	 * @return
	 */
	public String findUser(){
		int offset=0;//��ʼ����
		int line=2;//ÿҳ��ʾ������
		if(request.getParameter("offset")!=null){
			offset= Integer.parseInt(request.getParameter("offset"));
		}
		PageModel model=this.userService.findUser(offset, line);
		ActionContext.getContext().put("model", model);
		this.pagePath="/user.jsp";
		return "pagePath";
		
	}
	/**
	 * ��ת������û���ҳ��
	 * @return
	 */
	public String gotoAddUserPage(){
		this.pagePath="/add_user.jsp";
		return"pagePath";
	}
	/**
	 * ����û��������û����ݣ�
	 * @return
	 */
	public String addUser(){
		
		this.userService.addUser(user);
		return this.findUser();
//		this.pagePath="userAction!findUser";
//		return "addUser";
	}
	
	/**
	 * ��ת���༭ҳ��
	 * @return
	 */
	public String gotoEditPage(){
		//��ȡҳ�洫�ݹ�����ID
		int id=Integer.parseInt(request.getParameter("id").toString());
		//����Id��ѯ�û���Ϣ
		User user=this.userService.getUserByID(id);
		request.setAttribute("user",user);
		this.pagePath="/edit_user.jsp";
		return "pagePath";
		
	}
	/**
	 * �༭�û���Ϣ
	 * @return
	 */
	public String editUser(){
		this.userService.updateUser(user);
		return this.findUser();
	}
	
	/**
	 * ɾ���û���Ϣ
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
