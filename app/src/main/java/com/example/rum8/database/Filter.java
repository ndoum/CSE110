package com.example.rum8.database;

import java.util.Locale;
import java.util.Map;

public class Filter {

    public static boolean match(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return alcoholValuePasses(u1Data, u2Data)
                && allowPetsValue(u1Data, u2Data)
                && cleanValuePasses(u1Data, u2Data)
                && genderPasses(u1Data, u2Data)
                && overnightGuestsValuePasses(u1Data, u2Data)
                && partyValuePasses(u1Data, u2Data)
                && reservedValuePasses(u1Data, u2Data)
                && smokeValuePasses(u1Data, u2Data)
                && stayUpLateOnWeekdaysValuePasses(u1Data, u2Data);
    }

    private static boolean alcoholValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "alcohol_value");
    }

    private static boolean allowPetsValue(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "allow_pets_value");
    }

    private static boolean cleanValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "clean_value");
    }

    private static boolean genderPasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        final String u1Gender = (String) u1Data.get("gender");
        final String u2Gender = (String) u2Data.get("gender");

        final long u1GenderPref = (long) u1Data.get("prefer_same_gender_roommate_value");
        final long u2GenderPref = (long) u2Data.get("prefer_same_gender_roommate_value");

        return u1Gender.equals(u2Gender) || (u1GenderPref == 0 && u2GenderPref == 0);
    }

    private static boolean overnightGuestsValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "overnight_guests_value");
    }

    private static boolean partyValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "party_value");
    }

    private static boolean reservedValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "reserved_value");
    }

    private static boolean smokeValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "smoke_value");
    }

    private static boolean stayUpLateOnWeekdaysValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, "stay_up_late_on_weekdays_value");
    }

    private static boolean differenceLessThanTwo(final Map<String, Object> u1Data,
                                                 final Map<String, Object> u2Data,
                                                 final String personalKey) {
        final String roommateKey = String.format(Locale.US, "roommate_%s", personalKey);

        final long u1RoommateValue = (long) u1Data.get(personalKey);
        final long u2PersonalValue = (long) u2Data.get(roommateKey);

        final long u1PersonalValue = (long) u1Data.get(roommateKey);
        final long u2RoommateValue = (long) u2Data.get(personalKey);

        return Math.abs(u1RoommateValue - u2PersonalValue) < 2 && Math.abs(u1PersonalValue - u2RoommateValue) < 2;
    }

}
