java -Xms2G -Xmx2G -classpath ./out/production/thrift  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc100.log -Dwrite.statistics=true -Djava.ext.dirs="./lib" com.th.hello.test.THTest 100 300000 > benchmark100.log
pause