package com.example.xiyougame;

import org.json.JSONObject;

import com.appflood.AFBannerView;
import com.appflood.AppFlood;
import com.appflood.AppFlood.AFRequestDelegate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class AdDemoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化
		AppFlood.initialize(this, "应用key", "秘钥", AppFlood.AD_ALL); //最后个参数决定广告的显示类型
		
		/*“AppFlood.AD_NONE”(only for advertiser applications with no publishing) 相当于测试
		“AppFlood.AD_PANEL”(More Games Panel ads only)               //更多用于游戏的面板广告
		“AppFlood.AD_FULLSCREEN”(Fullscreen interstitial ads only)   //全屏广告
		“AppFlood.AD_LIST”(List View ads only)                      //列表广告（类似积分墙）
		“AppFlood.AD_DATA”(Customized ad format use only)           //定做广告
		“AppFlood.AD_NOTIFICATION”(notification ad format only)     //推送广告
		“AppFlood.AD_ICON”(icon ad format only)                     //只有图片的广告
		“AppFlood.AD_INTERSTITIAL”(new interstitial ad)             //全屏且有较强的3d效果广告
		“AppFlood.AD_ALL”(for all ad formats)*/                     //所有广告均支持
		AppFlood.showFullScreen(this); //普通全屏广告(竖版下：左右填充整个手机屏幕)（横版下：上下左右均留有空隙）\
		AppFlood.showPanel(this, AppFlood.PANEL_TOP); //面板广告(非常适用于游戏)。第2个参数决定面板从顶部/底部弹出
		//AppFlood.PANEL_TOP 和 AppFlood.PANEL_BOTTOM
		AppFlood.showList(this,AppFlood.LIST_ALL); // 列表广告 ，第2个参数5个值
		// LIST_GAME(只显示游戏广告), LIST_APP(只显示应用), LIST_ALL, LIST_TAB_GAME(可以在游戏应用列表之间切换) and LIST_TAB_APP.
		
		//AppFlood.handleAFClick(this, backUrl, clickUrl); 返回时的url,back的url
		AppFlood.destroy(); //退出程序时调用
		AppFlood.showInterstitial(this); //全屏且3D效果，效果比较给力
		AppFlood.isConnected(); //检查广告sdk是否连接
		
		//预加载
		AppFlood.preload(AppFlood.AD_FULLSCREEN | AppFlood.AD_ALL, new AFRequestDelegate() {
			@Override
			public void onFinish(JSONObject arg0) {//完成时的回调
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(AdDemoActivity.this, "preload finished", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		//AppFlood.queryAFPoint(requestDelegate);
		//AppFlood.consumeAFPoint(1, requestDelegate);
		//获取广告元数据--用于自定义广告
		AppFlood.getADData(new AFRequestDelegate() {
			@Override
			public void onFinish(JSONObject arg0) {
				Log.v("ADEMO", arg0.toString());
			}
		});
	}
	
	//banner广告
	public void showBanner(AFBannerView afBannerView, Activity context, int type) {
		//banner广告的容器rl_container
		RelativeLayout rl_container=new RelativeLayout(this); 
		afBannerView = new AFBannerView(this, type, true);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		if (type == AppFlood.BANNER_MIDDLE) {
			params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		} else {
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		}

		rl_container.addView(afBannerView, params);
	}
	
	
}
