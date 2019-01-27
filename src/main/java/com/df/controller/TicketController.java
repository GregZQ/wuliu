package com.df.controller;

import com.df.constant.TicketStat;
import com.df.controller.utils.*;
import com.df.domain.Detail;
import com.df.domain.PageBean;
import com.df.domain.Ticket;
import com.df.domain.User;
import com.df.service.TicketService;
import com.df.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Value("${SAVE_PATH}")
    private String savePath;

    @Value("${USER_FILE_PATH}")
    private String userFilePath;

    @Value("${TICKET_SHEET}")
    private String ticketSheet;
    /**
     * 到发票打印界面
     * @param model
     * @return
     */
    @GetMapping("/openticket")
    public String tickePage(HttpServletRequest request,Model model){
        //获取登录用户
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        //获取当前用户所有开票数量
        Integer count= ticketService.getAllCountByUid(user.getId());
        //数据显示
        String  dateValue=TimeTransfer.dateToString(new Date(),"yyyy-MM-dd");
        model.addAttribute("dateValue",dateValue);
        model.addAttribute("goodId","100000"+count);
        model.addAttribute("company",user.getCompany());
        model.addAttribute("uid",user.getId());
        return "openticket";
    }
    /**
     * 保存订单
     * @param ticket 需要保存的订单信息
     * @param tag 是否打印
     * @return
     */
    @PostMapping("/detail/saveticket")
    public String saveTicket(HttpServletResponse response, Ticket ticket, Integer tag) throws Exception {

        User user = this.userService.findUserById(ticket.getUid());
        //复制文件
        String fileName = ExcelUtil.generateExcelName();
        FileUtil.copyFile(userFilePath,user.getTicket(), savePath,fileName);

        String filePath = FileUtil.getFilePath(savePath,fileName);

        PrintUtils.saveOpenTicket(filePath,ticket,ticketSheet);

        ticket.setSavePath(filePath);
        ticket.setState(TicketStat.NOCHOSE.getCode());
        this.ticketService.addTicket(ticket);

        return "redirect:/detail";
    }
    /**
     * 根据id选择查看哪一个ticket
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ticket/{id}")
    public String ticket(@PathVariable Integer id,Model model){

        Ticket detail=this.ticketService.findDetailById(id);
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
    public @ResponseBody PageBean getHistoty(HttpServletRequest request,String loadTime, String goodid, PageBean pageBean){
        //用于设置null
        if (loadTime!=null&&loadTime.trim().equals("")){
            loadTime=null;
        }
        if (goodid!=null&&goodid.trim().equals("")){
            goodid=null;
        }
        //获取当前用户
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);
        pageBean=PageBean.checkPageBean(pageBean);

        int count=this.ticketService.getCountByLoadTimeAndGoodidAndUid(loadTime,goodid,user.getId());

        List<Detail> list=this.ticketService.findDetailByLoadTimeAndGoodidAndPageAndUid(
                                            loadTime,goodid,
                                        (pageBean.getCurrentPage()-1)*pageBean.getPer(),
                                            pageBean.getPer(),
                                               user.getId()
                                               );

        pageBean=PageBean.finishPageBean(list,count,pageBean);

        return pageBean;
    }

    @PostMapping("/ticket/{id}")
    public @ResponseBody String deleteById(@PathVariable Integer id){
        Integer count=this.ticketService.deleteById(id);

        if (count>0){
            return "true";
        }
        return "false";
    }
}
