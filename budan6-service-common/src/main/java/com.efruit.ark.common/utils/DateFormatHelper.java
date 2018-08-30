package com.efruit.ark.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormatHelper {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }

        final String format = DateFormatUtils.format(date, DATE_FORMAT);
        return format;
    }

    public static String formatDateList(List<Date> dateList) {
        if (CollectionUtils.isEmpty(dateList)) {
            return "";
        }

        List<String> dateStrList = new ArrayList<>();
        for (Date date : dateList) {
            if (date != null) {
                dateStrList.add(DateFormatUtils.format(date, DATE_FORMAT));
            }
        }

        return StringUtils.join(dateStrList, ",");
    }
}
