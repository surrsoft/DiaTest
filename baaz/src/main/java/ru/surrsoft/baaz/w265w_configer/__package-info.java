/**
 * [[w265w]] Проект - конфигурирование виджетов прямо на телефоне.
 *
 * Конфигурации виджетов сохраняются в БД.
 *
 * <li> ТЕРМИНЫ </li>
 * <li> -- _стартовый-список (_startList) - стартовый набор из "_key"-"Class"-"description" </li>
 * <li> -- _корневой-цикл (_rootLoop) - начальный цикл пробежки по _startList </li>
 * <li> -- _confoj (_конфигурационный-объект) - </li>
 * <li> ---- _confojdef - дефолтный конфигурационный объект </li>
 * <li> ---- _confojdb - конфигурационный объект из _db </li>
 * <li> -- _db - БД в которой хранятся _записи (_конфигурационные-объекты)</li>
 * <li> -- _tag - идентификатор для отдельной _записи </li>
 * <li> -- _entry (_запись) - </li>
 * <li> -- _picker - </li>
 * <li> -- _form - элемент по нажатию на который вызывется _picker и отображающий результат </li>
 * <li> ---- _dem (_демонстратор) - демонстратор результата работы _picker </li>
 * <li> -- _ontoper (_наслоение) - сущность применяемая для изменения дефолтного _конфигурационного-объекта</li>
 * <li> -- _fieldsimple (_простое-поле) - поле выраженное типом-с-одним-полем, либо примитивным типом</li>
 * <li> -- _fieldnosimple (_непростое-поле, _неэлементарный-тип) - поле выраженное типом-с-несколькими-другими-полями</li>
 *
 * ТРЕБОВАНИЯ
 * <li> -- у _конфигурационного-объекта (класса) обязательно должен быть конструктор по умолчанию </li>
 */
package ru.surrsoft.baaz.w265w_configer;

