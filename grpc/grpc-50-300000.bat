java -Xms2G -Xmx2G -classpath ./target/classes  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc50.log -Dwrite.statistics=true -Djava.ext.dirs="./lib" io.grpc.examples.test.GRPCTest 50 300000 > benchmark50.log
pause