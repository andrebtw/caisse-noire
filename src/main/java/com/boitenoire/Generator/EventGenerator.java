package com.boitenoire.Generator;

import com.boitenoire.Model.*;

import com.boitenoire.RepositoryEvent.RepositoryEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class EventGenerator {
    private final RepositoryEvent eventRepository;

    private final Random random = new Random();

    private static final int NUMBER_OF_EVENTS = 100_000;

    public EventGenerator(RepositoryEvent eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void generateEvents() {

        System.out.println("Début de la génération...");

        List<Event> events = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_EVENTS; i++) {

            Event event = generateEvent();

            events.add(event);

            // On enregistre par lots de 1000
            if (events.size() == 1000) {
                eventRepository.saveAll(events);
                events.clear();

                System.out.println(
                        "Événements générés : " + (i + 1)
                );
            }
        }

        // Enregistre les éventuels événements restants
        if (!events.isEmpty()) {
            eventRepository.saveAll(events);
        }

        System.out.println("Génération terminée !");
    }


    private Event generateEvent() {

        String eventType = generateEventType();

        LocalDateTime timestamp = generateDate();

        String userId = "user-" + random.nextInt(10_000);

        String source = generateSource();

        Metadata metadata = generateMetadata();

        Object data = generateData(eventType);

        Event event = new Event();

        event.setId(null);
        event.setEventType(eventType);
        event.setTimestamp(timestamp);
        event.setUserId(userId);
        event.setSource(source);
        event.setMetadata(metadata);
        event.setData(data);

        return event;
    }


    private String generateEventType() {

        int number = random.nextInt(100);

        if (number < 30) {
            return "LOGIN";
        }

        if (number < 50) {
            return "API_CALL";
        }

        if (number < 70) {
            return "NOTIFICATION";
        }

        if (number < 85) {
            return "PAYMENT";
        }

        return "APPLICATION_ERROR";
    }


    private LocalDateTime generateDate() {

        LocalDateTime start =
                LocalDateTime.of(2025, 1, 1, 0, 0);

        LocalDateTime end =
                LocalDateTime.of(2025, 12, 31, 23, 59);

        long seconds =
                java.time.Duration.between(start, end).getSeconds();

        long randomSeconds =
                random.nextLong(seconds + 1);

        return start.plusSeconds(randomSeconds);
    }


    private String generateSource() {

        String[] sources = {
                "web",
                "mobile",
                "api"
        };

        return sources[random.nextInt(sources.length)];
    }


    private Metadata generateMetadata() {

        String ipAddress =
                random.nextInt(256) + "." +
                        random.nextInt(256) + "." +
                        random.nextInt(256) + "." +
                        random.nextInt(256);

        String[] browsers = {
                "Chrome",
                "Firefox",
                "Safari",
                "Edge"
        };

        String userAgent =
                browsers[random.nextInt(browsers.length)];

        return new Metadata(ipAddress, userAgent);
    }


    private Object generateData(String eventType) {

        switch (eventType) {

            case "LOGIN":
                return generateLoginData();

            case "PAYMENT":
                return generatePaymentData();

            case "APPLICATION_ERROR":
                return generateApplicationErrorData();

            case "API_CALL":
                return generateApiCallData();

            case "NOTIFICATION":
                return generateNotificationData();

            default:
                return null;
        }
    }


    private LoginData generateLoginData() {

        boolean success = random.nextInt(100) < 90;

        String[] methods = {
                "PASSWORD",
                "GOOGLE",
                "APPLE"
        };

        String authenticationMethod =
                methods[random.nextInt(methods.length)];

        return new LoginData(
                success,
                authenticationMethod
        );
    }


    private PaymentData generatePaymentData() {

        double amount =
                5 + (random.nextDouble() * 495);

        String[] currencies = {
                "EUR",
                "USD",
                "GBP"
        };

        String[] statuses = {
                "SUCCESS",
                "FAILED",
                "PENDING"
        };

        String[] paymentMethods = {
                "CARD",
                "PAYPAL",
                "BANK_TRANSFER"
        };

        return new PaymentData(
                BigDecimal.valueOf(amount).setScale(2, java.math.RoundingMode.HALF_UP),
                currencies[random.nextInt(currencies.length)],
                statuses[random.nextInt(statuses.length)],
                paymentMethods[random.nextInt(paymentMethods.length)]
        );
    }


    private ApplicationErrorData generateApplicationErrorData() {

        String[] errorCodes = {
                "ERR-500",
                "ERR-404",
                "ERR-401",
                "ERR-403",
                "ERR-503"
        };

        String[] severities = {
                "LOW",
                "MEDIUM",
                "HIGH",
                "CRITICAL"
        };

        String[] messages = {
                "Internal server error",
                "Resource not found",
                "Unauthorized access",
                "Access forbidden",
                "Service unavailable"
        };

        String[] services = {
                "authentication-service",
                "message-service",
                "payment-service",
                "notification-service"
        };

        return new ApplicationErrorData(
                errorCodes[random.nextInt(errorCodes.length)],
                severities[random.nextInt(severities.length)],
                messages[random.nextInt(messages.length)],
                services[random.nextInt(services.length)]
        );
    }


    private ApiCallData generateApiCallData() {

        String[] endpoints = {
                "/api/users",
                "/api/messages",
                "/api/login",
                "/api/payments",
                "/api/notifications"
        };

        String[] methods = {
                "GET",
                "POST",
                "PUT",
                "DELETE"
        };

        int[] statusCodes = {
                200,
                201,
                400,
                401,
                404,
                500
        };

        long responseTime =
                20 + random.nextInt(1980);

        return new ApiCallData(
                endpoints[random.nextInt(endpoints.length)],
                methods[random.nextInt(methods.length)],
                statusCodes[random.nextInt(statusCodes.length)],
                responseTime
        );
    }


    private NotificationData generateNotificationData() {

        String[] channels = {
                "EMAIL",
                "SMS",
                "PUSH"
        };

        String[] templates = {
                "WELCOME",
                "NEW_MESSAGE",
                "PASSWORD_RESET",
                "PAYMENT_CONFIRMATION"
        };

        String[] statuses = {
                "SENT",
                "FAILED",
                "PENDING"
        };

        return new NotificationData(
                channels[random.nextInt(channels.length)],
                templates[random.nextInt(templates.length)],
                statuses[random.nextInt(statuses.length)]
        );
    }
}
