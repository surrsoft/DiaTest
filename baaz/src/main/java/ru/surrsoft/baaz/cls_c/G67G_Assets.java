package ru.surrsoft.baaz.cls_c;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * app/src/main/assets/
 * <p>
 */
public class G67G_Assets {

  /**
   * Получение содержимого файла (1) расположенного в assets
   * <p>
   * ИСТОЧНИК: http://stackoverflow.com/questions/9544737/read-file-from-assets
   *
   * @param pathfile (1) -- например "filename.txt" или "folder/testFile.txt"
   * @return --
   */
  @NonNull
  public static String readFileAssets(String pathfile, Context ctx) {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(ctx.getAssets().open(pathfile)));
      // do reading, usually loop until end of file reading
      String mLine;
      String div = "";
      while ((mLine = reader.readLine()) != null) {
        sb.append(div).append(mLine);
        div = "\n";
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return sb.toString();
  }


}
