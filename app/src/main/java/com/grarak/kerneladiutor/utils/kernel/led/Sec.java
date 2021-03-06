package com.grarak.kerneladiutor.utils.kernel.led;

import android.content.Context;

import com.grarak.kerneladiutor.fragments.ApplyOnBootFragment;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;

/**
 * Created by willi on 13.08.16.
 */

public class Sec {

    private static final String LHC = "/sys/class/sec/led/led_highpower_current";
    private static final String LLC = "/sys/class/sec/led/led_lowpower_current";
    private static final String LNDO = "/sys/class/sec/led/led_notification_delay_on";
    private static final String LNDOFF = "/sys/class/sec/led/led_notification_delay_off";
    private static final String LNRC = "/sys/class/sec/led/led_notification_ramp_control";
    private static final String LNRU = "/sys/class/sec/led/led_notification_ramp_up";
    private static final String LNRD = "/sys/class/sec/led/led_notification_ramp_down";
    private static final String LP = "/sys/class/sec/led/led_pattern";

    public static void testPattern(boolean test) {
        run(Control.write(test ? "3" : "0", LP), null, null);
    }

    public static boolean isTestingPattern() {
        return Utils.readFile(LP).equals("3");
    }

    public static boolean hasPattern() {
        return Utils.existFile(LP);
    }

    public static void setNotificationRampDown(int value, Context context) {
        run(Control.write(String.valueOf(value), LNRD), LNRD, context);
    }

    public static int getNotificationRampDown() {
        return Utils.strToInt(Utils.readFile(LNRD));
    }

    public static boolean hasNotificationRampDown() {
        return Utils.existFile(LNRD);
    }

    public static void setNotificationRampUp(int value, Context context) {
        run(Control.write(String.valueOf(value), LNRU), LNRU, context);
    }

    public static int getNotificationRampUp() {
        return Utils.strToInt(Utils.readFile(LNRU));
    }

    public static boolean hasNotificationRampUp() {
        return Utils.existFile(LNRU);
    }

    public static void enableNotificationRampControl(boolean enable, Context context) {
        run(Control.write(enable ? "1" : "0", LNRC), LNRC, context);
    }

    public static boolean isNotificationRampControlEnabled() {
        return Utils.readFile(LNRC).equals("1");
    }

    public static boolean hasNotificationRampControl() {
        return Utils.existFile(LNRC);
    }

    public static void setNotificationDelayOff(int value, Context context) {
        run(Control.write(String.valueOf(value), LNDOFF), LNDOFF, context);
    }

    public static int getNotificationDelayOff() {
        return Utils.strToInt(Utils.readFile(LNDOFF));
    }

    public static boolean hasNotificationDelayOff() {
        return Utils.existFile(LNDOFF);
    }

    public static void setNotificationDelayOn(int value, Context context) {
        run(Control.write(String.valueOf(value), LNDO), LNDO, context);
    }

    public static int getNotificationDelayOn() {
        return Utils.strToInt(Utils.readFile(LNDO));
    }

    public static boolean hasNotificationDelayOn() {
        return Utils.existFile(LNDO);
    }

    public static void setLowpowerCurrent(int value, Context context) {
        run(Control.write(String.valueOf(value), LLC), LLC, context);
    }

    public static int getLowpowerCurrent() {
        return Utils.strToInt(Utils.readFile(LLC));
    }

    public static boolean hasLowpowerCurrent() {
        return Utils.existFile(LLC);
    }

    public static void setHighpowerCurrent(int value, Context context) {
        run(Control.write(String.valueOf(value), LHC), LHC, context);
    }

    public static int getHighpowerCurrent() {
        return Utils.strToInt(Utils.readFile(LHC));
    }

    public static boolean hasHighpowerCurrent() {
        return Utils.existFile(LHC);
    }

    public static boolean supported() {
        return hasHighpowerCurrent() || hasLowpowerCurrent() || hasNotificationDelayOn()
                || hasNotificationDelayOff() || hasNotificationRampControl()
                || hasNotificationRampUp() || hasNotificationRampDown();
    }

    private static void run(String command, String id, Context context) {
        Control.runSetting(command, ApplyOnBootFragment.LED, id, context);
    }

}
