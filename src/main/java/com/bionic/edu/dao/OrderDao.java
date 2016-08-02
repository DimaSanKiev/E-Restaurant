package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportDish;
import com.bionic.edu.util.ReportTotal;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends GenericDao<Orders> {

    /**
     * Finds all orders with 'READY_FOR_SHIPMENT' status or 'DELIVERING' status,
     * ordered by order's taking time.
     *
     * @return list of orders ordered by taking time or {@literal null} if none found
     */
    List<Orders> getDeliveryListByTime();

    /**
     * Finds all orders with 'READY_FOR_SHIPMENT' status or 'DELIVERING' status,
     * ordered by order's status. Orders with 'READY_FOR_SHIPMENT' goes first.
     *
     * @return list of orders ordered by order's status or {@literal null} if none found
     */
    List<Orders> getDeliveryListByStatus();

    /**
     * Finds all the specified customer's orders.
     *
     * @param customerId id of a customer whose orders are looking for
     * @return list of customer's orders or {@literal null} if none found
     */
    List<Orders> getCustomersOrder(int customerId);

    /**
     * Returns the total report of orders during the specified period.
     *
     * @param startPeriod start date of report
     * @param endPeriod   end date of report
     * @return list of {@link com.bionic.edu.util.ReportTotal} instances
     * or {@literal null} if none found
     */
    List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod);

    /**
     * Returns report by dish categories during the specified period.
     *
     * @param startPeriod start date of report
     * @param endPeriod   end date of report
     * @return list of {@link com.bionic.edu.util.ReportCategory} instances
     * or {@literal null} if none found
     */
    List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod);

    /**
     * Returns report of sold dishes during the specified period.
     *
     * @param startPeriod start date of report
     * @param endPeriod   end date of report
     * @return list of {@link com.bionic.edu.util.ReportDish} instances
     * or {@literal null} if none found
     */
    List<ReportDish> getReportDish(Date startPeriod, Date endPeriod);
}
