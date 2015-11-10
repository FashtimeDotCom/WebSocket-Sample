package com.shengbo.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

@ServerEndpoint(value = "/websocket")
public class WebSocketTest {

	private static Map<String,Session> sessionMap = new HashMap<String,Session>();
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		JSONObject json = JSONObject.fromObject(message);
		if(!(json.getString("account")).equals("Guest"+session.getId())) {
			if(json.getInt("isFirstLogin")==1) {//登录
				sessionMap.remove("Guest"+session.getId());
				sessionMap.put(json.getString("account"), session);
			}
			if(json.getInt("isFirstLogin")==3) {//注销
				sessionMap.remove(json.getString("account"), session);
				sessionMap.put("Guest"+session.getId(),session);
			}
		}
		sendAll(json.getString("account")+":"+json.getString("data"));
		//'{"account":"'+data.account+'","data":"用户'+data.account+'加入群聊","isFirstLogin":1}'
	}

	@OnOpen
	public void onOpen(Session session) {
		sessionMap.put("Guest"+session.getId(), session);
	}

	/**
	 * 发送消息给所有session
	 * @param message
	 */
	private void sendAll(String message) {
		Session session = null;
		for (String key : sessionMap.keySet()) {
			session = sessionMap.get(key);
			try {
				if(session.isOpen()) {
					session.getBasicRemote().sendText(message);
				} else {
					sessionMap.remove(session);
				}
			} catch (IOException e) {
				System.out.println("++++++"+e.getMessage());
				continue;
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("============");
	}
}