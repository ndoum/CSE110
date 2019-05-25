package com.example.rum8.database;

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
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.ALCOHOL_VALUE);
    }

    private static boolean allowPetsValue(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.ALLOW_PETS_VALUE);
    }

    private static boolean cleanValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.CLEAN_VALUE);
    }

    private static boolean genderPasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        final String u1Gender = (String) u1Data.get(Db.Keys.GENDER);
        final String u2Gender = (String) u2Data.get(Db.Keys.GENDER);

        final long u1GenderPref = (long) u1Data.get(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE);
        final long u2GenderPref = (long) u2Data.get(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE);

        return u1Gender.equals(u2Gender) || (u1GenderPref == 0 && u2GenderPref == 0);
    }

    private static boolean overnightGuestsValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.OVERNIGHT_GUESTS_VALUE);
    }

    private static boolean partyValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.PARTY_VALUE);
    }

    private static boolean reservedValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.RESERVED_VALUE);
    }

    private static boolean smokeValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.SMOKE_VALUE);
    }

    private static boolean stayUpLateOnWeekdaysValuePasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        return differenceLessThanTwo(u1Data, u2Data, Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE);
    }

    private static boolean differenceLessThanTwo(final Map<String, Object> u1Data,
                                                 final Map<String, Object> u2Data,
                                                 final String personalKey) {
        final String roommateKey = String.format("roommate_%s", personalKey);

        final long u1RoommateValue = (long) u1Data.get(personalKey);
        final long u2PersonalValue = (long) u2Data.get(roommateKey);

        final long u1PersonalValue = (long) u1Data.get(roommateKey);
        final long u2RoommateValue = (long) u2Data.get(personalKey);

        return Math.abs(u1RoommateValue - u2PersonalValue) < 2 && Math.abs(u1PersonalValue - u2RoommateValue) < 2;
    }

}
