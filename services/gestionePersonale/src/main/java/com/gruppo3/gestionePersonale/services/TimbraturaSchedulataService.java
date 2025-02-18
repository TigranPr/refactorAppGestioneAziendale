package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.TimbraturaRequest;
import com.gruppo3.gestionePersonale.dto.TimbraturaSchedulataRequest;
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

    public EntityIdResponse createTimbraturaScheduled(TimbraturaSchedulataRequest request) throws MyEntityNotFoundException, SchedulingException, SchedulerException {
        var utente = utenteClient.getUtenteById(request.dipendenteId());
        TimbraturaSchedulata timbraturaScheduled = TimbraturaSchedulata.builder()
                .ingresso(request.ingresso())
                .uscita(request.uscita())
                .inizioPausaPranzo(request.inizioPausaPranzo())
                .finePausa(request.finePausa())
                .dipendenteId(request.dipendenteId())
                .build();
        timbraturaSchedulataRepository.save(timbraturaScheduled);
        JobDetail jobDetails = buildJobDetail(timbraturaScheduled);
        Trigger trigger = buildJobTrigger(jobDetails, convertToDate(timbraturaScheduled.getPublishTime()));
        scheduler.scheduleJob(jobDetails, trigger);
        return EntityIdResponse.builder().id(timbraturaScheduled.getId()).build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date publishTime) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(publishTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();
    }

    private JobDetail buildJobDetail(TimbraturaSchedulata timbraturaSchedulata) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("id", timbraturaSchedulata.getId());
        jobDataMap.put("entityData", timbraturaSchedulata);
        return JobBuilder.newJob(TimbraturaSchedulataService.class)
                .withIdentity(String.valueOf(timbraturaSchedulata.getId()), "comunicazioni")
                .storeDurably()
                .setJobData(jobDataMap)
                .build();
    }

    private Date convertToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        Long scheduledId = jobDataMap.getLong("id");
        try {
            TimbraturaSchedulata timbraturaSchedulata = timbraturaSchedulataRepository.findById(scheduledId)
                    .orElseThrow(() -> new JobExecutionException("Comunicazione non trovata"));

            Long idUtente = timbraturaSchedulata.getId();
            var utente = utenteClient.getUtenteById(idUtente);

            TimbraturaRequest request = new TimbraturaRequest(
                    timbraturaSchedulata.getIngresso(),
                    timbraturaSchedulata.getUscita(),
                    timbraturaSchedulata.getInizioPausaPranzo(),
                    timbraturaSchedulata.getFinePausa(),
                    idUtente);
            timbraturaService.createTimbratura(request);

            timbraturaSchedulataRepository.deleteById(scheduledId);
        } catch (Exception e) {
            throw new JobExecutionException("Errore durante l'esecuzione della comunicazione", e);
        }
    }
}
