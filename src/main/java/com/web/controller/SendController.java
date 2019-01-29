package com.web.controller;

import com.web.constant.TicketStat;
import com.web.support.PrintFileSupport;
import com.web.utils.*;
import com.web.domain.*;
import com.web.service.TicketService;
import com.web.service.SendsService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/*
* 与发车有关的类
* */
@Controller
public class SendController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SendsService sendsService;

    @Autowired
    private UserService userService;

    @Value("${USER_FILE_PATH}")
    private String userFilePath;

    @Value("${SAVE_PATH}")
    private String savePath;

    @Value("${FUBEN_SHEET}")
    private String fubenSheet;

    /**
     * 根据选择的selects，生成运输清单表
     * @param selects 选择的selects
     * @return
     */
    @PostMapping("/adddetail")
    public ModelAndView  addDetail(HttpServletRequest request,String selects){
        Sends sends =new Sends();
        sends.setSendTime(new Date());

        if (Objects.nonNull(sends)){
            String[] values= selects.split(",");
            this.sendsService.createNew(sends);

            //设置为选中未发送
            for (int i=0;i<values.length;i++){
                this.ticketService.updateStateSidById(Integer.valueOf(values[i]),
                                                       Integer.valueOf(sends.getSid()), TicketStat.NOSTAT.getCode());
            }

            ModelAndView modelAndView=new ModelAndView("redirect:" +
                    "/senddetails/"+ sends.getSid());
            return  modelAndView;
        }
        return null;
    }

    /**
     * 根据id列出运输清单
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/senddetails/{id}")
    public String showList(HttpServletRequest request ,@PathVariable Integer id,Model model){

            BigDecimal temp=new BigDecimal(0);
            List<Detail> list=this.ticketService.findDetailListBySid(id);
            Sends sends =this.sendsService.findSendsById(id);

            //获取用户
            String username = CookitUtil.getUsername(request);
            User user = this.userService.findUserByUsername(username);

            model.addAttribute("company",user.getCompany());
            model.addAttribute("list",list);
            model.addAttribute("sends", sends);


            return "senddetails";
    }

    /**
     * 添加发货清单
     * @param request
     * @param sends
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/senddetails/finish")
    public String saveList(HttpServletRequest request, Sends sends, Model model) throws Exception {

        //获取当前登录用户
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        //更新票据状态
        List<Detail> list = this.ticketService.findDetailListBySid(sends.getSid());
        list.stream().forEach(
            detail -> {
                this.ticketService.updateStateSidById(detail.getId(),
                        sends.getSid(),TicketStat.CHOSE.getCode());
            }
        );

        //生成文件
        String fileName = ExcelUtil.generateExcelName();
        FileUtil.copyFile(userFilePath,user.getFuben(),savePath,fileName);
        String filePath = FileUtil.getFilePath(savePath,fileName);
        PrintFileSupport.saveSendList(filePath,fubenSheet, sends,list,user);

        //补全数据
        sends.setSavePath(filePath);
        sends.setUid(user.getId());
        this.sendsService.updateSends(sends);
        model.addAttribute("sid", sends.getSid());
        return "success";
    }

    /*
    * 历史清单查询
    * */
    @GetMapping("/senddetails/query")
    public @ResponseBody  PageBean queryHistory(HttpServletRequest request,String  loadTime, String carid, PageBean pageBean){
            String username = CookitUtil.getUsername(request);

            User user = this.userService.findUserByUsername(username);

            if (loadTime!=null&&loadTime.trim().equals("")){
                loadTime=null;
            }
            if (carid!=null&&carid.trim().equals("")){
                carid=null;
            }
            pageBean=PageBean.checkPageBean(pageBean);

            int count=this.sendsService.getCountByTimeAndCarIdAndUid(loadTime,carid,user.getId());

            List<Sends> list=this.sendsService.queryHistoryByTimeAndCarIdAndUid(loadTime,carid,
                    (pageBean.getCurrentPage()-1)*pageBean.getPer(),pageBean.getPer(),user.getId());

            pageBean=PageBean.finishPageBean(list,count,pageBean);
            return pageBean;
    }

    /**
     * 根据id删除发货清单
     * 对应相应的货物信息也会删除
     * @param id
     * @return
     */
    @PostMapping("/senddetails/{id}")
    public @ResponseBody String removeSend(@PathVariable Integer id){
        Integer count=this.sendsService.deleteBySid(id);
        if (count>0){
            return "true";
        }
        return "false";
    }
}
