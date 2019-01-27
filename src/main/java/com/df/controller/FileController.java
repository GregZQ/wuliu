package com.df.controller;

import com.df.constant.FileType;
import com.df.controller.utils.PrintUtils;
import com.df.domain.Sends;
import com.df.domain.Ticket;
import com.df.service.SendsService;
import com.df.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件下载保存controller
 */
@Controller
public class FileController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SendsService sendsService;

    /**
     * 下载文件
     * @param response
     * @param id 查询id
     * @param type  文件类型
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response,Integer id,
                         Integer type) throws IOException {
        String filePath = null;
        if (FileType.TICKET.match(type)) {
            Ticket ticket = this.ticketService.findDetailById(id);
            filePath = ticket.getSavePath();
        }else if (FileType.SENDLIST.match(type)){
            Sends sends = this.sendsService.findSendListById(id);
            filePath = sends.getSavePath();
        }
        PrintUtils.print(response,filePath);
    }
}
