package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class SupplyAPI
 */
@WebServlet("/SupplyAPI")
public class SupplyAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Supply SupplyObj = new Supply();



    /**
     * Default constructor. 
     */
    public SupplyAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = SupplyObj.insertItem(request.getParameter("SupID"),
				request.getParameter("itemName"),
				request.getParameter("qty"),
				request.getParameter("price"),
				request.getParameter("total"),
				request.getParameter("date"));
				
		response.getWriter().write(output);


		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<?, ?> paras = getParasMap(request);
		String output = SupplyObj.updateItem(paras.get("hidSupplyIDSave").toString(),
				paras.get("SupID").toString(),
				paras.get("itemName").toString(),
				paras.get("qty").toString(),
				paras.get("price").toString(),
				paras.get("total").toString(),
				paras.get("date").toString());
				response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<?, ?> paras = getParasMap(request);
		String output = SupplyObj.deleteItem(paras.get("supID").toString());
		
		response.getWriter().write(output);
	} 
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{

	String[] p = param.split("=");
	map.put(p[0], p[1]);
	}
	}
	catch (Exception e)
	{
	}
	return map;
	}



	

}
