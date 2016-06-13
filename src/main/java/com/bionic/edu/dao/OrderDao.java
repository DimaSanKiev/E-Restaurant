package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends GenericDao<Orders> {

    List<Orders> getDeliveryListByTime();

    List<Orders> getDeliveryListByStatus();

    List<Orders> getCustomersOrder(int customerId);


    List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod);

    List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod);
}
