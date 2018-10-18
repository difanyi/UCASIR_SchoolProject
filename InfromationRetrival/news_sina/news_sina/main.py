from scrapy import cmdline
import logging
import logging.handlers

LOG_FILE = 'newsupdate.log'

handler = logging.handlers.RotatingFileHandler(LOG_FILE, maxBytes = 1024*1024, backupCount = 5) # 实例化handler   
fmt = '%(asctime)s - %(filename)s:%(lineno)s - %(name)s - %(message)s'  
  
formatter = logging.Formatter(fmt)   # 实例化formatter  
handler.setFormatter(formatter)      # 为handler添加formatter  
  
logger = logging.getLogger('update news and comments')    # 获取名为tst的logger  
logger.addHandler(handler)           # 为logger添加handler  
logger.setLevel(logging.DEBUG)  
  
logger.info('更新新闻及评论... ...')  

cmdline.execute("scrapy crawl news".split())

logger.info('更新完成')  