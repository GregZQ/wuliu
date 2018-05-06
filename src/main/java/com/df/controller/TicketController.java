package com.df.controller;

import com.df.controller.utils.TimeTransfer;
import com.df.dao.DetailDao;
import com.df.domain.Detail;
import com.df.domain.PageBean;
import com.df.domain.Ticket;
import com.df.service.DetailService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private DetailService detailService;

    /**
     * 到发票打印界面
     * @param model
     * @return
     */
    @GetMapping("/openticket")
    public String tickePage(Model model){
        Integer count=detailService.getAllCount();
        String  dateValue=TimeTransfer.dateToString(new Date(),"yyyy-MM-dd");
        model.addAttribute("dateValue",dateValue);
        model.addAttribute("goodId","100000"+count);
        return "openticket";
    }

    /**
     * 根据id选择查看哪一个ticket
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ticket/{id}")
    public String ticket(@PathVariable Integer id,Model model){

        Detail detail=this.detailService.findDetailById(id);
        model.addAttribute("ticket",detail);
        return "openticket2";
    }

    /**
     * 查看ticket历史
     * @param loadTime  装载时间
     * @param goodid  货物id
     * @param pageBean  分页信息
     * @return
     */
    @GetMapping("/ticket/history")
    public @ResponseBody PageBean getHistoty(String loadTime, String goodid, PageBean pageBean){
        //用于设置null
        if (loadTime!=null&&loadTime.trim().equals("")){
            loadTime=null;
        }
        if (goodid!=null&&goodid.trim().equals("")){
            goodid=null;
        }
        pageBean=PageBean.checkPageBean(pageBean);
        int count=this.detailService.getCountByLoadTimeAndGoodid(loadTime,goodid);

        List<Detail> list=this.detailService.findDetailByLoadTimeAndGoodidAndPage(
                                            loadTime,goodid,(pageBean.getCurrentPage()-1)*pageBean.getPer(),
                                            pageBean.getPer()
                                               );

        pageBean=PageBean.finishPageBean(list,count,pageBean);

        return pageBean;
    }

    @PostMapping("/ticket/{id}")
    public @ResponseBody String deleteById(@PathVariable Integer id){
        Integer count=this.detailService.deleteById(id);

        if (count>0){
            return "true";
        }
        return "false";
    }
}
