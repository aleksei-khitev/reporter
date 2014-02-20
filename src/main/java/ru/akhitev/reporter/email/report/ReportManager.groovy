/*
 * ru.akhitev.reporter. Программа для отправки по почте отчетов о работе в формате HTML
 * Copyright (c) 2014 Хитёв Алексей
 *
 * Этот файл - часть ru.akhitev.reporter. Вы можете перераспространять ее и/или изменять ее на условиях
 * Стандартной общественной лицензии GNU в том виде, в каком она была опубликована Фондом свободного программного
 * обеспечения; либо версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.
 * Эта программа распространяется в надежде, что она будет полезной, но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии
 * ТОВАРНОГО ВИДА или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной общественной лицензии GNU.
 * Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой программой. Если это не так, см.
 * <http://www.gnug/licenses/>.
 */

package ru.akhitev.reporter.email.report

import org.apache.log4j.Logger;
/**
 * Интерфейс для работы с шаблонами тела письма
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
interface ReportManager{
    /**
     * Создание тела письма
     * @param title Основной заголовок в шаблоне
     * @param firstHeader Заголовок секции системных сообщений
     * @param secondHeader Заголовок секции сообщений обработки
     * @return Получившееся тело письма
     */
    String createReport(String title, String firstHeader, String secondHeader)
    /**
     * Добавление строки в список системных сообщений
     * @param firstCol Наполнение первой ячейки таблицы
     * @param secondCol Наполнение второй ячейки таблицы
     */
    void addToSystemErrorList(String firstCol, String secondCol);
    /**
     * Добавление в список сообщений обработки
     * @param firstCol Наполнение первой ячейки таблицы
     * @param secondCol Наполнение второй ячейки таблицы
     */
    void addToStringErrorList(String firstCol, String secondCol);
    /**
     * Были ли занесены какие либо сообщения об ошибках
     * @return
     */
    boolean havingErrors()
    /**
     * Задача журнала log4j для сообщений об ошибках работы
     * @param logger Журнал log4j
     */
    void setLogger(Logger logger)
}