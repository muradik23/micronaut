package edu.ada.micronaut.controller;

import java.io.IOException;

public interface FinancialController {

    Object getFinancialData(String financial_data_provider, String... stock_index) throws IOException;

}
