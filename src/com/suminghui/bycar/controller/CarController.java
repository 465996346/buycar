package com.suminghui.bycar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sun.swing.StringUIClientPropertyKey;

import com.suminghui.bycar.bean.Pagination;
import com.suminghui.bycar.bean.Result;
import com.suminghui.bycar.bean.User;
import com.suminghui.bycar.bean.WebResults;
import com.suminghui.bycar.common.AppContext;
import com.suminghui.bycar.common.Constants;
import com.suminghui.bycar.exception.ParameterException;
import com.suminghui.bycar.exception.ServiceException;
import com.suminghui.bycar.service.UserService;
import com.suminghui.bycar.util.PathUtil;
import com.suminghui.bycar.util.StringUtil;

@Controller
@RequestMapping(Constants.APP_WEB_URL_CAR_PREFIX)
public class CarController extends BaseController {
    private static final String WEB_LIST_JSP = "car/list";
    private static final String WEB_EDIT_JSP = "car/edit";
    private static final String WEB_ADD_PAGE = "car/add";
    private static final String WEB_LIST_PAGE = "web/car/list";

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login/json", method = RequestMethod.POST)
    @ResponseBody
    public Result loginJson(@RequestParam(value = "userName", defaultValue = "") String userName, @RequestParam(value = "password", defaultValue = "") String password) {
        Result result = new Result();
        User user = null;
        try {
            user = userService.getByUserName(userName, password);
            this.addSession(Constants.USER, user);
            result.put(WebResults.status.getValue(), 1);
            result.put(Constants.USER, this.getUser());
            result.put(WebResults.url.getValue(), PathUtil.getFullPath(WEB_LIST_JSP));
        } catch (ParameterException exception) {

        } catch (ServiceException exception) {
            result.put(WebResults.status.getValue(), 0);
            result.put(WebResults.errMessage.getValue(), exception.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(WEB_LIST_JSP);
        return modelAndView;
    }
    
    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public Result listTest(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        Result result = new Result();
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        pagination.setPageSize(pageSize);

        List<User> users = userService.selectUserList(pagination);
        result.put(Constants.USER, this.getUser());
        result.put("pagination", pagination);
        result.put(Constants.DATAS, users);

        return result;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.DATAS, id);
        modelAndView.setViewName(WEB_EDIT_JSP);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/json/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result editJson(@PathVariable int id) {
        Result result = new Result();

        User user = userService.selectUserInfoById(id);
        result.put(WebResults.status.getValue(), 1);
        result.put(Constants.DATAS, user);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(WEB_ADD_PAGE);

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(User user) {
        Result result = new Result();
        try {
            if (user != null) {
                if (user.getId() > 0) {
                    User userTemp = userService.selectUserInfoById(user.getId());
                    if (user.getPassword().trim() != null && !user.getPassword().isEmpty()) {
                        this.removeSession(Constants.USER);
                    } else {
                        user.setPassword(userTemp.getPassword());
                    }
                    userService.updateUser(user);
                } else {
                    userService.insertUser(user);
                }
            }
        } catch (ParameterException exception) {

        }

        result.put(WebResults.status.getValue(), 1);
        result.put(WebResults.message.getValue(), "操作成功！");
        result.put(WebResults.url.getValue(), PathUtil.getFullPath(WEB_LIST_JSP));
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.delete(id);
        RedirectView redirectView = new RedirectView(PathUtil.getFullPath(WEB_LIST_JSP));
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}
