package site.danielszczesny.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.danielszczesny.backend.model.Account;
import site.danielszczesny.backend.model.lolapp.Champions;
import site.danielszczesny.backend.model.timofinance.Record;
import site.danielszczesny.backend.service.RecordService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@RestController
@RequestMapping("/tf")
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public ModelAndView getLogin() {
        return new ModelAndView("tf_main");
    }

    @PostMapping
    public void postMapping(@RequestParam String username, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/tf/" + username.trim());
    }

    @GetMapping("/{username}")
    public ModelAndView getMappingUser(@PathVariable(name = "username") String username) {
        userExist(username);
        ModelAndView model = new ModelAndView("tf_overview");
        Account account = recordService.getAccountByUsername(username);
        Set<Record> records = account.getRecords();
        model.addObject(records);
        return model;
    }

    private boolean userExist(String username) {
        return recordService.getAccountByUsername(username) != null;
    }
}
