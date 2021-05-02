package edu.ada.micronaut.service;

import java.util.List;

public interface FinancialService {

    Object getFinancialData(List<String> stock_index);

}
