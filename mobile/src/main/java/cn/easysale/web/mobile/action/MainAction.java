package cn.easysale.web.mobile.action;

import cn.easysale.core.entity.Company;
import cn.easysale.core.service.CompanyService;
import cn.easysale.core.support.BaseAction;
import cn.easysale.core.support.Config;
import cn.easysale.core.support.Page;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by liaozhisong on 3/22/14.
 */
@Controller
@RequestMapping("/main")
public class MainAction extends BaseAction {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/index")
    public String index(Page page, Model model) {
        Company company = new Company();
        company.setName("bbb");
        companyService.addObject(company);
        logger.info("create time:" + company.getCreateDate());
        List<Company> list = companyService.findAll();
        logger.info("list1:" + list.size());

        Company cc = new Company();
        cc.setName("b");
        Page<Company> companyPage = companyService.findCompanyList(cc, page);
        logger.info("list2:" + companyPage.getData().size());

        model.addAttribute("message", companyPage);
        logger.info("index page!!!");
        return "main/index";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {
        FileUtils.writeByteArrayToFile(new File(Config.UPLOAD_PATH + file.getOriginalFilename()), file.getBytes());
        return "main/index";
    }
}
