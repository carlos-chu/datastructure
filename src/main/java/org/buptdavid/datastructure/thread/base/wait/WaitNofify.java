package org.buptdavid.datastructure.thread.base.wait;

/**
 * 生产者
 * 
 * @author bjchuwenqiang
 * @date 2018年4月23日
 */
class Produce extends Thread {
	public void produce() {
		synchronized (WaitNofify.obj) {
			if (WaitNofify.produceCount > WaitNofify.MAX) {
				try {
					System.out.println("产品已满,请稍候再生产");
					WaitNofify.obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			WaitNofify.produceCount++;
			System.out.println("生产者生产第" + WaitNofify.produceCount + "个产品.");
			WaitNofify.obj.notifyAll();
		}
	}

	@Override
	public void run() {
		while (true) {
			produce();
		}
	}
}

class Consume extends Thread {
	public void consume() {
		synchronized (WaitNofify.obj) {
			if (WaitNofify.produceCount <= 0) {
				try {
					System.out.println("没商品了，等待生产");
					WaitNofify.obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("消费者取走了第" + WaitNofify.produceCount + "个产品.");
			WaitNofify.produceCount--;
			WaitNofify.obj.notifyAll();
		}
	}

	@Override
	public void run() {
		while (true) {
			consume();
		}
	}

}

public class WaitNofify {
	public static Object obj = new Object();
	public static final int MAX = 5;
	public static int produceCount = 0;

	public static void main(String[] args) {
		new Produce().start();
		new Consume().start();
	}

}
