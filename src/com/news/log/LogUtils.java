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
        Logger log = Logger.getLogger(loggerName);  //����һ��Ψһ��log��־��һ�����������  
        log.setLevel(Level.ALL);  //������־����
        FileHandler fileHandler = new FileHandler("indexlog.log",true);  //����testlog.log����־�ļ����ļ��ǿ�׷�ӵ�
        fileHandler.setLevel(Level.ALL);  //������־����
        fileHandler.setFormatter(new LogFormatter());  //������־��ʽ
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
