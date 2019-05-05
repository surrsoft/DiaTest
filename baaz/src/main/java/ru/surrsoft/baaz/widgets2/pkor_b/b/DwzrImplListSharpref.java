package ru.surrsoft.baaz.widgets2.pkor_b.b;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.tclasses.TSharprefCommon_01;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Dwzr;


/**
 * Реализация [dwzr]-интерфейса используемого в [pkor]-виджете. Особенности:
 * 1) использует ArrayList-список (_список) как источник данных,
 * 2) использует Bysa.sharprefCommon ([ppmk]) для хранения этого ArrayList-списка
 * <p>
 * ПОНЯТИЯ:
 * -- _список - ArrayList-список элементов являющихся моделями данных для элементов создаваемого
 * RecyclerView
 * <p>
 * //new//
 * ID: [[gvhf]]
 *
 * @param <E>  - тип элемента списка
 * @param <VH> - тип ViewHolder элемента списка
 */
public class DwzrImplListSharpref<E, VH extends RecyclerView.ViewHolder> implements Dwzr<VH, E> {

  private ArrayList<E> mElems;
  private IViewHolderBind<VH, E> viewHolderBind;
  private IViewHolderCreate<VH> viewHolderCreate;
  private IElemAdder<E> elemAdder;
  private String stKeySharPref;

  //--- constructors

  /**
   * @param stKeySharPref (1) -- ключ, по которому в Bysa.sharprefCommon будет хранится
   *                      сериализованный список
   */
  public DwzrImplListSharpref(String stKeySharPref) {
    U.se(TString_01.isEmpty(stKeySharPref), "");
    this.stKeySharPref = stKeySharPref;
  }

  //---

  /**
   * Режим работы со _списком при создании this. Если режим не указан, то используется режим INITIAL
   */
  public enum Mode {
    /**
     * Обычный режим. Режим по умолчанию
     */
    INITIAL,
    /**
     * В этом режиме содержимое RecyclerView (_список) сбрасывается к дефолтному
     */
    RESET
  }

  //--- builders

  /**
   * Задание _списка элементов (2)
   *
   * @param mode      (1) --
   * @param elems     (2) --
   * @param typeToken (3) -- здесь должен быть Gson-токен-типа E. Например, если E это тип Some, то
   *                  сюда должна быть передан результат такой конструкции "new
   *                  TypeToken&lt;ArrayList&lt;Some>>(){}". Так приходится делать из-за стирания
   *                  типов, т.е. если эту конструкцию прописать внутри с типом E, то корректного
   *                  преобразования Json в объект не будет
   * @return --
   */
  public DwzrImplListSharpref<E, VH> buElems(Mode mode, ArrayList<E> elems, TypeToken<ArrayList<E>> typeToken) {
    U.se(elems == null, "");
    U.se(mode == null, "");
    //---
    switch (mode) {
      case INITIAL:
        this.mElems = TSharprefCommon_01.listInit(stKeySharPref, typeToken, () -> elems);
        break;
      case RESET:
        mElems = elems;
        mtUpdateStore();
        break;
    }
    //---
    return this;
  }

  /**
   * В (1) нужно делать биндинг
   *
   * @param viewHolderBind (1) --
   * @return --
   */
  public DwzrImplListSharpref<E, VH> buViewHolderBind(IViewHolderBind<VH, E> viewHolderBind) {
    U.se(viewHolderBind == null, "");
    this.viewHolderBind = viewHolderBind;
    return this;
  }

  /**
   * В (1) нужно создавать ViewHolder
   *
   * @param viewHolderCreate (1) --
   * @return --
   */
  public DwzrImplListSharpref<E, VH> buViewHolderCreate(IViewHolderCreate<VH> viewHolderCreate) {
    U.se(viewHolderCreate == null, "");
    this.viewHolderCreate = viewHolderCreate;
    return this;
  }

  /**
   * "Дёргалка" (1) получает вызов от [pkor] когда пользователь хочет создать новый элемент
   *
   * @param elemAdder (1) --
   * @return --
   */
  public DwzrImplListSharpref<E, VH> buElemAdder(IElemAdder<E> elemAdder) {
    U.se(elemAdder == null, "");
    this.elemAdder = elemAdder;
    return this;
  }

  @Override
  public E elemGetByIndex(int index) {
    return mElems.get(index);
  }

  //---
  @Override
  public VH viewHolderCreate(Context ctx) {
    return viewHolderCreate.fun(ctx);
  }

  @Override
  public void viewHolderBind(VH wrapedHolder, int index) {
    E elem = mElems.get(index);
    viewHolderBind.fun(wrapedHolder, index, elem);
  }

  @Override
  public void remove(int index) {
    mElems.remove(index);
    mtUpdateStore();
  }

  @Override
  public void add(AppCompatActivity activity) {
    U.se(elemAdder == null, "");
    //---
    E elem = elemAdder.fun();

    //TODO
    /*
    U.se(elem == null, "");
    //---
    mElems.add(elem);
    //---
    mtUpdateStore();
    */
  }

  @Override
  public void swap(int iFirstIndex, int iSecondIndex) {
    TArray_01.swap(mElems, iFirstIndex, iSecondIndex);
    mtUpdateStore();
  }

  @Override
  public long getCount() {
    return this.mElems.size();
  }


  //--- interfaces

  /**
   * Реализующий должен создать ViewHolder типа VH и вернуть его
   *
   * @param <VH>
   */
  public interface IViewHolderCreate<VH extends RecyclerView.ViewHolder> {
    VH fun(Context ctx);
  }

  /**
   * Реализующий должен сделать binding содержимого элемента E во ViewHolder типа VH
   *
   * @param <VH>
   * @param <E>
   */
  public interface IViewHolderBind<VH extends RecyclerView.ViewHolder, E> {
    void fun(VH viewHolder, int iIndex, E elem);
  }


  /**
   *
   */
  public interface IElemAdder<E> {
    E fun();
  }

  //---

  /**
   * Обновление списка в Bysa.sherprefCommon
   */
  private void mtUpdateStore() {
    TSharprefCommon_01.listPut(stKeySharPref, mElems);
  }

}
