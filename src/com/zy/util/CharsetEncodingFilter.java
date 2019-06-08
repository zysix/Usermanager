package com.zy.util;
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/CharsetEncodingFilter")
public class CharsetEncodingFilter implements Filter {  
	protected String encoding=null;
	protected FilterConfig filterConfig=null;
	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(null!=encoding) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charser="+encoding);
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");
	}
}
