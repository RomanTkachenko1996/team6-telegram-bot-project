package edu.goit.feature.handler;

import java.util.HashMap;
import java.util.Map;

public class HashMapCurrencyModeService {
    private final Map<Long, String> timeMassage = new HashMap<>();
    private final Map<String, String> callbackData = new HashMap<>();

    public String getOriginalCurrency(long chatId, String callbackData) {
        return timeMassage.getOrDefault(chatId,callbackData);
    }

//    public String getCallbackDataForTime(String mainCallbackData, String callbackData) {
//        return callbackData.getOrDefault(mainCallbackData,callbackData);
//    }
}
