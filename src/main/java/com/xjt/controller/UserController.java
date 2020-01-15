package com.xjt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjt.model.*;
import com.xjt.service.IUserService;
import com.xjt.util.GenerateImageUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-25 14:44
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private GenerateImageUtil generateImageUtil;


    @RequestMapping(value = "/login")

    public String UserLogin(HttpServletRequest request) throws Exception {

        return "login";
    }

    @RequestMapping(value = "/logon")

    public String UserLogon(HttpServletRequest request) throws Exception {

        return "logon";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "index";
    }


    @RequestMapping(value = "/home")
    public String ToAbout(HttpServletRequest request) throws Exception {
        return "home";
    }


    @RequestMapping(value = "/portfolio")
    public String ToPortfolio(HttpServletRequest request) throws Exception {
        return "portfolio";
    }

    @RequestMapping(value = "/blog")
    public String ToBlog(HttpServletRequest request) throws Exception {
        return "blog";
    }


    @RequestMapping(value = "/contact")
    public String ToContact(HttpServletRequest request) throws Exception {
        return "contact";
    }


    @RequestMapping(params = "method=VerificationCode")
    @ResponseBody
    public String VerificationCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(true);
        response.setContentType("image/jpeg");
        Map<String, Object> mapTest = generateImageUtil.GenerateVerificationCode();
        session.setAttribute("imageCode", mapTest.get("code"));

        BufferedImage image = (BufferedImage) mapTest.get("image");
//        ImageIO.write(image, "JPEG", response.getOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", baos);
        baos.flush();
        byte[] imageInByteArray = baos.toByteArray();
        baos.close();
        String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
        Map map = new HashMap<String, String>();
        map.put("msg", b64);

        return mapper.writeValueAsString(map);

    }


    @RequestMapping(params = "method=login")
    @ResponseBody
    public String login(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        ObjectMapper mapper = new ObjectMapper();
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//正则匹配邮箱
        String ph = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$";//正则匹配手机号
        String un = "^[A-Za-z0-9_-]{3,16}$";//正则匹配用户名
        String account = request.getParameter("account");
        String loginpasswd = request.getParameter("loginpasswd");
        String code = request.getParameter("code");

        System.out.println(session.getAttribute("imageCode"));


        User user = new User();
        user.setPassword(loginpasswd);
        if (account.matches(em)) {
            user.setEmail(account);
            user.setUsername(null);
            user.setMobile(null);
        } else if (account.matches(ph)) {
            user.setEmail(null);
            user.setUsername(null);
            user.setMobile(account);
        } else if (account.matches(un)) {
            user.setEmail(null);
            user.setUsername(account);
            user.setMobile(null);
        }
        try {
            List<User> result = userService.login(user);
            if (BCrypt.checkpw(loginpasswd, result.get(0).getPassword())) {
                if (session.getAttribute("imageCode").equals(code)) {
                    System.out.println("success!");
                    Map map = new HashMap<String, String>();
                    map.put("msg", "success");
                    session.setAttribute("name", result.get(0).getUsername());
                    session.setAttribute("id", result.get(0).getId());
                    return mapper.writeValueAsString(map);
                } else {
                    return "{\"msg\":\"验证码输入错误\"}";
                }

            }

        } catch (Exception e) {
            return "{\"msg\":\"验证码输入错误\"}";
        }


//        ModelAndView mv = new ModelAndView();
//        User user = userService.selectUser(2);
//        mv.addObject("user", user);
//        mv.setViewName("login");
        return "{\"msg\":\"用户名或密码输入错误\"}";

    }


    @RequestMapping(params = "method=logon")
    @ResponseBody
    public String logon(HttpServletRequest request) throws Exception {
//        Logger log = Logger.getLogger(
//                UserController.class.getName());

        ObjectMapper mapper = new ObjectMapper();
        String mail = request.getParameter("mail");
        String username = request.getParameter("username");
        String phonenumber = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());//BCrypt 加密
        LogonBean userLogon = new LogonBean();
        userLogon.setEmail(mail);
        userLogon.setUsername(username);
        userLogon.setMobile(phonenumber);
        userLogon.setPassword(hashedPassword);
        try {
            if (userService.logon(userLogon) > 0) {
                System.out.println("success!");
                Map map = new HashMap<String, String>();
                map.put("msg", "success");
//            map.put("user", account);
//            session.setAttribute("user", account);
                System.out.println(mapper.writeValueAsString(map));


                return mapper.writeValueAsString(map);
            }
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            System.out.println("*********" + stringWriter.toString());
        }


//        ModelAndView mv = new ModelAndView();
//        User user = userService.selectUser(2);
//        mv.addObject("user", user);
//        mv.setViewName("login");
        return "login";

    }

//    @RequestMapping(params = "method=checkRegisteredLegal")
//    @ResponseBody
//    public String checkRegisteredLegal( HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//
//        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//正则匹配邮箱
//        String ph = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$";//正则匹配手机号
//        String un = "^[A-Za-z0-9_-]{3,16}$";//正则匹配用户名
//        String imputData = request.getParameter("imputdata");
//
//        User user = new User();
//        if (imputData.matches(em)){
//            user.setEmail(imputData);
//            user.setUsername(null);
//            user.setMobile(null);
//            if (userService.login(user).size()>0)
//                return "{\"msg\":\"该邮箱已被注册\"}";
//            else
//                return "{\"msg\":\"该邮箱可用\"}";
//        }
//
//        else if (imputData.matches(ph)){
//            user.setEmail(null);
//            user.setUsername(null);
//            user.setMobile(imputData);
//            if (userService.login(user).size()>0)
//                return "{\"msg\":\"该手机号已被注册\"}";
//            else
//                return "{\"msg\":\"该手机号可用\"}";
//        }
//        else if (imputData.matches(un)){
//            user.setEmail(null);
//            user.setUsername(imputData);
//            user.setMobile(null);
//            if (userService.login(user).size()>0)
//                return "{\"msg\":\"该用户名已被注册\"}";
//            else
//                return "{\"msg\":\"该用户名可用\"}";
//        }
//
//
//        return "{\"msg\":\"输入错误\"}";
//
//
//    }

    @RequestMapping(params = "method=checkEmail")
    @ResponseBody
    public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//正则匹配邮箱
        String imputData = request.getParameter("imputdata");

        User user = new User();
        if (imputData.matches(em)) {
            user.setEmail(imputData);
            user.setUsername(null);
            user.setMobile(null);
            if (userService.login(user).size() > 0)
                return "{\"msg\":\"该邮箱已被注册\",\"available\":\"no\"}";
            else
                return "{\"msg\":\"该邮箱可用\",\"available\":\"yes\"}";
        } else {
            return "{\"msg\":\"邮箱格式不正确\",\"available\":\"no\"}";
        }

    }

    @RequestMapping(params = "method=checkUserName")
    @ResponseBody
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String un = "^[A-Za-z0-9_-]{3,16}$";//正则匹配用户名
        String imputData = request.getParameter("imputdata");

        User user = new User();
        if (imputData.matches(un)) {
            user.setEmail(null);
            user.setUsername(imputData);
            user.setMobile(null);
            if (userService.login(user).size() > 0)
                return "{\"msg\":\"该用户名已被注册\",\"available\":\"no\"}";
            else
                return "{\"msg\":\"该用户名可用\",\"available\":\"yes\"}";
        } else {
            return "{\"msg\":\"用户名格式不正确\",\"available\":\"no\"}";
        }

    }

    @RequestMapping(params = "method=checkPhoneNumber")
    @ResponseBody
    public String checkPhoneNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String ph = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$";//正则匹配手机号
        String imputData = request.getParameter("imputdata");

        User user = new User();
        if (imputData.matches(ph)) {
            user.setEmail(null);
            user.setUsername(null);
            user.setMobile(imputData);
            if (userService.login(user).size() > 0)
                return "{\"msg\":\"该手机号已被注册\",\"available\":\"no\"}";
            else
                return "{\"msg\":\"该手机号可用\",\"available\":\"yes\"}";
        } else {
            return "{\"msg\":\"手机号格式不正确\",\"available\":\"no\"}";
        }

    }

    @RequestMapping(params = "method=getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String name = session.getAttribute("name").toString();
        String id = session.getAttribute("id").toString();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", name);
        map.put("id", id);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(map);

    }

    @RequestMapping(params = "method=getCategory")
    @ResponseBody
    public String getCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
//        Map<String,Object>map = new HashMap<String, Object>();
//
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setUserid(Integer.parseInt(id));

        List<CategoryBean> list = userService.getCategory(categoryBean);
        //System.out.println(category.get(0).getName());


        return objectMapper.writeValueAsString(list);

    }


    @RequestMapping(params = "method=updateCategory")
    @ResponseBody
    public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        String name = request.getParameter("name");
        int cid = 0;
        if (request.getParameter("id") != null)
            cid = Integer.parseInt(request.getParameter("id"));
//        Map<String,Object>map = new HashMap<String, Object>();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setUserid(Integer.parseInt(id));
        categoryBean.setId(cid);
        categoryBean.setName(name);

        if (userService.updateCategory(categoryBean) > 0) {

            return "{\"msg\":\"success\"}";
        }

        return "{\"msg\":\"fail\"}";

    }

    @RequestMapping(params = "method=insertCategory")
    @ResponseBody
    public String insertCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        String name = request.getParameter("name");
        int cid = 0;
        if (request.getParameter("id") != null)
            cid = Integer.parseInt(request.getParameter("id"));
//        Map<String,Object>map = new HashMap<String, Object>();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setUserid(Integer.parseInt(id));
        categoryBean.setName(name);

        if (userService.insertCategory(categoryBean) > 0) {

            return "{\"msg\":\"success\"}";
        }


        return "{\"msg\":\"fail\"}";

    }

    @RequestMapping(params = "method=deleteCategory")
    @ResponseBody
    public String deleteCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        int cid = 0;
        if (request.getParameter("id") != null)
            cid = Integer.parseInt(request.getParameter("id"));
//        Map<String,Object>map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setUserid(Integer.parseInt(id));
        categoryBean.setId(cid);

        if (userService.deleteCategory(categoryBean) > 0) {

            return "{\"msg\":\"success\"}";
        }

        return "{\"msg\":\"fail\"}";

    }

    @RequestMapping(params = "method=recordBill")
    @ResponseBody
    public String recordBill(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        int cid = 0;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        String spend = request.getParameter("spend");
        String comment = request.getParameter("comment");
        String dateStr = request.getParameter("date");
        String category = request.getParameter("category");
        date = format.parse(dateStr);


//        Map<String,Object>map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        RecordBean recordBean = new RecordBean();
        recordBean.setUserid(Integer.parseInt(id));
        recordBean.setSpend(Double.parseDouble(spend));
        recordBean.setComment(comment);
        recordBean.setDate(date);
        recordBean.setCategory(category);

        if (userService.recordBill(recordBean) > 0) {

            return "{\"msg\":\"success\"}";
        }

        return "{\"msg\":\"fail\"}";

    }


    @RequestMapping(params = "method=getSpendSituation")
    @ResponseBody
    public String getSpendSituation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
//        Map<String,Object>map = new HashMap<String, Object>();
//
        ObjectMapper objectMapper = new ObjectMapper();
        SpendBean spendBean = new SpendBean();
        spendBean.setId(Integer.parseInt(id));

        List<SpendBean> list = userService.getSpendSituation(spendBean);


        return objectMapper.writeValueAsString(list);

    }


    @RequestMapping(params = "method=getSpendTrend")
    @ResponseBody
    public String getSpendTrend(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        String month = request.getParameter("month");
//        Map<String,Object>map = new HashMap<String, Object>();
//
        ObjectMapper objectMapper = new ObjectMapper();
        TrendBean trendBean = new TrendBean();
        trendBean.setUserid(Integer.parseInt(id));
        trendBean.setMonth(Integer.parseInt(month));

        List<TrendBean> list = userService.getSpendTrend(trendBean);


        return objectMapper.writeValueAsString(list);

    }


}
