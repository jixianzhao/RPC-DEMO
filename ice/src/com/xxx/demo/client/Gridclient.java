package com.xxx.demo.client;

import com.xxx.demo.demo.MyServicePrx;
import com.xxx.demo.demo.MyServicePrxHelper;
import com.xxx.demo.demo.Order;

/**
 * Created by magicdoom on 2015/7/11.
 */
public class Gridclient
{


    public void      start(int perCount)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
           // String[] initParams = new String[] { "--Ice.Default.Locator=IceGrid/Locator:tcp -h 127.0.0.1 -p 7061"
               //};
            String[] initParams = new String[] { "--Ice.Config=client.properties"
            };
            ic = Ice.Util.initialize(initParams);
            // 传入远程服务单元的名称、网络协议、IP以及端口，获取OnlineBook的远程代理，这里使用stringToProxy方式
            Ice.ObjectPrx base = ic
                    .stringToProxy("OnlineBook");
            // 通过checkedCast向下转型，获取OnlineBook接口的远程，并同时检测根据传入的名称获取服务单元是否OnlineBook的代理接口，如果不是则返回null对象
            MyServicePrx onlinBook = MyServicePrxHelper.uncheckedCast(base);
            if (onlinBook == null) {
                throw new Error("Invalid proxy");
            }


            long start=System.currentTimeMillis();
            int count=perCount;
            Order x=null;
            for(int i=0;i<count;i++)
            {
                Order order=new Order();
                order.orderDate=count;
                order.orderId=start;
                order.orderNum= String.valueOf(i);
                order.phone ="1358"+i ;
                order.ticketType=i ;
                order.orderStatus=i;
                order.amount=i;
                x=   onlinBook.hello(order);
                //System.out.println(x);
            }
            long used=System.currentTimeMillis()-start;
           System.out.println(x);
            System.out.println("tps "+count*1000.0/used);
        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
//       System.exit(status);
    }






    public static void main(String[] args) {
        new Gridclient().start(1);
    }
}
