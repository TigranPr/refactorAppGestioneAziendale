package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.TimbraturaRequest;
import com.gruppo3.gestionePersonale.dto.TimbraturaSchedulataRequest;
import com.gruppo3.gestionePersonale.entity.Timbratura;
import com.gruppo3.gestionePersonale.entity.TimbraturaSchedulata;
import com.gruppo3.gestionePersonale.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestionePersonale.repository.TimbraturaSchedulataRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TimbraturaSchedulataService implements Job {

    @Autowired
    private TimbraturaSchedulataRepository timbraturaSchedulataRepository;
    @Autowired
    private TimbraturaService timbraturaService;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private UtenteClient utenteClient;

    public void timbratureGiornaliereScheduler() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TimbraturaSchedulataService.class)
                .withIdentity("timbrature giornaliere", "emailJobs")
                .storeDurably()
                .build();
        Trigger trigger = buildJobTrigger(jobDetail);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private Trigger buildJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(18, 5))
                .build();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = now.toLocalDate().atTime(18, 0, 0);
            List<Timbratura> timbrature = timbraturaService.getTimbratureGiornaliere(startOfDay, endOfDay);
            if (timbrature.isEmpty()) {
                return;
            }
            String csvContent = generateCSV(timbrature); // Genera il CSV
            sendEmailWithCSV(csvContent); // Invia l'email con il CSV in allegato
        } catch (Exception e) {
            throw new JobExecutionException("Errore durante la generazione del riepilogo delle timbrature", e);
        }
    }

    public String generateCSV(List<Timbratura> timbrature) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID, DipendenteID, Ingresso, Uscita, Inizio Pausa, Fine Pausa\n");
        for (Timbratura t : timbrature) {
            csvBuilder.append(t.getId()).append(",")
                    .append(t.getDipendenteId()).append(",")
                    .append(t.getIngresso()).append(",")
                    .append(t.getUscita()).append(",")
                    .append(t.getInizioPausaPranzo()).append(",")
                    .append(t.getFinePausa())
                    .append("\n");
        }
        return csvBuilder.toString();
    }
}
