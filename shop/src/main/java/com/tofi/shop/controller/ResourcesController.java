package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("resources")
public class ResourcesController {
    private ItemService itemService;

    @Inject
    public ResourcesController(ItemService itemService){
        this.itemService = itemService;
    }

    @RequestMapping("image/{itemId}")
    public String download(@PathVariable("itemId") Integer itemId,
                           HttpServletResponse response)
            throws IOException, ServiceException {
        Item item = itemService.findById(itemId);
        response.setHeader("Content-Disposition", "inline;filename=\"" +item.getImageFilename()+ "\"");
        OutputStream out = response.getOutputStream();
        response.setContentType(item.getImageFormat());
        IOUtils.copy(new ByteArrayInputStream(item.getImage()), out);
        out.flush();
        out.close();
        return null;
    }

//    @RequestMapping("")
//    public @ResponseBody String Index(){
//        return "resources";
//    }
//
//    @RequestMapping("/info")
//    public @ResponseBody byte[] GetInfoImage(@RequestParam(name = "itemId", defaultValue = "-1") String itemId) throws ServiceException {
//        if (itemId.equals("-1"))
//            return null;
//        int id = Integer.parseInt(itemId);
//        Item item = itemService.findById(id);
//        return Base64.decode(item.getImage());
//    }
}
