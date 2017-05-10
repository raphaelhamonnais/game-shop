package webservices.rest;

import model.handler.HibernateTransactionHandler;
import webservices.WebServiceException;

public class PaginationHandler {

    private String requestCount;
    private String requestGet;
    private boolean upToDate = false;
    private Long count = 0L;
    private Integer pageSize = 20;
    private Long nbPages = 0L;


    public PaginationHandler(String requestCount, String requestGet) {
        this.requestCount = requestCount;
        this.requestGet = requestGet;
        updateCount();
    }

    private void updateCount() {
        if (requestCount == null || requestCount.isEmpty())
            throw new WebServiceException("Request in PaginationHandler must be set");

        count = (Long) new HibernateTransactionHandler()
                .openSession()
                .createQuery(requestCount)
                .getUniqueResultAndClose();

        Double nbPagesD = Math.ceil(count.doubleValue() / pageSize);
        nbPages = nbPagesD.longValue();
        upToDate = true;
    }

    private void checkAndUpdate() {
        if (! isUpToDate())
            updateCount();
    }


    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public boolean isUpToDate() {
        return upToDate;
    }

    public void setUpToDate(boolean upToDate) {
        this.upToDate = upToDate;
    }

    public long getCount() {
        checkAndUpdate();
        return count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.upToDate = false;
    }

    public long getNbPages() {
        checkAndUpdate();
        return nbPages;
    }

    public String getRequestGet() {
        return requestGet;
    }
}
