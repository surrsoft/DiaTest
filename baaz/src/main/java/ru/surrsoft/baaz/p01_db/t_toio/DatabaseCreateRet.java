package ru.surrsoft.baaz.p01_db.t_toio;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.Database;

/**
 * В поле database будет ссылка на реинкарнацию созданной или уже существующей БД
 */
public class DatabaseCreateRet {
  public Database.EIvny eivny;
  public String comment;
  public Database database;
  /**
   * TRUE означает что БД была либо успешно создана, либо уже существовала
   */
  public boolean bExist;

  public DatabaseCreateRet(Database.EIvny eivny, String comment, Database database, boolean exist) {
    this.eivny = eivny;
    this.comment = comment;
    this.database = database;
    this.bExist = exist;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "DatabaseCreateRet{" +
      "\n" + Ycyf_01.RWRY_INN + ":eivny=" + eivny +
      "\n" + Ycyf_01.RWRY_INN + ":comment='" + comment + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":database=" + database +
      "\n" + Ycyf_01.RWRY_INN + ":bExist=" + bExist +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}
