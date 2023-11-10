#!/usr/bin/python
# -*- coding: UTF-8 -*
import os
import sys
import time

appName = '''mobileserver-0.0.1-SNAPSHOT.jar'''
timeSleep = 3
nohupHead = '''nohup  '''
nohupTail = ''' > /dev/null 2>&1 &'''
jarCmd = " java -Xms2g -Xmx2g -Xmn1g -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m "
printGC = " -Xloggc:{0}mobile_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M "
printOutMemmoryError = " -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath={1}heapdump.hprof "
jar = ''' -jar -Dspring.profiles.active={2} {3} --logging.config=file:./config/logback.xml'''


def chooseEnvAndExecuteCmd(jarName=appName):
    exeJarCmd = jarCmd
    if isLinux():
        exeJarCmd = nohupHead + exeJarCmd + nohupTail
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


def mkdirIfNotExists(dirName):
    if os.path.exists(dirName) and os.path.isfile(dirName):
        os.remove(dirName)
    if not os.path.exists(dirName):
        os.mkdir(dirName)


def genJarCmd():
    result = jarCmd + printGC + printOutMemmoryError + jar
    return result


def executeTestCmd(jarName=appName):
    exeJarCmd = genJarCmd()
    outMemmoryPath = os.getcwd() + os.sep
    gcPath = os.getcwd() + os.sep + "log" + os.sep
    if isLinux():
        exeJarCmd = nohupHead + exeJarCmd + nohupTail
    exeJarCmd = exeJarCmd.format(gcPath, outMemmoryPath, "dev", jarName)
    print("测试环境执行的命令:" + exeJarCmd)
    os.system(exeJarCmd)
    print("程序启动完成!")


def executeProdCmd(jarName=appName):
    exeJarCmd = genJarCmd()
    outMemmoryPath = os.getcwd() + os.sep
    gcPath = os.getcwd() + os.sep + "log" + os.sep
    if isLinux():
        exeJarCmd = nohupHead + exeJarCmd + nohupTail
    exeJarCmd = exeJarCmd.format(gcPath, outMemmoryPath, "prod", jarName)
    print("生产环境执行的命令:" + exeJarCmd)
    os.system(exeJarCmd)
    print("程序启动完成!")


def isLinux():
    osname = sys.platform
    if osname.lower().startswith("linux"):
        print("当前系统为:" + osname)
        return True
    else:
        print("当前系统为:" + osname)
        return False


if __name__ == '__main__':
    mkdirIfNotExists("log")
    killJarIfStartUp()
    executeProdCmd()
