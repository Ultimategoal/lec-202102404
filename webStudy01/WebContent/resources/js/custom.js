/**
 * 
 */
$.customAlert=function(message){
	alert(message);
}
$.fn.sessionTimer=function(obj){
	const TIMEOUT = obj.timeout;
	const AJAXURL = obj.url;
	console.log(this)
	
	this.html(obj);
	
	// 비동기(url)
	return this;
}