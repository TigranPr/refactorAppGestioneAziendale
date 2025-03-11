<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>README - Progetto Microservizi</title>
    <style>
        body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; }
        h1, h2, h3 { color: #333; }
        pre { background: #f4f4f4; padding: 10px; border-radius: 5px; }
        code { font-family: monospace; color: #d63384; }
    </style>
</head>
<body>
    <h1>Progetto Microservizi</h1>
    <p>Questo progetto è un sistema backend sviluppato con un'architettura a microservizi, utilizzando <strong>Spring Boot</strong>, <strong>Kafka</strong>, <strong>Docker</strong> e <strong>Kubernetes</strong>.</p>

    <h2>Architettura</h2>
    <ul>
        <li><strong>Config Server</strong>: Gestisce le configurazioni centralizzate.</li>
        <li><strong>Discovery Service (Eureka)</strong>: Permette il service discovery automatico.</li>
        <li><strong>Gateway</strong>: Unico punto di accesso per l'applicazione.</li>
        <li><strong>Microservizi:</strong>
            <ul>
                <li>Gestione Utenti</li>
                <li>Notifiche</li>
                <li>Gestione Timbrature</li>
                <li>Gestione Curriculum</li>
                <li>Gestione Commenti e Like</li>
                <li>Gestione Email</li>
            </ul>
        </li>
    </ul>

    <h2>Sicurezza</h2>
    <p>La sicurezza è gestita con <strong>Spring Boot Security</strong> e <strong>JWT</strong>, centralizzata nel Gateway.</p>

    <h2>Tecnologie Utilizzate</h2>
    <ul>
        <li>Spring Boot</li>
        <li>Spring Boot Security</li>
        <li>Kafka & Zookeeper</li>
        <li>Docker</li>
        <li>Kubernetes</li>
        <li>MongoDB, PostgreSQL, MySQL</li>
    </ul>

    <h2>Avvio del Progetto</h2>
    <pre><code>docker-compose up -d</code></pre>
    <p>Assicurati di avere Docker e Kubernetes configurati correttamente.</p>

    <h2>Conclusione</h2>
    <p>Questo progetto dimostra l'efficacia dei microservizi nella gestione aziendale, garantendo scalabilità e sicurezza.</p>
</body>
</html>
