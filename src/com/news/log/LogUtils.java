package com.news.log;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogUtils {    
    public Logger createLog(String loggerName) throws IOException{  
        Logger log = Logger.getLogger(loggerName);  //创建一个唯一的log标志，一般用类的名称  
        log.setLevel(Level.ALL);  //设置日志级别
        FileHandler fileHandler = new FileHandler("indexlog.log",true);  //生成testlog.log的日志文件，文件是可追加的
        fileHandler.setLevel(Level.ALL);  //设置日志级别
        fileHandler.setFormatter(new LogFormatter());  //设置日志格式
        log.addHandler(fileHandler);  
        return log;
    } 
} 


class LogFormatter extends Formatter {  
    @Override  
    public String format(LogRecord record) {  
        Date date = new Date();  
        String sDate = date.toString();  
        return "[" + sDate + "]" + "[" + record.getLevel() + "]"  
                + record.getLoggerName() + record.getMessage() + "\r\n";  
    }  
}
