package com.scrumtrek.simplestore;

/**
 * Created by Rihter on 20.11.2015.
 */
public interface IReportCreator
{
    void initialize(String header);
    void addReportItem(ReportItem reportItem);
    void finalize(String optional);

    String getReport();
}
