package com.kosta.KOSTA_3_final.service.subscribe;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class ReqPaymentScheduler {
    private ThreadPoolTaskScheduler scheduler;
	@Autowired
	SchedulePayment setSchedulePay;
 
    public void stopScheduler() {
        scheduler.shutdown();
    }
 
    public void startScheduler(int customer_uid, int price) {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        // 스케쥴러가 시작되는 부분 
        scheduler.schedule(getRunnable(customer_uid, price), getTrigger());
    }
 
    private Runnable getRunnable(int customer_uid, int price){
        return () -> {
        	setSchedulePay.schedulePay(customer_uid, price);
        };
    }
 
    private Trigger getTrigger() {
        // 작업 주기 설정 
        return new PeriodicTrigger(3, TimeUnit.MINUTES);
    }
}