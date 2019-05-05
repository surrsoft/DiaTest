package ru.surrsoft.baaz.widgets2.utils.anim;

/**
 * Интерфейс анимаций
 * <p>
 * ДЛЯ ПОИСКА: [animation], [анимация]
 * <p>
 *
 */
public interface IKtub {
    /**
     * Запустить анимацию
     */
    void iktub_start();

    /**
     * Остановить анимацию
     */
    void iktub_stop();

    /**
     * @return TRUE если вызывался {@link #iktub_start()}, но не вызывался {@link #iktub_stop()}
     */
    boolean iktub_isStarted();
}
