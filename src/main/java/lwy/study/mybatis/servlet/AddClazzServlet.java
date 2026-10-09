package lwy.study.mybatis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lwy.study.mybatis.pojo.Clazz;
import lwy.study.mybatis.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addClazz")
public class AddClazzServlet extends HttpServlet {
    @Autowired
    private IClazzService clazzService;


    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        clazzService = webApplicationContext.getBean(IClazzService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取上传信息
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        String cno = request.getParameter("cno");
        String cname = request.getParameter("cname");
        Integer tid = Integer.parseInt(request.getParameter("tid"));
       // 创建对象
        Clazz clazz = new Clazz(cid,cno,cname,tid);
        clazzService.add(clazz);
        // 提示添加成功，然后自动跳转回班级列表页面
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('添加成功');location.href='"
                + request.getContextPath() + "/queryClazz';</script>");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
