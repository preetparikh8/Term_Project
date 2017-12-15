package somepackage;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.AdminLoginDAO;
import DAO.ApprovalDAO;
import DAO.LoginDAO;
import DAO.ManagerReqDAO;
import DAO.RegistrationDAO;

@ManagedBean

public class Event {
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public String address;
	public String number;
	public String email;
	public String msg;
	public String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String username) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String username) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String username) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String username) {
		this.number = number;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String username) {
		this.email = email;
	}
	
	public String goPage() {
		{
			Boolean result = LoginDAO.validateUser(username, password, role);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if (result) {
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				facesContext.getExternalContext().getSessionMap().put("message", "Success");
				// session.setAttribute("Message", this.getMsg());
				session.setAttribute("Username", this.getUsername());
				if(this.getRole().equals("user"))
				{
				return "Index";
				}
				else
				{
					return "ManagerDashboard";
				}
			} 
			else {
				this.setMsg("Invalid Credentials");
				facesContext.getExternalContext().getSessionMap().put("message", "Invalid Credentials");
				return "login";
			}
		}
	}
	
	public String goReg() {
		{
			this.setMsg(null);
			try {
				RegistrationDAO register = new RegistrationDAO();
				if (register.registerUser(this)) {
					this.setMsg("Registration Successful");
					FacesContext facesContext = FacesContext.getCurrentInstance();
					HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
					facesContext.getExternalContext().getSessionMap().put("message", "Registration Successful");
					session.setAttribute("Username", this.getUsername());
					return "Login";
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg",
							"User already exist");
					return "Registration";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				this.setMsg("Some Error Found \t" + e.toString());
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "Registration";
			}
		}
}
	
	public String adminLogin() {
		{
			Boolean result = AdminLoginDAO.validateUser(username, password);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if (result) {
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				facesContext.getExternalContext().getSessionMap().put("message", "Success");
				// session.setAttribute("Message", this.getMsg());
				session.setAttribute("Username", this.getUsername());
				return "admin";
			} else {
				this.setMsg("Invalid Credentials");
				facesContext.getExternalContext().getSessionMap().put("message", "Invalid Credentials");
				return "adminlogin";
			}
		}
	}
	
	public String logOut() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	            .getExternalContext().getSession(false);
	    session.invalidate();
		return "Login";
	}
	
	public String managerReq() {
		{
			this.setMsg(null);
			try {
				ManagerReqDAO register = new ManagerReqDAO();
				if (register.managerReq(this)) {
					FacesContext facesContext = FacesContext.getCurrentInstance();
					HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
					facesContext.getExternalContext().getSessionMap().put("message", "Request Sent");
					session.setAttribute("Username", this.getUsername());
					return "ManagerDashboard";
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg",
							"Request Already Sent");
					return "ManagerDashboard";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				this.setMsg("Some Error Found \t" + e.toString());
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "ManagerDashboard";
			}
		}
}
	public String adminApp() {
		{
			try {
				ApprovalDAO register = new ApprovalDAO();
				if (register.appr(this)) {
					FacesContext facesContext = FacesContext.getCurrentInstance();
					HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
					session.setAttribute("Username", this.getUsername());
					return "admin";
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg",
							"Request Already Sent");
					return "admin";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				this.setMsg("Some Error Found \t" + e.toString());
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "admin";
			}
		}
}
}
