package com.ande.temp;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TempServlet")
public class TempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response) ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("utf-8"); 
		
		//================================================查询mysql数据库，获得数据信息
		String sql = "select * from temp " ;
		List<TempBean> querySql = MysqlUtil.querySql(sql);
		
		//================================================封装数据
		//echarts对象
		Echarts es = new Echarts() ;
		//x轴数据
		List<String> xdata = new ArrayList<String>() ;
		//ser数据
		List<Float> sdata = new ArrayList<Float>() ;
		for(TempBean tb : querySql){
			xdata.add(tb.getId()+"") ;
			sdata.add(Float.valueOf(tb.getHight())/10) ;
		}
		es.setCategory(xdata);
		es.setSeriesData(sdata); 
		
		//=================================================封装json数据格式，进行返回
		response.setCharacterEncoding("utf-8");
		//获取json集合的字符串
		JSONArray json = JSONArray.fromObject(es) ;
		System.out.println(json.toString());
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush(); 
		out.close();
	}

}
