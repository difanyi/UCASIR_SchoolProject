@echo off
rem ���µ��������Լ�����������
cd .\InfromationRetrival\news_sina\news_sina\
python main.py
rem ��������
cd ../../../
java -jar CreateIndex.jar
shutdown -s