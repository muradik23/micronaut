package edu.ada.micronaut.service.impl;

import edu.ada.micronaut.service.FinancialService;
//import org.slf4j.Logger;
import java.time.Instant;
import java.util.logging.*;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

@Singleton
public class YahooFinanceServiceImpl implements FinancialService {

//    protected static final Logger logger = LoggerFactory.getLogger(YahooFinanceServiceImpl.class);
    Logger logger = Logger.getLogger(YahooFinanceServiceImpl.class.getName());
    FileHandler fh;
    {
        try {
            fh = new FileHandler("logs.log"); // logs are saved in logs.log file;
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getFinancialData(List<String> stocks) {
        Map<String, BigDecimal> stocksPrice = new HashMap<>();
        try {
            logger.info(Instant.now().toString() + ": User requested " + stocks.toString());
            String[] stockArr = new String[stocks.size()];
            stockArr = stocks.toArray(stockArr);
            Map<String, Stock> stocksMap = YahooFinance.get(stockArr);
            stocksMap.forEach((k, v) -> {
                try {
                    stocksPrice.put(k, v.getQuote(true).getPrice());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return stocksPrice;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stocksPrice;
    }
}

