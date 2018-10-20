package kr.yuhan.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;

import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanNoticeVO;
import kr.yuhan.service.YuhanMemberCheckService;

@Controller
public class YuhanMainController 
{
	
	@Inject
	private YuhanMemberCheckService service;
	
	@RequestMapping(value = "/header", method=RequestMethod.GET)
	public void header(HttpServletRequest request, Model model, HttpSession session)
	{	
		if(session.getAttribute("sessionID") != null)
		{
			model.addAttribute("sessionCheck", "LoginOk");
		}
		else
		{
			try 
			{
				Connection.Response loginPageResponse = Jsoup.connect("https://portal.yuhan.ac.kr/user/login.face")
				        .timeout(3000)
				        .header("Origin", "https://portal.yuhan.ac.kr")
				        .header("Referer", "https://portal.yuhan.ac.kr/user/login.face")
				        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				        .header("Content-Type", "application/x-www-form-urlencoded")
				        .header("Accept-Encoding", "gzip, deflate, br")
				        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				        .method(Connection.Method.GET)
				        .execute();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
		
	
	
	public Map<String, String> getDivCookiee()
	{
		
		Map<String, String> loginTryCookie = new HashMap<String, String>();
		try 
		{
			Connection.Response loginPageResponse = Jsoup.connect("https://portal.yuhan.ac.kr/user/loginProcess.face")
			        .timeout(3000)
			        .header("Origin", "https://portal.yuhan.ac.kr")
			        .header("Referer", "https://portal.yuhan.ac.kr/user/login.face")
			        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
			        .header("Content-Type", "application/x-www-form-urlencoded")
			        .header("Accept-Encoding", "gzip, deflate, br")
			        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
			        .method(Connection.Method.GET)
			        .execute();
			
			/*Document loginPageDocument = loginPageResponse.parse();
			System.out.println("<--------------------------->" + loginPageDocument + "\n<----------------------------->");*/
			loginTryCookie = loginPageResponse.cookies();
			
			System.out.println("loginTryCookie------------: " + loginTryCookie);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginTryCookie;
	}
	
	
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public void loginGet()
	{
		
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String headerPost(@RequestParam("id") String id, @RequestParam("password") String password, Model model, HttpServletResponse cookieResponse, YuhanMemberVO vo, HttpSession session)
	{
		vo.setMemberID(id);
		vo.setMemberPW(password);

		if(service.selectIDPW(vo) == 0)
		{
			System.out.println("id : " + id);
			System.out.println("pw : " + password);
			
			model.addAttribute("IDPWCheck", "No");
			
			if(service.selectMember(id) == null)
			{
				Map<String, String> loginTryCookie = getDivCookiee();
				Map<String, String> loginCookie = new HashMap<String, String>();

				String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
				
				Map<String, String> data = new HashMap<>();
				data.put("userId", id);
				data.put("password", password);
				
				TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() 
				{
					@Override
					public X509Certificate[] getAcceptedIssuers() 
					{
						// TODO Auto-generated method stub
						return new X509Certificate[0];
					}
					
					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException 
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException 
					{
						// TODO Auto-generated method stub
						
					}
				}};

		        SSLContext sc;
				try 
				{
					sc = SSLContext.getInstance("TLS");
					sc.init(null, trustAllCerts, new SecureRandom());
					
					HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				} 
				catch (NoSuchAlgorithmException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (KeyManagementException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Connection.Response response;
				
				try 
				{
					response = Jsoup.connect("https://portal.yuhan.ac.kr/user/loginProcess.face")
					        .userAgent(userAgent)
					        .timeout(3000)
					        .header("Origin", "https://portal.yuhan.ac.kr")
					        .header("Referer", "https://portal.yuhan.ac.kr/user/login.face")
					        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					        .header("Content-Type", "application/x-www-form-urlencoded")
					        .header("Accept-Encoding", "gzip, deflate, br")
					        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					        .cookies(loginTryCookie)
					        .data(data)
					        .method(Connection.Method.POST)
					        .execute();
					
					loginCookie = response.cookies();
					
					System.out.println("Login Check " + loginCookie);
					
					if(loginCookie.get("EnviewSessionId") == null)
					{
						System.out.println("로그인 틀림");
						
						model.addAttribute("loginCheck", "Fail");
						
						return "/header";
					}
					else
					{
						System.out.println("로그인 성공");
						model.addAttribute("id", id);
						
						/* ================= 로그인 성공후 쿠키 생성 ==================== */
						Iterator<String>iter = loginCookie.keySet().iterator();
						while(iter.hasNext())
						{
							String keys = (String)iter.next();
							String keyVal = loginCookie.get(keys).toString();
							switch (keys) 
							{
								case "EnviewSessionId":
									CookieGenerator EnviewSessionId = new CookieGenerator();
									EnviewSessionId.setCookieName("EnviewSessionId");
									EnviewSessionId.addCookie(cookieResponse, keyVal);
									//System.out.println("EnviewSessionId Cookie OK");
									break;
								case "EnviewLangKnd":
									CookieGenerator EnviewLangKnd = new CookieGenerator();
									EnviewLangKnd.setCookieName("EnviewLangKnd");
									EnviewLangKnd.addCookie(cookieResponse, keyVal);
									//System.out.println("EnviewLangKnd Cookie OK");
									break;
								case "JSESSIONID":
									CookieGenerator JSESSIONID = new CookieGenerator();
									JSESSIONID.setCookieName("JSESSIONID");
									JSESSIONID.addCookie(cookieResponse, keyVal);
									//System.out.println("JSESSIONID Cookie OK");
									break;
								case "ENPASSTGC":
									CookieGenerator ENPASSTGC = new CookieGenerator();
									ENPASSTGC.setCookieName("ENPASSTGC");
									ENPASSTGC.addCookie(cookieResponse, keyVal);
									//System.out.println("ENPASSTGC Cookie OK");
									break;
								default:
									break;
							}
							System.out.println("키값 = " + keys);
							System.out.println("밸류값 = " + loginCookie.get(keys));
						}
						
						/* ============================================================= */
						model.addAttribute("loginCheck", "Success");

						if(service.selectMember(id) != null)
						{
							model.addAttribute("joinCheck", "joinOk");
							
							vo.setMemberID(id);
							vo.setMemberPW(password);
						}
						else
						{
							model.addAttribute("joinCheck", "joinNo");
						}
					
					}
				} 
				catch (IOException e) 
				{
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			model.addAttribute("IDPWCheck", "Ok");
			
			System.out.println("id: " + vo.getMemberID());
			session.setAttribute("sessionID", vo.getMemberID());
			session.setAttribute("sessionHak", vo.getMemberHak());
		}
			return "/login";
	}
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public void main(HttpServletRequest request, Model model, HttpSession session)
	{
		/***************** 로그인 세션 설정 ******************/
		if(session.getAttribute("sessionID") == null)
		{
			model.addAttribute("model_SessionID", "No");
		}
		else
		{
			model.addAttribute("loginMemberList", service.select_Member(session.getAttribute("sessionID").toString()));
			model.addAttribute("memberClass", service.selectMemberClass(session.getAttribute("sessionID").toString()));
			session.setAttribute("memberClass", service.selectMemberClass(session.getAttribute("sessionID").toString())); // 학생의 반을 저장해둔다.
			
			System.out.println(session.getAttribute("sessionID"));
			System.out.println("memberClass : " + service.selectMemberClass(session.getAttribute("sessionID").toString()));
	
		}
		/************************************************* 2018.10.09 이진주 */
		
		String url = "https://cse.yuhan.ac.kr/?page_id=871"; //문서 객체
		ArrayList<YuhanNoticeVO> list = new ArrayList<YuhanNoticeVO>();
		Document doc=null;
		try 
		{
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("#content .cut_strings a");
		Elements date = doc.select("#content table tbody td.kboard-latest-date");
		
		
		//System.out.println(date.get(0));
		//System.out.println("size + :" + date.size());
		//System.out.println("text : " + newsHeadlines.text());
		//System.out.println("date : " + date.text());
		
		
		for (int i = 0; i <= 4; i++) 
		{
			YuhanNoticeVO vo =new YuhanNoticeVO();
			
			Element element = newsHeadlines.get(i);
			Element elementDate = date.get(i);
			
			
			
			String urlSub[] = element.attr("href").split("/");
			
			vo.setSubject(element.text());
			vo.setUrl("notice" + urlSub[1]);
			vo.setDate(elementDate.text());
			
			list.add(vo);
			
			
			System.out.println("title : " + list.get(i).getSubject());
			System.out.println("url : " + urlSub[1]);
			System.out.println("date : " + list.get(i).getDate());
		}
		
		
		
		model.addAttribute("list", list);
		//request.setAttribute("list", list);
		
		/*for(Element middleline : date)
		{
			System.out.println(middleline.text());
			
			
		}
		
		
		
		model.addAttribute("list", list);*/
		
		//return "main";
		
		/* ============== 로그인 회원 이름 출력 =============== */
		/*
			////////////////////////////////////////////////////
			Map<String, String> loginCookie = new HashMap<String, String>();
			
			loginCookie.put("EnviewSessionId", (String) request.getAttribute("EnviewSessionId"));
			
			Cookie[] loginCookieArr = request.getCookies();
			System.out.println(loginCookieArr.length);
			for(int i = 0; i>loginCookieArr.length; i++)
			{
				System.out.println("zzzzzzzzzzzzzzzzzz" + loginCookieArr[i].getName());
			}
			
			System.out.println(loginCookie);
			///////////////////////////////////////////////////
		
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
		
		try 
		{
			Document loginNameDocument = Jsoup.connect("https://portal.yuhan.ac.kr/portal/yuhan/mainpage/stu")
			        .userAgent(userAgent)
			        .header("Referer", "https://portal.yuhan.ac.kr")
			        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*;q=0.8")
			        .header("Content-Type", "application/x-www-form-urlencoded")
			        .header("Accept-Encoding", "gzip, deflate, br")
			        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
			        .cookies(loginCookie)
			        .get();
			
			//Elements loginName = loginNameDocument.select("#container");
			//System.out.println("oooooooooooooooo : " + loginName.get(0));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ================================================ 
	*/
		
	
	}
	
	@RequestMapping(value = "/notice", method=RequestMethod.GET)
	public void notice(/*@RequestParam("uid") String uid*/)
	{
		String urlNotice = "https://cse.yuhan.ac.kr/";
		String urlrNotice = "https://cse.yuhan.ac.kr/?uid=63936&mod=document&page_id=871";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";										
		System.out.println(urlNotice);
		
		ArrayList<YuhanNoticeVO> listNotice = new ArrayList<YuhanNoticeVO>();
		Document doc=null;
		try 
		{
			
			
			Map<String, String> map =new HashMap<String, String>();
			
			
			
			Response res=Jsoup.connect(urlNotice).userAgent(userAgent).
													method(Connection.Method.GET).
													execute();
			
			
			Map<String,String> cookieMap=res.cookies();
			
			cookieMap = res.cookies();
			map.put("PHPSESSID", cookieMap.get("PHPSESSID"));
			map.put("JSESSIONID", cookieMap.get("JSESSIONID"));
			map.put("logincheck", cookieMap.get("logincheck"));
			map.put("urlToRedirectTo", urlrNotice);
			
			System.out.println("@@@@map "+map);



	
			
			System.out.println(cookieMap);
			 Connection con=Jsoup.connect(urlrNotice).userAgent(userAgent).cookies(map);
			
			
			doc=con.get();
			System.out.println(doc);
			if(1==1) {
				return;
			}
			doc = Jsoup.connect(urlNotice).get();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc.title());
		Elements title = doc.select("#main");
		
		System.out.println(title.get(0));
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list()
	{
		return "/list";
	}
	
	@RequestMapping(value = "/homeMain", method=RequestMethod.GET)
	public String homeMain()
	{
		return "/homeMain";
	}
	
	@RequestMapping(value = "/data", method=RequestMethod.GET)
	public String data()
	{
		return "/datalist";
	}
	
	@RequestMapping(value = "/mypage", method=RequestMethod.GET)
	public void mypage()
	{
		
	}
	
	@RequestMapping(value = "/homenotice", method=RequestMethod.GET)
	public void homenotice()
	{
		
	}
	
	@RequestMapping(value = "/homeNoticeRead", method=RequestMethod.GET)
	public String homeNoticeRead()
	{
		return "/homenoticeread";
	}
	
	@RequestMapping(value = "/homeworkList", method=RequestMethod.GET)
	public String homeworkList()
	{
		return "/homeworklist";
	}
	
	@RequestMapping(value = "/homework", method=RequestMethod.GET)
	public void homework()
	{
		
	}
	
	@RequestMapping(value = "/joinType", method=RequestMethod.GET)
	public void joinType()
	{
		
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public void join()
	{
		
	}
	
	@RequestMapping(value = "/dataRead", method=RequestMethod.GET)
	public void dataRead()
	{
		
	}
	
	@RequestMapping(value = "/noticeMore", method=RequestMethod.GET)
	public void noticeMore(Model model)
	{
		String url = "https://cse.yuhan.ac.kr/?pageid=2&page_id=871"; //문서 객체
		ArrayList<YuhanNoticeVO> list = new ArrayList<YuhanNoticeVO>();
		Document doc=null;
		try 
		{
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("#content td.kboard-latest-title a");
		Elements date = doc.select("#content table tbody td.kboard-latest-date");
		
		System.out.println(newsHeadlines.get(0));
		System.out.println("=================================================");
		/*for (int i = 0; i <= 23; i++) 
		{
			System.out.println("i : " + i);
			YuhanNoticeVO vo =new YuhanNoticeVO();
			
			Element element = newsHeadlines.get(i);
			Element elementDate = date.get(i);
			
			vo.setSubject(element.text());
			vo.setUrl(url+ element.attr("href"));
			vo.setDate(elementDate.text());
			
			list.add(vo);
			
			System.out.println("title : " + list.get(i).getSubject());
			System.out.println("url : " + list.get(i).getUrl());
			System.out.println("date : " + list.get(i).getDate());
		}
		
		
		
		model.addAttribute("list", list);*/
		//request.setAttribute("list", list);
		
		for(Element middleline : newsHeadlines)
		{
			System.out.println(newsHeadlines.text());
		}
		
		
		
		//model.addAttribute("list", list);*/
	}
	
	@RequestMapping(value = "/studentJoin", method=RequestMethod.GET)
	public void studentJoin(@RequestParam("id") String id, Model model)
	{
		model.addAttribute("id", id);
	}
	
	@RequestMapping(value = "/studentJoin", method=RequestMethod.POST)
	public String studentJoin(YuhanMemberVO vo, Model model, HttpSession session)
	{
		service.insertMember(vo);
		
		model.addAttribute("joinCheck", "Success");

		/* ==================== 로그인 세션 부분 ====================== */
		
		session.setAttribute("memberID", vo.getMemberID());
		//학생 학번도 필요 - 조은숙
		/* ======================================================== */
		
		return "/studentJoin";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model)
	{
		/***************** 로그아웃 ******************/
		if(session.getAttribute("sessionID") == null)
		{
			model.addAttribute("model_SessionID", "No");
		}
		else
		{
			session.invalidate();
			
			return "/header";
		}
		
		return "/logout";
		/************************************************* 2018.10.10 이진주 */
	}
}
