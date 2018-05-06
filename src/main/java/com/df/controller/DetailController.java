package com.df.controller;


import com.df.controller.utils.ExcelUtil;
import com.df.controller.utils.PrintUtils;
import com.df.domain.PageBean;
import com.df.domain.Ticket;
import com.df.service.DetailService;
import com.df.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private SendService sendService;

    /**
     * 保存订单，将订单转为一个detail
     * @param ticket 需要保存的订单信息
     * @param tag 是否打印
     * @return
     */
    @PostMapping("/detail/saveticket")
    public String saveTicket(Ticket ticket,Integer tag){

        this.detailService.addTicket(ticket);
        if (tag==1){
            try {
                PrintUtils.printOpenTicket(ticket,"Sheet2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/detail";
    }
    /*
     * 请求detail页
     * 因为涉及局部刷新，所以使用ajax+json异步请求方式请求数据
     */
    @GetMapping("/detail/list")

    public @ResponseBody PageBean list(PageBean pageBean){
        refresh();
        pageBean=PageBean.checkPageBean(pageBean);

        Integer start=(pageBean.getCurrentPage()-1)*pageBean.getPer();
        Integer nums=pageBean.getPer();
        List datas=this.detailService.listByTimeAndPage(start,nums);
        Integer count=this.detailService.getAllCount();
        pageBean=PageBean.finishPageBean(datas,count,pageBean);

        return pageBean;
    }

    private void refresh() {
        /**
         * 因为可能选择后后悔，所以在不选择的情况下调用它
         */
        this.detailService.refresh();
        this.sendService.refresh();
    }

    /*
    *  删除细节
    * 返回json串表示成功
    */
    @PostMapping("/detail/remove/{id}")
    @ResponseBody String deleteDetailById(@PathVariable Integer id){

          Integer result=this.detailService.deleteDetailById(id);
          if (result==1){
              return "true";
          }
          return "false";
    }
}
