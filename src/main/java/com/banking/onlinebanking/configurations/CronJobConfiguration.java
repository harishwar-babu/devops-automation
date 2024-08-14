package com.banking.onlinebanking.configurations;
import com.banking.onlinebanking.service.impl.DeactivatedAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class CronJobConfiguration {
    private final DeactivatedAccountService deactivatedAccountService;

    @Scheduled(fixedRateString = "#{@bankConfiguration.getDeactivationInterval()}000")
    /*
    @Scheduled(cron = "0 * * * *)"
     */
    private void executeCron(){
        log.info("cron executed for deactivation at : {}",LocalDateTime.now());
        deactivatedAccountService.removeDeactivatedAccounts();
    }
}
