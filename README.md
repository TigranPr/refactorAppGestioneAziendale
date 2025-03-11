# Progetto Microservizi  

Questo progetto è un sistema backend sviluppato con un'architettura a microservizi, utilizzando **Spring Boot**, **Kafka**, **Docker** e **Kubernetes**.  

## Architettura  
- **Config Server**: Gestisce le configurazioni centralizzate.  
- **Discovery Service (Eureka)**: Permette il service discovery automatico.  
- **Gateway**: Unico punto di accesso per l'applicazione.  
- **Microservizi**:  
  - Gestione Utenti  
  - Notifiche  
  - Gestione Timbrature  
  - Gestione Curriculum  
  - Gestione Commenti e Like  
  - Gestione Email  

## Sicurezza  
La sicurezza è gestita con **Spring Boot Security** e **JWT**, centralizzata nel Gateway.  

## Tecnologie Utilizzate  
- Spring Boot  
- Spring Boot Security  
- Kafka & Zookeeper  
- Docker  
- Kubernetes  
- MongoDB, PostgreSQL, MySQL  

## Installazione  
Per eseguire il progetto in locale, segui questi passi:  

```sh
git clone [https://github.com/tuo-username/tuo-repo.git](https://github.com/TigranPr/refactorAppGestioneAziendale.git)
cd tuo-repo
docker-compose up -d
