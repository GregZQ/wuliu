package com.web.controller;

import com.web.utils.CookitUtil;
import com.web.utils.FileUtil;
import com.web.support.PrintFileSupport;
import com.web.domain.Des;
import com.web.domain.Place;
import com.web.domain.User;
import com.web.service.DesService;
import com.web.service.PlaceService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个性化配置相关内容
 */
@Controller
public class PersonalController {
    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private DesService desService;

    @Value("${SYS_TICKET_FILE}")
    private String sysTicketFile;

    @Value("${SYS_FUBEN_FILE}")
    private String sysFubenFile;

    @Value("${SYS_END_FILE}")
    private String sysEndFile;

    @Value("${USER_FILE_PATH}")
    private String userFilePath;

    @Value("${TICKET_SHEET}")
    private String ticketSheet;

    @Value("${FUBEN_SHEET}")
    private String fubenSheet;

    /**
     * 获取某个用户的公司名称
     * @param request
     * @return
     */
    @GetMapping("/personal/company")
    @ResponseBody public  String findCompanyByUser(HttpServletRequest request){
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        return user.getCompany();
    }

    /**
     * 更新用户的公司名称
     * @param request
     * @param company
     * @return
     * @throws Exception
     */
    @PostMapping("/personal/rename")
    public String renameCompany(HttpServletRequest request,String company) throws Exception {
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        user.setCompany(company);
        updateFile(user);
        this.userService.renameCompany(user);

        return "redirect:/personal";
    }

    /**
     * 根据id删除收发货物地址
     * @param id
     * @return
     */
    @GetMapping("/personal/place/{id}")
    @ResponseBody public String deletePlace(@PathVariable Integer id){
        this.placeService.deleteById(id);
        return "true";
    }

    /**
     * 根据用户添加收发货物地址
     * @param request
     * @param place
     * @return
     */
    @PostMapping("/personal/place")
    public String addPlace(HttpServletRequest request,String place){
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        Place temp = new Place();
        temp.setUid(user.getId());
        temp.setPlace(place);
        this.placeService.addPlace(temp);

        return "redirect:/personal";
    }

    /**
     * 根据id获取运输地址列表
     * @param id
     * @return
     */
    @GetMapping("/personal/loadplace/{id}")
    @ResponseBody public String deleteLoadPlace(@PathVariable Integer id){
        this.desService.deleteById(id);
        return "true";
    }

    /**
     * 根据用户添加运输地址
     * @param request
     * @param loadPlace
     * @return
     */
    @PostMapping("/personal/loadplace")
    public String loadPlace(HttpServletRequest request,String loadPlace){
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        Des des = new Des();
        des.setLoadPlace(loadPlace);
        des.setUid(user.getId());
        this.desService.addDes(des);

        return "redirect:/personal";
    }

    /**
     * 获取某个用户的开票收发货地址
     * @param request
     * @return
     */
    @GetMapping("/personal/place")
    @ResponseBody List<Place> findPlaceByUser(HttpServletRequest request){
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        List<Place> list = this.placeService.findByUid(user.getId());
        return list;
    }

    /**
     * 获取某个用户的运输收/发货地址
     * @param request
     * @return
     */
    @GetMapping("/personal/loadplace")
    @ResponseBody List<Des> findLoadPlaceByUser(HttpServletRequest request){
        String username = CookitUtil.getUsername(request);
        User user = this.userService.findUserByUsername(username);

        List<Des> list = this.desService.findByUid(user.getId());

        return list;
    }

    private void updateFile(User user) throws Exception {
        String ticketPath = FileUtil.getFilePath(userFilePath,user.getTicket());
        String fubenPath  = FileUtil.getFilePath(userFilePath,user.getFuben());
        PrintFileSupport.updateTicketFile(ticketPath,ticketSheet,user);
        PrintFileSupport.updateFubenFile(fubenPath,fubenSheet,user);
    }
}
