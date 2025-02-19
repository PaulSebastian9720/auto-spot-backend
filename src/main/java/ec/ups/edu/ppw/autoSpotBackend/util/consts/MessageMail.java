package ec.ups.edu.ppw.autoSpotBackend.util.consts;

import java.util.Map;

public class MessageMail {

    public static final Map<String, String> ACCOUNT_CREATION_EMAIL = Map.of(
            "subject", "✅ Bienvenido a AutoSpot",
            "body", "Hola {username},\n\n¡Tu registro se ha realizado exitosamente! Ahora cuentas con nuestos servicios de manera rápida y sencilla.\n\nGracias por unirte a AutoSpot.\n\n🚗 ¡Feliz viaje!"
    );

    public static final Map<String, String> LOGIN_NOTIFICATION_EMAIL = Map.of(
            "subject", "🔔 Aviso de inicio de sesión",
            "body", "Hola {username},\n\nSe ha detectado un nuevo inicio de sesión en tu cuenta. Si fuiste tú, ignora este mensaje."
    );

    public static final Map<String, String> VEHICLES_IN_PARKING_CLOSING_SOON_EMAIL = Map.of(
            "subject", "⚠️ Aviso: Tu vehículo está en un parqueadero que cerrará pronto",
            "body", "Hola {username},\n\nTe informamos que el estacionamiento **{parkingName}** cerrará en breve y tu vehículo **{vehiclePlate}** aún se encuentra dentro.\n\n⏳ Hora de cierre: {closingTime}\n\nPor favor, retira tu vehículo antes de la hora indicada para evitar inconvenientes.\n\n🚗 ¡Gracias por usar AutoSpot!"
    );

    public static String generateContractEndingEmailBody(String username, String parkingName,  String parkingLocation) {
        return "Hola " + username + ",\n\n" +
                "Queremos recordarte que tu contrato de estacionamiento en **" + parkingName + "** finaliza hoy.\n\n" +
                "📅 Fecha de vencimiento:  Maximo 10 minutos \n" +
                "📍 Vehiculos: " + parkingLocation + ".\n\n" +
                "Gracias por confiar en AutoSpot. 🚗";
    }




}

