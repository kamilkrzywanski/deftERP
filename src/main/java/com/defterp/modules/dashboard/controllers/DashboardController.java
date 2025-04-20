package com.defterp.modules.dashboard.controllers;


import com.defterp.dataAccess.GenericDAO;
import com.defterp.modules.dashboard.queryBuilders.DashboardQueryBuilder;
import com.defterp.translation.annotations.UserLocale;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */
@Named(value = "dashBoard")
@ViewScoped
public class DashboardController implements Serializable {

    private final int NUMBER_OF_WEEKS = 8;
    private final int NUMBER_OF_MONTHS = 6;
    private final int NUMBER_OF_DAYS = 10;
    private final int NUMBER_OF_QUARTERS = 4;
    private final int NUMBER_OF_TOP_ITEMS = 5;
    @Inject
    private GenericDAO dataAccess;
    @Inject
    @UserLocale
    private Locale locale;
    private String query;
    private Gson gson;
    private String periodType;
    private List<Object[]> resultList;
    private String compareTable;
    private String salesCogsProfit;
    private String purchasesAmount;
    private String topSalesByProduct;
    private String topPurchasesByProduct;
    private String topSalesByCustomer;
    private String topPurchasesByVendor;
    private String topReceivablesByCustomer;
    private String topPayablesByVendor;
    private String newCustomers;
    private String reminders;
    private String payableReceivable;
    private String customerPayment;
    private String vendorPayment;
    private int interval;

    @PostConstruct
    public void init() {

        periodType = "Month";
        gson = new Gson();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfSaleOrdersByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getSalesAmountByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getCostOfGoodsSoldByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getTotalProfitByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            compareTable = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getSalesAmountByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getCostOfGoodsSoldByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getTotalProfitByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            resultList.add(resolveMonthName(NUMBER_OF_MONTHS));

            salesCogsProfit = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getPurchasesAmountByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            resultList.add(resolveMonthName(NUMBER_OF_MONTHS));

            purchasesAmount = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopSalesByProductQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topSalesByProduct = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopPurchasesByProductQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topPurchasesByProduct = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopSalesByCustomerQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topSalesByCustomer = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopPurchasesByVendorQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topPurchasesByVendor = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopReceivablesByCustomerQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topReceivablesByCustomer = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTopPayablesByVendorQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            topPayablesByVendor = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getNumberOfNewCustomersByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(resolveMonthName(NUMBER_OF_MONTHS));

            newCustomers = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getNumberOfSaleOrdersToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getNumberOfPurchaseOrdersToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getNumberOfInvoicesToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getNumberOfBillsToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reminders = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getTotalReceivablesQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getTotalPayablesQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            payableReceivable = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getCustomerPaymentsByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(resolveMonthName(NUMBER_OF_MONTHS));
            customerPayment = gson.toJson(resultList);

            // ----------------------------------------------------------------

            resultList.clear();

            query = DashboardQueryBuilder.getVendorPaymentsByPeriodQuery(NUMBER_OF_MONTHS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(resolveMonthName(NUMBER_OF_MONTHS));
            vendorPayment = gson.toJson(resultList);

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void performanceIndicators() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfSaleOrdersByPeriodQuery(2, periodType);
            resultList = dataAccess.findWithNativeQuery(query);

            query = DashboardQueryBuilder.getSalesAmountByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getCostOfGoodsSoldByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getTotalProfitByPeriodQuery(2, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("compareTable", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //    public void saleAmount() {
//
//        PrimeFaces reqCtx = PrimeFaces.current();
//        resultList = new ArrayList<>();
//
//        try {
//
//            query = DashboardQueryBuilder.getSalesAmountByPeriodQuery(6, "Month");
//            resultList.addAll(dataAccess.findWithNativeQuery(query));
//            resultList.add(resolveMonthName(6));
//            reqCtx.ajax().addCallbackParam("salesAmount", gson.toJson(resultList));
//
//        } catch (Exception ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public void topSalesByProduct() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopSalesByProductQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topSalesByProduct", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void topPurchasesByProduct() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopPurchasesByProductQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topPurchasesByProduct", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void topPurchasesByVendor() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopPurchasesByVendorQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topPurchasesByVendor", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void topSalesByCustomer() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopSalesByCustomerQuery(NUMBER_OF_TOP_ITEMS, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topSalesByCustomer", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void topReceivablesByCustomer() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopReceivablesByCustomerQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topReceivablesByCustomer", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void topPayablesByVendor() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTopPayablesByVendorQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("topPayablesByVendor", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newCustomers() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfNewCustomersByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(getPeriodLabels());

            reqCtx.ajax().addCallbackParam("newCustomers", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salesOrdersToConfirm() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfSaleOrdersToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("salesOrdersToConfirm", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void purchaseOrdersToConfirm() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfPurchaseOrdersToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("purchaseOrdersToConfirm", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void invoicesToConfirm() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfInvoicesToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("invoicesToConfirm", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void billsToConfirm() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getNumberOfBillsToConfirmQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("billsToConfirm", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receivables() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTotalReceivablesQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("receivables", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void payables() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getTotalPayablesQuery();
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            reqCtx.ajax().addCallbackParam("payables", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customerPayment() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getCustomerPaymentsByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(getPeriodLabels());

            reqCtx.ajax().addCallbackParam("customerPayment", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vendorPayment() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getVendorPaymentsByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(getPeriodLabels());

            reqCtx.ajax().addCallbackParam("vendorPayment", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salesCogsProfit() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getSalesAmountByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getCostOfGoodsSoldByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            query = DashboardQueryBuilder.getTotalProfitByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));

            resultList.add(getPeriodLabels());

            reqCtx.ajax().addCallbackParam("salesCogsProfit", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void purchasesAmount() {

        PrimeFaces reqCtx = PrimeFaces.current();
        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getPurchasesAmountByPeriodQuery(interval, periodType);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            resultList.add(getPeriodLabels());

            reqCtx.ajax().addCallbackParam("purchasesAmount", gson.toJson(resultList));

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object[] getPeriodLabels() {
        switch (periodType) {
            case "Quarter":
                return resolveQuarterName(interval);
            case "Month":
                return resolveMonthName(interval);
            case "Week":
                return resolveWeekNumber(interval);
            default:
                return resolveDays(interval);
        }
    }

    private Object[] resolveQuarterName(int interval) {

        resultList = new ArrayList<>();

        try {

            query = DashboardQueryBuilder.getGenerateYearQuartersNamesQuery(interval);
            resultList.addAll(dataAccess.findWithNativeQuery(query));
            return resultList.get(0);

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            return new Object[0];
        }
    }

    private Object[] resolveMonthName(int interval) {
        Object[] months = new Object[interval];
        LocalDate date = LocalDate.now();

        for (int i = 0; i < interval; i++) {
            if (i > 0) {
                date = date.minusMonths(1);
            }
            months[i] = date.getMonth().getDisplayName(java.time.format.TextStyle.SHORT, locale);
        }
        return months;
    }

    private Object[] resolveWeekNumber(int interval) {
        Object[] weeks = new Object[interval];
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");

        for (int i = 0; i < interval; i++) {
            if (i > 0) {
                date = date.minusWeeks(1);
            }
            weeks[i] = formatter.format(date.with(java.time.DayOfWeek.SUNDAY));
        }
        return weeks;
    }

    private Object[] resolveDays(int interval) {
        Object[] days = new Object[interval];
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");

        for (int i = 0; i < interval; i++) {
            if (i > 0) {
                date = date.minusDays(1);
            }
            String dayName = date.getDayOfWeek().getDisplayName(java.time.format.TextStyle.SHORT, locale);
            days[i] = dayName + " - " + formatter.format(date);
        }
        return days;
    }

    public String getPeriod() {
        return periodType;
    }

    public void setPeriod(String periodType) {

        this.periodType = periodType;

        switch (periodType) {
            case "Month":
                interval = NUMBER_OF_MONTHS;
                break;
            case "Quarter":
                interval = NUMBER_OF_QUARTERS;
                break;
            case "Week":
                interval = NUMBER_OF_WEEKS;
                break;
            default:
                interval = NUMBER_OF_DAYS;
                break;
        }
    }

    public String getCompareTable() {
        return compareTable;
    }

    public String getSalesCogsProfit() {
        return salesCogsProfit;
    }

    public String getPurchasesAmount() {
        return purchasesAmount;
    }

    public String getTopSalesByProduct() {
        return topSalesByProduct;
    }

    public String getTopPurchasesByProduct() {
        return topPurchasesByProduct;
    }

    public String getTopSalesByCustomer() {
        return topSalesByCustomer;
    }

    public String getTopPurchasesByVendor() {
        return topPurchasesByVendor;
    }

    public String getTopReceivablesByCustomer() {
        return topReceivablesByCustomer;
    }

    public String getTopPayablesByVendor() {
        return topPayablesByVendor;
    }

    public String getNewCustomers() {
        return newCustomers;
    }

    public String getPayableReceivable() {
        return payableReceivable;
    }

    public String getCustomerPayment() {
        return customerPayment;
    }

    public String getVendorPayment() {
        return vendorPayment;
    }

    public String getReminders() {
        return reminders;
    }

}
