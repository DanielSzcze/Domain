package site.danielszczesny.backend;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.minidev.json.reader.JsonWriter;
import site.danielszczesny.backend.model.timofinance.ChargeType;
import site.danielszczesny.backend.model.timofinance.IncomeType;

import java.util.Arrays;


public class Runner {
    public static void main(String[] args) throws ParseException {
        String temp = Arrays.toString(ChargeType.values());
        System.out.println(temp);

        JSONParser obj = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

        JSONObject parse = (JSONObject) obj.parse(Arrays.toString(IncomeType.values()));
        System.out.println(parse.toJSONString());
    }
}
