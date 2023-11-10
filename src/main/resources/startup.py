#!/usr/bin/python
# -*- coding: UTF-8 -*
import os
import sys
import time

appName = '''mobileserver-0.0.1-SNAPSHOT.jar'''
timeSleep = 3
# setsid = '''setsid '''
nohupHead = '''nohup  '''
nohupTail = ''' > /dev/null 2>&1 &'''
jarCmd = '''java -jar -Dspring.profiles.active={0} {1}'''


def chooseEnvAndExecuteCmd(jarName=appName):
    exeJarCmd = jarCmd
    if isLinux():
        exeJarCmd = nohupHead + jarCmd + nohupTail
        # exeJarCmd = setsid + jarCmd
    while True:
        print("请输入数字(0或1)选择程序运行环境,或输入（exit)退出")
        print("0:测试环境  1:生产环境")
        environment = str(input())
        if environment.isdigit():
            if int(environment) == 0:
                exeJarCmd = exeJarCmd.format("dev", jarName)
                print("测试环境执行的命令:" + exeJarCmd)
            elif int(environment) == 1:
                exeJarCmd = exeJarCmd.format("prod", jarName)
                print("生产环境执行的命令:" + exeJarCmd)
            else:
                print("您输入的数字不为(0或1)，请重新输入!")
                continue
            os.system(exeJarCmd)
            print("程序启动完成!")
            break
        elif environment == "exit":
            print("程序退出!")
            break
        else:
            print("输入值为(" + environment + "),不规范，请重新输入!")
            continue


def killJarIfStartUp(jarName=appName):
    if isLinux():
        result = os.popen("jps -l").read()
        print("当前运行的jar进程有:")
        print(result)
        for lineDetail in result.split(os.linesep):
            if jarName in lineDetail:
                lineDetailArr = lineDetail.strip(" ").split(" ")
                pid = lineDetailArr[0].strip(" ")
                print("程序" + jarName + "的pid为:" + pid)
                print("杀掉java进程:" + jarName)
                print("kill -9 " + pid)
                os.system("kill -9 " + pid)
                print(str(timeSleep) + "秒后程序启动")
                time.sleep(3)
                break


def isLinux():
    osname = sys.platform
    if osname.lower().startswith("linux"):
        print("当前系统为:" + osname)
        return True
    else:
        print("当前系统为:" + osname)
        return False


if __name__ == '__main__':
    killJarIfStartUp()
    chooseEnvAndExecuteCmd()
