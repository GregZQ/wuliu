package com.df.controller;

import com.df.controller.utils.DetailListProperties;
import com.df.controller.utils.PrintUtils;
import com.df.controller.utils.TimeTransfer;
import com.df.domain.Detail;
import com.df.domain.PageBean;
import com.df.domain.SendList;
import com.df.service.DetailService;
import com.df.service.SendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/*
* 与发车有关的类
* */
@Controller
public class SendController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private SendService sendService;

    /**
     * 根据选择的selects，生成运输清单表
     * @param selects 选择的selects
     * @return
     */
    @PostMapping("/adddetail")
    public ModelAndView  addDetail(String selects){
        SendList sendList=new SendList();

        if (selects!=null&&!selects.trim().equals("")){


            String[] values= selects.split(",");

            this.sendService.createNew(sendList);

            //设置选中的出库日期
            for (int i=0;i<values.length;i++){
                this.detailService.updateDateSidById(Integer.valueOf(values[i]),
                                                       Integer.valueOf(sendList.getSid()), TimeTransfer.getCurrentDate());
            }

            ModelAndView modelAndView=new ModelAndView("redirect:" +
                    "/senddetails/"+sendList.getSid());
            return  modelAndView;
        }
        return null;
    }

    /**
     * 根据id列出运输清单
     * 同时列出页数，每页固定35行
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/senddetails/{id}")
    public String showList(@PathVariable Integer id,Model model){

            BigDecimal temp=new BigDecimal(0);

            List<Detail> list=this.detailService.findDetailListBySid(id);


            SendList sendList=this.sendService.findSendListById(id);

            if (sendList.getMoneysMe()==null) {
                BigDecimal moneyMe = new BigDecimal(0);
                BigDecimal moneyHe = new BigDecimal(0);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPayType() == 2) {//payType不会为空
                       moneyMe= moneyMe.add(list.get(i).getPayCount()==null?temp:list.get(i).getPayCount());
                    }
                    moneyHe= moneyHe.add(list.get(i).getCollection()==null?temp:list.get(i).getCollection());
                }
                int tempCount=list.size();
                tempCount+=3;
                if (tempCount% DetailListProperties.PER_NUMS==0){
                    tempCount/=DetailListProperties.PER_NUMS;
                }else{
                    tempCount/=DetailListProperties.PER_NUMS;
                    tempCount+=1;
                }
                sendList.setTotalPages(tempCount);
                sendList.setMoneysMe(moneyMe);
                sendList.setMoneysHe(moneyHe);
            }
            model.addAttribute("list",list);

            model.addAttribute("sendList",sendList);
            return "senddetails";
    }

    @PostMapping("/senddetails/finish")
    public String saveList(SendList sendList,Model model){
        /*
        * 更新完毕后打印
        * */
        this.sendService.updateSendList(sendList);

        List<Detail> list=this.detailService.findDetailListBySid(sendList.getSid());
        print(sendList,list,"Sheet1");

        model.addAttribute("sid",sendList.getSid());
        return "success";
    }

    /*
    * 历史清单查询
    * */
    @GetMapping("/senddetails/query")
    public @ResponseBody  PageBean queryHistory(String  loadTime, String carid, PageBean pageBean){
            if (loadTime!=null&&loadTime.trim().equals("")){
                loadTime=null;
            }
            if (carid!=null&&carid.trim().equals("")){
                carid=null;
            }
            pageBean=PageBean.checkPageBean(pageBean);

            int count=this.sendService.getCountByTimeAndCarId(loadTime,carid);

            List<SendList> list=this.sendService.queryHistoryByTimeAndCarId(loadTime,carid,
                    (pageBean.getCurrentPage()-1)*pageBean.getPer(),pageBean.getPer());

            pageBean=PageBean.finishPageBean(list,count,pageBean);
            return pageBean;
    }

    @PostMapping("/senddetails/{id}")
    public @ResponseBody String removeSend(@PathVariable Integer id){
        Integer count=this.sendService.deleteBySid(id);
        if (count>0){
            return "true";
        }
        return "false";
    }
    /*
    * 进行打印
    * */
    private void print(SendList sendList,List<Detail> list,String sheetName)  {

        try {
            PrintUtils.printSendList(sendList,list,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
