package com.jooyeon.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class KaKaoLogin
 */
@WebServlet("/kokoLogin")
public class kokoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kokoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("111111111111111111");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		/////////////////////////////////////////////////////////////////////////////
		/*
		POST /oauth/token HTTP/1.1
		Host: kauth.kakao.com
		-----
		grant_type	String	"authorization_code"로 고정	O
		client_id	String	앱 생성 시 발급 받은 REST API	O
		redirect_uri	String	인증 코드가 리다이렉트된 URI	O
		code	String	인증 코드 받기 요청으로 얻은 인증 코드	O
		------
		Content-type: application/x-www-form-urlencoded;charset=utf-8
		 */
		//http://localhost:8080/jyproject/kokoLogin?code=xkmpzeupxoCyRjz4KWv5y-Bev_fxG5pgiunIo4V24Cbjya4_nmZL6RaKfo2pqls4nZjDPQo9c04AAAFz1fP9_Q
		//http://localhost:8080/jsp_ajax/kokoLogin?code=Dgr7LiDkFoVobctlXsp8bpNykvPrgNVpXgII95PQoNIH8QFquPggTXtPDVco6quY-SjrEAo9dVwAAAFz1hk2Kw
		String client_id="a5c4e80d1a39794cebbf754bcb5092d0";
//		String redirect_uri ="http://localhost:8080/JYProject/kokoLogin";
		String redirect_uri ="http://joooo1234.cafe24.com/jyproject/kokoLogin";
		String url ="https://kauth.kakao.com/oauth/token?";
		String code =request.getParameter("code");  System.out.println(code);
		
		url+="grant_type=authorization_code&";
		url+="client_id="+client_id+"&";
		url+="redirect_uri="+redirect_uri +"&";
		url+="code="+code;
		
		////////////////////////////////////////////////////////////////////////////
		URL kakaourl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)kakaourl.openConnection();
		conn.setDoInput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
		///////////////////////////////////////////////////////////////////////
		BufferedReader br = null;
		String line = null;
		StringBuffer sb = new StringBuffer();
		if(conn.getResponseCode()==200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else { br = new BufferedReader(new InputStreamReader(conn.getErrorStream())); }
		
		while( (line=br.readLine()) !=null  ) { sb.append(line); }//end while
		System.out.println(sb.toString());

		
		//1. json 파일에서 access_token 추출하기(1)
		JsonParser jsonparser = new JsonParser();
		JsonObject result = (JsonObject) jsonparser.parse(sb.toString());
		String access_token = result.get("access_token").getAsString();
 		System.out.println("access_token : " + access_token);
		///////////////////////////////////////////////////////////// 사용자 정보요청(2)
		//2. https://developers.kakao.com/docs/latest/ko/user-mgmt/rest-api
		
		/*
		GET/POST /v2/user/me HTTP/1.1
		Host: kapi.kakao.com
		Authorization: Bearer {access_token}
		Content-type: application/x-www-form-urlencoded;charset=utf-8
		/
		Content-type: application/x-www-form-urlencoded;charset=utf-8
		
		https://kapi.kakao.com/v2/user/me 
		//
		 Authorization	헤더 포맷
		Authorization: Bearer ${access_token}	O
		 */

 		/*
 	target_id_type	사용자 ID 타입, "user_id"로 고정	O
		target_id	회원번호	                    O
 		 
 		 */
		String url2 ="https://kapi.kakao.com/v2/user/me";
		//////////////////////////////////////////////////////////
		//GET
		kakaourl = new URL(url2);
		conn = (HttpURLConnection) kakaourl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer "+access_token);
		conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
		
		/*
		 property_keys
		Name	Description
		properties.nickname	서비스에서 쓰이는 사용자 닉네임
		기본 값은 앱연결시의 카카오계정 닉네임
		properties.profile_image	서비스에서 쓰이는 사용자 프로필 이미지 URL
		기본 값은 앱연결시의 카카오계정 프로필 이미지 URL(640* 640)
		properties.thumbnail_image	서비스에서 쓰이는 사용자 썸네일 이미지 URL
		기본 값은 앱 연결 시의 카카오계정 썸네일 프로필 이미지 URL(110* 110)
		kakao_account.profile	카카오계정의 프로필 소유 여부
		실시간 닉네임과 프로필 이미지 URL
		kakao_account.email	카카오계정의 이메일 소유 여부
		이메일 값, 이메일 인증 여부, 이메일 유효 여부
		kakao_account.age_range	카카오계정의 연령대 소유 여부, 연령대 값
		kakao_account.birthday	카카오계정의 생일 소유 여부, 생일 값
		kakao_account.gender	카카오계정의 성별 소유 여부, 성별 값
		 */
		//////////////////////////////////////////////////////////
		br = null; line = null;
		sb = new StringBuffer();
		if(conn.getResponseCode() ==200 ) { 
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));}
		else { br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));}
		
		while( (line=br.readLine())!=null ) { sb.append(line);}
		System.out.println(sb.toString());
		
		
		//3. 사용자 view페이지에서 확인 - 프로필 파싱 
		//properties":{"nickname":"주연","profile_image":"http://k.kakaocdn.net/dn/bA1CZA/btqEFFDA9WI/1eqCCbDaWUjz3iw5AqyvM1/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/bA1CZA/btqEFFDA9WI/1eqCCbDaWUjz3iw5AqyvM1/img_110x110.jpg"},"kakao_account":{"profile_needs_agreement":false,"profile":{"nickname":"주연","thumbna
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject)jsonParser.parse(sb.toString());
		JsonObject profile = (JsonObject) jsonObject.get("properties");
		String nickname = profile.get("nickname").getAsString();
		String profile_image = profile.get("profile_image").getAsString();
		String id = jsonObject.get("id").getAsString();
		
// 		System.out.println("id : " + id);	
//		System.out.println("nickname : " + nickname);
//		System.out.println("profile_image : " + profile_image);
		
		request.setAttribute("id", id);
		request.setAttribute("nickname",nickname);
		request.setAttribute("profile_image",profile_image);
		request.getRequestDispatcher("/idCheck.members").forward(request, response);
	
	        
		br.close(); conn.disconnect();
		///////////////////

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
