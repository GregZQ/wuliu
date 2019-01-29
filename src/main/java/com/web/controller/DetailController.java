package com.web.controller;


import com.web.utils.CookitUtil;
import com.web.domain.Detail;
import com.web.domain.PageBean;
import com.web.domain.User;
import com.web.service.SendsService;
import com.web.service.TicketService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SendsService sendsService;

    @Autowired
    private UserService userService;
    /*
     * 请求detail页
     * 因为涉及局部刷新，所以使用ajax+json异步请求方式请求数据
     */
    @GetMapping("/detail/list")
    public @ResponseBody PageBean list(HttpServletRequest request,PageBean pageBean){
        //刷新
        refresh();
        //获取当前登录用户
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        //分页查询当前数据
        pageBean=PageBean.checkPageBean(pageBean);
        Integer start=(pageBean.getCurrentPage()-1)*pageBean.getPer();
        Integer nums=pageBean.getPer();
        List<Detail> datas=this.ticketService.findByPageAndUid(start,nums,user.getId());
        Integer count=this.ticketService.getAllCountByUid(user.getId());
        //更新分页数据
        pageBean=PageBean.finishPageBean(datas,count,pageBean);

        return pageBean;
    }

    /**
     * 刷新
     */
    private void refresh() {
        /**
         * 因为可能选择后后悔，所以在不选择的情况下调用它
         */
        this.ticketService.refresh();
        this.sendsService.refresh();
    }

    /*
    *  删除细节
    * 返回json串表示成功
    */
    @PostMapping("/detail/remove/{id}")
    @ResponseBody String deleteDetailById(@PathVariable Integer id){

          Integer result=this.ticketService.deleteById(id);
          if (result==1){
              return "true";
          }
          return "false";
    }
}
