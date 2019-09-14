package com.al.nppm.common.interceptor;

import com.al.nppm.common.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class HeaderInterceptor implements HandlerInterceptor {
    private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config/appConfig");

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception {
        String appId=request.getHeader("appId");
        String appKey=request.getHeader("appKey");

        String cfgappId="";
        String cfgappKey="";
        if(bundle.containsKey("appId")){
            cfgappId=bundle.getString("appId");
        }
        if(bundle.containsKey("appKey")){
            cfgappKey=bundle.getString("appKey");
        }
        if(!StringUtil.isEmpty(cfgappId)&&!StringUtil.isEmpty(cfgappKey)){
            if(cfgappId.equals(appId)&&cfgappKey.equals(appKey)){
                return true;
            }
        }else{
            return  true;
        }
        response.setContentType("text/plain; charset=utf-8");
        JSONObject json=new JSONObject();
        json.put("status",-1);
        json.put("message","未经授权的访问");
        response.getWriter().write(json.toJSONString());
        return false;
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }
}
