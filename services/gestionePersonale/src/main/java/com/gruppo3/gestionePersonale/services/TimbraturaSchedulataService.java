package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.entity.Timbratura;
import com.gruppo3.gestionePersonale.repository.TimbraturaSchedulataRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private JavaMailSender javaMailSender;

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
            LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);
            List<Timbratura> timbrature = timbraturaService.getTimbratureGiornaliere(startOfDay, endOfDay);
            if (timbrature.isEmpty()) {
                return;
            }
            String csvContent = generateCSV(timbrature);
            sendEmailWithCSV(csvContent);
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

    public void sendEmailWithCSV(String csvContent) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("fcramerotti91@gmail.com");
            helper.setSubject("Riepilogo Timbrature Giornaliere");
            helper.setText("In allegato trovi il riepilogo delle timbrature giornaliere in formato CSV.");
            File tempFile = File.createTempFile("timbrature", ".csv");
            java.nio.file.Files.write(tempFile.toPath(), csvContent.getBytes());
            helper.addAttachment(tempFile.getName(), tempFile);
            javaMailSender.send(message);
            tempFile.delete();
        } catch (MailException | IOException | MessagingException e) {
            System.err.println("Errore durante l'invio dell'email: " + e.getMessage());
        }
    }
}
