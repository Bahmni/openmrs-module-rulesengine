/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://www.bahmni.org/license/mplv2hd.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.rulesengine.util;

import org.bahmni.csv.CSVEntity;
import org.bahmni.csv.CSVFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader<T extends CSVEntity> {

    public List<T> readCsvFileToMemory(CSVFile csvFile, Class<T> entityClass) {
        try {
            T csvEntity;
            List<T> csvRowsFromFile = new ArrayList<>();
            csvFile.openForRead();
            while ((csvEntity = (T) csvFile.readEntity(entityClass)) != null) {
                csvRowsFromFile.add(csvEntity);
            }
            return csvRowsFromFile;
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
