package com.defterp.modules.dashboard.queryBuilders;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

public class DashboardQueryBuilder {


    public static String getGenerateYearQuartersNamesQuery(int numberOfPeriods) {

        String query = "SELECT ";

        for (int i = 0; i < numberOfPeriods; i++) {
            if (i < numberOfPeriods - 1) {
                query += "concat(EXTRACT(YEAR FROM CURRENT_DATE - interval '" + i + " quarter'), '-Q', EXTRACT(QUARTER FROM CURRENT_DATE - interval '" + i + " quarter')), ";
            } else {
                query += "concat(EXTRACT(YEAR FROM CURRENT_DATE - interval '" + i + " quarter'), '-Q', EXTRACT(QUARTER FROM CURRENT_DATE - interval '" + i + " quarter'));";
            }
        }

        return query;
    }


    public static String getNumberOfSaleOrdersByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN 1 ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN 1 ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN 1 ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN 1 ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM date), EXTRACT(QUARTER FROM date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN 1 ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM date), EXTRACT(QUARTER FROM date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN 1 ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN 1 ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN 1 ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"sale_order\";";
        return query;
    }

    public static String getSalesAmountByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.credit ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.credit ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.credit ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.credit ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.credit ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.credit ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.credit ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.credit ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"journal_item\" item \n" +
                "JOIN \"journal\" journal ON item.journal_id = journal.id \n" +
                "WHERE journal.name = 'Customer Invoices';";
        return query;
    }

    public static String getCostOfGoodsSoldByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.cost_of_goods_sold ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.cost_of_goods_sold ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.cost_of_goods_sold ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.cost_of_goods_sold ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.cost_of_goods_sold ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.cost_of_goods_sold ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.cost_of_goods_sold ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.cost_of_goods_sold ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"journal_item\" item \n" +
                "JOIN \"journal\" journal ON item.journal_id = journal.id \n" +
                "WHERE journal.name = 'Customer Invoices';";
        return query;
    }

    public static String getTotalProfitByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN (item.credit - item.cost_of_goods_sold) ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"journal_item\" item \n" +
                "JOIN \"journal\" journal ON item.journal_id = journal.id \n" +
                "WHERE journal.name = 'Customer Invoices';";
        return query;
    }


    public static String getPurchasesAmountByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.debit ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(item.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN item.debit ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.debit ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN item.debit ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.debit ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM item.date), EXTRACT(QUARTER FROM item.date)) = CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN item.debit ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.debit ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(item.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN item.debit ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"journal_item\" item \n" +
                "JOIN \"journal\" journal ON item.journal_id = journal.id \n" +
                "WHERE journal.name = 'Vendor Bills';";
        return query;
    }

    public static String getTopSalesByProductQuery(int nProducts, String periodType) {
        if (nProducts <= 0) {
            nProducts = 5;
        }
        String query = "SELECT pr.name, SUM(line.sub_total) AS total_sales, SUM(line.quantity) AS total_quantity " +
                "FROM \"sale_order_line\" line " +
                "JOIN \"product\" pr ON line.product_id = pr.id " +
                "JOIN \"sale_order\" sale ON line.order_id = sale.id WHERE ";
        switch (periodType) {
            case "Month":
                query += "TO_CHAR(sale.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '0 month', 'YYYY-MM') ";
                break;
            case "Quarter":
                query += "CONCAT(EXTRACT(YEAR FROM sale.date), EXTRACT(QUARTER FROM sale.date)) = " +
                        "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '0 quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '0 quarter'))";
                break;
            case "Day":
                query += "DATE(sale.date) = CURRENT_DATE - INTERVAL '0 day' ";
                break;
            default: // Week
                query += "TO_CHAR(sale.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '0 week', 'IYYY-IW') ";
                break;
        }
        query += "GROUP BY pr.name ORDER BY SUM(line.sub_total) DESC LIMIT " + nProducts + ";";
        return query;
    }

    public static String getTopPurchasesByProductQuery(int nProducts, String periodType) {
        if (nProducts <= 0) {
            nProducts = 5;
        }
        String query = "SELECT pr.name, SUM(line.sub_total) AS total_sub_total, SUM(line.quantity) AS total_quantity " +
                "FROM \"purchase_order_line\" line " +
                "JOIN \"product\" pr ON line.product_id = pr.id " +
                "JOIN \"purchase_order\" purchase ON line.order_id = purchase.id WHERE ";

        switch (periodType) {
            case "Month":
                query += "TO_CHAR(purchase.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '0 month', 'YYYY-MM') ";
                break;
            case "Quarter":
                query += "CONCAT(EXTRACT(YEAR FROM purchase.date), EXTRACT(QUARTER FROM purchase.date)) = " +
                        "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '0 quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '0 quarter'))";
                break;
            case "Day":
                query += "DATE(purchase.date) = CURRENT_DATE - INTERVAL '0 day' ";
                break;
            default: // Week
                query += "TO_CHAR(purchase.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '0 week', 'IYYY-IW') ";
                break;
        }
        query += "GROUP BY pr.name ORDER BY SUM(line.sub_total) DESC LIMIT " + nProducts + ";";
        return query;
    }

    public static String getTopPurchasesByVendorQuery(int nPartners, String periodType) {
        if (nPartners <= 0) {
            nPartners = 5;
        }
        String query = "SELECT par.name, SUM(purchase.amount_total) AS total_purchases, COUNT(*) AS total_orders " +
                "FROM \"purchase_order\" purchase " +
                "JOIN \"partner\" par ON purchase.partner_id = par.id WHERE ";

        switch (periodType) {
            case "Month":
                query += "TO_CHAR(purchase.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '0 month', 'YYYY-MM') ";
                break;
            case "Quarter":
                query += "CONCAT(EXTRACT(YEAR FROM purchase.date), EXTRACT(QUARTER FROM purchase.date)) = " +
                        "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '0 quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '0 quarter'))";
                break;
            case "Day":
                query += "DATE(purchase.date) = CURRENT_DATE - INTERVAL '0 day' ";
                break;
            default: // Week
                query += "TO_CHAR(purchase.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '0 week', 'IYYY-IW') ";
                break;
        }

        query += "AND par.supplier = true GROUP BY par.name ORDER BY SUM(purchase.amount_total) DESC LIMIT " + nPartners + ";";
        return query;
    }

    public static String getTopSalesByCustomerQuery(int nPartners, String periodType) {
        if (nPartners < 0) {
            nPartners = 5;
        }
        String query = "SELECT par.name, SUM(sale.amount_total) AS total_sales, COUNT(*) AS total_orders " +
                "FROM \"sale_order\" sale " +
                "JOIN \"partner\" par ON sale.partner_id = par.id WHERE ";
        switch (periodType) {
            case "Month":
                query += "TO_CHAR(sale.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '0 month', 'YYYY-MM') ";
                break;
            case "Quarter":
                query += "CONCAT(EXTRACT(YEAR FROM sale.date), EXTRACT(QUARTER FROM sale.date)) = " +
                        "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '0 quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '0 quarter'))";
                break;
            case "Day":
                query += "DATE(sale.date) = CURRENT_DATE - INTERVAL '0 day' ";
                break;
            default: // Week
                query += "TO_CHAR(sale.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '0 week', 'IYYY-IW') ";
                break;
        }
        query += "AND par.customer = true GROUP BY par.name ORDER BY SUM(sale.amount_total) DESC LIMIT " + nPartners + ";";
        return query;
    }

    public static String getNumberOfNewCustomersByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(create_date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN 1 ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(create_date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN 1 ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;
            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN 1 ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN 1 ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;
            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM create_date), EXTRACT(QUARTER FROM create_date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN 1 ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM create_date), EXTRACT(QUARTER FROM create_date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN 1 ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;
            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN 1 ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(create_date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN 1 ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"partner\" WHERE customer = true;";
        return query;
    }

    public static String getNumberOfSaleOrdersToConfirmQuery() {
        String query = "SELECT count(*), COALESCE(SUM(amount_total), 0) " +
                "FROM \"sale_order\" " +
                "WHERE state = 'Quotation';";
        return query;
    }

    public static String getNumberOfPurchaseOrdersToConfirmQuery() {
        String query = "SELECT count(*), COALESCE(SUM(amount_total), 0) " +
                "FROM \"purchase_order\" " +
                "WHERE state = 'Quotation';";
        return query;
    }

    public static String getNumberOfInvoicesToConfirmQuery() {
        String query = "SELECT count(*), COALESCE(SUM(amount_total), 0) " +
                "FROM \"invoice\" " +
                "WHERE state = 'Draft' AND type = 'Sale';";
        return query;
    }

    public static String getNumberOfBillsToConfirmQuery() {
        String query = "SELECT count(*), COALESCE(SUM(amount_total), 0) " +
                "FROM \"invoice\" " +
                "WHERE state = 'Draft' AND type = 'Purchase';";
        return query;
    }

    public static String getTopReceivablesByCustomerQuery() {
        String query = "SELECT par.name, SUM(inv.residual) " +
                "FROM \"invoice\" inv " +
                "JOIN \"partner\" par ON inv.partner_id = par.id " +
                "WHERE inv.state = 'Open' AND type = 'Sale' AND inv.residual > 0 " +
                "GROUP BY par.name " +
                "ORDER BY SUM(inv.residual) DESC LIMIT 5;";
        return query;
    }

    public static String getTopPayablesByVendorQuery() {
        String query = "SELECT par.name, SUM(inv.residual) " +
                "FROM \"invoice\" inv " +
                "JOIN \"partner\" par ON inv.partner_id = par.id " +
                "WHERE inv.state = 'Open' AND type = 'Purchase' AND inv.residual > 0 " +
                "GROUP BY par.name " +
                "ORDER BY SUM(inv.residual) DESC LIMIT 5;";
        return query;
    }

    public static String getTotalReceivablesQuery() {
        String query = "SELECT SUM(inv.residual) " +
                "FROM \"invoice\" inv " +
                "WHERE inv.state = 'Open' AND type = 'Sale';";
        return query;
    }

    public static String getTotalPayablesQuery() {
        String query = "SELECT SUM(inv.residual) " +
                "FROM \"invoice\" inv " +
                "WHERE inv.state = 'Open' AND type = 'Purchase';";
        return query;
    }

    public static String getCustomerPaymentsByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(invPay.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN invPay.paid_amount ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(invPay.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN invPay.paid_amount ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;

            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN invPay.paid_amount ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN invPay.paid_amount ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;

            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM invPay.date), EXTRACT(QUARTER FROM invPay.date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN invPay.paid_amount ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM invPay.date), EXTRACT(QUARTER FROM invPay.date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN invPay.paid_amount ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;

            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN invPay.paid_amount ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN invPay.paid_amount ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"invoice_payment\" invPay \n" +
                "JOIN \"journal_entry\" entry ON invPay.journal_entry_id = entry.id \n" +
                "JOIN \"payment\" pay ON pay.entry_id = entry.id \n" +
                "JOIN \"journal\" journal ON pay.journal_id = journal.id \n" +
                "WHERE pay.type = 'in' AND pay.partner_type = 'customer' AND invPay.name <> 'Write-Off' \n" +
                "GROUP BY journal.name ORDER BY journal.name ASC;";
        return query;
    }

    public static String getVendorPaymentsByPeriodQuery(int numberOfPeriods, String periodType) {
        String query = "SELECT ";
        switch (periodType) {
            case "Day":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN DATE(invPay.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN invPay.paid_amount ELSE 0 END) AS day_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN DATE(invPay.date) = CURRENT_DATE - INTERVAL '" + i + " day' THEN invPay.paid_amount ELSE 0 END) AS day_" + i + " ";
                    }
                }
                break;

            case "Month":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN invPay.paid_amount ELSE 0 END) AS month_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'YYYY-MM') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " month', 'YYYY-MM') THEN invPay.paid_amount ELSE 0 END) AS month_" + i + " ";
                    }
                }
                break;

            case "Quarter":
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM invPay.date), EXTRACT(QUARTER FROM invPay.date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN invPay.paid_amount ELSE 0 END) AS quarter_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN CONCAT(EXTRACT(YEAR FROM invPay.date), EXTRACT(QUARTER FROM invPay.date)) = " +
                                "CONCAT(EXTRACT(YEAR FROM CURRENT_DATE - INTERVAL '" + i + " quarter'), EXTRACT(QUARTER FROM CURRENT_DATE - INTERVAL '" + i + " quarter')) THEN invPay.paid_amount ELSE 0 END) AS quarter_" + i + " ";
                    }
                }
                break;

            default: // Week
                for (int i = 0; i < numberOfPeriods; i++) {
                    if (i < numberOfPeriods - 1) {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN invPay.paid_amount ELSE 0 END) AS week_" + i + ", ";
                    } else {
                        query += "SUM(CASE WHEN TO_CHAR(invPay.date, 'IYYY-IW') = TO_CHAR(CURRENT_DATE - INTERVAL '" + i + " week', 'IYYY-IW') THEN invPay.paid_amount ELSE 0 END) AS week_" + i + " ";
                    }
                }
                break;
        }
        query += "FROM \"invoice_payment\" invPay \n" +
                "JOIN \"journal_entry\" entry ON invPay.journal_entry_id = entry.id \n" +
                "JOIN \"payment\" pay ON pay.entry_id = entry.id \n" +
                "JOIN \"journal\" journal ON pay.journal_id = journal.id \n" +
                "WHERE pay.type = 'out' AND pay.partner_type = 'supplier' AND invPay.name <> 'Write-Off' \n" +
                "GROUP BY journal.name ORDER BY journal.name ASC;";
        return query;
    }

}
