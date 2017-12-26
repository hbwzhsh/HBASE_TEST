package com.ande.temp;

import net.sf.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EChartsDataServelt extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json; charset=utf-8");
        JSONObject jsonObj=new JSONObject();
        //后台数据，这里给定数据就不从数据库获取了
        Map map=new HashMap();
        map.put("category", new String[]{"第一季度","第二季度","第三季度","第四季度"});
        map.put("soft_data", new int[]{80, 300, 200, 100} );
        map.put("net_data", new int[]{50, 200, 120, 200});
        map.put("mobile_data",new int[]{20, 60, 30, 15});
        jsonObj.putAll(map);
        response.getWriter().println(jsonObj.toString());
    }

}
