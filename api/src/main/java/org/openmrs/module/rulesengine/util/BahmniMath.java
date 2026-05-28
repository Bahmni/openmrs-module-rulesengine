/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.util;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.math.BigDecimal;
import java.util.Date;

public class BahmniMath {
    public static double getTwoDigitRoundUpValue(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Integer ageInYears(Date birthDate, Date asOfDate) {
        return Years.yearsBetween(new LocalDate(birthDate), new LocalDate(asOfDate)).getYears();
    }
}
