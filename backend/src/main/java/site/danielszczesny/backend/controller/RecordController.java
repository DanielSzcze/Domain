package site.danielszczesny.backend.controller;

import com.sun.net.httpserver.Headers;
import lombok.extern.java.Log;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.danielszczesny.backend.model.Account;
import site.danielszczesny.backend.model.lolapp.Champions;
import site.danielszczesny.backend.model.timofinance.*;
import site.danielszczesny.backend.service.RecordService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

@Log
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
        Set<Record> records = recordService.getAllRecordsByUsername(username);
        model.addObject("records", records);
//        log.info(records.toString());
        return model;
    }

    private boolean userExist(String username) {
        return recordService.getAccountByUsername(username) != null;
    }

    @GetMapping("/getIncomeTypes")
    public String getTypes() {
//        log.info("getIncomeTypes");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\"IncomeTypes\": [");
        for (IncomeType type: IncomeType.values()) {
            stringBuilder.append("\"").append(type.toString()).append("\",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("],\"ChargeTypes\": [");
        for (ChargeType type: ChargeType.values()) {
            stringBuilder.append("\"").append(type.toString()).append("\",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]}");

        return stringBuilder.toString();
    }

    @GetMapping("/getPeriodTypes")
    public String getPeriodTypes() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (TimePeriods time: TimePeriods.values()) {
            stringBuilder.append("\"").append(time.toString()).append("\",");
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");

        return stringBuilder.toString();
    }

    @PostMapping(value = "/createRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public int createRecord(@RequestBody String json, HttpServletResponse response)
            throws ParseException {
        JSONParser obj = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        JSONObject parse = (JSONObject) obj.parse(json);
//        log.info(parse.toString());
        float amount = Float.parseFloat((String) parse.get("amount"));
        String time = (String) parse.get("time");
        String type = (String) parse.get("type");
        String username = (String) parse.get("username");
        Type tempIncome;
        try {
            tempIncome = IncomeType.valueOf(type);
            recordService.save(recordService.getAccountByUsername(username),
                    (IncomeType) tempIncome, amount,TimePeriods.valueOf(time));
        } catch (Exception e) {
            tempIncome = ChargeType.valueOf(type);
            recordService.save(recordService.getAccountByUsername(username),
                    (ChargeType) tempIncome, amount,TimePeriods.valueOf(time));
        }
        return 1;
    }

    @GetMapping(value = "/{username}/getRecords")
    public String getRecords(@PathVariable("username") String username) throws ParseException {
        Set<Record> records = recordService.getAllRecordsByUsername(username);

//        log.info("update - getRecords");

        StringBuilder result = new StringBuilder();

        result.append("[{");
        for (Record r:records) {
            result.append("\"id\":\"").append(r.getId()).append("\",")
                    .append("\"userId\":\"").append(r.getUserId()).append("\",")
                    .append("\"income\":\"").append(r.getIncome()).append("\",")
                    .append("\"charge\":\"").append(r.getCharge()).append("\",")
                    .append("\"amount\":\"").append(r.getAmount()).append("\",")
                    .append("\"period\":\"").append(r.getPeriod()).append("\"},{");
        }
        result.replace(result.length() - 3, result.length(), "}]");
        return result.toString();
    }
}
