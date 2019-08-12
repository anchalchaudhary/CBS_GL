function login(){
	  document.getElementById("login").style.display = "block";
	  document.getElementById("liLogin").className = "tab activeTab";
	  document.getElementById("register").style.display = "none";
	  document.getElementById("liRegister").className="tab";
}
function register(){
	  document.getElementById("register").style.display = "block";
	  document.getElementById("liRegister").className ="tab activeTab";
	  document.getElementById("login").style.display = "none";
	  document.getElementById("liLogin").className="tab";
}