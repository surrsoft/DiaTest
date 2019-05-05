package ru.surrsoft.baaz.univers;

/**
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public enum ETypeResponse {
    /**
     * Тело ответа успешно получено. Код ответа в диапазоне 200..300 (включительно)
     */
    OK(),
    /**
     * Тело ответа успешно получено. Код ответа вне диапазона 200...300 (включительно)
     */
    KO(),
    /**
     * Ошибка при извлечении тела ответа
     */
    ERR_BODY(),
    /**
     * Неизвестный хост (java.net.UnknownHostException)
     */
    ERR_UNKNOWN_HOST(),
    /**
     * Нет соединения или обрыв соединения (java.net.ConnectException)
     */
    ERR_CONNECT(),
    /**
     * Истек таймаут (java.net.SocketTimeoutException)
     */
    ERR_TIMEOUT(),
    /**
     * Другая ошибка во время запроса (IOException)
     */
    ERR_IOE(),
    ;
}
