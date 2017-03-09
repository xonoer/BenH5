package org.benmobile.entity.app;

public class AppInfo {

	public String versionCode;	
	public String versionName;
	public String SessionID="";
	public String UUID="";
	public String Appkey="";	
	public Object android_market;
	public String API_GATEWAY_version_no="";
	public String Appsec="";	
	/**
	 * 邮掌柜加的参数
	 */
	public String vpsService_villageNo="";
	public String vpsService_usrOnlyid="";
	/**
	 * 邮掌柜加的参数
	 */
	public String token="";	
	/**
	 * 邮掌柜加密登录返回
	 */
	public String header_token="";
	
	
	
	@Deprecated
	public AppInfo(String version_code,String version_name,String session_id,
			String uuid,String app_key,String market){
		this.versionCode = version_code;
		this.versionName = version_name;
		this.SessionID = session_id;
		this.UUID = uuid;
		this.Appkey = app_key;
		this.android_market = market;
	}
	
	public AppInfo(String version_code,String version_name,String session_id,
			String uuid,String app_key,String app_sec,String market){
		this.versionCode = version_code;
		this.versionName = version_name;
		this.SessionID = session_id;
		this.UUID = uuid;
		this.Appkey = app_key;
		this.Appsec = app_sec;
		this.android_market = market;
	}
	public AppInfo(String version_code,String version_name,String session_id,
			String uuid,String app_key,String app_sec,String market,
			String vpsService_villageNo){
		this.versionCode = version_code;
		this.versionName = version_name;
		this.SessionID = session_id;
		this.UUID = uuid;
		this.Appkey = app_key;
		this.Appsec = app_sec;
		this.android_market = market;
		this.vpsService_villageNo = vpsService_villageNo;
	}
	public AppInfo(String version_code,String version_name,String session_id,
			String uuid,String app_key,String app_sec,String market,
			String vpsService_villageNo,String vpsService_usrOnlyid){
		this.versionCode = version_code;
		this.versionName = version_name;
		this.SessionID = session_id;
		this.UUID = uuid;
		this.Appkey = app_key;
		this.Appsec = app_sec;
		this.android_market = market;
		this.vpsService_villageNo = vpsService_villageNo;
		this.vpsService_usrOnlyid = vpsService_usrOnlyid;
	}
}
