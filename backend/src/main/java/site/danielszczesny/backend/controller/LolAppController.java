package site.danielszczesny.backend.controller;

import lombok.extern.java.Log;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.danielszczesny.backend.model.lolapp.Champions;
import site.danielszczesny.backend.service.AccountService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Log
@RestController
@RequestMapping("/lolapp")
public class LolAppController {

    private AccountService accountService;

    @Autowired
    public LolAppController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ModelAndView getMapping() {
        return new ModelAndView("lolapp_main");
    }

    @PostMapping
    public void postMapping(@RequestParam String username, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/lolapp/" + username.trim());
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ModelAndView getMappingUser(@PathVariable(name = "username") String username) {
        userExist(username);
        ModelAndView model = new ModelAndView("chestArray");
        String[] chestArrayByUsername = accountService.getChestArrayByUsername(username);
        model.addObject("chestArrayAcc", Arrays.asList(chestArrayByUsername));
        model.addObject("championsNames", Champions.values());
        log.info("Get lolapp/" + username);
        return model;
    }

    private boolean userExist(String username) {
        if (accountService.usernameExist(username)) {
            log.info("User exist");
            return true;
        } else {
            accountService.save(username);
            log.warning("User not exist");
            return userExist(username);
        }
    }

    @PostMapping(value = "/changevalue", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public int changeValue(@RequestBody String json) throws ParseException {
        JSONParser obj = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        JSONObject parse = (JSONObject) obj.parse(json);

        String username = (String) parse.get("username");
        int id = Integer.parseInt(String.valueOf(parse.get("id")));

        log.info("User " + username + " change value for champion id: " + id);
        return accountService.changeChestState(username, id);
    }

    @GetMapping("/getdata/{username}")
    public Object getData(@PathVariable(name = "username") String username) {
        String[] chestArrayByUsername = accountService.getChestArrayByUsername(username);
        System.out.println(Arrays.toString(chestArrayByUsername));
        return chestArrayByUsername;
    }
}
