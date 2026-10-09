package lwy.study.mybatis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lwy.study.mybatis.service.impl.ClazzServiceImpl;
import lwy.study.mybatis.pojo.Clazz;
import lwy.study.mybatis.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/queryClazz")
public class QueryClazzServlet extends HttpServlet {

    @Autowired
    private IClazzService clazzService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        clazzService = webApplicationContext.getBean(IClazzService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");
        int pageNum = 1;
        int pageSize = 5;
        if(pageNumStr != null && !pageNumStr.equals("")){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !pageSizeStr.equals("")){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        List<Clazz> clazzes = clazzService.selectByPage(pageNum, pageSize);
        req.setAttribute("clazz", clazzes);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageSize", pageSize);
        req.getRequestDispatcher("/clazz.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
