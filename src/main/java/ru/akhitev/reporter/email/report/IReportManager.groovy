/*
 * ru.akhitev.reporter is a library for encryption.
 * Copyright (c) 2014 Aleksei Khitevi (Хитёв Алексей Юрьевич).
 *
 * This file is part of ru.akhitev.encrypter
 *
 * ru.akhitev.reporter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * ru.akhitev.encrypter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package ru.akhitev.reporter.email.report

import org.apache.log4j.Logger;
/**
 * The interface used for email body template's managing
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
interface IReportManager {
    /**
     * The method used for email's body forming
     * @param title Main title at the template
     * @param firstHeader System section title
     * @param secondHeader Another section title
     * @return Email's body
     */
    String createReport(String title, String firstHeader, String secondHeader)
    /**
     * The method used for adding message to system section
     * @param firstCol First column text
     * @param secondCol Second column text
     */
    void addToSystemErrorList(String firstCol, String secondCol);
    /**
     * The method used for adding message to another section
     * @param firstCol First column text
     * @param secondCol Second column text
     */
    void addToStringErrorList(String firstCol, String secondCol);
    /**
     * Did messages add to the report
     * @return
     */
    boolean havingErrors()
}