package ru.surrsoft.baaz.cls_c;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EStrings;

/**
 * Работа с внешними, внутренними хранилищами, файлами
 */
public class G67G_Storages {
  private static final String TAG = ":G67G.Storages";


  /**
   * Проверка существования файла
   *
   * @param file
   * @return
   */
  public static boolean isFileExist(File file) {
    return file.exists();
  }

  /**
   * Проверка доступно ли внешнее хранилище для чтения и записи
   *
   * @return
   */
  public static boolean isExternalStorageWritable(Context context) {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      return true;
    }

    // внешнее хранилище недоступно
    String s2 = TLang_01.getString_En("(!) НЕУДАЧА. Хранилище недоступно. Возможно демонтирована карта памяти",
      "(!) FAILURE. Storage is not available. Perhaps dismantled memory card");
    G67G.Logs.ToastAaPrintln_B(s2, context);

    return false;
  }

  /**
   * Создание File на базе существующего файла. Выводит сообщения с лог и
   * toast.
   *
   * @param aThFiNm111 (1) -- асолютное имя файла, например
   *                   "/mnt/sdcard/file.txt"
   * @return null при неудаче (файл отсутствует или не позволяет чтение)
   */
  public static File createFile(String aThFiNm111, Context context) {

    File fi = new File(aThFiNm111);

    // проверки
    if (!fi.exists()) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) файл не существует [" + aThFiNm111 + "]",
        ":info: (!) file not exist [" + aThFiNm111 + "]"), context);
      return null;
    } else if (!fi.canRead()) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) файл не читабелен [" + aThFiNm111 + "]",
        ":info: (!) file not readable [" + aThFiNm111 + "]"), context);
      return null;
    }

    return fi;
  }

  /**
   * Создание File папки (1) во внешнем хранилище. Если папки не
   * существует то она создается. g62g
   *
   * @param aThFiNm100 (1) -- путь к папке, например "/mnt/sdcard/folder"
   * @return null при неудачах (хранилище недоступно)
   */
  public static File createFd(String aThFiNm100, Context context) {

    // проверка доступности внешнего хранилища
    boolean b = isExternalStorageWritable(context);
    if (!b) {
      return null;
    }

    File fi = new File(aThFiNm100);
    // создание папки если ее еще нет
    fi.mkdirs();

    return fi;
  }

  /**
   * Копирование из файла (1) в файл (2). <br>
   * <br>
   * Только копирование, никакие проверки не выполняются - должны быть
   * выполнены до. В лог и toast выводятся сообщения. <br>
   * <br>
   * Используется библиотека apache
   * (http://commons.apache.org/proper/commons
   * -io/javadocs/api-2.4/index.html)
   *
   * @param aInputFi  (1) -- ThFiNm111 входного файла, например
   *                  "/mnt/sdcard/fileinput.txt"
   * @param aOutputFi (2) -- ThFiNm111 выходного файла, например
   *                  "/mnt/sdcard/fileoutput.txt"
   * @return true если успешно, false иначе
   */
  public static boolean copyFile(File aInputFi, File aOutputFi, Context context) {

    try {
      FileUtils.copyFile(aInputFi, aOutputFi);

      String s1 = TLang_01.getString_En("Успешно", "Successfully");
      G67G.Logs.ToastAaPrintln_B(s1, context);

      return true;
    } catch (NullPointerException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) исходный файл или файл назначения == null",
        ":info: (!) source or destination file is null"), context);
      e.printStackTrace();
    } catch (IOException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(
        ":info: (!) исходный файл или файл назначения непригодны ИЛИ произошла ошибка "
          + "ввода/вывода при копировании",
        ":info: (!) source or destination files is invalid OR IO error occurs during copying"), context);
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Создает файл пустой (1), публичный (ThPubFi), во внешнем хранилище, в
   * папке по имени (2). Папка (2) будет расположена на одном уровне со
   * стандартной папкой "Download" (наиболее вероятный путь это
   * "/mnt/sdcard/NAME_FOLDER"). В манифесте должно быть разрешение
   * WRITE_EXTERNAL_STORAGE
   *
   * @param fileName   (1) -- имя файла, например "somefile.db"
   * @param folderName (2) -- имя папки, например "Fixator". Если нужна
   *                   вложенность, можно указать несколько папок через
   *                   разделитель, например "Fixator"+File.separator+"InFixator"
   * @return URI созданного файла или null
   */
  public static URI createFileEmpty(String fileName, String folderName) {
    URI ret = null; // возвращаемый путь

    // директория внешнего хранилища для публичных файлов (ThPubFi)
    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    String pathS = path.getAbsolutePath();

    // Получение пути к папке в которую будет помещена новая папка.
    // ..удаление из пути последнего члена. Например, до
    // "/mnt/sdcard/Download", после "/mnt/sdcard"
    int i = pathS.lastIndexOf(File.separator);
    String pathD = pathS.substring(0, i);

    File folder = new File(pathD, folderName); // File для создания папки
    if (!folder.exists()) {
      boolean b = folder.mkdirs(); // создание папки
      if (!b) { // не удалось создать папку
        String s = "!!! не удалось создать папку. Проверьте наличие разрешения WRITE_EXTERNAL_STORAGE в манифесте";
        throw new IllegalArgumentException(s);
      }
    }

    //абсолютный путь к папке
    String pathF = pathD + File.separator + folderName;

    // СОЗДАНИЕ File
    File fiDb = new File(pathF, fileName);
    // непостредственное создание файла
    try {
      fiDb.createNewFile();
      ret = fiDb.toURI();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * Запись строки (2) в файл (1). Файл (1) должен существовать
   *
   * @param file (1) -- File, должен существовать
   * @param str  (2) -- строка которую нужно записать
   * @return TRUE если запись была выполнена, иначе FALSE
   */
  public static boolean writeFile(@NonNull File file, @NonNull String str) {
    if (!file.exists()) {
      Log2_01.i(TAG, "-- (!) невозможно записать в файл - файл не существует", Log2_01.CONF_LOG1);
      return false; //======X
    }
    try {
      FileWriter fw = new FileWriter(file);
      fw.write(str);
      fw.flush();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
      Log2_01.i(TAG, "-- (!) ошибка при записи в файл", Log2_01.CONF_LOG1);
      return false; //======X
    }
    return true;
  }

  /**
   * Запись текста (3) в файл (1) расположенный в папке (2). Если файл отсутствует, он создается; если
   * существует - перезаписывается
   *
   * @param gpath         (1) -- например "folder1"+File.separator+"folder2"
   * @param gfilename     (2) -- например "file.txt"
   * @param stringToWrite (3) -- строка которую нужно записать
   * @return TRUE если запись была выполнена
   */
  public static boolean writeFileForce(@Nullable String gpath, @Nullable String gfilename, @Nullable String stringToWrite) {
    if (stringToWrite == null || gfilename == null || gpath == null) return false; //======X
    File file = createFileOrGetExist(gpath, gfilename);
    return file != null && writeFile(file, stringToWrite);
  }

  /**
   * см. writeFile_B(String, String, String)
   *
   * @param file
   * @return см. writeFile_B(String, String, String)
   */
  public static boolean writeFileForce(@Nullable File file, @Nullable String stringToWrite) { //T ODO
    if (file == null || stringToWrite == null) return false; //======X
    return writeFileForce(file.getParent(), file.getName(), stringToWrite);
  }

  /**
   * Чтение содежимого файла (1)
   *
   * @param file (1) -- File
   * @return возвращает null при нештатах
   */
  @Nullable
  public static String readFile(@NonNull File file) {
    StringBuffer sb = new StringBuffer();
    if (!file.exists()) {
      Log2_01.i(TAG, "-- (!) прочитать файл - файл не сущестует", Log2_01.CONF_LOG1);
      return null; //======X
    }
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String s;
      while ((s = br.readLine()) != null) {
        sb.append(s);
      }
      fr.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      Log2_01.i(TAG, "-- (!) ошибка при чтении файла //FileNotFoundException", Log2_01.CONF_LOG1);
      return null; //======X
    } catch (IOException e) {
      e.printStackTrace();
      Log2_01.i(TAG, "-- (!) ошибка при чтении файла //IOException", Log2_01.CONF_LOG1);
      return null; //======X
    }
    return sb.toString();
  }


  /**
   * Создает файл с именем (1) по адресу (2). Если файл уже существует то возвращает ссылку на него
   * <p>
   * В манифесте должно быть разрешение WRITE_EXTERNAL_STORAGE
   *
   * @param gpath     (1) -- путь [GPath], например "folder" или "folder1"+File.separator+"folder2"
   * @param gfilename (2) -- имя файла [GFilename], например "somefile.db"
   * @return File созданного физического файла, File уже существующего файла, null при нештатах
   */
  @Nullable
  public static File createFileOrGetExist(@Nullable String gpath, @Nullable String gfilename) {

    if (gfilename == null || gpath == null) return null; //======X
    File file = new File(gpath, gfilename);
    if (file.exists()) { //======X файл уже существует
      if (file.isFile()) {
        return file;
      } else {
        Log2_01.i(TAG, "--0922 (!) не удалось создать файл - по указанному пути существует папка с " +
          "таким же именем как указанное имя файла (имя = " + gfilename + ")", Log2_01.CONF_LOG1);
        return null;
      }
    }

    File filePar = file.getParentFile(); // File для создания папки если она еще не существует
    if (filePar == null) {
      Log2_01.i(TAG, "--0922 (!) не удалось создать папку - нет родительской папки", Log2_01.CONF_LOG1);
      return null; //======X
    }

    if (!filePar.exists()) {
      boolean b = filePar.mkdirs(); //создание папки
      if (!b) {
        Log2_01.i(TAG, "--0922 (!) не удалось создать папку. Проверьте наличие разрешения WRITE_EXTERNAL_STORAGE в манифесте", Log2_01.CONF_LOG1);
        return null; //======X
      }
    }

    //непостредственное создание файла
    try {
      boolean ok = file.createNewFile();
      if (ok) {
        return file; //======X
      } else {
        Log2_01.i(TAG, "--0922 (!) не удалось создать файл", Log2_01.CONF_LOG1);
      }
    } catch (IOException e) {
      e.printStackTrace();
      Log2_01.i(TAG, "--0922 (!) IOException при создании файла", Log2_01.CONF_LOG1);
    }
    return null;
  }

  /**
   * см. {@link #createFileOrGetExist(String, String)}
   *
   * @param file
   * @return см. {@link #createFileOrGetExist(String, String)}
   */
  @Nullable
  public static File createFileOrGetExist(@Nullable File file) {
    if (file == null) return null; //======X
    return createFileOrGetExist(file.getParent(), file.getName());
  }

  /**
   * ВАЖНО! - не забываем про разрешение uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
   * в манифесте
   * <p>
   * Копирует БД (1) в файл (3) папки (2) внешнего хранилища (m109m@ExSt).
   * Пишет о результатах работы в лог и Toast.
   *
   * @param aDbNm (1) -- имя файла БД
   * @param aFdNm (2) -- имя папки в которую будет помещен файл в который
   *              будет скопирован файл БД, например "Fixator" или
   *              "Fixator/InFixator"
   * @param aFiNm (3) -- имя файла в который будет скопирована БД
   * @return 2 - копирование выполнено успешно; 1 - внешнее хранилище
   * недоступно; 0 - прочие проблемы;
   */
  public static int copyDbFromApp(String aDbNm, String aFdNm, String aFiNm, Context context) {
    // ПРОВЕРКА доступности внешнего хранилища
    if (!isExternalStorageWritable(context)) {
      return 1;
    }
    // создание файла вывода
    URI uriFiOutput = createFileEmpty(aFiNm, aFdNm);
    // если файл вывода не создался
    if (uriFiOutput == null) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) ошибка при создании файла",
        ":info: (!) error while creating file"), context);
      return 0;
    }
    // файл вывода
    File fiOutput = new File(uriFiOutput);
    // файл ввода - БД
    File fiInput = context.getDatabasePath(aDbNm);
    // КОПИРОВАНИЕ
    try {
      FileUtils.copyFile(fiInput, fiOutput);
      String s1 = TLang_01.getString_En("Успешно (см. " + fiOutput.getAbsolutePath() + ")", "Successfully (see " + fiOutput.getAbsolutePath() + ")");
      G67G.Logs.ToastAaPrintln_B(s1, context);
      return 2;
    } catch (NullPointerException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) исходный файл или файл назначения == null",
        ":info: (!) source or destination file is null"), context);
      e.printStackTrace();
    } catch (IOException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(
        ":info: (!) исходный файл или файл назначения непригодны ИЛИ произошла ошибка "
          + "ввода/вывода при копировании",
        ":info: (!) source or destination files is invalid OR IO error occurs during copying"), context);
      e.printStackTrace();
    }

    return 0;

  }

  /**
   * ВАЖНО! - не забываем про разрешение uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
   * в манифесте
   * <p>
   * Копирует файл БД (1) из внешнего хранилища (m109m@ExSt) в файл БД
   * приложения. Пишет о результатах работы в лог и Toast.
   *
   * @param pathfileNameDbOnStorage (1) -- имя файла БД во внешнем хранилище, например
   *                                "/mnt/sdcard/file.db" или "/storage/emulated/0/db.sqlite"
   * @param fileNameDbApp           (2) -- имя файла БД приложения, например "dbfile"
   * @return 2 - копирование выполнено успешно; 1 - внешнее хранилище
   * недоступно; 0 - прочие проблемы;
   */
  public static int copyDbToApp(String pathfileNameDbOnStorage, String fileNameDbApp,
                                Context context) {
    // если внешнее хранилище недоступно
    if (!isExternalStorageWritable(context)) {
      return 1;
    }

    // файл входной
    File fileInput = new File(pathfileNameDbOnStorage);
    // проверки
    if (!fileInput.exists()) {
      G67G.Logs.ToastAaPrintln_B("!!! файл для копирования \"" + pathfileNameDbOnStorage +
        "\" отсутствует в хранилище", context);
      return 0;
    }
    if (!fileInput.canRead()) {
      G67G.Logs.ToastAaPrintln_B("!!! файл для копирования \"" + pathfileNameDbOnStorage +
        "\" не читаем", context);
      return 0;
    }
    if (fileInput.isDirectory()) {
      G67G.Logs.ToastAaPrintln_B("!!! файл для копирования \"" + pathfileNameDbOnStorage +
        "\" не является файлом - является директорией", context);
      return 0;
    }

    // файл выходной (файл БД)
    File fiOutput = context.getDatabasePath(fileNameDbApp);
    // проверки
    if (!fiOutput.exists() || !fiOutput.canRead() || fiOutput.isDirectory()) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) файл БД или не существует или "
        + "не читабелен или является директорией", ":info: (!) database file either not exist or not"
        + " readable or is a folder"), context);
      return 0;
    }

    // КОПИРОВАНИЕ
    try {
      FileUtils.copyFile(fileInput, fiOutput);
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En("Успешно", "Successfully"), context);
      return 2;
    } catch (NullPointerException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) исходный файл или файл назначения == null",
        ":info: (!) source or destination file is null"), context);
      e.printStackTrace();
    } catch (IOException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(
        ":info: (!) исходный файл или файл назначения непригодны ИЛИ произошла ошибка "
          + "ввода/вывода при копировании",
        ":info: (!) source or destination files is invalid OR IO error occurs during copying"), context);
      e.printStackTrace();
    }

    return 0;
  }

  /**
   * ВАЖНО! - не забываем про разрешение uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
   * в манифесте
   * <p>
   * Копирует файл БД (1) из внешнего хранилища (m109m@ExSt) в файл БД
   * приложения. Пишет о результатах работы в лог и Toast.
   * <p>
   * От А отличается более совершенной системой информирования о происходящем
   *
   * @param pathfileDbStorage (1) -- имя файла БД во внешнем хранилище, например
   *                          "/mnt/sdcard/file.db" или "/storage/emulated/0/db.sqlite"
   * @param filenameDbApp     (2) -- имя файла БД приложения, например "dbfile"
   * @return 2 - копирование выполнено успешно; 1 - внешнее хранилище
   * недоступно; 0 - прочие проблемы;
   * <p>
   * #version 1 05-07-2016
   * <p>
   * НЕЗАВЕРШЕННЫЙ МЕТОД - ДОДЕЛАТЬ //TODO доделать метод
   */
  public static int copyDbToApp_B(String pathfileDbStorage, String filenameDbApp,
                                  final Context context, final boolean showLogOnLogcat,
                                  final boolean showLogOnToast, final boolean trowException,
                                  final boolean showInfoForUser) {
    class C26 {
      void go(String str1, String str2) {
        if (trowException) throw new SomeException(str1);
        if (showLogOnLogcat) Log2_01.d(TAG, str1, Log2_01.CONF_LOG1);
        if (showInfoForUser) {
          Toast.makeText(context, str2, Toast.LENGTH_LONG).show();
        } else if (showLogOnToast)
          Toast.makeText(context, str1, Toast.LENGTH_LONG).show();
      }
    }
    C26 c26 = new C26();

    // если внешнее хранилище недоступно
    if (!isExternalStorageWritable(context)) {
      c26.go("~~~ внешнее хранилище недоступно", EStrings.G67G_001_001.val());
      return 1;
    }

    // файл входной
    File fileInput = new File(pathfileDbStorage);
    // проверки
    if (!fileInput.exists()) {
      String str1 = String.format(EStrings.G67G_001_002.val(), pathfileDbStorage);
      c26.go(str1, str1);
      return 0;
    }
    if (!fileInput.canRead()) {
      String str1 = String.format(EStrings.G67G_001_003.val(), pathfileDbStorage);
      c26.go(str1, str1);
      return 0;
    }
    if (fileInput.isDirectory()) {
      String str1 = String.format(EStrings.G67G_001_004.val(), pathfileDbStorage);
      c26.go(str1, str1);
      return 0;
    }

    // файл выходной (файл БД)
    File fiOutput = context.getDatabasePath(filenameDbApp);
    // проверки
    if (!fiOutput.exists()) {
      String str1 = String.format(EStrings.G67G_001_005.val(), filenameDbApp);
      c26.go(str1, str1);
      return 0;
    }
    if (!fileInput.canRead()) {
      String str1 = String.format(EStrings.G67G_001_006.val(), filenameDbApp);
      c26.go(str1, str1);
      return 0;
    }
    if (fiOutput.isDirectory()) {
      String str1 = String.format(EStrings.G67G_001_007.val(), filenameDbApp);
      c26.go(str1, str1);
      return 0;
    }

    // КОПИРОВАНИЕ
    try {
      FileUtils.copyFile(fileInput, fiOutput);
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En("Успешно (" + filenameDbApp + ")",
        "Successfully (" + filenameDbApp + ")"), context);
      return 2;
    } catch (NullPointerException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(":info: (!) исходный файл или файл " +
          "назначения == null",
        ":info: (!) source or destination file is null"), context);
      e.printStackTrace();
    } catch (IOException e) {
      G67G.Logs.ToastAaPrintln_B(TLang_01.getString_En(
        ":info: (!) исходный файл или файл назначения непригодны ИЛИ произошла " +
          "ошибка "
          + "ввода/вывода при копировании",
        ":info: (!) source or destination files is invalid OR IO error occurs " +
          "during copying"), context);
      e.printStackTrace();
    }

    return 0;
  }

  /**
   * Копирование базы данных (2) из Assets в приложение, под именем (3). Файл БД приложения (3)
   * должен уже существовать на момент копирования, он будет полностью перезаписан.
   * Никаких проверок не выполняется
   *
   * @param context
   * @param nameFrom (2) -- имя файла БД в папке Assets
   * @param dbName   (3) -- имя файла БД в приложении
   * @return true если выполнено успешное копирование без ошибок
   */
  public static boolean copyDbToAppFromAssets(Context context, String nameFrom, String dbName) {
    boolean ret = true;
    InputStream iStream = null;
    File dbFile = null;
    try {
      iStream = context.getResources().getAssets().open(nameFrom);
      dbFile = context.getDatabasePath(dbName);
    } catch (IOException e) {
      ret = false;
      e.printStackTrace();
    }
    try {
      FileUtils.copyInputStreamToFile(iStream, dbFile);
    } catch (IOException e) {
      ret = false;
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * Получение пути к корневой папке публичного внешнего хранилища (папка
   * на одном уровне с Download и т.п.)
   *
   * @return например "/mnt/sdcard"
   */
  public static String getExternalFd() {
    // директория внешнего хранилища для публичных файлов ThPubF
    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    // директория. На конце тут будет "/Download"
    String pathS = path.getAbsolutePath();

    // извлечение всего кроме окончания "/Download"
    int i = pathS.lastIndexOf(File.separator);
    String pathD = pathS.substring(0, i);

    return pathD;
  }

  /**
   * Проверка что внешнее-хранилище ([w92w]) доступно по крайней мере для чтения
   *
   * @return
   */
  public boolean isExternalStorageReadable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state) ||
      Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
  }

}
