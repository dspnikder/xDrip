package groucho.poc;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 *
 *
 */
public abstract class SensorDevice {
    private UUID uuid;
    private OffsetDateTime introduced;
    private int firmwareRevision;
    private int hardwareRevision;
    private String hwRevStr;
    private String firmwareRevStr;

    public abstract DeviceLocator locate();

}
