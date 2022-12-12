package multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 스레드풀샘플
 */
public class MultiThread implements Runnable{
	
	//출력 메서지 템플릿
	
	private static final String MSG_TEMPLATE = "출력 중입니다. [%s][%d회]";
	
	//스레드명
	
	private final String threadName;
	
	public MultiThread(String threadName) {
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		for(int i = 1; i<100; i++) {
			System.out.println(String.format(MSG_TEMPLATE, threadName, i));
		}
	}
	

	public static void main(String[] args) {
		MultiThread runnable1 = new MultiThread("multiThread1");
		MultiThread runnable2 = new MultiThread("multiThread2");
		MultiThread runnable3 = new MultiThread("multiThread3");
		
		//스레드 동시 실행 수는 3
		
		ExecutorService	executorService = Executors.newFixedThreadPool(3);
		executorService.execute(runnable1);
		executorService.execute(runnable2);
		executorService.execute(runnable3);
		
		executorService.shutdown();
		
		try {
			if(!executorService.awaitTermination(5, TimeUnit.MINUTES)) {
				//타임아웃 후에도 아직 실행이 끝나지 않았다.
				executorService.shutdown();
			}
		}catch (Exception e) {
			// 종료 대기 시에 뭔가 오류가 발생했다.
			System.out.println(e.getMessage());
			executorService.shutdown();
		}
		
		
	}

}
