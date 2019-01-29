package com.web.controller;

import com.web.constant.FileType;
import com.web.support.PrintFileSupport;
import com.web.domain.Sends;
import com.web.domain.Ticket;
import com.web.service.SendsService;
import com.web.service.TicketService;
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
            Sends sends = this.sendsService.findSendsById(id);
            filePath = sends.getSavePath();
        }
        PrintFileSupport.print(response,filePath);
    }
}
