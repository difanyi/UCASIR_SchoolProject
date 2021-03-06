package com.news.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.search.NewsSearcher;
import com.news.search.SearchBean;
import com.news.search.Tuple;

public class SearchResult extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet方法执行");
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
		try {
            String queryStr = new String(request.getParameter("searchtext").getBytes("ISO-8859-1"),"utf-8");
            String model = new String(request.getParameter("model"));
            System.out.println(model);
            System.out.println(queryStr);
            int pageIndex=1;
            if(request.getParameter("pageIndex") != null){
            	pageIndex = Integer.valueOf((String)request.getParameter("pageIndex"));
            }
            NewsSearcher newsSearcher = new NewsSearcher();
            Tuple resultTuple = newsSearcher.search(queryStr, Integer.parseInt(model));
//            Tuple resultTuple = newsSearcher.search(queryStr, 0);
            List<SearchBean> result = resultTuple.getResult();
            double costSecond = resultTuple.getCostSeconds();
            System.out.println("costSecond:"+costSecond);
            String[] rW = resultTuple.getRelateWords(); 
            int totalPage = (resultTuple.getSize()-1)/10+1;
            List<String> relateWords = Arrays.asList(rW);
            if(pageIndex < 1)
            	pageIndex = 1;
            else if (pageIndex > totalPage) {
				pageIndex = totalPage;
			}
            result = result.subList((pageIndex-1)*10, Math.min(resultTuple.getSize(), pageIndex*10));
            
			System.out.println("searchBean.result.size: " + result.size());
			HttpSession session = request.getSession();
			session.setAttribute("results",result);
			session.setAttribute("costSecond",costSecond);
			session.setAttribute("relateWords",relateWords);
			session.setAttribute("model", model);
			session.setAttribute("query", queryStr);
			session.setAttribute("pageIndex", String.valueOf(pageIndex));
			session.setAttribute("totalPage", String.valueOf(totalPage));
			String url="/UcasIR/search_results.jsp";
			response.sendRedirect(url);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理POST请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
