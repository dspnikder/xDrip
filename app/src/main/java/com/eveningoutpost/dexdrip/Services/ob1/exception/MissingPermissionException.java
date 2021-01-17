package com.eveningoutpost.dexdrip.Services.ob1.exception;

public class MissingPermissionException extends Exception{
    private RequiredPermissions missing;
    public MissingPermissionException(RequiredPermissions missing){
        super("Permission to access " + missing.name() + " not granted yet");
        this.missing = missing;
    }

    public RequiredPermissions getMissingPermission() {
        return missing;
    }
}
