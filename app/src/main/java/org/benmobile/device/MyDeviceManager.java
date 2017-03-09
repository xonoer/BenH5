package org.benmobile.device;

import java.io.File;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import org.benmobile.R;
import org.benmobile.config.Configs;
import org.benmobile.log.BenH5Log;
import org.benmobile.utils.UtilTools;


public class MyDeviceManager {
	public MyAndroiddevice deviceInfo = null;

	public void init(Context conext) {
		TelephonyManager svr = (TelephonyManager) conext
				.getSystemService(Context.TELEPHONY_SERVICE);
		deviceInfo = new MyAndroiddevice();
		if (svr != null) {
			deviceInfo.setLine1num(svr.getLine1Number());
			deviceInfo.setImei(svr.getDeviceId());
			deviceInfo.setImsi(svr.getSubscriberId());
			deviceInfo.setNet(deviceInfo.net(svr.getNetworkType()));
		}
		deviceInfo.setAndroid_market(conext.getString(R.string.marketId));
		WindowManager wm = (WindowManager) conext
				.getSystemService(Context.WINDOW_SERVICE);
		deviceInfo
				.setDisplayDensity(conext.getResources().getDisplayMetrics().densityDpi);
		if (wm != null) {
			Display dm = wm.getDefaultDisplay();
			deviceInfo.setDisplayWidth(dm.getWidth());
			deviceInfo.setDisplayHeight(dm.getHeight());
			deviceInfo.setRefrashRatio(dm.getRefreshRate());
			DisplayMetrics dmt = new DisplayMetrics();
			dm.getMetrics(dmt);
			deviceInfo.setDensity(dmt.density);

		}
		deviceInfo.setOS_Product(android.os.Build.PRODUCT);
		deviceInfo.setOS_CPU_ABI(android.os.Build.CPU_ABI);
		deviceInfo.setOS_TAGS(android.os.Build.TAGS);
		deviceInfo
				.setOS_VERSION_CODES_BASE(android.os.Build.VERSION_CODES.BASE);
		deviceInfo.setOS_MODEL(android.os.Build.MODEL);
		deviceInfo.setOS_SDK(android.os.Build.VERSION.SDK);
		deviceInfo.setOS_SDK_INT(android.os.Build.VERSION.SDK_INT);
		deviceInfo.setOS_VERSION_RELEASE(android.os.Build.VERSION.RELEASE);
		deviceInfo.setOS_DEVICE(android.os.Build.DEVICE);
		deviceInfo.setOS_DISPLAY(android.os.Build.DISPLAY);
		deviceInfo.setOS_BRAND(android.os.Build.BRAND);
		deviceInfo.setOS_BOARD(android.os.Build.BOARD);
		deviceInfo.setOS_FINGERPRINT(android.os.Build.FINGERPRINT);
		deviceInfo.setOS_ID(android.os.Build.ID);
		deviceInfo.setOS_MANUFACTURER(android.os.Build.MANUFACTURER);
		deviceInfo.setOS_USER(android.os.Build.USER);
		try {
			ConnectivityManager cm = (ConnectivityManager) conext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null) {
				deviceInfo.setNetworktype(info.getType()); // WIFI/MOBILE
				deviceInfo.setNetType(info.getTypeName());
				if (deviceInfo.getNetType().equals("mobile")) {
					deviceInfo.setNetType(deviceInfo.net);
				}
				if (deviceInfo.isMobileNet())
					deviceInfo.setExtranetworkinfo(info.getExtraInfo()
							.toLowerCase());
			}
		} catch (Exception e) {

		}
		WifiManager wifiManager = (WifiManager) conext
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		deviceInfo.setMacAddress(wifiInfo.getMacAddress());
		deviceInfo.setAndroidID(Secure.getString(conext.getContentResolver(),
				Secure.ANDROID_ID));
		refrashstoragestatic(conext);
	}

	public void refrashstoragestatic(Context conext) {
		double blocksize = 0;
		double bloccount = 0;
		deviceInfo.setSharedState(android.os.Environment
				.getExternalStorageState().equals(Environment.MEDIA_SHARED));
		deviceInfo.setSdPresent(android.os.Environment
				.getExternalStorageState().equals(
						android.os.Environment.MEDIA_MOUNTED));
		if (deviceInfo.isSdPresent()) {
			// String sd="/mnt/sd";
			deviceInfo.setExternalStorageDirectory(Environment
					.getExternalStorageDirectory());
			if (deviceInfo.getExternalStorageDirectory() != null) {
				StatFs sf = new StatFs(
						deviceInfo.ExternalStorageDirectory.getPath());
				blocksize = sf.getBlockSize();
				bloccount = sf.getAvailableBlocks();
				BenH5Log.debug(MyDeviceManager.class.toString(),
						String.valueOf(blocksize));
				BenH5Log.debug(MyDeviceManager.class.toString(),
						String.valueOf(bloccount));
				deviceInfo.setAvailableBlocksInSD(blocksize * bloccount / 1024
						/ 1024);
				BenH5Log.debug(MyDeviceManager.class.toString(),
						String.valueOf(deviceInfo.getAvailableBlocksInSD()));
			}
		}
		deviceInfo.setDataDirectory(Environment.getDataDirectory());
		if (null != deviceInfo.getDataDirectory()) {
			StatFs sf = new StatFs(deviceInfo.DataDirectory.getPath());
			blocksize = sf.getBlockSize();
			bloccount = sf.getAvailableBlocks();
			BenH5Log.debug(MyDeviceManager.class.toString(),
					String.valueOf(blocksize));
			deviceInfo.setAvailableBlocksInApp(bloccount * blocksize / 1024
					/ 1024);
		}
		deviceInfo.setRootDirectory(Environment.getRootDirectory());
		if (deviceInfo.getRootDirectory() != null) {
			StatFs sf = new StatFs(deviceInfo.RootDirectory.getPath());
			blocksize = sf.getBlockSize();
			bloccount = sf.getAvailableBlocks();
			BenH5Log.debug(MyDeviceManager.class.toString(),
					String.valueOf(blocksize));
			deviceInfo.setAvailableBlocksInRoot(blocksize * bloccount / 1024
					/ 1024);
		}
		checkcachepath(conext);
	}

	private void checkcachepath(Context conext) {
		if (deviceInfo.isSdPresent()) {
			File temp = deviceInfo.getExternalStorageDirectory();
			File f = new File(temp.getPath() + Configs.BenH5_CACHE_PATH);
			File f2 = new File(temp.getPath() + Configs.BenH5_DOWNLOAD_PATH);
			deviceInfo.setExternalcachepath(f, f2);
		}
		File temp = conext.getCacheDir();
		File f = new File(temp.getPath() + "/download");
		deviceInfo.setInternalcachepath(f);
	}

	@SuppressWarnings("unused")
	private void modifyp() {
		// [文件夹705:drwx---r-x]
		String[] args1 = { "chmod", "705",
				deviceInfo.internaldownload.getPath() };
		UtilTools.exec(args1);
	}
}