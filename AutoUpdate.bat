@echo off
rem 更新当天新闻以及近四天评论
cd .\InfromationRetrival\news_sina\news_sina\
python main.py
rem 更新索引
cd ../../../
java -jar CreateIndex.jar
shutdown -s