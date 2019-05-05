package ru.surrsoft.baaz.p01_db.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.p01_db.Cell;
import ru.surrsoft.baaz.p01_db.Cells;
import ru.surrsoft.baaz.p01_db.Database;
import ru.surrsoft.baaz.p01_db.Databases;
import ru.surrsoft.baaz.p01_db.EFieldType;
import ru.surrsoft.baaz.p01_db.Field;
import ru.surrsoft.baaz.p01_db.Fields;
import ru.surrsoft.baaz.p01_db.Row;
import ru.surrsoft.baaz.p01_db.Rows;
import ru.surrsoft.baaz.p01_db.Table;
import ru.surrsoft.baaz.p01_db.Tables;
import ru.surrsoft.baaz.p01_db.b.DatabaseName;
import ru.surrsoft.baaz.p01_db.b.FieldName;
import ru.surrsoft.baaz.p01_db.b.TableName;
import ru.surrsoft.baaz.p01_db.t_toio.DatabaseCreateRet;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NScrollView;

/**
 * Демо для [drvx]-инкарнации-реляционной-БД
 */
public class ADrvxDemo extends AppCompatActivity {

  private TextView tvDbInfos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //---
    Vdin_01.logStart("activity //190224-075500", this);

    //--- layMain
    NScrollView layScroll = new BuLayScroll_01(this)
      .buPaddings(10)
      .build();

    NLinearLayout layMain = new BuLayLinear_01(this)
      .addTo(layScroll)
      .build();

    //--- кнопка показа информации о всех базах данных приложения

    //кнопка при нажатию на которую информация выводится в tvDbInfos
    new BuilderTV(this)
      .addTo(layMain)
      .text("[Информация обо всех БД приложения]")
      .textColor(Color.BLACK)
      .underline(true)
      .paddings(5)
      .onclick(v -> tvDbInfos.setText(ADrvxDemo.this.mtDatabasesInfo()))
      .create();

    //TV в который выводится информация
    tvDbInfos = new BuilderTV(this)
      .addTo(layMain)
      .textColor(Color.RED)
      .create();

    //---
    setContentView(layScroll);

    //--- кнопка создания БД
    new BuilderTV(this)
      .addTo(layMain)
      .text("[Создать БД]")
      .textColor(Color.BLACK)
      .underline(true)
      .paddings(5)
      .onclick(v -> mtCreateDb())
      .create();

    //---
    Vdin_01.logEnd("", this);
  }

  private void mtDatabaseDelete(String stDbName) {
    Database db = new Database(this, new DatabaseName(stDbName));
    //---
    db.remove();
  }

  private String mtDatabasesInfo() {
    Databases dbs = new Databases(this);
    dbs.pull();
    return Ycyf_01.adapt(dbs.toString());
  }

  /**
   * Демонстрация создания новой БД, а так же демонстрации вывода информации о полученной
   * _реинкарнации БД (вывод в лог с помощью Ycyf_01.adapt(...))
   */
  private void mtCreateDb() {
    //--- навязывание БД
    DatabaseCreateRet ret = new Database(this, new DatabaseName("db38"))
      .buTables(new Tables(this)
        .buAddTable(new Table(new TableName("t1"))
          .buFields(new Fields()
            .buAddField(new Field()
              .buName(new FieldName("f1"))
              .buFieldType(EFieldType.INTEGER)
            )
          )
          .buRows(new Rows()
            .buAddRow(new Row()
              .buAddCells(new Cells()
                .buAddCell(new Cell()
                  .buField(new Field().buName(new FieldName("f1")))
                  .buValue("100")
                ))))))
      .create_B();

    Log.d("tag", "--16-- ret [" + Ycyf_01.adapt(ret.toString()) + "]");

  }

}
