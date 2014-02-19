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