package com.suminghui.bycar.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class LoggerAppender extends DailyRollingFileAppender {

    /**
     * 解决日志安级别输出的目的
     * 源代码
     * public boolean isAsSevereAsThreshold(Priority priority)
     * {
     * return threshold == null || priority.isGreaterOrEqual(threshold);
     * }
     * 源代码本来的意思是如果该级别低则输出的日志中就包含级别高的日志信息，比如说info级别的日志信息输出就会包含error级别的信息
     * 我们通过复写DailyRollingFileAppender中的isAsSevereAsThreshold方法，将原来的逻辑更改成只有带级别相同的时候再输出
     */
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        // 只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }

}
