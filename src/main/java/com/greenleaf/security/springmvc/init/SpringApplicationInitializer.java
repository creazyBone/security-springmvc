package com.greenleaf.security.springmvc.init;

import com.greenleaf.security.springmvc.config.ApplicationConfig;
import com.greenleaf.security.springmvc.config.WebConfig;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    //springmvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * / 和 /* 都是正确的，谁无聊说是错误的，不同的框架使用不同的拦截路径而已。
     < url-pattern > / </ url-pattern > 不会匹配到*.jsp，即：*.jsp不会进入spring的 DispatcherServlet类 。
     < url-pattern > /* </ url-pattern > 会匹配*.jsp，会出现返回jsp视图时再次进入spring的DispatcherServlet 类，导致找不到对应的controller所以报404错。
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
