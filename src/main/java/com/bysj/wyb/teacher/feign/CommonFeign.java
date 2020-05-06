package com.bysj.wyb.teacher.feign;


import com.bysj.wyb.teacher.configuration.MultipartSupportConfig;
import com.bysj.wyb.teacher.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangyb
 */
@FeignClient(name = "pd-common",configuration = MultipartSupportConfig.class)
public interface CommonFeign {

    @RequestMapping(value = "/system/logCounter")
    void logCounter(@RequestParam String uid);

    @RequestMapping(value = "/system/upload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result upload(@RequestPart("file") MultipartFile file, @RequestParam String uploadCatalogAndName);
}
